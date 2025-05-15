package com.duckysocks.mikubrewery.recipe.stations.mill_station;

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

public record MillStationRecipe(Ingredient inputItem, ItemStack output) implements Recipe<MillStationRecipeInput> {
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    // read Recipe JSON files --> new MillStationRecipe

    @Override
    public boolean matches(MillStationRecipeInput input, World world) {
        if(world.isClient) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(MillStationRecipeInput input, RegistryWrapper.WrapperLookup registrylookup) {
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
        return ModRecipes.MILLSTATION_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.MILLSTATION_TYPE;
    }

    public static class Serializer implements RecipeSerializer<MillStationRecipe> {
        public static final MapCodec<MillStationRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(MillStationRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(MillStationRecipe::output)
        ).apply(inst, MillStationRecipe::new));

        public static final PacketCodec<RegistryByteBuf, MillStationRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, MillStationRecipe::inputItem,
                        ItemStack.PACKET_CODEC, MillStationRecipe::output,
                        MillStationRecipe::new);

        @Override
        public MapCodec<MillStationRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, MillStationRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
