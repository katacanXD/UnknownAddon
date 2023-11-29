package ru.feytox.zoomify.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.feytox.zoomify.Config;
import ru.feytox.zoomify.Zoomify;
import ru.feytox.zoomify.util.Color;
import ru.feytox.zoomify.clicker.cContriller;

@Environment(EnvType.CLIENT)
@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private Box boundingBox;

    @Unique
    private Entity entity = ((Entity) (Object) this);

    @Inject(at = @At("RETURN"), method = "getBoundingBox", cancellable = true)
    public void getBoundingBox(@NotNull CallbackInfoReturnable<Box> cir) {
        if (entity instanceof PlayerEntity player && !Config.pan && Config.permission) {
            if (!Zoomify.checkBlocklistALL(player)) {
                if (Config.toggleH && !player.isMainPlayer() && !cContriller.HideH) {
                    cir.setReturnValue(new Box(
                            boundingBox.minX - cContriller.getSize(), boundingBox.minY, boundingBox.minZ - cContriller.getSize(),
                            boundingBox.maxX + cContriller.getSize(), boundingBox.maxY, boundingBox.maxZ + cContriller.getSize()));
                }
            }
        }
        if (cContriller.OnlySmallMob && !Config.pan && Config.permission) {
            if ((entity instanceof SilverfishEntity) || (entity instanceof EndermiteEntity) || (entity instanceof SpiderEntity) ||
                    (entity instanceof WolfEntity) || (entity instanceof EndermanEntity) || (entity instanceof CaveSpiderEntity)) {
                if (Config.toggleMobH && !cContriller.HideMobH) {
                    cir.setReturnValue(new Box(
                            boundingBox.minX - cContriller.getMobSize(), boundingBox.minY, boundingBox.minZ - cContriller.getMobSize(),
                            boundingBox.maxX + cContriller.getMobSize(), boundingBox.maxY + cContriller.getMobSize(), boundingBox.maxZ + cContriller.getMobSize()));
                }
            }
        } else if ((entity instanceof LivingEntity) && !(entity instanceof PlayerEntity) && !Config.pan && Config.permission) {
            if (Config.toggleMobH && !cContriller.HideMobH) {
                cir.setReturnValue(new Box(
                        boundingBox.minX - cContriller.getMobSize(), boundingBox.minY, boundingBox.minZ - cContriller.getMobSize(),
                        boundingBox.maxX + cContriller.getMobSize(), boundingBox.maxY + cContriller.getMobSize(), boundingBox.maxZ + cContriller.getMobSize()));
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

    @Inject(method = "getTeamColorValue", at = @At("RETURN"), cancellable = true)
    public void injectChangeColorValue(CallbackInfoReturnable<Integer> cir) {
        if (Config.ClanGlow && Zoomify.checkBlocklistALL(entity)) {
            cir.setReturnValue(new Color(65, 236, 146).getColorValue());
        } else if (Config.EnemyGlow && Zoomify.checkEnemyList(entity)) {
            cir.setReturnValue(new Color(203, 44, 49).getColorValue());
        } else if(entity instanceof SpiderEntity){
            cir.setReturnValue(new Color(203, 44, 49).getColorValue());
        } else {
            cir.setReturnValue(new Color(255, 255, 255).getColorValue());
        }
    }

    @Inject(at = @At("HEAD"), method = "isGlowing", cancellable = true)
    public void isGlowing(CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof PlayerEntity player) {
            if (!player.isMainPlayer() && !Config.pan && Config.permission) {
                if (Config.PlayerGlow) {
                    cir.setReturnValue(true);
                }
                if (Config.ClanGlow && Zoomify.checkBlocklistALL(player)){
                    cir.setReturnValue(true);
                }
                if (Config.EnemyGlow && Zoomify.checkEnemyList(player)){
                    cir.setReturnValue(true);
                }
            }
        }
        if (Config.SpiderGlow && (entity instanceof SpiderEntity) && !Config.pan && Config.permission) {
            cir.setReturnValue(true);
        }
    }
}
