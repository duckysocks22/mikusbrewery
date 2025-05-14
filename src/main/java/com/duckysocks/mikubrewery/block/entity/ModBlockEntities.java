package com.duckysocks.mikubrewery.block.entity;

import com.duckysocks.mikubrewery.MikusBrewery;
import com.duckysocks.mikubrewery.block.ModBlocks;
import com.duckysocks.mikubrewery.block.custom.stations.MillStation;
import com.duckysocks.mikubrewery.block.entity.custom.stations.MillStationEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<MillStationEntity> MILL_STATION_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MikusBrewery.MOD_ID, "mill_station_be"),
                    BlockEntityType.Builder.create(MillStationEntity::new, ModBlocks.MILL_STATION).build(null));

    public static void registerBlockEntities() {
        MikusBrewery.LOGGER.info("Registering Block Entities for " + MikusBrewery.MOD_ID);
    }
}
