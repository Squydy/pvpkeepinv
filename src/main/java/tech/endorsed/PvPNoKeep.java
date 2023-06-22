package tech.endorsed;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PvPNoKeep implements ModInitializer {
    public static final String MOD_ID = "pvpnokeep";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final GameRules.Key<GameRules.BooleanRule> PVP_KEEP_INVENTORY;

    @Override
    public void onInitialize() {
    }

    static {
        PVP_KEEP_INVENTORY = GameRuleRegistry.register(
                "keepInventoryPvp",
                GameRules.Category.PLAYER,
                GameRuleFactory.createBooleanRule(true));
    }
}