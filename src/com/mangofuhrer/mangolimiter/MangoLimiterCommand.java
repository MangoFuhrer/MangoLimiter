package com.mangofuhrer.mangolimiter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class MangoLimiterCommand implements CommandExecutor {

    private final MangoLimiter plugin;
    private final FlyLimiter flyLimiter;

    public MangoLimiterCommand(MangoLimiter plugin, FlyLimiter flyLimiter) {
        this.plugin = plugin;
        this.flyLimiter = flyLimiter;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = plugin.getConfig();
        LangManager lang = plugin.getLang();

        String usePermission = config.getString("permissions.use", "mangolimiter.use");
        if (!sender.hasPermission(usePermission)) {
            sender.sendMessage(colorize(lang.get("errors.no-permission")));
            return true;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            for (String line : lang.getList("help")) {
                sender.sendMessage(colorize(line));
            }
            return true; //MANGOFUHRER//
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            flyLimiter.reloadConfig();
            sender.sendMessage(colorize(lang.get("reload-success")));
            return true;
        }

        sender.sendMessage(colorize(lang.get("errors.unknown-command")));
        return true;
    }

    private String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
