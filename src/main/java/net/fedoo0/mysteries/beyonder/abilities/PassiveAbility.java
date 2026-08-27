package net.fedoo0.mysteries.beyonder.abilities;

import net.fedoo0.mysteries.beyonder.BeyonderData;
import net.fedoo0.mysteries.beyonder.pathway.Pathway;


public abstract class PassiveAbility {
    private final Pathway pathway;
    private final int requiredSequence;
    public PassiveAbility(Pathway pathway, int requiredSequence) {
        this.pathway = pathway;
        this.requiredSequence = requiredSequence;
    }
    public boolean appliesTo(BeyonderData data) {
        return data.getPathway().equals(pathway) && data.getSequence() <= requiredSequence;
    }

    protected abstract void onActivate(AbilityContext context);

    protected abstract void onDeactivate(AbilityContext context);

    public abstract void tick(AbilityContext context);
}
