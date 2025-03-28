package github.com.leodipaula.PartyOrganizer.repository.secretSanta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import github.com.leodipaula.PartyOrganizer.entity.Party;
import github.com.leodipaula.PartyOrganizer.repository.secretSanta.projections.ParticipantSortedProjection;
import jakarta.transaction.Transactional;

public interface secretSantaRepository extends JpaRepository<Party, Long> {
    @Transactional
    @Modifying
    @Query(value = """
            WITH selected AS (
                SELECT p.id
                FROM Participant p
                WHERE p.party_id = :partyId AND p.sorteado = false
                ORDER BY RANDOM()
                LIMIT 1
            )
            UPDATE Participant p
            SET p.sorteado = true
            WHERE p.id IN (SELECT id FROM selected)
            RETURNING p.name, p.gifts
            """, nativeQuery = true)
    List<ParticipantSortedProjection> sortParticipantByPartyId(@Param("partyId") long partyId);

}
