package github.com.leodipaula.partyorganizer.projections;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PartyProjection(String name, LocalDateTime date, String local, String description,
        String theme, String dressCode, BigDecimal price) {
}
