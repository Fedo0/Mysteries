package net.fedoo0.mysteries.item;

import net.fedoo0.mysteries.MysteriesMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MysteriesMod.MODID);

    public  static final DeferredItem<Item> DAGGER = ITEMS.register("dagger",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
