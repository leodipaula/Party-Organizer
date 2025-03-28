package github.com.leodipaula.PartyOrganizer.entity;

import java.util.List;

import org.hibernate.annotations.ColumnTransformer;

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
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Nonnull
    private String name;

    @Nonnull
    @ColumnTransformer(read = "pgp_sym_decrypt(telefone::bytea, 'your_secret_key')", write = "pgp_sym_encrypt(?, 'your_secret_key')")
    private String telefone;

    @Nonnull
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean owner;

    @Column(nullable = true, columnDefinition = "boolean default false")
    private boolean sorted;

    @Column(nullable = true, columnDefinition = "string default null")
    private String nameOfSortedParticipant;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @Column(nullable = true, columnDefinition = "boolean default false")
    private boolean confirmed;

    @Nullable
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participant", fetch = FetchType.LAZY)
    private List<Food> foods;

    @Nullable
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participant", fetch = FetchType.LAZY)
    private List<Drink> drinks;

    @Nullable
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participant", fetch = FetchType.LAZY)
    private List<Gift> gifts;

    @Column(nullable = true, columnDefinition = "boolean default false")
    private boolean responsibleForAnotherParticipant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participant", fetch = FetchType.LAZY)
    private List<ParticipantWithoutWpp> responsibleParticipants;

}
