package com.raeden.raidLibs.mcutils;

import com.raeden.raidLibs.lang.LanguageManager;
import com.raeden.raidLibs.lang.LibLang;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.raeden.raidLibs.RaidLibs.languageManager;

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

    private void blockScanHelper() {

    }

    public boolean scanForBlock(Player player, Location location, String operationType, int size, Block blockType) {
        if(location == null) {
            languageManager.getMsg(LanguageManager.MessageTarget.CONSOLE, LibLang.BLOCK_SCAN_LOC_ERROR);
            return false;
        }

        if(blockType == null) {
            languageManager.getMsg(LanguageManager.MessageTarget.CONSOLE, LibLang.BLOCK_SCAN_BLOCK_ERROR);
            return false;
        }

        World world = location.getWorld();
        int radiusSquared = size * size;
        int startX = location.getBlockX() - size;
        int endX = location.getBlockX() + size;
        int startY = location.getBlockY() - size;
        int endY = location.getBlockY() + size;
        int startZ = location.getBlockZ() - size;
        int endZ = location.getBlockZ() + size;

        // Operation types = "square", "sqr", "cube", "cuboid", "0" || "circle", "circular", "sphere", "sph", "1"
        List<String> squareOperation = new ArrayList<>(List.of("square", "sqr", "cube", "cuboid", "0"));
        List<String> circleOperation = new ArrayList<>(List.of("circle", "circular", "sphere", "sph", "1"));

        if(squareOperation.contains(operationType)) {
            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    for (int z = startZ; z <= endZ; z++) {
                        Block block = Objects.requireNonNull(world).getBlockAt(x, y, z);
                        if(block.getBlockData().getMaterial().equals(blockType.getBlockData().getMaterial())) {
                            return true;
                        }
                    }
                }
            }
        }

        if(circleOperation.contains(operationType)) {
            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    for (int z = startZ; z <= endZ; z++) {
                        if (location.distanceSquared(new Location(world, x, y, z)) > radiusSquared) {
                            continue;
                        }
                        Block block = Objects.requireNonNull(world).getBlockAt(x, y, z);
                        if(block.getBlockData().getMaterial().equals(blockType.getBlockData().getMaterial())) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
