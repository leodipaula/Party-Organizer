package github.com.leodipaula.partyorganizer.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("tb_participant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @Id
    private Long id;

    private String name;
    private String telefone;
    private boolean owner;
    private boolean sorted;
    private boolean confirmed;
    private boolean responsibleForAnotherParticipant;

    @Column("name_sorted_participant")
    private String nameOfSortedParticipant;

    @Column("usar_id")
    private Long userId;

    @Column("party_id")
    private Long partyId;
}
