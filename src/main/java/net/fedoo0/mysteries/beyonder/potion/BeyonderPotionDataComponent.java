package net.fedoo0.mysteries.beyonder.potion;

import net.fedoo0.mysteries.MysteriesMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BeyonderPotionDataComponent {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MysteriesMod.MOD_ID);


    public static final Supplier<DataComponentType<BeyonderPotionData>> BEYONDER_POTION_DATA =
            DATA_COMPONENTS.registerComponentType(
                    "beyonder_potion_data",
                    builder -> builder
                            .persistent(BeyonderPotionData.CODEC)
            );
}
