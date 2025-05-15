package com.duckysocks.mikubrewery.datagen;

import com.duckysocks.mikubrewery.block.ModBlocks;
import com.duckysocks.mikubrewery.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EXAMPLE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MILL_STATION);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DRYING_STATION);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ESPRESSO_STATION);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //itemModelGenerator.register(ModItems.EXAMPLE_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.WINE, Models.GENERATED);

        itemModelGenerator.register(ModItems.COFFEE_BEANS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COFFEE_GROUNDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MEGA_COFFEE, Models.GENERATED);

        itemModelGenerator.register(ModItems.TEA_LEAVES, Models.GENERATED);
        itemModelGenerator.register(ModItems.DRIED_TEA_LEAVES, Models.GENERATED);
        itemModelGenerator.register(ModItems.MATCHA, Models.GENERATED);
        itemModelGenerator.register(ModItems.TEA, Models.GENERATED);
    }
}
