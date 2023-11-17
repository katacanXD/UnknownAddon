package ru.feytox.zoomify;

import eu.midnightdust.lib.config.MidnightConfig;

import java.util.ArrayList;
import java.util.List;

public class Config extends MidnightConfig {

    public static boolean pan =  false;
    public static boolean permission =  true;

    public static boolean toggleMod = false;

    public static BlockingType blockingType = BlockingType.ALL;

    public static List<String> blocklist = new ArrayList<>();

    public enum BlockingType {
        ALL
    }

    public static boolean toggleOnlineWhitelist = true;
    public static boolean togglePermissionList = true;

    public static String onlinePermissionListUrl = "https://raw.githubusercontent.com/katacanXD/helios-whitelist/main/PermissionList.html";
    public static String onlineWhitelistUrl = "https://raw.githubusercontent.com/katacanXD/helios-whitelist/main/index.html";

    public static void save() {
        Config.write(Zoomify.MOD_ID);
    }

    public static boolean flag = false;

    public static boolean toggleHitbox = false;
    public static boolean HideHits = false;
    private static double size;
    public static double getSize() {return Math.abs(size);}
    public static void setSize(double size) {Config.size = size;}


    public static boolean toggleMobHitbox = false;
    public static boolean HideMobHits = false;
    public static boolean OnlySmallMob = false;
    private static double mobSize;
    public static double getMobSize() {return Math.abs(mobSize);}
    public static void setMobSize(double mobSize) {Config.mobSize = mobSize;}
}
