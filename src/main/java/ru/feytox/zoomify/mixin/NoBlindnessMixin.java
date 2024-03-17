package ru.feytox.zoomify.mixin;

import net.minecraft.client.render.BackgroundRenderer.FogData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.feytox.zoomify.test;

@Mixin(targets = "net.minecraft.client.render.BackgroundRenderer$BlindnessFogModifier")
public class NoBlindnessMixin {
    @Inject(at = @At("HEAD"), method = "applyStartEndModifier(Lnet/minecraft/client/render/BackgroundRenderer$FogData;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/effect/StatusEffectInstance;FF)V", cancellable = true)
    public void applyStartEndModifier(FogData fogData, LivingEntity entity, StatusEffectInstance effect, float viewDistance, float tickDelta, CallbackInfo ci) {
        if (test.flag && !test.pan && test.permission) {
            fogData.fogStart = viewDistance;
            fogData.fogEnd = viewDistance;
            ci.cancel();
        }
    }
}
