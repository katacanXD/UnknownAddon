package ru.feytox.zoomify.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.feytox.zoomify.Config;
import ru.feytox.zoomify.Zoomify;

@Mixin(Entity.class)
public class HideEntityMixin {
    @Inject(at = @At("RETURN"), method = "getBoundingBox", cancellable = true)
    public void myMod$getBoundingBox$ret(@NotNull CallbackInfoReturnable<Box> cir) {
        Entity entity = (Entity) (Object) this;
        if (Config.clanHideToggle && !Config.pan && Config.permission) {
            if (entity instanceof PlayerEntity player) {
                if (Zoomify.checkBlocklistALL(player) && !player.isMainPlayer()) {
                    cir.setReturnValue(new Box(
                            0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                }
            }
        }
        if (Config.HideToggle && !Config.pan && Config.permission) {
            if (!(entity instanceof PlayerEntity) && !(entity instanceof ArmorStandEntity)) {
                cir.setReturnValue(new Box(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
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
