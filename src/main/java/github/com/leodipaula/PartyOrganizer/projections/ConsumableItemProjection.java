package github.com.leodipaula.PartyOrganizer.projections;

import java.math.BigDecimal;

public record ConsumableItemProjection(String name, BigDecimal price, Integer quantity) {

}
