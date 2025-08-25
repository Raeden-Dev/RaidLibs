package com.raeden.raidLibs.file_management;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raeden.raidLibs.general_mc_utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

public class FileManagement {
    public static void createNewFile(String location, String file_name) {
        File file = new File(location, file_name);

        if(file.exists()) {
            Logger.infoLog(null, "A file with that name and type already exists!");
            return;
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDirectory(String location, String dir_name) {
        File file = new File(location, dir_name);

        if(file.isDirectory() && file.exists()) {
            Logger.infoLog(null, "A directory with that name already exists!");
            return;
        }

        file.mkdir();
    }

    public static boolean doesFileExist(File file, String extension) {
        if(file.exists() && file.isFile() && extension != null) {
            return true;
        }

        String fileName = file.getName();

        if(extension == null) {
            return false;
        }

        return fileName.endsWith(extension);
    }

    public static <T> T readJSONFile(File file_name, Class<T> clazz) {
        if(!doesFileExist(file_name, ".json")) {
            return null;
        }

        Gson gson = new Gson();

        try (Reader reader = new FileReader(file_name)) {
            Type type = new TypeToken<T>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            Logger.severeLog(null," Failed to read " + file_name + "!");
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void writeToJSONFile(File file_name, Class<T> clazz, boolean flush_close) {
        if(!doesFileExist(file_name, ".json")) {
            return;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            Writer writer = new FileWriter(file_name);

            gson.toJson(clazz, writer);

            if(flush_close) {
                writer.flush();
                writer.close();
            }

        } catch (IOException e) {
            Logger.severeLog(null, "Failed to write to " + file_name + "!");
            e.printStackTrace();

        }
    }
}
