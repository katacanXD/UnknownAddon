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
import ru.feytox.zoomify.test;
import ru.feytox.zoomify.Zoomify;
import ru.feytox.zoomify.test;

@Mixin(ProjectileUtil.class)
public class SilenceRaycastMixin {
    @Redirect(method = "raycast", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getBoundingBox()Lnet/minecraft/util/math/Box;"))
    private static Box myCoolModName$updateTargetedEntity$(Entity entity) {
        Box originalBox = entity.getBoundingBox();
        if (entity instanceof PlayerEntity player && !test.pan && test.permission && test.HideH && !player.isMainPlayer() && !Zoomify.checkBlocklistALL(player) && test.toggleH) {
            return new Box(originalBox.minX - test.getSize(), originalBox.minY, originalBox.minZ - test.getSize(),
                    originalBox.maxX + test.getSize(), originalBox.maxY, originalBox.maxZ + test.getSize());
        }
        if ((!test.OnlySmallMob) && !test.pan && test.permission && (entity instanceof LivingEntity) && !(entity instanceof PlayerEntity) && (test.toggleMobH && test.HideMobH)) {
            return new Box(originalBox.minX - test.getMobSize(), originalBox.minY, originalBox.minZ - test.getMobSize(),
                    originalBox.maxX + test.getMobSize(), originalBox.maxY + test.getMobSize(), originalBox.maxZ + test.getMobSize());
        }
        if ((test.OnlySmallMob) && !test.pan && test.permission && ((entity instanceof SilverfishEntity) || (entity instanceof EndermiteEntity) || (entity instanceof SpiderEntity) ||
                (entity instanceof WolfEntity) || (entity instanceof EndermanEntity) || (entity instanceof CaveSpiderEntity)) && (test.toggleMobH && test.HideMobH)) {
            return new Box(originalBox.minX - test.getMobSize(), originalBox.minY, originalBox.minZ - test.getMobSize(),
                    originalBox.maxX + test.getMobSize(), originalBox.maxY + test.getMobSize(), originalBox.maxZ + test.getMobSize());
        }
        return originalBox;
    }
}
