package com.duckysocks.mikubrewery.item;

import com.duckysocks.mikubrewery.MikusBrewery;
import com.duckysocks.mikubrewery.item.custom.drinks.Coffee;
import com.duckysocks.mikubrewery.item.custom.drinks.Wine;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // Standard Items
    //public static final Item EXAMPLE_ITEM = registerItem("EXAMPLE_ITEM", new Item(new Item.Settings()));

    public static final Item WINE = registerItem("wine", new Wine(new Item.Settings().food(ModFoodComponents.WINE)));
    public static final Item COFFEE = registerItem("coffee", new Coffee(new Item.Settings().food(ModFoodComponents.COFFEE)));

    public static final Item COFFEE_BEANS = registerItem("coffee_beans", new Item(new Item.Settings()));
    public static final Item COFFEE_GROUNDS = registerItem("coffee_grounds", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MikusBrewery.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MikusBrewery.LOGGER.info("Registering Mod Items for " + MikusBrewery.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            //entries.add(EXAMPLE_ITEM);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModItems.WINE);
            fabricItemGroupEntries.add(ModItems.COFFEE);
        });
    }
}
