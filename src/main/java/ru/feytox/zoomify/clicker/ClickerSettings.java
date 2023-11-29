package ru.feytox.zoomify.clicker;

import net.minecraft.client.MinecraftClient;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ClickerSettings {
    public static final Path CONFIG_DIRECTORY = Paths.get(MinecraftClient.getInstance().runDirectory.getPath() + "/evo-plus");
    public static final Path CONFIG_FILE = Paths.get(CONFIG_DIRECTORY + "/megacfg.json");
    public static Properties properties = new Properties();
    private boolean lActive;
    private boolean rActive;
    private int lDelayInt;
    private int rDelayInt;
    private boolean lHoldMode;
    private boolean rHoldMode;
    private boolean lEntityMode;
    private boolean rEntityMode;
    private boolean lCooldownAttackMode;

    private boolean flag;
    private boolean HideH;
    private boolean HideMobH;
    private boolean OnlySmallMob;

    private double size;
    private double mobSize;

    public ClickerSettings() {
    }
    public void set(boolean lActive, boolean rActive, int lClickDelayInt, int rClickDelayInt, boolean lHoldMode,
                    boolean rHoldMode, boolean lTargetEntityMode, boolean rTargetEntityMode, boolean lCooldownAttackMode,
                    double size, double mobSize, boolean flag, boolean HideH, boolean HideMobH, boolean OnlySmallMob) {
        this.lActive = lActive;
        this.rActive = rActive;
        this.lDelayInt = lClickDelayInt;
        this.rDelayInt = rClickDelayInt;
        this.lHoldMode = lHoldMode;
        this.rHoldMode = rHoldMode;
        this.lEntityMode = lTargetEntityMode;
        this.rEntityMode = rTargetEntityMode;
        this.lCooldownAttackMode = lCooldownAttackMode;
        this.size = size;
        this.mobSize = mobSize;
        this.flag = flag;
        this.HideH = HideH;
        this.HideMobH = HideMobH;
        this.OnlySmallMob = OnlySmallMob;
        try (FileWriter writer = new FileWriter(CONFIG_FILE.toFile())) {
            writer.write("lActive = " + this.lActive + "\n");
            writer.write("rActive = " + this.rActive + "\n");
            writer.write("lDelayInt = " + this.lDelayInt + "\n");
            writer.write("rDelayInt = " + this.rDelayInt + "\n");
            writer.write("lHoldMode = " + this.lHoldMode + "\n");
            writer.write("rHoldMode = " + this.rHoldMode + "\n");
            writer.write("lEntityMode = " + this.lEntityMode + "\n");
            writer.write("rEntityMode = " + this.rEntityMode + "\n");
            writer.write("lCooldownMode = " + this.lCooldownAttackMode + "\n");
            writer.write("size = " + this.size + "\n");
            writer.write("mobSize = " + this.mobSize + "\n");
            writer.write("flag = " + this.flag + "\n");
            writer.write("HideH = " + this.HideH + "\n");
            writer.write("HideMobH = " + this.HideMobH + "\n");
            writer.write("OnlySmallMob = " + this.OnlySmallMob + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load() {
        if (!Files.exists(CONFIG_FILE)) create();
        try {
            properties.load(new FileInputStream(CONFIG_FILE.toFile()));
            this.lActive = Boolean.parseBoolean(properties.getProperty("lActive"));
            this.rActive = Boolean.parseBoolean(properties.getProperty("rActive"));
            this.lDelayInt = Integer.parseInt(properties.getProperty("lDelayInt"));
            this.rDelayInt = Integer.parseInt(properties.getProperty("rDelayInt"));
            this.lHoldMode = Boolean.parseBoolean(properties.getProperty("lHoldMode"));
            this.rHoldMode = Boolean.parseBoolean(properties.getProperty("rHoldMode"));
            this.lEntityMode = Boolean.parseBoolean(properties.getProperty("lEntityMode"));
            this.rEntityMode = Boolean.parseBoolean(properties.getProperty("rEntityMode"));
            this.lCooldownAttackMode = Boolean.parseBoolean(properties.getProperty("lCooldownMode"));
            this.size = Double.parseDouble(properties.getProperty("size"));
            this.mobSize = Double.parseDouble(properties.getProperty("mobSize"));
            this.flag = Boolean.parseBoolean(properties.getProperty("flag"));
            this.HideH = Boolean.parseBoolean(properties.getProperty("HideH"));
            this.HideMobH = Boolean.parseBoolean(properties.getProperty("HideMobH"));
            this.OnlySmallMob = Boolean.parseBoolean(properties.getProperty("OnlySmallMob"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cContriller.lActive = this.lActive;
        cContriller.rActive = this.rActive;
        cContriller.lClickDelayInt = this.lDelayInt;
        cContriller.rClickDelayInt = this.rDelayInt;
        cContriller.lHoldMode = this.lHoldMode;
        cContriller.rHoldMode = this.rHoldMode;
        cContriller.lTargetEntityMode = this.lEntityMode;
        cContriller.rTargetEntityMode = this.rEntityMode;
        cContriller.lCooldownAttackMode = this.lCooldownAttackMode;
        cContriller.size = this.size;
        cContriller.mobSize = this.mobSize;
        cContriller.flag = this.flag;
        cContriller.HideH = this.HideH;
        cContriller.HideMobH = this.HideMobH;
        cContriller.OnlySmallMob = this.OnlySmallMob;
    }
    private void create() {
        try {
            Files.createDirectories(CONFIG_DIRECTORY);
            Files.createFile(CONFIG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        set(false, false, 0, 0, false, false, false, false, false, 0.0, 0.0, true, false, false, false);
    }
}
