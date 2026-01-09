package com.raeden.raidLibs.lang;

import com.raeden.raidLibs.mcutils.GeneralUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LanguageManager {
    private final JavaPlugin plugin;
    private final LanguageModule module;
    private final File defaultLangFile;
    private final List<String> placeholders;
    private FileConfiguration langConfig;

    private final List<String> defaultVarList = new ArrayList<>(List.of("<v>", "</>", "<->", "&v", "~v", "-VAR", "{/}"));

    public LanguageManager(LanguageModule module) {
        this.plugin = module.getPlugin();
        this.module = module;
        this.placeholders = module.getPlaceholders();
        defaultLangFile = new File(plugin.getDataFolder(), "lang/" + module.getDefaultLanguage());
        initialize(module.getLanguage());
    }

    // load the assigned lang file from the config
    public void initialize(String lang) {
        File langFile = new File(plugin.getDataFolder(), "lang/" + lang);
        if(!defaultLangFile.exists()) {
            plugin.saveResource(defaultLangFile.getName(), false);
        }
        // Set to default if it doesn't exist
        if(!langFile.exists()) {
            langFile = defaultLangFile;
        }
        loadLang(langFile);
    }

    public void loadLang(File langFile) {
        langConfig = YamlConfiguration.loadConfiguration(langFile);
    }

    // Lang file settings
    public void setLangFile(Player player, String lang) {
        File newLang = new File(plugin.getDataFolder(), "lang/" + (lang.contains(".yml") ? lang : lang + ".yml"));
        if(newLang.exists()) {
            loadLang(newLang);
            if(player != null) player.sendMessage(getMsgWithInput(MessageTarget.PLAYER, LangPaths.LC_SUCCESS_PM, lang));
            GeneralUtils.infoLog(getMsgWithInput(null, LangPaths.LC_SUCCESS_CM, lang));
        } else {
            loadLang(defaultLangFile);
            if(player != null) player.sendMessage(getMsgWithInput(MessageTarget.PLAYER, LangPaths.LC_FAIL_PM, lang));
            GeneralUtils.infoLog(getMsgWithInput(null, LangPaths.LC_FAIL_CM, lang));
        }
    }

    ///
    /// Message
    ///
    public String getPlayerMsgPrefix() {
        String prefix = langConfig.getString(LangPaths.GEN_PREFIX.getPath());
        if (prefix == null) return "";
        return ChatColor.translateAlternateColorCodes('&', prefix);
    }

    // Standard way to retrieve message from a lang.yml
    public String getMsg(MessageTarget target, LangKey path) {
        if(langConfig == null) return "<Could not find language config file!>";
        String rawMessage = langConfig.getString(path.getPath());

        if (rawMessage == null) {
            if (target.equals(MessageTarget.PLAYER)) return ChatColor.RED + "<Could not find message from lang file!>";
            return "<Could not find message from lang file!>";
        }

        return buildMessage(rawMessage, target);
    }

    // Standard way to retrieve a StringList in a .yml
    public List<String> getMsgList(MessageTarget target, LangKey path) {
        if(langConfig == null) return Collections.singletonList("<Could not find language config file!>");
        List<String> rawList = langConfig.getStringList(path.getPath());

        if (rawList.isEmpty()) {
            if (target.equals(MessageTarget.PLAYER)) return Collections.singletonList(ChatColor.RED + "<Could not find message list from lang file!>");
            return Collections.singletonList("<Could not find message list from lang file!>");
        }

        List<String> result = new ArrayList<>();

        for (String line : rawList) {
            result.add(buildMessage(line, target));
        }

        return result;
    }

    // Use if there is a single placeholder in the message you'll fetch
    public String getMsgWithInput(MessageTarget target, LangKey path, String input) {
        if(langConfig == null) return ChatColor.RED + "<Could not find language config file!>";
        String rawMessage = langConfig.getString(path.getPath());

        if (rawMessage == null) {
            if (target.equals(MessageTarget.PLAYER)) return ChatColor.RED + "<Could not find message from lang file!>";
            return "<Could not find message from lang file!>";
        }

        // Replace variable
        String varChar = hasAVarInText(placeholders, rawMessage);
        if(varChar != null) {
            rawMessage = rawMessage.replace(varChar, input);
        }

        return buildMessage(rawMessage, target);
    }

    public String getMsgWithInput(MessageTarget target, String msg, String input) {
        String rawMessage = msg;

        if (rawMessage == null) {
            if (target.equals(MessageTarget.PLAYER)) return ChatColor.RED + "<UNDEFINED MESSAGE>";
            return "<UNDEFINED MESSAGE>";
        }

        // Replace variable
        String varChar = hasAVarInText(placeholders, rawMessage);
        if(varChar != null) {
            rawMessage = rawMessage.replace(varChar, input);
        }

        return buildMessage(rawMessage, target);
    }

    // Use if there is a multiple placeholder in the message you'll fetch
    public String getMsgWithInputs(MessageTarget target, LangKey path, List<String> inputs) {
        if (langConfig == null) return ChatColor.RED + "<Could not find language config file!>";

        String rawMessage = langConfig.getString(path.getPath());
        if (rawMessage == null) {
            return target.equals(MessageTarget.PLAYER)
                    ? ChatColor.RED + "<Could not find message from lang file!>"
                    : "<Could not find message from lang file!>";
        }

        List<String> inputList = clampInputToPlaceholders(rawMessage, inputs);

        // Replace placeholders with values
        String formattedMsg = formatMultiVarMsg(placeholders, inputList, rawMessage);

        return buildMessage(formattedMsg, target);
    }

    //
    public String getMsgWithInputs(MessageTarget target, String msg, List<String> inputs) {
        if (msg == null) {
            return target.equals(MessageTarget.PLAYER)
                    ? ChatColor.RED +  "<UNDEFINED MESSAGE>"
                    :  "<UNDEFINED MESSAGE>";
        }

        List<String> inputList = clampInputToPlaceholders(msg, inputs);

        // Replace placeholders with values
        String formattedMsg = formatMultiVarMsg(placeholders, inputList, msg);

        return buildMessage(formattedMsg, target);
    }

    ///
    /// Helpers
    ///

    // Replaces a single placeholder from each message in a message List using provided data List
    public List<String> replacePlaceholders(List<String> rawMessages, List<String> data) {
        List<String> finalList =  new ArrayList<>();

        for(int i = 0; i < rawMessages.size(); i++) {
            String rawMsg = rawMessages.get(i);
            String varChar = hasAVarInText(placeholders, rawMsg);

            String loreInfo;

            try {
                loreInfo = data.get(i);
            } catch (IndexOutOfBoundsException e) {
                loreInfo = "<UNDEFINED>";
            }
            String msg = rawMsg.replace(Objects.requireNonNull(varChar), loreInfo);

            finalList.add(msg);
        }

        return finalList;
    }

    // Makes sure you have enough inputs for the number of placeholder present in a Message (string)
    public List<String> clampInputToPlaceholders(String rawMessage, List<String> inputs) {
        List<String> inputList = new ArrayList<>(inputs);
        int totalVarInRawMsg = totalVarInText(placeholders, rawMessage);
        if (totalVarInRawMsg > inputList.size()) {
            while (inputList.size() < totalVarInRawMsg) inputList.add("<UNDEFINED>");
        } else if (inputList.size() > totalVarInRawMsg) {
            while (inputList.size() > totalVarInRawMsg) inputList.removeLast();
        }

        return inputList;
    }

    // Manages color translation and adding prefix to message (used by all getMsg methods)
    private String buildMessage(String rawMessage, MessageTarget target) {
        String prefix = getPrefix(target);
        StringBuilder message = new StringBuilder();
        if(!prefix.isEmpty()) {
            if(target.equals(MessageTarget.CONSOLE)) {
                message.append(prefix);
            } else {
                message.append(ChatColor.translateAlternateColorCodes('&', prefix));
            }
        }
        if(target.equals(MessageTarget.CONSOLE)) {
            message.append(rawMessage);
        } else {
            message.append(ChatColor.translateAlternateColorCodes('&', rawMessage));
        }
        return message.toString();
    }

    private String getPrefix(MessageTarget target) {
        return switch (target) {
            case PLAYER -> langConfig.getString(module.getPlayerPrefixPath(), "");
            case CONSOLE -> langConfig.getString(module.getConsolePrefixPath(), "");
            case LIST -> langConfig.getString(module.getListPrefixPath(), "");
            case NONE -> "";
        };
    }

    private int totalVarInText(List<String> varList, String msg) {
        if (varList == null || varList.isEmpty()) varList = defaultVarList;

        int count = 0;
        for (String s : varList) {
            int index = msg.indexOf(s);
            while (index != -1) {
                count++;
                index = msg.indexOf(s, index + s.length());
            }
        }
        return count;
    }

    private String hasAVarInText(List<String> varList, String msg) {
        if (varList == null || varList.isEmpty()) varList = defaultVarList;

        for (String s : varList) {
            if (msg.contains(s)) return s;
        }
        return null;
    }

    private String formatMultiVarMsg(List<String> varList, List<String> inputList, String msg) {
        if (varList == null || varList.isEmpty()) varList = defaultVarList;

        String formatted = msg;
        int idx = 0;
        for (String s : varList) {
            while (formatted.contains(s) && idx < inputList.size()) {
                formatted = formatted.replaceFirst(java.util.regex.Pattern.quote(s), inputList.get(idx));
                idx++;
            }
        }
        return formatted;
    }

    public List<String> getDefaultVarList() {
        return defaultVarList;
    }

    public enum MessageTarget {
        PLAYER,
        CONSOLE,
        LIST,
        NONE
    }
}
