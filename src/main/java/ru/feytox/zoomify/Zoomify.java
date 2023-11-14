package ru.feytox.zoomify;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import ru.feytox.zoomify.commands.Command;

public class Zoomify implements ModInitializer {
    public static final String MOD_ID = "zoomify";

    @Override
    public void onInitialize() {
        Command.init();
        
        OnlineWhitelist.reloadWhitelist();

        KeyBinding toggleFlagKey = registerKey("noblidness", GLFW.GLFW_KEY_H);
        KeyBinding toggleModKey = registerKey("clanhide", GLFW.GLFW_KEY_J);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(toggleModKey.wasPressed()) {
                Config.toggleMod = !Config.toggleMod;
                Config.save();
                showToggleStatus("clanhide", Config.toggleMod);
            }
            while(toggleFlagKey.wasPressed()) {
                Config.flag = !Config.flag;
                Config.save();
                showToggleStatus("noblidness", Config.flag);
            }
        });
    }

    public static void showToggleStatus(String keyName, boolean toggleBoolean) {
        if (!Config.pan){
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.literal(Text.translatable(MOD_ID + ".midnightconfig." + keyName)
                .getString() + ": " + Text.translatable(toggleBoolean? "options.on":"options.off").getString()), true);

    }}

    public static boolean checkBlocklistALL(Entity entity) {
        String entityName = entity.getName().getString();
        return (Config.blocklist.contains(entity.getName().getString()) && Config.blockingType.equals(Config.BlockingType.ALL))
        || (Config.toggleOnlineWhitelist && OnlineWhitelist.onlineList.contains(entityName));
    }

    private static KeyBinding registerKey(String keyname, int key) {
        return KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + MOD_ID + "." + keyname,
                        InputUtil.Type.KEYSYM,
                        key, MOD_ID + ".midnightconfig.title"
                ));
    }
}
