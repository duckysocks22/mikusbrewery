package com.duckysocks.mikubrewery.recipe.stations.espresso_station;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record EspressoStationRecipeInput(ItemStack input) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return input;
    }

    @Override
    public int getSize() {
        return 1;
    }
}
