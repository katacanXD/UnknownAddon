package ru.feytox.zoomify.mixin;

import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ru.feytox.zoomify.util.Runes;

@Mixin(InventoryS2CPacket.class)
public class InventoryS2CPacketMixin {
    @Redirect(method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/listener/ClientPlayPacketListener;onInventory(Lnet/minecraft/network/packet/s2c/play/InventoryS2CPacket;)V"))
    public void apply(ClientPlayPacketListener instance, InventoryS2CPacket inventoryS2CPacket){
        try {
            instance.onInventory(inventoryS2CPacket);
        }
        catch (NullPointerException e){
            if (System.nanoTime() > Runes.ignoreErrorsUntil){
                throw e;
            }
        }
    }
}
