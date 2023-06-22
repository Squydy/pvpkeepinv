package tech.endorsed.mixin;

import tech.endorsed.AttackerAccessor;
import tech.endorsed.PvPNoKeep;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow
    @Final
    private PlayerInventory inventory;

    @Shadow protected void vanishCursedItems() {

    }

    @Inject(method = "dropInventory",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;dropInventory()V",
                    shift = At.Shift.AFTER),
            cancellable = true)
    private void doNotDropOnPvP(CallbackInfo ci) {
        Entity attacker = ((AttackerAccessor)this).getDamageSource().getAttacker();
        if (attacker == null) return;
        World world = attacker.getWorld();
        if (!world.isClient && attacker.isPlayer()
                && world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)
                && !world.getGameRules().getBoolean(PvPNoKeep.PVP_KEEP_INVENTORY)) {
            this.vanishCursedItems();
            this.inventory.dropAll();
            ci.cancel();
        }
    }
}
