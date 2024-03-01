package ru.feytox.zoomify.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;


public class Runes {
    public static int set = -1;
    public static Text ShouldCloseNetScreenTitle;
    public static long ignoreErrorsUntil = System.nanoTime();

    public static void runesChange(int set) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        MinecraftClient mc = MinecraftClient.getInstance();

        assert mc.player != null;
        player.networkHandler.sendChatCommand("runesbag");
        Runes.set = set;
    }
}
