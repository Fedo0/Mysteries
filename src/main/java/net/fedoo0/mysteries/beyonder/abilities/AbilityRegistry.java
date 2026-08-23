package net.fedoo0.mysteries.beyonder.abilities;

import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class AbilityRegistry {
    private static final Map<ResourceLocation, Ability> ABILITIES = new LinkedHashMap<>();
    private static final Map<ResourceLocation, PassiveAbility> PASSIVES = new LinkedHashMap<>();

    public static void register(ResourceLocation id, Ability ability) {
        if(!ABILITIES.containsKey(id)) {
            ABILITIES.put(id, ability);
        }
    }
    public static Ability get(ResourceLocation id) {
        return ABILITIES.get(id);
    }



    public static void registerPassive(ResourceLocation id, PassiveAbility passiveAbility) {
        if(!PASSIVES.containsKey(id)) {
            PASSIVES.put(id, passiveAbility);
        }
    }
    public static PassiveAbility getPassive(ResourceLocation id) {
        return PASSIVES.get(id);
    }

    public static Collection<PassiveAbility> getAllPassives() {
        return PASSIVES.values();
    }


}
