package com.duckysocks.mikubrewery.sound;

import com.duckysocks.mikubrewery.MikusBrewery;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent MILL_STATION_RUN = registerSoundEvent("mill_station_run");

    //public static final BlockSoundGroup EXAMPLE_SOUND_GROUP = new BlockSoundGroup(1f, 1f,
    //        EXAMPLE_BLOCK_BREAK, EXAMPLE_BLOCK_STEP, EXAMPLE_BLOCK_PLACE, etc);


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(MikusBrewery.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        MikusBrewery.LOGGER.info("Registering Mod Sounds for " + MikusBrewery.MOD_ID);
    }
}
