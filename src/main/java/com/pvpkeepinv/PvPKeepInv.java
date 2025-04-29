package com.pvpkeepinv;

import org.bukkit.plugin.java.JavaPlugin;

public class PvPKeepInv extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("PvPKeepInv enabled!");
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
}

    @Override
    public void onDisable() {
        getLogger().info("PvPKeepInv disabled!");
    }
}
