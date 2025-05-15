package com.duckysocks.mikubrewery.recipe.stations.drying_station;

import com.duckysocks.mikubrewery.recipe.ModRecipes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record DryingStationRecipe(Ingredient inputItem, ItemStack output) implements Recipe<DryingStationRecipeInput> {
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    // read Recipe JSON files --> new DryingStationRecipe

    @Override
    public boolean matches(DryingStationRecipeInput input, World world) {
        if(world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(DryingStationRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.DRYING_STATION_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.DRYING_STATION_TYPE;
    }

    public static class Serializer implements RecipeSerializer<DryingStationRecipe> {
        public static final MapCodec<DryingStationRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(DryingStationRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(DryingStationRecipe::output)
        ).apply(inst, DryingStationRecipe::new));

        public static final PacketCodec<RegistryByteBuf, DryingStationRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, DryingStationRecipe::inputItem,
                        ItemStack.PACKET_CODEC, DryingStationRecipe::output,
                        DryingStationRecipe::new);

        @Override
        public MapCodec<DryingStationRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, DryingStationRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
