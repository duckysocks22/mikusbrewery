package com.duckysocks.mikubrewery.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        //List<ItemConvertible> EXAMPLE_ORE_SMELTABLES = List.of(ModItems.RAW_EXAMPLE_ORE, ModBlocks.EXAMPLE_ORE,
        //          ModBlocks.EXAMPLE_DEEPSLATE_ORE);

        //offerSmelting(exporter, EXAMPLE_ORE_SMELTABLES, RecipeCategory.MISC, ModItems.EXAMPLE_BAR, 0.25f, 200, "example_bar");
        //offerBlasting(exporter, EXAMPLE_ORE_SMELTABLES, RecipeCategory.MISC, ModItems.EXAMPLE_BAR, 0.25f, 100, "example_bar");

        //offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.EXAMPLE_BAR, RecipeCategory.DECORATIONS, ModBlocks.EXAMPLE_BAR_BLOCK);

        //ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.EXAMPLE_RAW_ORE_BLOCK)
        //          .pattern("RRR")
        //          .pattern("RRR")
        //          .pattern("RRR")
        //          .input('R', ModItems.RAW_EXAMPLE_ORE)
        //          .criterion(hasItem(ModItems.RAW_EXAMPLE_ORE), conditionsForItem(ModItems.RAW_EXAMPLE_ORE))
        //          .offerTo(exporter);

        //ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_EXAMPLE_ORE, 9)
        //          .input(ModBlocks.RAW_EXAMPLE_ORE_BLOCK)
        //          .criterion(hasItem(ModBlocks.RAW_EXAMPLE_ORE_BLOCK), conditionsForItem(ModBlocks.RAW_EXAMPLE_ORE_BLOCK))
        //          offerTo(exporter);

        //ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_EXAMPLE_ORE, 9)
        //          .input(ModBlocks.OTHER_BLOCK)
        //          .criterion(hasItem(ModBlocks.RAW_EXAMPLE_ORE_BLOCK), conditionsForItem(ModBlocks.RAW_EXAMPLE_ORE_BLOCK))
        //          offerTo(exporter, Identifier.of(MikusBrewery.MOD_ID, "raw_example_ore_from_other_block));


    }
}
