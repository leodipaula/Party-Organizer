package github.com.leodipaula.PartyOrganizer.entity;

import java.util.List;

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
public class ParticipantWithoutWpp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Nonnull
    private String name;

    @Column(nullable = true, columnDefinition = "boolean default false")
    private boolean confirmed;

    @Column(nullable = true, columnDefinition = "boolean default false")
    private boolean sorted;

    @Nullable
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participantWithoutWpp", fetch = FetchType.LAZY)
    private List<Food> foods;

    @Nullable
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participantWithoutWpp", fetch = FetchType.LAZY)
    private List<Drink> drinks;

    @Nullable
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participantWithoutWpp", fetch = FetchType.LAZY)
    private List<Gift> gifts;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;
}
