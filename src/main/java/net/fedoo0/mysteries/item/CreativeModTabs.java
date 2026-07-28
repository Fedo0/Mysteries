package net.fedoo0.mysteries.item;

import net.fedoo0.mysteries.MysteriesMod;
import net.fedoo0.mysteries.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MysteriesMod.MOD_ID);

    public static final Supplier<CreativeModeTab> MYSTERIES_TAB = CREATIVE_MODE_TAB.register("mysteries_tab",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.DAGGER.get()))
    //                .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MysteriesMod.MODID, "previous tab name"))
    //                add this line to all following creative mod tabs
                    .title(Component.translatable("creativetab.mysteriesmod.mysteries_items"))
                    .displayItems(
                            (ItemDisplayParameters, output) -> {
                                output.accept(ModItems.DAGGER);
                                output.accept(ModBlocks.MAGIC_CAULDRON);
                            }
                    ).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
