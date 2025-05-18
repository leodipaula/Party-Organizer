package github.com.leodipaula.PartyOrganizer.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("tb_consumable_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ConsumableItem {
    @Id
    private Long id;

    private String name;
    private Double price;
    private Integer quantity;

    @Column("type")
    private ConsumableType type;

    @Column("participant_id")
    private Long participantId;

    @Column("participant_without_wpp_id")
    private Long participantWithoutWppId;
}
