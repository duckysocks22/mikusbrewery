package com.duckysocks.mikubrewery.recipe.stations.drying_station;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record DryingStationRecipeInput(ItemStack input) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return input;
    }

    @Override
    public int getSize() {
        return 1;
    }
}
