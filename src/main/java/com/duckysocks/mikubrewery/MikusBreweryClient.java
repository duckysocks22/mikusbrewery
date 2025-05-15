package com.duckysocks.mikubrewery;

import com.duckysocks.mikubrewery.screen.ModScreenHandlers;
import com.duckysocks.mikubrewery.screen.custom.station.espressostation.EspressoStationScreen;
import com.duckysocks.mikubrewery.screen.custom.station.millstation.MillStationScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MikusBreweryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.MILLSTATION_SCREEN_HANDLER, MillStationScreen::new);
        HandledScreens.register(ModScreenHandlers.ESPRESSO_STATION_SCREEN_HANDLER, EspressoStationScreen::new);
    }
}
