package ru.feytox.zoomify.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import ru.feytox.zoomify.Config;
import ru.feytox.zoomify.clicker.cContriller;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static ru.feytox.zoomify.Zoomify.MOD_ID;

public class Command {
    public static void init() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal("h")
                .requires(source -> !Config.pan && Config.permission)
                .then(literal("click")
                        .executes(context -> {
                            cContriller.lTimer = 0;
                            cContriller.rTimer = 0;
                            cContriller.setActive(!cContriller.getActive());
                            showToggleStatus("clicker", cContriller.getActive());

                            return 1;
                        }))
                .then(literal("ch")
                        .executes(context -> {
                            Config.clanHideToggle = !Config.clanHideToggle;
                            showToggleStatus("clanhide", Config.clanHideToggle);

                            return 1;
                        }))
                .then(literal("light")
                        .executes(context -> {
                            Config.PlayerGlow = !Config.PlayerGlow;
                            showToggleStatus("playerglow", Config.PlayerGlow);

                            return 1;
                        }))
        ));
    }
    public static void showToggleStatus(String commandName, boolean toggleBoolean) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.literal(Text.translatable(MOD_ID + ".midnightconfig." + commandName)
                .getString() + ": " + Text.translatable(toggleBoolean? "options.on":"options.off").getString()), true);

    }
}