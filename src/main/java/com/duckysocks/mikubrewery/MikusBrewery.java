package com.duckysocks.mikubrewery;

import com.duckysocks.mikubrewery.block.ModBlocks;
import com.duckysocks.mikubrewery.block.entity.ModBlockEntities;
import com.duckysocks.mikubrewery.block.entity.renderer.DryingStationBlockEntityRenderer;
import com.duckysocks.mikubrewery.item.ModItemGroups;
import com.duckysocks.mikubrewery.item.ModItems;
import com.duckysocks.mikubrewery.recipe.ModRecipes;
import com.duckysocks.mikubrewery.screen.ModScreenHandlers;
import com.duckysocks.mikubrewery.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MikusBrewery implements ModInitializer {
	public static final String MOD_ID = "mikubrewery";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing 'Miku's Brewery', sit tight!");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();

		ModSounds.registerSounds();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();

		ModRecipes.registerRecipes();

		BlockEntityRendererFactories.register(ModBlockEntities.DRYING_STATION_BE, DryingStationBlockEntityRenderer::new);
	}
}