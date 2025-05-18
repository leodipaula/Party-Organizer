package github.com.leodipaula.PartyOrganizer.repository;

import github.com.leodipaula.PartyOrganizer.projections.ParticipantSortedProjection;
import reactor.core.publisher.Mono;

public interface SecretSantaRepository {
    Mono<ParticipantSortedProjection> sortParticipantByPartyId(Long partyId);
}

