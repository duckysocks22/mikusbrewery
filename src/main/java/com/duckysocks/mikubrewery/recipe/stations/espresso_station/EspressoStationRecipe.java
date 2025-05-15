package com.duckysocks.mikubrewery.recipe.stations.espresso_station;

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

public record EspressoStationRecipe(Ingredient inputItem, ItemStack output) implements Recipe<EspressoStationRecipeInput> {
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    // read Recipe JSON files --> new EspressoStationRecipe

    @Override
    public boolean matches(EspressoStationRecipeInput input, World world) {
        if(world.isClient) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(EspressoStationRecipeInput input, RegistryWrapper.WrapperLookup registrylookup) {
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
        return ModRecipes.ESPRESSO_STAITON_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ESPRESSO_STATION_TYPE;
    }

    public static class Serializer implements RecipeSerializer<EspressoStationRecipe> {
        public static final MapCodec<EspressoStationRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(EspressoStationRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(EspressoStationRecipe::output)
        ).apply(inst, EspressoStationRecipe::new));

        public static final PacketCodec<RegistryByteBuf, EspressoStationRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, EspressoStationRecipe::inputItem,
                        ItemStack.PACKET_CODEC, EspressoStationRecipe::output,
                        EspressoStationRecipe::new);

        @Override
        public MapCodec<EspressoStationRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, EspressoStationRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
