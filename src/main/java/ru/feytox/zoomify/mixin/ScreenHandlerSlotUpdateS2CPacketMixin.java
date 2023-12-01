package ru.feytox.zoomify.mixin;

import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ru.feytox.zoomify.util.Runes;

@Mixin(ScreenHandlerSlotUpdateS2CPacket.class)
public class ScreenHandlerSlotUpdateS2CPacketMixin {
    @Redirect(method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/listener/ClientPlayPacketListener;onScreenHandlerSlotUpdate(Lnet/minecraft/network/packet/s2c/play/ScreenHandlerSlotUpdateS2CPacket;)V"))
    public void apply(ClientPlayPacketListener instance, ScreenHandlerSlotUpdateS2CPacket screenHandlerSlotUpdateS2CPacket){
        try {
            instance.onScreenHandlerSlotUpdate(screenHandlerSlotUpdateS2CPacket);
        }
        catch (NullPointerException e){
            if (System.nanoTime() > Runes.ignoreErrorsUntil){
                throw e;
            }
        }
    }
}
