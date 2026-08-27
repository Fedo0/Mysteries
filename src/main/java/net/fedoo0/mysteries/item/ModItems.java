package net.fedoo0.mysteries.item;

import net.fedoo0.mysteries.MysteriesMod;
import net.fedoo0.mysteries.beyonder.potion.BeyonderPotion;
import net.fedoo0.mysteries.item.custom.Dagger;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MysteriesMod.MOD_ID);

    public  static final DeferredItem<Item> DAGGER = ITEMS.register("dagger",
            () -> new Dagger(
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(150)
                            .attributes(SwordItem.createAttributes(Tiers.IRON, 3.0f, -2.0f))
            ));

    public static final DeferredItem<Item> BEYONDER_POTION = ITEMS.register("beyonder_potion",
            () -> new BeyonderPotion(
                    new Item.Properties()
                            .component(
                            DataComponents.FOOD, new FoodProperties.Builder()
                            .nutrition(0)
                            .saturationModifier(0.0F)
                            .alwaysEdible()
                            .build()
                    )
            )
            );




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
