package ru.feytox.zoomify.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ru.feytox.zoomify.Config;
import ru.feytox.zoomify.Zoomify;

@Mixin(ProjectileUtil.class)
public class SilenceHitboxMixin {
    @Redirect(method = "raycast", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getBoundingBox()Lnet/minecraft/util/math/Box;"))
    private static Box myCoolModName$updateTargetedEntity$(Entity entity) {
        Box originalBox = entity.getBoundingBox();
        if (entity instanceof PlayerEntity player && !Config.pan && Config.permission && Config.HideHits && !player.isMainPlayer() && !Zoomify.checkBlocklistALL(player) && Config.toggleHitbox) {
            return new Box(originalBox.minX - Config.getSize(), originalBox.minY, originalBox.minZ - Config.getSize(),
                    originalBox.maxX + Config.getSize(), originalBox.maxY, originalBox.maxZ + Config.getSize());
        }
        if ((!Config.OnlySmallMob) && !Config.pan && Config.permission && (entity instanceof LivingEntity) && !(entity instanceof PlayerEntity) && (Config.toggleMobHitbox && Config.HideMobHits)) {
            return new Box(originalBox.minX - Config.getMobSize(), originalBox.minY, originalBox.minZ - Config.getMobSize(),
                    originalBox.maxX + Config.getMobSize(), originalBox.maxY + Config.getMobSize(), originalBox.maxZ + Config.getMobSize());
        }
        if ((Config.OnlySmallMob) && !Config.pan && Config.permission && ((entity instanceof SilverfishEntity) || (entity instanceof EndermiteEntity) || (entity instanceof SpiderEntity) ||
                (entity instanceof WolfEntity) || (entity instanceof EndermanEntity) || (entity instanceof CaveSpiderEntity)) && (Config.toggleMobHitbox && Config.HideMobHits)) {
            return new Box(originalBox.minX - Config.getMobSize(), originalBox.minY, originalBox.minZ - Config.getMobSize(),
                    originalBox.maxX + Config.getMobSize(), originalBox.maxY + Config.getMobSize(), originalBox.maxZ + Config.getMobSize());
        }
        return originalBox;
    }
}
