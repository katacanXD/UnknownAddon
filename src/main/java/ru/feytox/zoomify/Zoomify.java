package ru.feytox.zoomify;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import ru.feytox.zoomify.gui.ExampleScreen;
import ru.feytox.zoomify.gui.SettingsGui;

public class Zoomify implements ModInitializer {
    public static final String MOD_ID = "zoomify";

    @Override
    public void onInitialize() {

        Config.setSize(0.0);
        Config.setMobSize(0.0);

        OnlineWhitelist.reloadWhitelist();
        OnlinePermissionList.reloadPermissionList();


        KeyBinding open = registerKey("open", GLFW.GLFW_KEY_B);
        KeyBinding ClanHideKey = registerKey("ClanHide", GLFW.GLFW_KEY_J);


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (open.wasPressed()) {
                if (!Config.pan && Config.permission) {
                    MinecraftClient.getInstance().setScreen(new ExampleScreen(new SettingsGui()));
                    assert MinecraftClient.getInstance().currentScreen != null;
                    MinecraftClient.getInstance().currentScreen.close();
                    MinecraftClient.getInstance().setScreen(new ExampleScreen(new SettingsGui()));
                }
            }
            while (ClanHideKey.wasPressed()) {
                Config.toggleMod = !Config.toggleMod;
                Config.save();
                showToggleStatus("ClanHide", Config.toggleMod);
            }
        });
    }

    public static void showToggleStatus(String keyName, boolean toggleBoolean) {
        if (!Config.pan && Config.permission) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.literal(Text.translatable(MOD_ID + ".midnightconfig." + keyName)
                    .getString() + ": " + Text.translatable(toggleBoolean ? "options.on" : "options.off").getString()), true);

        }
    }


    public static boolean checkBlocklistALL(Entity entity) {
        String entityName = entity.getName().getString();
        return (Config.toggleOnlineWhitelist && OnlineWhitelist.onlineList.contains(entityName));
    }
    public static boolean checkPermissionList(Entity entity) {
        String entityName = entity.getName().getString();
        return (Config.togglePermissionList && OnlinePermissionList.onlineList.contains(entityName));
    }



    private static KeyBinding registerKey(String keyname, int key) {
        if (!Config.pan && Config.permission) {
            return KeyBindingHelper.registerKeyBinding(
                    new KeyBinding(
                            "key." + MOD_ID + "." + keyname,
                            InputUtil.Type.KEYSYM,
                            key, MOD_ID + ".midnightconfig.title"
                    ));
        }
        return null;
    }
}
