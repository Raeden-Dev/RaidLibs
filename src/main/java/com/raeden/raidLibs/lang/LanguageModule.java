package com.raeden.raidLibs.lang;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class LanguageModule {
    private final JavaPlugin plugin;
    private final String dataFolder;
    private final String defaultLanguage;
    private final String language;
    private final List<String> placeholders;
    private final String playerPrefixPath;
    private final String consolePrefixPath;
    private final String listPrefixPath;

    public LanguageModule(JavaPlugin plugin, String dataFolder, String defaultLanguage, String language, List<String> placeholders, String playerPrefix, String consolePrefix, String listPrefixPath) {
        this.plugin = plugin;
        this.dataFolder = dataFolder;
        this.defaultLanguage = defaultLanguage;
        this.language = language;
        this.placeholders = placeholders;
        this.playerPrefixPath = playerPrefix;
        this.consolePrefixPath = consolePrefix;
        this.listPrefixPath = listPrefixPath;
    }

    public JavaPlugin getPlugin() {return plugin;}
    public String getDataFolder() {return dataFolder;}
    public String getDefaultLanguage() {return defaultLanguage;}
    public String getLanguage() {return language;}
    public List<String> getPlaceholders() {return placeholders;}
    public String getPlayerPrefixPath() {return playerPrefixPath;}
    public String getConsolePrefixPath() {return consolePrefixPath;}
    public String getListPrefixPath() {return listPrefixPath;}
}
