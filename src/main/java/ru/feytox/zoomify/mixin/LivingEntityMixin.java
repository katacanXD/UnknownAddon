package ru.feytox.zoomify.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.feytox.zoomify.test;

@Mixin(ClientPlayerEntity.class)
public class LivingEntityMixin extends AbstractClientPlayerEntity {
    @Shadow @Final public ClientPlayNetworkHandler networkHandler;

    @SuppressWarnings("DataFlowIssue")
    private LivingEntityMixin() {
        super(null, null);
    }

    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;)V", at = @At("HEAD"), cancellable = true)
    public void swingHand(Hand hand, CallbackInfo ci) {
        if (test.noSwing) {
            if (getMainHandStack().isIn(ItemTags.PICKAXES) || getMainHandStack().isIn(ItemTags.AXES) ||
                    getMainHandStack().isIn(ItemTags.SHOVELS) || getMainHandStack().isIn(ItemTags.HOES) ||
                    getMainHandStack().isOf(Items.SHEARS)) {
                ci.cancel();
                this.networkHandler.sendPacket(new HandSwingC2SPacket(hand));
            }
        }
    }
}
