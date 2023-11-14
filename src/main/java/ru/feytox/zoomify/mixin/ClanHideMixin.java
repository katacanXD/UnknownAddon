package ru.feytox.zoomify.mixin;

import net.minecraft.entity.Entity;
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
public class ClanHideMixin {
    @Inject(at = @At("RETURN"), method = "getBoundingBox", cancellable = true)
    public void myMod$getBoundingBox$ret(@NotNull CallbackInfoReturnable<Box> cir) {
        Entity entity = (Entity) (Object) this;
        if (Config.toggleMod && !Config.pan) {
            if (entity instanceof PlayerEntity player) {
                if (Zoomify.checkBlocklistALL(player) && !player.isMainPlayer()) {
                    cir.setReturnValue(new Box(
                            0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                }
            }
        }
    }
}
