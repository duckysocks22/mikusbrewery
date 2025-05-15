package com.duckysocks.mikubrewery.datagen;

import com.duckysocks.mikubrewery.item.ModItems;
import com.duckysocks.mikubrewery.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        //getOrCreateTagBuilder(ModTags.Items.EXAMPLE1)
        //          .add(ModItems.EXAMPLE_ITEM1)
        //          .add(ModItems.EXAMPLE_ITEM2);

        getOrCreateTagBuilder(ModTags.Items.MILLABLE_ITEMS)
                .add(ModItems.COFFEE_BEANS)
                .add(ModItems.TEA_LEAVES);
    }
}
