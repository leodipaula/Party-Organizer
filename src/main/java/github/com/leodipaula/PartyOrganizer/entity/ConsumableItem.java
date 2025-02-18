package github.com.leodipaula.PartyOrganizer.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ConsumableItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consumable_item_seq")
    @SequenceGenerator(name = "consumable_item_seq", sequenceName = "consumable_item_seq", allocationSize = 1)
    private long id;

    @Nonnull
    private String name;

    @Nonnull
    private Double price;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = true)
    private Participant participant;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "participantWithoutWpp_id", nullable = true)
    private ParticipantWithoutWpp participantWithoutWpp;
}
