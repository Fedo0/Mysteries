package net.fedoo0.mysteries.beyonder.characteristic;

import javax.annotation.Nullable;
import java.util.UUID;

public class Characteristic {
    private final  UUID id;
    private final String pathway;
    private final int sequence;
    @Nullable private UUID owner;
    public Characteristic( @Nullable UUID owner, String pathway, int sequence) {
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.pathway = pathway;
        this.sequence = sequence;
    }

    public Characteristic(UUID id, @Nullable UUID owner, String pathway, int sequence) {
        this.id = id;
        this.owner = owner;
        this.pathway = pathway;
        this.sequence = sequence;
    }

    public void setOwner(@Nullable UUID uuid) {this.owner = uuid;}

    @Nullable
    public UUID getOwner() {return owner;}

    public int getSequence() {return sequence;}

    public String getPathway() {return pathway;}

    public UUID getId() {return id;}

}
