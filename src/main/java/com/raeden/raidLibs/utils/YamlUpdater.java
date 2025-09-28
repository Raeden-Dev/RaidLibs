package com.raeden.raidLibs.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class YamlUpdater {
    private final JavaPlugin plugin;

    public YamlUpdater(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public FileConfiguration updateFile(File file) {
        if(!file.exists()) {
            plugin.saveResource(file.getName(), false);
        }

        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        InputStream stream =  plugin.getResource(file.getName());

        if(stream != null) {
            YamlConfiguration defaultCfg = YamlConfiguration.loadConfiguration(new InputStreamReader(stream, StandardCharsets.UTF_8));
            boolean updated = false;

            Set<String> keys = defaultCfg.getKeys(true);
            for(String key : keys) {
                if(!cfg.contains(key)) {
                    cfg.set(key, defaultCfg.get(key));
                    updated = true;
                }
            }

            if(updated) {
                try {
                    cfg.save(file);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return cfg;
    }
}
