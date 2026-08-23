package net.fedoo0.mysteries.beyonder.abilities;

import net.fedoo0.mysteries.beyonder.BeyonderData;


public abstract class PassiveAbility {
    private final String pathway;
    private final int requiredSequence;
    public PassiveAbility(String pathway, int requiredSequence) {
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
