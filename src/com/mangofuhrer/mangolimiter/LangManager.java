package com.mangofuhrer.mangolimiter;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class LangManager {

    private final MangoLimiter plugin;
    private FileConfiguration langConfig;

    public LangManager(MangoLimiter plugin) {
        this.plugin = plugin;
        loadLangFile();
    }

    private void loadLangFile() {
        String lang = plugin.getConfig().getString("language", "en");
        File langFile = new File(plugin.getDataFolder(), "lang/" + lang + ".yml");

        if (!langFile.exists()) {
            langFile.getParentFile().mkdirs();
            plugin.saveResource("lang/" + lang + ".yml", false); //MANGOFUHRER//
        }

        langConfig = YamlConfiguration.loadConfiguration(langFile);

        InputStream defLangStream = plugin.getResource("lang/" + lang + ".yml");
        if (defLangStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defLangStream));
            langConfig.setDefaults(defConfig);
        }
    }

    public String get(String path) {
        String raw = langConfig.getString(path);
        if (raw == null) {
            return ChatColor.RED + "Missing lang: " + path;
        }
        return translateColors(raw);
    }

    public List<String> getList(String path) {
        List<String> rawList = langConfig.getStringList(path);
        return rawList.stream()
                .map(this::translateColors)
                .collect(Collectors.toList());
    }

    private String translateColors(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
