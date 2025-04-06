package com.raeden.raidLibs.general_mc_utils;

import org.bukkit.Bukkit;

public class Logger {
    public static void infoLog(String plugin_prefix, String message) {
        if(plugin_prefix != null) {
            Bukkit.getLogger().info("[" + plugin_prefix + "]: " + message);
        } else {
            Bukkit.getLogger().info(message);
        }
    }

    public static void warningLog(String plugin_prefix, String message) {
        if(plugin_prefix != null) {
            Bukkit.getLogger().warning("[" + plugin_prefix + "]: " + message);
        } else {
            Bukkit.getLogger().warning(message);
        }
    }

    public static void severeLog(String plugin_prefix, String message) {
        if(plugin_prefix != null) {
            Bukkit.getLogger().severe("[" + plugin_prefix + "]: " + message);
        } else {
            Bukkit.getLogger().severe(message);
        }
    }

    public static void debugLog(String plugin_prefix, String message) {
        if(plugin_prefix != null) {
            Bukkit.getLogger().info("[" + plugin_prefix + "] - DEBUG >>> " + message);
        } else {
            Bukkit.getLogger().info("DEBUG >>>" + message);
        }
    }
}
