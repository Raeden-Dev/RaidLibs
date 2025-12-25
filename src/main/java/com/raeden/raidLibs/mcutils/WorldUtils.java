package com.raeden.raidLibs.mcutils;

import org.bukkit.*;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class WorldUtils {
    public void createEmptyWorld(String worldName) {
        WorldCreator creator = new WorldCreator(worldName);
        creator.environment(World.Environment.NORMAL);
        creator.generator(new ChunkGenerator() {
            @NotNull
            @Override
            public ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int x, int z, @NotNull BiomeGrid biome) {
                return createChunkData(world); // void
            }
            
        });

        World world = Bukkit.createWorld(creator);
        if(world != null) {
            Location spawnPoint = new Location(world, 0, 64, 0);
            world.getBlockAt(spawnPoint).setType(Material.STONE);
            world.setSpawnLocation(spawnPoint);
            GeneralUtils.infoLog("Created world " + worldName);
        } else {
            GeneralUtils.infoLog("Failed to create world " + worldName);
        }
    }
}
