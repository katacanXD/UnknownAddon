package ru.feytox.zoomify;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressWarnings("UnnecessaryModifier")
public class test {
    // Constants
    private static final Path CONFIG = FabricLoader.getInstance().getGameDir().resolve("evo-plus").resolve("megacfg.json");
    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.FINAL)
            .setPrettyPrinting()
            .create();

    // Runtime fields
    public static transient boolean permission = false;
    public static transient boolean pan = false;

    public static transient boolean clanHideToggle = false;
    public static transient boolean HideToggle = false;

    public static transient boolean noSwing = true;


    public static transient boolean PlayerGlow = false;
    public static transient boolean ClanGlow = false;
    public static transient boolean EnemyGlow = false;
    public static transient boolean SpiderGlow = false;


    public static transient boolean toggleH = false;
    public static transient boolean toggleMobH = false;

    public static transient boolean hits;
    public static transient boolean glow;
    public static transient boolean click;


    // Config fields
    public static int lClickDelayInt = 0;
    public static int rClickDelayInt = 0;
    public static boolean Active = false;
    public static boolean lActive = false;
    public static boolean rActive = false;
    public static boolean lHoldMode = false;
    public static boolean rHoldMode = false;
    public static boolean lTargetEntityMode = false;
    public static boolean rTargetEntityMode = false;
    public static boolean lCooldownAttackMode = false;
    public static int lTimer = 0;
    public static int rTimer = 0;

    public static boolean flag = true;
    public static boolean HideH = false;
    public static boolean HideMobH = false;
    public static boolean OnlySmallMob = false;

    public static double size = 0.0;
    public static double mobSize = 0.0;

//    public static void main(String[] args) {
//        load();
//    }

    public static void load() {
        try {
            if (!Files.isRegularFile(CONFIG)) {
                System.out.println("no config found. will use default values.");
                return;
            }
            String value = Files.readString(CONFIG);
            GSON.fromJson(value, test.class);
            System.out.println("Loaded config.");
        } catch (Throwable t) {
            System.err.println("Unable to read config.");
            t.printStackTrace();
        }
    }

    public static void save() {
        try {
            @SuppressWarnings("InstantiationOfUtilityClass")
            String value = GSON.toJson(new test());
            Files.createDirectories(CONFIG.toAbsolutePath().getParent());
            Files.writeString(CONFIG, value);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static double getSize() {
        return Math.abs(size);
    }
    public static void setSize(double size) {
        test.size = size;
    }
    public static double getMobSize() {
        return Math.abs(mobSize);
    }
    public static void setMobSize(double mobSize) {
        test.mobSize = mobSize;
    }

    public static int getLClickDelayInt(){
        return lClickDelayInt;
    }
    public static int getRClickDelayInt(){
        return rClickDelayInt;
    }
}