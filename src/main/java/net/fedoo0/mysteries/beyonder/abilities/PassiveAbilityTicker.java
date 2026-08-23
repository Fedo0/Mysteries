package net.fedoo0.mysteries.beyonder.abilities;

import net.fedoo0.mysteries.beyonder.Beyonder;
import net.fedoo0.mysteries.beyonder.BeyonderData;
import net.minecraft.server.level.ServerPlayer;

public class PassiveAbilityTicker {
    public static void tick(Iterable<ServerPlayer> onlinePlayers, Beyonder beyonder) {
        for (ServerPlayer player : onlinePlayers) {
            BeyonderData beyonderData = beyonder.getBeyonder(player.getUUID());
            if (beyonderData != null) {
                for (PassiveAbility passive : AbilityRegistry.getAllPassives()) {
                    if (passive.appliesTo(beyonderData)) {
                        passive.tick(new AbilityContext(player, beyonderData));
                    }
                }
            }
        }
    }

}
