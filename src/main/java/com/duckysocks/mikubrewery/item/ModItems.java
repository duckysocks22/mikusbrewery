package com.duckysocks.mikubrewery.item;

import com.duckysocks.mikubrewery.MikusBrewery;
import com.duckysocks.mikubrewery.item.custom.drinks.Coffee;
import com.duckysocks.mikubrewery.item.custom.drinks.MegaCoffee;
import com.duckysocks.mikubrewery.item.custom.drinks.Tea;
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

    public static final Item COFFEE_BEANS = registerItem("coffee_beans", new Item(new Item.Settings()));
    public static final Item COFFEE_GROUNDS = registerItem("coffee_grounds", new Item(new Item.Settings()));
    public static final Item COFFEE = registerItem("coffee", new Coffee(new Item.Settings().food(ModFoodComponents.COFFEE)));
    public static final Item MEGA_COFFEE = registerItem("mega_coffee", new MegaCoffee(new Item.Settings().food(ModFoodComponents.MEGA_COFFEE)));

    public static final Item TEA_LEAVES = registerItem("tea_leaves", new Item(new Item.Settings()));
    public static final Item DRIED_TEA_LEAVES = registerItem("dried_tea_leaves", new Item(new Item.Settings()));
    public static final Item MATCHA = registerItem("matcha", new Item(new Item.Settings()));
    public static final Item TEA = registerItem("tea", new Tea(new Item.Settings().food(ModFoodComponents.TEA)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MikusBrewery.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MikusBrewery.LOGGER.info("Registering Mod Items for " + MikusBrewery.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModItems.COFFEE_BEANS);
            fabricItemGroupEntries.add(ModItems.COFFEE_GROUNDS);

            fabricItemGroupEntries.add(ModItems.TEA_LEAVES);
            fabricItemGroupEntries.add(ModItems.DRIED_TEA_LEAVES);
            fabricItemGroupEntries.add(ModItems.MATCHA);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModItems.WINE);
            fabricItemGroupEntries.add(ModItems.COFFEE);
            fabricItemGroupEntries.add(ModItems.TEA);
        });
    }
}
