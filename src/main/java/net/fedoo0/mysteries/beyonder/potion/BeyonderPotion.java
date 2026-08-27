package net.fedoo0.mysteries.beyonder.potion;

import net.fedoo0.mysteries.beyonder.Beyonder;
import net.fedoo0.mysteries.beyonder.pathway.Pathway;
import net.fedoo0.mysteries.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BeyonderPotion extends Item {


    public BeyonderPotion(Properties properties) {
        super(properties);
    }

    public static ItemStack createBeyonderPotion(Pathway pathway, int sequence) {
        ItemStack stack = new ItemStack(ModItems.BEYONDER_POTION.get());

        stack.set(BeyonderPotionDataComponent.BEYONDER_POTION_DATA.get(),
                new BeyonderPotionData(pathway, sequence)
        );

        return stack;

    }

    public static BeyonderPotionData getBeyonderPotionData(ItemStack stack) {
        return stack.get(BeyonderPotionDataComponent.BEYONDER_POTION_DATA.get());
    }

    @Override
    public Component getName(ItemStack stack) {

        BeyonderPotionData data = getBeyonderPotionData(stack);

        if (data == null) {
            return Component.literal("Unknown Beyonder Potion");
        }

        return Component.literal(
                data.pathway()
                        + " Sequence "
                        + data.sequence()
                        + " Potion"
        );
    }


    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide && livingEntity instanceof ServerPlayer player) {
            BeyonderPotionData data = getBeyonderPotionData(stack);
            Beyonder.get(livingEntity.getServer().overworld()).registerBeyonder(
                    livingEntity.getUUID(), data.pathway(), data.sequence(), livingEntity.getServer().overworld()
            );
        }
        return ItemStack.EMPTY;
    }



}
