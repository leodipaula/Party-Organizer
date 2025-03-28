package github.com.leodipaula.PartyOrganizer.repository.partyOrganizer.participant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import github.com.leodipaula.PartyOrganizer.entity.Participant;
import github.com.leodipaula.PartyOrganizer.repository.partyOrganizer.projections.DrinkProjection;
import github.com.leodipaula.PartyOrganizer.repository.partyOrganizer.projections.FoodProjection;
import github.com.leodipaula.PartyOrganizer.repository.partyOrganizer.projections.ParticipantProjection;
import github.com.leodipaula.PartyOrganizer.repository.partyOrganizer.projections.PartyProjection;

public interface participantRepository extends JpaRepository<Participant, Long> {
  @Query(value = """
      SELECT p.name AS name, p.confirmed AS confirmed
      FROM Participant p
      WHERE p.party_id = :partyId
        AND EXISTS (SELECT 1 FROM Participant p_sub WHERE p_sub.id = :participantId AND p_sub.party_id = p.party_id)

      UNION ALL

      SELECT pw.name AS name, pw.confirmed AS confirmed
      FROM ParticipantWithoutWpp pw
      WHERE pw.party_id = :partyId
        AND EXISTS (SELECT 1 FROM ParticipantWithoutWpp pw_sub WHERE pw_sub.party_id = pw.party_id AND pw_sub.id = :participantId)
      """, nativeQuery = true)
  List<ParticipantProjection> findParticipantsByParticipantIdAndParty(
      @Param("participantId") long participantId,
      @Param("partyId") long partyId);

  @Query(value = """
      SELECT p.name AS name, p.date AS date, p.local AS local, p.description AS description, p.theme AS theme, p.dressCode AS dressCode, p.price AS price
      FROM Party p
      WHERE p.participants.id = :participantId
      """)
  List<PartyProjection> findPartiesByParticipantId(@Param("participantId") long participantId);

  @Query(value = """
      SELECT d.name AS name, d.price AS price
      FROM Drink d
      WHERE d.party.id = :partyId
        AND d.participant.id = :participantId

      UNION ALL

      SELECT d.name AS name, d.price AS price
      FROM Drink d
      WHERE d.party.id = :partyId
        AND d.participantWithoutWpp.id = :participantId
      """)
  List<DrinkProjection> findDrinksByParticipantId(
      @Param("participantId") long participantId,
      @Param("partyId") long partyId);

  @Query(value = """
      SELECT f.name AS name, f.price AS price
      FROM Food f
      WHERE f.party.id = :partyId
        AND f.participant.id = :participantId

      UNION ALL

      SELECT f.name AS name, f.price AS price
      FROM Food f
      WHERE f.party.id = :partyId
        AND f.participantWithoutWpp.id = :participantId
      """)
  List<FoodProjection> findFoodsByParticipantId(
      @Param("participantId") long participantId,
      @Param("partyId") long partyId);

  @Query(value = """
      SELECT d.name AS name, d.price AS price
      FROM Drink d
      WHERE d.party.id = :partyId
      """)
  List<DrinkProjection> findAllDrinksOfPartyByPartyId(@Param("partyId") long partyId);

  @Query(value = """
      SELECT f.name AS name, f.price AS price
      FROM Food f
      WHERE f.party.id = :partyId
      """)
  List<FoodProjection> findAllFoodsOfPartyByPartyId(@Param("partyId") long partyId);
}
