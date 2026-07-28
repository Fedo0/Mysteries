package net.fedoo0.mysteries.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.effects.PlaySoundEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SpongeBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Dagger extends Item {

    public static final Map<UUID, List<BlockPos>> SpiritWallBounds = new ConcurrentHashMap<>();

    public Dagger(Properties properties) {
        super(properties);
    }



    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        if (!level.isClientSide & (player != null)) {
            if (SpiritWallBounds.get(player.getUUID()) == null) {
                List<BlockPos> SpiritWallCorner = new ArrayList<>();
                SpiritWallCorner.add(pos);
                SpiritWallBounds.put(player.getUUID(), SpiritWallCorner);
                level.playLocalSound(player, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1F, 1F);
            }
            else if (SpiritWallBounds.get(player.getUUID()).size() >= 8) {
                player.sendSystemMessage(Component.literal("You already have a spirit wall set up"));
            }
            else {
                List<BlockPos> SpiritWallCorner = SpiritWallBounds.get(player.getUUID());
                if (!SpiritWallCorner.contains(pos)) {
                    SpiritWallCorner.add(pos);
                    SpiritWallBounds.put(player.getUUID(),SpiritWallCorner);

                }
            }

        }


        return super.useOn(context);
    }
}

