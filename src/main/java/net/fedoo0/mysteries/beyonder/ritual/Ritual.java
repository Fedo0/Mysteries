package net.fedoo0.mysteries.beyonder.ritual;

public abstract class Ritual {
    private final RitualType ritualType;
    private final int gainedAttention;

    public Ritual(RitualType ritualType, int gainedAttention) {
        this.ritualType = ritualType;
        this.gainedAttention = gainedAttention;
    }

    public  boolean requirementsMet(RitualContext context) {
        // заглушка
        return true;
    }



}
