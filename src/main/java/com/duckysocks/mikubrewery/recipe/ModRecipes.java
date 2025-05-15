package com.duckysocks.mikubrewery.recipe;

import com.duckysocks.mikubrewery.MikusBrewery;
import com.duckysocks.mikubrewery.recipe.stations.mill_station.MillStationRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<MillStationRecipe> MILLSTATION_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(MikusBrewery.MOD_ID, "mill_station"),
                    new MillStationRecipe.Serializer());

    public static final RecipeType<MillStationRecipe> MILLSTATION_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(MikusBrewery.MOD_ID, "mill_station"), new RecipeType<MillStationRecipe>() {
                @Override
                public String toString() {
                    return "mill_station";
                }});

    public static void registerRecipes() {
        MikusBrewery.LOGGER.info("Registering Custom Recipes for " + MikusBrewery.MOD_ID);
    }

}
