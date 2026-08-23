package net.fedoo0.mysteries;

import net.fedoo0.mysteries.beyonder.Beyonder;
import net.fedoo0.mysteries.beyonder.abilities.PassiveAbilityTicker;
import net.fedoo0.mysteries.beyonder.pathways.main.fool.Ingredients;
import net.fedoo0.mysteries.block.ModBlocks;
import net.fedoo0.mysteries.item.CreativeModTabs;
import net.fedoo0.mysteries.item.ModItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;

@Mod(MysteriesMod.MOD_ID)
public class MysteriesMod {
    public static final String MOD_ID = "mysteriesmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    // constructor
    public MysteriesMod(IEventBus modEventBus, ModContainer modContainer) {

        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        Ingredients.register(modEventBus);

        CreativeModTabs.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        DebugCommands.register(event.getDispatcher());
    }

    private int passiveTickCounter = 0;

    @SubscribeEvent
    public void onServerTick(ServerTickEvent.Post event) {
        if (++passiveTickCounter < 20) return;
        passiveTickCounter = 0;

        Beyonder beyonder = Beyonder.get(event.getServer().overworld());
        PassiveAbilityTicker.tick(event.getServer().getPlayerList().getPlayers(),beyonder);
    }

}
