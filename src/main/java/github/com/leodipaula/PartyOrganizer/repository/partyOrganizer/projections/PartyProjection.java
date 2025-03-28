package github.com.leodipaula.PartyOrganizer.repository.partyOrganizer.projections;

import java.time.LocalDateTime;

public interface PartyProjection {
    String name();

    LocalDateTime date();

    String local();

    String description();

    String theme();

    String dressCode();

    Double price();
}
