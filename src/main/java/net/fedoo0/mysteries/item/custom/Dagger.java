package net.fedoo0.mysteries.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class Dagger extends Item {

    public static final Map<UUID, List<BlockPos>> SpiritWallBounds = new ConcurrentHashMap<>();

    public Dagger(Properties properties) {
        super(properties);
    }




    }


