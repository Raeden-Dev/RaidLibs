package com.raeden.raidLibs.mcutils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GeneralUtils {
    public static boolean hasPerm(Player player, String permission) {
        return player.isOp() || player.hasPermission(permission);
    }

    private static final String pluginPrefix = "[RaidLibs] ";

    public static void infoLog(String message) {
        Bukkit.getLogger().warning(pluginPrefix + message);
    }

    public static void warningLog(String message) {
        Bukkit.getLogger().warning(pluginPrefix+ message);
    }

    public static void severeLog(String message) {
        Bukkit.getLogger().warning(pluginPrefix + message);
    }

    public static void debugLog(String message) {
        Bukkit.getLogger().warning(pluginPrefix + "DEBUG >> " +  message);
    }
}
