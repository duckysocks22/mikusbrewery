package com.duckysocks.mikubrewery.util;

import com.duckysocks.mikubrewery.MikusBrewery;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MikusBrewery.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> MILLABLE_ITEMS = createTag("millable_items");
        public static final TagKey<Item> DRYABLE_ITEMS = createTag("dryable_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(MikusBrewery.MOD_ID, name));
        }
    }
}
