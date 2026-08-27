package net.fedoo0.mysteries.beyonder.existence;

import net.fedoo0.mysteries.beyonder.ritual.RitualContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Existence {
    private final String name;
    private final UUID id;
    private final int attentionRequired;
    public Existence(String name, int attentionRequired, UUID id) {
        this.name = name;
        this.id = id;
        this.attentionRequired = attentionRequired;
    }

    public Existence(String name, int attentionRequired, int attentionRequired1) {
        this.name = name;
        this.attentionRequired = attentionRequired1;
        this.id = UUID.randomUUID();
    }

    public void respond(RitualContext context, int attention) {
        if (attention >= attentionRequired) {
            // response logic do ts later im so fucking sleepy
        }
    }

}
