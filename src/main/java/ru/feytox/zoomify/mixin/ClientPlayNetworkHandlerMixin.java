package ru.feytox.zoomify.mixin;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.feytox.zoomify.util.Runes;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

    @Shadow @Final private MinecraftClient client;

    @Shadow public abstract void sendPacket(Packet<?> packet);


    @Inject(method = "onOpenScreen", at = @At("HEAD"), cancellable = true)
    public void onOpenScreen(OpenScreenS2CPacket packet, CallbackInfo ci){
        if (Runes.set != -1){
            ci.cancel();
            Runes.ShouldCloseNetScreenTitle = packet.getName();
            sendPacket(new ClickSlotC2SPacket(packet.getSyncId(), 0, Runes.set, 0, SlotActionType.PICKUP, ItemStack.EMPTY,
                    Int2ObjectMaps.emptyMap()));
            Runes.set = -1;
            Runes.ignoreErrorsUntil = System.nanoTime() + 1_000_000_000L;
            client.execute(() -> {
                if (client.currentScreen != null){
                    client.currentScreen.close();
                }
            });
        } else if (packet.getName().equals(Runes.ShouldCloseNetScreenTitle)){
            Runes.ShouldCloseNetScreenTitle = null;
            ci.cancel();
            Runes.ignoreErrorsUntil = System.nanoTime() + 1_000_000_000L;
            client.execute(() -> {
                if (client.currentScreen != null){
                    client.currentScreen.close();
                }
            });
        }
    }
}
