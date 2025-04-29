package com.pvpkeepinv;

import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!(event.getEntity().getKiller() instanceof Player killer)) {
            return;
        }

        // only if keepInventory is false
        // should add a config or gamerule or something
        if (!event.getEntity().getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            // clear dropped experience to prevent xp duplication
            event.setDroppedExp(0);
        }
    }
}
