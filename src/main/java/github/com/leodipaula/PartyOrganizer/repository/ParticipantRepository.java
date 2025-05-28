package github.com.leodipaula.partyorganizer.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import github.com.leodipaula.partyorganizer.entity.Participant;
import github.com.leodipaula.partyorganizer.projections.ConsumableItemProjection;
import github.com.leodipaula.partyorganizer.projections.ParticipantProjection;
import github.com.leodipaula.partyorganizer.projections.PartyProjection;
import reactor.core.publisher.Flux;

@Repository
public interface ParticipantRepository extends R2dbcRepository<Participant, Long> {

  @Query("""
          SELECT name, confirmed
          FROM tb_participants
          WHERE party_id = :partyId
            AND EXISTS (
              SELECT 1 FROM tb_participants p_sub WHERE p_sub.id = :participantId AND p_sub.party_id = tb_participants.party_id
            )
          UNION ALL
          SELECT name, confirmed
          FROM tb_participants_without_wpp
          WHERE party_id = :partyId
            AND EXISTS (
              SELECT 1 FROM tb_participants_without_wpp pw_sub WHERE pw_sub.id = :participantId AND pw_sub.party_id = tb_participants_without_wpp.party_id
            )
      """)
  Flux<ParticipantProjection> findParticipantsByParticipantIdAndParty(Long participantId,
      Long partyId);

  @Query("""
          SELECT p.name, p.date, p.local, p.description, p.theme, p.dress_code AS dressCode, p.price
          FROM tb_parties p
          JOIN tb_participants pa ON pa.party_id = p.id
          WHERE pa.id = :participantId
      """)
  Flux<PartyProjection> findPartiesByParticipantId(Long participantId);

  @Query("""
          SELECT ci.name, ci.price, ci.quantity
          FROM tb_consumable_items ci
          JOIN tb_participants p ON ci.participant_id = p.id
          WHERE p.party_id = :partyId AND p.id = :participantId
      """)
  Flux<ConsumableItemProjection> findItemsByParticipantId(Long participantId, Long partyId);

  @Query("""
          SELECT ci.name, ci.price, ci.quantity
          FROM tb_consumable_items ci
          JOIN tb_participants_without_wpp pw ON ci.participant_without_wpp_id = pw.id
          WHERE pw.party_id = :partyId AND pw.id = :participantId
      """)
  Flux<ConsumableItemProjection> findItemsWithoutWppByParticipantId(Long participantId,
      Long partyId);

  @Query("""
          SELECT ci.name, ci.price, ci.quantity
          FROM tb_consumable_items ci
          JOIN tb_participants p ON ci.participant_id = p.id
          WHERE p.party_id = :partyId

          UNION ALL

          SELECT ci.name, ci.price, ci.quantity
          FROM tb_consumable_items ci
          JOIN tb_participants_without_wpp pw ON ci.participant_without_wpp_id = pw.id
          WHERE pw.party_id = :partyId
      """)
  Flux<ConsumableItemProjection> findAllItemsOfPartyByPartyId(Long partyId);
}
