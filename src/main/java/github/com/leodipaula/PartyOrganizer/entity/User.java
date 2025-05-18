package github.com.leodipaula.PartyOrganizer.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("tb_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Long id;

    private String name;
}
