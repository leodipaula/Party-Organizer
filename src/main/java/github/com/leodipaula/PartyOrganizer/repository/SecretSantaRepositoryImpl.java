package github.com.leodipaula.PartyOrganizer.repository;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import github.com.leodipaula.PartyOrganizer.projections.ParticipantSortedProjection;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class SecretSantaRepositoryImpl implements SecretSantaRepository {

    private final DatabaseClient client;

    @Override
    public Mono<ParticipantSortedProjection> sortParticipantByPartyId(Long partyId) {
        String query = """
                WITH selected AS (
                    SELECT id
                    FROM tb_participants
                    WHERE party_id = :partyId AND sorteado = false
                    ORDER BY RANDOM()
                    LIMIT 1
                )
                UPDATE tb_participants
                SET sorteado = true
                WHERE id IN (SELECT id FROM selected)
                RETURNING name, gifts
                """;

        return client.sql(query).bind("partyId", partyId)
                .map(row -> new ParticipantSortedProjection(row.get("name", String.class),
                        row.get("gifts", String.class)))
                .one();
    }
}

