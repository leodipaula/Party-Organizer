package github.com.leodipaula.partyorganizer.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("tb_parties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Party {
    @Id
    private Long id;

    private String name;
    private LocalDateTime date;
    private String local;
    private String description;
    private String theme;
    private String dressCode;
    private BigDecimal price;

    @Column("user_id")
    private Long userId;
}
