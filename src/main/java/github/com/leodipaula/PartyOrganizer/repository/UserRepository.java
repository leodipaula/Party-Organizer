package github.com.leodipaula.PartyOrganizer.repository;


import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import github.com.leodipaula.PartyOrganizer.entity.User;
import github.com.leodipaula.PartyOrganizer.projections.ParticipantProjection;
import github.com.leodipaula.PartyOrganizer.projections.PartyProjection;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    @Query("""
                SELECT p.name AS name, p.confirmed AS confirmed
                FROM tb_participants p
                WHERE p.user_id = :userId AND p.party_id = :partyId
                UNION
                SELECT pw.name AS name, pw.confirmed AS confirmed
                FROM participant_without_wpp pw
                WHERE pw.user_id = :userId
            """)
    Flux<ParticipantProjection> findAllParticipantsByUserId(Long userId, Long partyId);

    @Query("""
                SELECT p.name AS name, p.confirmed AS confirmed
                FROM tb_participants p
                WHERE p.user_id = :userId AND p.confirmed = TRUE AND p.party_id = :partyId
                UNION
                SELECT pw.name AS name, pw.confirmed AS confirmed
                FROM tb_participants_without_wpp pw
                WHERE pw.user_id = :userId AND pw.confirmed = TRUE
            """)
    Flux<ParticipantProjection> findConfirmedParticipantsByUserId(Long userId, Long partyId);

    @Query("""
                SELECT p.name AS name, p.date AS date, p.local AS local,
                       p.description AS description, p.theme AS theme,
                       p.dress_code AS dressCode, p.price AS price
                FROM tb_parties p
                WHERE p.user_id = :userId
            """)
    Flux<PartyProjection> findPartiesByUserId(Long userId);
}

