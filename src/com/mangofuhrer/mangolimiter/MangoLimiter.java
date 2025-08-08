package com.mangofuhrer.mangolimiter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MangoLimiter extends JavaPlugin {

    private FlyLimiter flyLimiter;
    private LangManager langManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("lang/en.yml", false);
        saveResource("lang/es.yml", false);
        langManager = new LangManager(this);
        Bukkit.getConsoleSender().sendMessage(getLang().get("plugin.enabled"));
        flyLimiter = new FlyLimiter(this);
        this.getCommand("mangolimiter").setExecutor(new MangoLimiterCommand(this, flyLimiter));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(getLang().get("plugin.disabled"));
    }

    public FlyLimiter getFlyLimiter() {
        return flyLimiter; //MANGOFUHRER//
    }

    public LangManager getLang() {
        return langManager;
    }
}
