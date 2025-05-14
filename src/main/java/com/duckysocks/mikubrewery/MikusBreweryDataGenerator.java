package com.duckysocks.mikubrewery;

import com.duckysocks.mikubrewery.datagen.ModBlockTagProvider;
import com.duckysocks.mikubrewery.datagen.ModItemTagProvider;
import com.duckysocks.mikubrewery.datagen.ModLootTableProvider;
import com.duckysocks.mikubrewery.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MikusBreweryDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
