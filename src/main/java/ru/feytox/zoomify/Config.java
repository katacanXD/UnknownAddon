package ru.feytox.zoomify;

import eu.midnightdust.lib.config.MidnightConfig;

import java.util.ArrayList;
import java.util.List;

public class Config extends MidnightConfig {

    public static boolean pan =  false;

    public static boolean toggleMod = false;

    public static BlockingType blockingType = BlockingType.ALL;

    public static List<String> blocklist = new ArrayList<>();

    public enum BlockingType {
        ALL
    }

    public static boolean toggleOnlineWhitelist = true;

    public static String onlineWhitelistUrl = "https://raw.githubusercontent.com/katacanXD/helios-whitelist/main/index.html";

    public static void save() {
        Config.write(Zoomify.MOD_ID);
    }

    public static boolean flag = true;
}
