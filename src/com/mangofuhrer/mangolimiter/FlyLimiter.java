package com.mangofuhrer.mangolimiter;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class FlyLimiter implements Listener {

    private final MangoLimiter plugin;
    private int maxHeight;
    private String bypassPermission;
    private boolean debug;

    public FlyLimiter(MangoLimiter plugin) {
        this.plugin = plugin;
        reloadConfig();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        maxHeight = config.getInt("flylimiter.max-height", 150);
        bypassPermission = config.getString("flylimiter.permissions.bypass", "mangolimiter.bypass");
        debug = config.getBoolean("debug", false);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer(); //MANGOFUHRER//

        if (!player.isFlying()) return;
        if (player.hasPermission(bypassPermission)) return;

        double currentY = player.getLocation().getY();
        if (currentY > maxHeight) {
            player.setFlying(false);
            String message = plugin.getLang().get("fly-limit-message").replace("%height%", String.valueOf(maxHeight));
            player.sendMessage(message);

            if (debug) {
                plugin.getLogger().info(player.getName() + " excedió la altura permitida: " + currentY + " > " + maxHeight);
            }
        }
    }
}
