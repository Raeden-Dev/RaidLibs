package com.raeden.raidLibs;

import com.raeden.raidLibs.lang.LanguageManager;
import com.raeden.raidLibs.lang.LanguageModule;
import org.bukkit.plugin.java.JavaPlugin;



public final class RaidLibs extends JavaPlugin {

    public static LanguageManager languageManager;

    @Override
    public void onEnable() {
        System.out.println("[RaidLibs] Plugin locked and loaded!");

        initializeLang();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initializeLang() {
        LanguageModule defaultModule = new LanguageModule(this,
                "lang",
                "en-us.yml",
                "en-us.yml",
                null,
                "system.prefix.player",
                "system.prefix.console",
                "system.prefix.list"
                );

        languageManager = new LanguageManager(defaultModule);
    }
}
