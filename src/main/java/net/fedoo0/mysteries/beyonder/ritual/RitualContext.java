package net.fedoo0.mysteries.beyonder.ritual;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;

public record RitualContext(ServerPlayer player, UUID existenceID) {
    public ServerLevel level() {
        return (ServerLevel)player.level();
    }

}
