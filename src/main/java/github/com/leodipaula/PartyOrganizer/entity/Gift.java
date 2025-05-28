package github.com.leodipaula.partyorganizer.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("tb_gifts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gift {
    @Id
    private Long id;

    private String name;
    private Double price;

    @Column("participant_id")
    private Long participantId;

    @Column("participant_without_wpp_id")
    private Long participantWithoutWppId;
}
