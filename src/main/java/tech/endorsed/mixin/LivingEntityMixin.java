package tech.endorsed.mixin;

import tech.endorsed.AttackerAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements AttackerAccessor {
    public DamageSource dropDamageSource;

    @Override
    public DamageSource getDamageSource() {
        return dropDamageSource;
    }

    @Inject(at = @At("HEAD"), method = "drop")
    private void onDrop(DamageSource source, CallbackInfo ci) {
        dropDamageSource = source;
    }
}