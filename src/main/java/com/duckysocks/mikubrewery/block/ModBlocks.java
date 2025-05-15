package com.duckysocks.mikubrewery.block;

import com.duckysocks.mikubrewery.MikusBrewery;
import com.duckysocks.mikubrewery.block.custom.stations.DryingStationBlock;
import com.duckysocks.mikubrewery.block.custom.stations.MillStationBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    //public static final Block EXAMPLE_BLOCK = registerBlock("example_block",
    //        new Block(AbstractBlock.Settings.create().strength(4f)
    //               .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block MILL_STATION = registerBlock("mill_station",
            new MillStationBlock(AbstractBlock.Settings.create().strength(2f).nonOpaque()
                    .requiresTool().sounds(BlockSoundGroup.WOOD)));

    public static final Block DRYING_STATION = registerBlock("drying_station",
            new DryingStationBlock(AbstractBlock.Settings.create().strength(1f).nonOpaque()
                    .requiresTool().sounds(BlockSoundGroup.WOOD)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MikusBrewery.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MikusBrewery.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        MikusBrewery.LOGGER.info("Registering Mod Blocks for " + MikusBrewery.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            //fabricItemGroupEntries.add(ModBlocks.EXAMPLE_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.MILL_STATION);
            fabricItemGroupEntries.add(ModBlocks.DRYING_STATION);
        });
    }
}
