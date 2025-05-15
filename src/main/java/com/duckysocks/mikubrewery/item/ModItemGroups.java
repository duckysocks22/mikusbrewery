package com.duckysocks.mikubrewery.item;

import com.duckysocks.mikubrewery.MikusBrewery;
import com.duckysocks.mikubrewery.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MIKUS_BREWERY_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MikusBrewery.MOD_ID, "mikus_brewery_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.WINE))
                    .displayName(Text.translatable("itemgroup.mikusbrewery.mikus_brewery_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.WINE);
                        entries.add(ModBlocks.MILL_STATION);

                        entries.add(ModItems.COFFEE_BEANS);
                        entries.add(ModItems.COFFEE_GROUNDS);
                        entries.add(ModItems.COFFEE);

                        entries.add(ModItems.TEA_LEAVES);
                        entries.add(ModItems.MATCHA);

                    }).build());


    public static void registerItemGroups() {
        MikusBrewery.LOGGER.info("Registering Item Groups for" + MikusBrewery.MOD_ID);
    }
}
