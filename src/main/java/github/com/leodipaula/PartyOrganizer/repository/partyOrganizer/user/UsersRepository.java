package github.com.leodipaula.PartyOrganizer.repository.partyOrganizer.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import github.com.leodipaula.PartyOrganizer.entity.Users;
import github.com.leodipaula.PartyOrganizer.repository.partyOrganizer.projections.ParticipantProjection;
import github.com.leodipaula.PartyOrganizer.repository.partyOrganizer.projections.PartyProjection;

public interface UsersRepository extends JpaRepository<Users, Long> {
        @Query(value = """
                        SELECT p.name AS name, p.confirmed AS confirmed
                        FROM Participant p
                        WHERE p.user.id = :userId AND p.party.id = :partyId
                        UNION
                        SELECT pw.name AS name, pw.confirmed AS confirmed
                        FROM ParticipantWithoutWpp pw
                        WHERE pw.user.id = :userId
                        """, nativeQuery = true)
        List<ParticipantProjection> findAllParticipantsByUserId(@Param("userId") Long userId,
                        @Param("partyId") Long partyId);

        @Query(value = """
                        SELECT p.name AS name, p.confirmed AS confirmed
                        FROM Participant p
                        WHERE p.user.id = :userId AND p.confirmed = true AND p.party.id = :partyId
                        UNION
                        SELECT pw.name AS name, pw.confirmed AS confirmed
                        FROM ParticipantWithoutWpp pw
                        WHERE pw.user.id = :userId AND pw.confirmed = true
                        """, nativeQuery = true)
        List<ParticipantProjection> findConfirmedParticipantsByUserId(@Param("userId") Long userId,
                        @Param("partyId") Long partyId);

        @Query("""
                        SELECT p.name AS name, p.date AS date, p.local AS local, p.description AS description, p.theme AS theme, p.dressCode AS dressCode, p.price AS price
                        FROM Party p
                        WHERE p.user.id = :userId
                        """)
        List<PartyProjection> findPartiesByUserId(@Param("userId") Long userId);
}
