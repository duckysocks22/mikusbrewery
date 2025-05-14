package com.duckysocks.mikubrewery;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MikusBrewery implements ModInitializer {
	public static final String MOD_ID = "mikubrewery";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing 'Miku's Brewery', sit tight!");
	}
}