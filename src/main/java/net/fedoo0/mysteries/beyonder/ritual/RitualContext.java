package net.fedoo0.mysteries.beyonder.ritual;

import net.fedoo0.mysteries.beyonder.existence.Existence;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;

public record RitualContext(ServerPlayer player, Existence existence) {
    public ServerLevel level() {
        return (ServerLevel)player.level();
    }

}
