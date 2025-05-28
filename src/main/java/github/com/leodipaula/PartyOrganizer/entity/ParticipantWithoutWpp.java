package github.com.leodipaula.partyorganizer.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("tb_participant_whithout_wpp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantWithoutWpp {
    @Id
    private Long id;

    private String name;
    private boolean confirmed;
    private boolean sorted;

    @Column("participant_id")
    private Long participantId;
}
