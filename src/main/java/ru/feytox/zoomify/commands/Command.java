package ru.feytox.zoomify.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import ru.feytox.zoomify.OnlineWhitelist;
import ru.feytox.zoomify.Config;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static ru.feytox.zoomify.Zoomify.MOD_ID;

public class Command {
    public static void init() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal("ch")
                .then(literal("reload")
                        .executes(context -> {
                            if (OnlineWhitelist.reloadWhitelist()) {
                                sendTranslatableText(MOD_ID + ".online.success");
                            } else {
                                sendTranslatableText(MOD_ID + ".online.fail");
                            }
                            return 1;
                        })
                        .then(argument("URL", StringArgumentType.greedyString())
                                .executes(context -> {
                                    String url = parseLast(context);
                                    if (OnlineWhitelist.reloadWhitelist(url)) {
                                        sendTranslatableText(MOD_ID + ".online.success");
                                    } else {
                                        sendTranslatableText(MOD_ID + ".online.fail");
                                    }

                                    return 1;
                                })))
                .requires(source -> !Config.pan)
                .then(literal("panic")
                        .executes(context -> {
                            Config.pan = true;
                            return 1;
                        }))));
    }

    private static <S> String parseLast(CommandContext<S> context) {
        String[] inputSplitted = context.getInput().split(" ");
        return inputSplitted[inputSplitted.length - 1];
    }

    private static void sendTranslatableText(String key) {
        sendMessage(Text.translatable(key));
    }

    private static void sendMessage(Text message) {
        MinecraftClient.getInstance().player.sendMessage(message, false);
    }
}
