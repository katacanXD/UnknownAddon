package ru.feytox.zoomify;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import org.lwjgl.glfw.GLFW;
import ru.feytox.zoomify.clicker.ClickerSettings;
import ru.feytox.zoomify.clicker.cContriller;
import ru.feytox.zoomify.gui.ExampleScreen;
import ru.feytox.zoomify.gui.SettingsGui;
import ru.feytox.zoomify.list.OnlineEnemyList;
import ru.feytox.zoomify.list.OnlinePermissionList;
import ru.feytox.zoomify.list.OnlineWhitelist;


public class Zoomify implements ModInitializer {



    public static final String MOD_ID = "zoomify";

    public static boolean checkBlocklistALL(Entity entity) {
        String entityName = entity.getName().getString();
        return (OnlineWhitelist.onlineList.contains(entityName));
    }

    public static boolean checkPermissionList(Entity entity) {
        String entityName = entity.getName().getString();
        return (OnlinePermissionList.onlineList.contains(entityName));
    }

    public static boolean checkEnemyList(Entity entity) {
        String entityName = entity.getName().getString();
        return (OnlineEnemyList.onlineList.contains(entityName));
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

    public static ClickerSettings config = new ClickerSettings();
    private void clientStart(MinecraftClient client) {
        config.load();
    }

    @Override
    public void onInitialize() {

        ClientLifecycleEvents.CLIENT_STARTED.register(this::clientStart);

        ru.feytox.zoomify.command.Command.init();

        cContriller.lClickDelayInt = 10;

        cContriller.setSize(0.0);
        cContriller.setMobSize(0.0);

        OnlineWhitelist.reloadWhitelist();
        OnlinePermissionList.reloadPermissionList();
        OnlineEnemyList.reloadEnemyList();


        KeyBinding open = registerKey("open", GLFW.GLFW_KEY_B);


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            cContriller.Update();
            cContriller.saveValues();
            while (open.wasPressed()) {
                if (!Config.pan && Config.permission) {
                    MinecraftClient.getInstance().setScreen(new ExampleScreen(new SettingsGui()));
                    assert MinecraftClient.getInstance().currentScreen != null;
                    MinecraftClient.getInstance().currentScreen.close();
                    MinecraftClient.getInstance().setScreen(new ExampleScreen(new SettingsGui()));
                }
            }
        });
    }
}
