package net.fedoo0.mysteries.beyonder.pathway.main.death.abilities;

import net.fedoo0.mysteries.beyonder.abilities.AbilityRegistry;
import net.fedoo0.mysteries.beyonder.abilities.PassiveAbility;
import net.minecraft.resources.ResourceLocation;

public class DeathAbilities {
    public static final PassiveAbility PHYSICAL_ENHANCEMENT = new PhysicalEnhancement();

    public static void register() {
        AbilityRegistry.registerPassive(ResourceLocation.fromNamespaceAndPath("mysteriesmod", "physical_enhancement"), PHYSICAL_ENHANCEMENT);
    }
}
