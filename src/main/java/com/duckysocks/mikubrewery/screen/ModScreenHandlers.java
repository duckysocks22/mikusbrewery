package com.duckysocks.mikubrewery.screen;

import com.duckysocks.mikubrewery.MikusBrewery;
import com.duckysocks.mikubrewery.screen.custom.station.MillStationScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<MillStationScreenHandler> MILLSTATION_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MikusBrewery.MOD_ID, "millstation_screen_handler"),
                    new ExtendedScreenHandlerType<>(MillStationScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        MikusBrewery.LOGGER.info("Registering Screen Handlers for " + MikusBrewery.MOD_ID);
    }
}
