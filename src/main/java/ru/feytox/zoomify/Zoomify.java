package ru.feytox.zoomify;

import com.google.common.collect.Lists;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.lwjgl.glfw.GLFW;
import ru.feytox.zoomify.c.cControls;
import ru.feytox.zoomify.gui.ExampleScreen;
import ru.feytox.zoomify.gui.SettingsGui;
import ru.feytox.zoomify.list.OnlineEnemyList;
import ru.feytox.zoomify.list.OnlinePermissionList;
import ru.feytox.zoomify.list.OnlineWhitelist;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.feytox.zoomify.list.OnlinePermissionList.isValidName;
import static ru.feytox.zoomify.test.load;


public class Zoomify implements ModInitializer {



    public static final String MOD_ID = "zoomify";

    public static boolean checkBlocklistALL(Entity entity) {
        String entityName = entity.getName().getString();
        return (OnlineWhitelist.isValidName(entityName));
    }

    public static boolean checkPermissionList(Entity entity) {
        String entityName = entity.getName().getString();
        return (isValidName(entityName));
    }

    public static boolean checkEnemyList(Entity entity) {
        String entityName = entity.getName().getString();
        return (OnlineEnemyList.isValidName(entityName));
    }

    private static KeyBinding registerKey(String keyname, int key) {
        if (!test.pan) {
            return KeyBindingHelper.registerKeyBinding(
                    new KeyBinding(
                            "key." + MOD_ID + "." + keyname,
                            InputUtil.Type.KEYSYM,
                            key, MOD_ID + ".midnightconfig.title"
                    ));
        }
        return null;
    }


    private static String dohast;

    private final List<Integer> ENCODED_COLOR_ALTERNATIVES = Arrays.asList(
            0, 170, 43520, 43690, 11141120, 11141290, 16755200, 11184810, 5592405, 5592575, 16733695
    );

    public String encodeColoredText(Text text) {
        TextColor color = text.getStyle().getColor();
        List<String> buffer = Lists.newArrayList(color == null ? "" : String.valueOf(ENCODED_COLOR_ALTERNATIVES.indexOf(color.getRgb())));

        for (Text sibling : text.getSiblings()) {
            color = sibling.getStyle().getColor();
            if (color == null || !ENCODED_COLOR_ALTERNATIVES.contains(color.getRgb())) continue;

            buffer.add(ENCODED_COLOR_ALTERNATIVES.indexOf(color.getRgb()) > 9 ? " " : ENCODED_COLOR_ALTERNATIVES.indexOf(color.getRgb()) + "");
        }

        buffer = String.join("", buffer).isEmpty()
                ? Arrays.asList(text.getString().replaceAll("[ §]", "").replaceAll("d", " "))
                : Arrays.asList(String.join("", buffer).replaceAll("-1", "").split(" "));

        return buffer.stream()
                .filter((num) -> !num.isEmpty())
                .map((number) -> String.valueOf((char) Integer.parseInt(number)))
                .collect(Collectors.joining());
    }

    public static void getId() throws IOException {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (!isValidName(mc.getSession().getUsername())){
//            test.permission = false;
//            mc.close();
        }
    }


    public static String currentServer = null;

    private void clientStart(MinecraftClient client) {
        load();
    }

    @Override
    public void onInitialize() {
        ClientLifecycleEvents.CLIENT_STARTED.register(this::clientStart);

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            currentServer = null;
        });

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            currentServer = null;
            handler.sendChatCommand("modinfo server");
        });
        ClientReceiveMessageEvents.ALLOW_GAME.register((message, overlay) -> {
            if (!overlay){
                String ka = encodeColoredText(message);
                System.out.println(ka);
                dohast = encodeColoredText(message);
            }
            return true;
        });

        ru.feytox.zoomify.command.Command.init();

        test.lClickDelayInt = 10;

        test.setSize(0.0);
        test.setMobSize(0.0);

        OnlineWhitelist.loadValidator("https://raw.githubusercontent.com/katacanXD/helios-whitelist/main/whitelist");
        OnlinePermissionList.loadValidator("https://raw.githubusercontent.com/katacanXD/helios-whitelist/main/permission");
        OnlineEnemyList.loadValidator("https://raw.githubusercontent.com/katacanXD/helios-whitelist/main/enemies");


        KeyBinding open = registerKey("open", GLFW.GLFW_KEY_B);


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            cControls.Update();
            test.save();
            if (client.getOverlay() == null) {
                try {
                    getId();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            while (open.wasPressed()) {
                if (!test.pan && test.permission) {
                    MinecraftClient.getInstance().setScreen(new ExampleScreen(new SettingsGui()));
                    assert MinecraftClient.getInstance().currentScreen != null;
                    MinecraftClient.getInstance().currentScreen.close();
                    MinecraftClient.getInstance().setScreen(new ExampleScreen(new SettingsGui()));

                }
            }
        });
    }
}
