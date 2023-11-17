package ru.feytox.zoomify.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.feytox.zoomify.Config;
import ru.feytox.zoomify.Zoomify;

@Mixin(Entity.class)
public class PlayerHitboxMixin {
    @Shadow
    private Box boundingBox;

    @Inject(at = @At("RETURN"), method = "getBoundingBox", cancellable = true)
    public void getBoundingBox(@NotNull CallbackInfoReturnable<Box> cir) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof PlayerEntity player && !Config.pan && Config.permission) {
            if (!Zoomify.checkBlocklistALL(player)) {
                if (Config.toggleHitbox && !player.isMainPlayer() && !Config.HideHits) {
                    cir.setReturnValue(new Box(
                            boundingBox.minX - Config.getSize(), boundingBox.minY, boundingBox.minZ - Config.getSize(),
                            boundingBox.maxX + Config.getSize(), boundingBox.maxY, boundingBox.maxZ + Config.getSize()));
                }
            }
        }
        if (Config.OnlySmallMob && !Config.pan && Config.permission) {
            if ((entity instanceof SilverfishEntity) || (entity instanceof EndermiteEntity) || (entity instanceof SpiderEntity) ||
                    (entity instanceof WolfEntity) || (entity instanceof EndermanEntity) || (entity instanceof CaveSpiderEntity))  {
                if (Config.toggleMobHitbox && !Config.HideMobHits) {
                    cir.setReturnValue(new Box(
                            boundingBox.minX - Config.getMobSize(), boundingBox.minY, boundingBox.minZ - Config.getMobSize(),
                            boundingBox.maxX + Config.getMobSize(), boundingBox.maxY + Config.getMobSize(), boundingBox.maxZ + Config.getMobSize()));
                }
            }
        } else if ((entity instanceof LivingEntity) && !(entity instanceof PlayerEntity) && !Config.pan && Config.permission) {
            if (Config.toggleMobHitbox && !Config.HideMobHits)  {
                cir.setReturnValue(new Box(
                        boundingBox.minX - Config.getMobSize(), boundingBox.minY, boundingBox.minZ - Config.getMobSize(),
                        boundingBox.maxX + Config.getMobSize(), boundingBox.maxY + Config.getMobSize(), boundingBox.maxZ + Config.getMobSize()));
            }
        }
        assert MinecraftClient.getInstance().player != null;
        if (entity instanceof PlayerEntity) {
            if (entity == MinecraftClient.getInstance().player) {
                if (Zoomify.checkPermissionList(entity)) {
                    Config.permission = true;
                } else if (!Zoomify.checkPermissionList(entity)) {
                    Config.permission = false;
                }
            }
        }
    }
}
