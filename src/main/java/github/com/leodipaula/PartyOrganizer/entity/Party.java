package github.com.leodipaula.PartyOrganizer.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Nonnull
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;

    @Nonnull
    private String local;

    @Nullable
    private String description;

    @Nullable
    private String theme;

    @Nullable
    private String dressCode;

    @Nullable
    private Double price;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Nonnull
    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Participant> participants;
}
