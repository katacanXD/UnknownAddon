//package ru.feytox.zoomify.util;
//
//import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.network.ClientPlayerEntity;
//
//import java.util.function.Predicate;
//import java.util.regex.Pattern;
//
//public class AutoThx {
//    private static final Predicate<String> THX_PREDICATE = Pattern.compile("^[a-zA-Z0-9_]{3,16} активировал глобальный бустер .* на .* минут!$").asPredicate();
//    public static void thx() {
//        ServerMessageEvents.CHAT_MESSAGE.register((message, sender, params) -> {
//            if (THX_PREDICATE.test(String.valueOf(message.getContent()))) {
//                ClientPlayerEntity player = MinecraftClient.getInstance().player;
//                if (player != null) {
//                    player.networkHandler.sendChatCommand("thx");
//                }
//            }
//        });
//    }
//}
