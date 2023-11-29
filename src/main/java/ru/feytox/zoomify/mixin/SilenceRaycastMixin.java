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
import ru.feytox.zoomify.clicker.cContriller;

@Mixin(ProjectileUtil.class)
public class SilenceRaycastMixin {
    @Redirect(method = "raycast", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getBoundingBox()Lnet/minecraft/util/math/Box;"))
    private static Box myCoolModName$updateTargetedEntity$(Entity entity) {
        Box originalBox = entity.getBoundingBox();
        if (entity instanceof PlayerEntity player && !Config.pan && Config.permission && cContriller.HideH && !player.isMainPlayer() && !Zoomify.checkBlocklistALL(player) && Config.toggleH) {
            return new Box(originalBox.minX - cContriller.getSize(), originalBox.minY, originalBox.minZ - cContriller.getSize(),
                    originalBox.maxX + cContriller.getSize(), originalBox.maxY, originalBox.maxZ + cContriller.getSize());
        }
        if ((!cContriller.OnlySmallMob) && !Config.pan && Config.permission && (entity instanceof LivingEntity) && !(entity instanceof PlayerEntity) && (Config.toggleMobH && cContriller.HideMobH)) {
            return new Box(originalBox.minX - cContriller.getMobSize(), originalBox.minY, originalBox.minZ - cContriller.getMobSize(),
                    originalBox.maxX + cContriller.getMobSize(), originalBox.maxY + cContriller.getMobSize(), originalBox.maxZ + cContriller.getMobSize());
        }
        if ((cContriller.OnlySmallMob) && !Config.pan && Config.permission && ((entity instanceof SilverfishEntity) || (entity instanceof EndermiteEntity) || (entity instanceof SpiderEntity) ||
                (entity instanceof WolfEntity) || (entity instanceof EndermanEntity) || (entity instanceof CaveSpiderEntity)) && (Config.toggleMobH && cContriller.HideMobH)) {
            return new Box(originalBox.minX - cContriller.getMobSize(), originalBox.minY, originalBox.minZ - cContriller.getMobSize(),
                    originalBox.maxX + cContriller.getMobSize(), originalBox.maxY + cContriller.getMobSize(), originalBox.maxZ + cContriller.getMobSize());
        }
        return originalBox;
    }
}
