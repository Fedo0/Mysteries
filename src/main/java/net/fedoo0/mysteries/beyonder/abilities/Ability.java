package net.fedoo0.mysteries.beyonder.abilities;

import net.fedoo0.mysteries.beyonder.BeyonderData;

public abstract class Ability {
    private final String pathway;
    private final int requiredSequence;
    private final int spiritualityCost;
    private final int tickCooldown;
    public Ability(String pathway, int requiredSequence, int spiritualityCost, int tickCooldown) {
        this.pathway = pathway;
        this.requiredSequence = requiredSequence;
        this.spiritualityCost = spiritualityCost;
        this.tickCooldown = tickCooldown;
    }

    public boolean canCast(BeyonderData beyonderData) {
        return beyonderData.getPathway().equals(pathway) && beyonderData.getSequence() <= requiredSequence && beyonderData.getSpirituality() >= spiritualityCost;
    }

    public void cast(AbilityContext context) {
        context.data().modifySpirituality(-spiritualityCost);
        execute(context);
    }

    protected abstract void execute(AbilityContext context);
}
