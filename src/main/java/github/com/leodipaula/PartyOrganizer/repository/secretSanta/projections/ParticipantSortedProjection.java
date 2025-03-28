package github.com.leodipaula.PartyOrganizer.repository.secretSanta.projections;

import java.util.List;

public interface ParticipantSortedProjection {
    String getName();

    List<GiftProjection> getGifts();
}
