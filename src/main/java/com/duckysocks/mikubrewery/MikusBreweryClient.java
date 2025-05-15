package com.duckysocks.mikubrewery;

import com.duckysocks.mikubrewery.screen.ModScreenHandlers;
import com.duckysocks.mikubrewery.screen.custom.station.MillStationScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MikusBreweryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.MILLSTATION_SCREEN_HANDLER, MillStationScreen::new);
    }
}
