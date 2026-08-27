package net.fedoo0.mysteries.beyonder.abilities;

import net.fedoo0.mysteries.beyonder.BeyonderData;
import net.fedoo0.mysteries.beyonder.pathway.Pathway;

public abstract class Ability {
    private final Pathway pathway;
    private final int requiredSequence;
    private final int spiritualityCost;
    private final int tickCooldown;
    public Ability(Pathway pathway, int requiredSequence, int spiritualityCost, int tickCooldown) {
        this.pathway = pathway;
        this.requiredSequence = requiredSequence;
        this.spiritualityCost = spiritualityCost;
        this.tickCooldown = tickCooldown;
    }

    public boolean canCast(BeyonderData beyonderData) {
        return tickCooldown == 0 && beyonderData.getPathway().equals(pathway) && beyonderData.getSequence() <= requiredSequence && beyonderData.getSpirituality() >= spiritualityCost;
        // might do a check for characteristics instead, still not sure
    }

    public void cast(AbilityContext context) {
        context.data().modifySpirituality(-spiritualityCost);
        execute(context);
    }

    protected abstract void execute(AbilityContext context);
}
