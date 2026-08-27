package net.fedoo0.mysteries.beyonder.ritual;

public abstract class Ritual {
    private final RitualType ritualType;
    private final int gainedAttention;

    public Ritual(RitualType ritualType, int gainedAttention) {
        this.ritualType = ritualType;
        this.gainedAttention = gainedAttention;
    }

    public abstract boolean requirementsMet(RitualContext context);

    public abstract void execute(RitualContext ritualContext);

    public void performRitual(RitualContext context) {
        if (!requirementsMet(context)) {
            // do some stuff for failing it or not I dunno tbh yet
        }
        else {
            execute(context);
        }
    }

}
