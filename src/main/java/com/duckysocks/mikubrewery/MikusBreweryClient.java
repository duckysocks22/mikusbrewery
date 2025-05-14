package com.duckysocks.mikubrewery;

import com.duckysocks.mikubrewery.block.entity.custom.stations.MillStationEntity;
import com.duckysocks.mikubrewery.screen.ModScreenHandlers;
import com.duckysocks.mikubrewery.screen.custom.station.MillStationScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MikusBreweryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.MILL_STATION_SCREEN_HANDLER, MillStationScreen::new);
    }
}
