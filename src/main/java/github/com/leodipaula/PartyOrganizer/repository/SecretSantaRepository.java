package github.com.leodipaula.partyorganizer.repository;

import github.com.leodipaula.partyorganizer.projections.ParticipantSortedProjection;
import reactor.core.publisher.Mono;

public interface SecretSantaRepository {
    Mono<ParticipantSortedProjection> sortParticipantByPartyId(Long partyId);
}

