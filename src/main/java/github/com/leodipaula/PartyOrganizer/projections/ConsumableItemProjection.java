package github.com.leodipaula.partyorganizer.projections;

import java.math.BigDecimal;

public record ConsumableItemProjection(String name, BigDecimal price, Integer quantity) {

}
