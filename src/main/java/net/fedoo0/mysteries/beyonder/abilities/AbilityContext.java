package net.fedoo0.mysteries.beyonder.abilities;

import net.fedoo0.mysteries.beyonder.BeyonderData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;
import javax.swing.text.html.parser.Entity;

public record AbilityContext(ServerPlayer caster, BeyonderData data, @Nullable Entity target, @Nullable BlockPos targetPos) {
    public AbilityContext(ServerPlayer caster, BeyonderData data) {
        this(caster, data, null, null);
    }
    public AbilityContext ofTarget(ServerPlayer caster, BeyonderData data, Entity target) {
        return new AbilityContext(caster,data,target,null);
    }
    public AbilityContext ofPosition(ServerPlayer caster, BeyonderData data, BlockPos targetPos) {
        return new AbilityContext(caster, data, null, targetPos);
    }
}
