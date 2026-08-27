package net.fedoo0.mysteries.beyonder.characteristic;

import net.fedoo0.mysteries.beyonder.pathway.Pathway;

import javax.annotation.Nullable;
import java.util.UUID;

public class Characteristic {
    private final  UUID id;
    private final Pathway pathway;
    private final int sequence;
    @Nullable private UUID owner;
    public Characteristic( @Nullable UUID owner, Pathway pathway, int sequence) {
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.pathway = pathway;
        this.sequence = sequence;
    }

    public Characteristic(UUID id, @Nullable UUID owner, Pathway pathway, int sequence) {
        this.id = id;
        this.owner = owner;
        this.pathway = pathway;
        this.sequence = sequence;
    }

    public void setOwner(@Nullable UUID uuid) {this.owner = uuid;}

    @Nullable
    public UUID getOwner() {return owner;}

    public int getSequence() {return sequence;}

    public Pathway getPathway() {return pathway;}

    public UUID getId() {return id;}

}
