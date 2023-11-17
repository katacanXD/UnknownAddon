package ru.feytox.zoomify.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ru.feytox.zoomify.Config;
import ru.feytox.zoomify.Zoomify;

import static ru.feytox.zoomify.OnlineWhitelist.reloadWhitelist;
import static ru.feytox.zoomify.Zoomify.showToggleStatus;

public class SettingsGui extends LightweightGuiDescription {
    public static final Identifier SUPER_OFF_IMAGE = new Identifier(Zoomify.MOD_ID, "textures/image_off.png");
    public static final Identifier SUPER_ON_IMAGE = new Identifier(Zoomify.MOD_ID, "textures/image_on.png");

    @Override
    public TriState isDarkMode() {
        return TriState.TRUE;
    }

    public SettingsGui() {

        WPlainPanel root = new WPlainPanel();
        root.setBackgroundPainter(BackgroundPainter.VANILLA);

        WToggleButton clanHide = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("ClanHide"));
        clanHide.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.toggleMod) {
            clanHide.setToggle(true);
        }
        clanHide.setOnToggle(on -> {
            Config.toggleMod = !Config.toggleMod;
//            reloadWhitelist();
            showToggleStatus("ClanHide", Config.toggleMod);
        });
        root.add(clanHide, 10, 10);


        WToggleButton NoBlindness = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("NoBlindness"));
        NoBlindness.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.flag) {
            NoBlindness.setToggle(true);
        }
        NoBlindness.setOnToggle(on -> {
            Config.flag = !Config.flag;
            showToggleStatus("NoBlindness", Config.flag);
        });
        root.add(NoBlindness, 10, 25);

        WDynamicLabel label = new WDynamicLabel(() -> I18n.translate("text.size", Config.getSize()));
        label.setColor(0xc3c3c3,0xc3c3c3);
        root.add(label, 210, 30, 6, 1);


        WToggleButton HitboxSizeText = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Player HitBox"));
        HitboxSizeText.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.toggleHitbox) {
            HitboxSizeText.setToggle(true);
        }
        HitboxSizeText.setOnToggle(on -> {
            Config.toggleHitbox = !Config.toggleHitbox;
        });
        root.add(HitboxSizeText, 100, 10);

        int sizeBox = (int) (Config.getSize() * 100);

        WSlider HitboxSize = new WSlider(0, 50, Axis.HORIZONTAL);
        HitboxSize.setValue(sizeBox);
        root.add(HitboxSize, 100, 30, 100, 10);


        WToggleButton HideHits = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Hide Player HitBox"));
        HideHits.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.HideHits) {
            HideHits.setToggle(true);
        }
        HideHits.setOnToggle(on -> {
            Config.HideHits = !Config.HideHits;
        });
        root.add(HideHits, 100, 40);



        WToggleButton MobHitboxSizeText = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Mob HitBox"));
        MobHitboxSizeText.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.toggleMobHitbox) {
            MobHitboxSizeText.setToggle(true);
        }
        MobHitboxSizeText.setOnToggle(on -> {
            Config.toggleMobHitbox = !Config.toggleMobHitbox;
        });
        root.add(MobHitboxSizeText, 100, 70);

        int sizeMobBox = (int) (Config.getMobSize() * 100);

        WSlider MobHitboxSize = new WSlider(0, 50, Axis.HORIZONTAL);
        MobHitboxSize.setValue(sizeMobBox);
        root.add(MobHitboxSize, 100, 90, 100, 10);

        WDynamicLabel MobHitSize = new WDynamicLabel(() -> I18n.translate("text.size", Config.getMobSize()));
        MobHitSize.setColor(0xc3c3c3,0xc3c3c3);
        root.add(MobHitSize, 210, 90, 6, 1);


        WToggleButton HideMobHits = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Hide Mob HitBox"));
        HideMobHits.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.HideMobHits) {
            HideMobHits.setToggle(true);
        }
        HideMobHits.setOnToggle(on -> {
            Config.HideMobHits = !Config.HideMobHits;
        });
        root.add(HideMobHits, 100, 100);


        WToggleButton OnlySmallMobs = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Only Small Mobs"));
        OnlySmallMobs.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.OnlySmallMob) {
            OnlySmallMobs.setToggle(true);
        }
        OnlySmallMobs.setOnToggle(on -> {
            Config.OnlySmallMob = !Config.OnlySmallMob;
        });
        root.add(OnlySmallMobs, 100, 115);

        ClientTickEvents.END_CLIENT_TICK.register(client -> Config.setSize((HitboxSize.getValue()/100.0)));
        ClientTickEvents.END_CLIENT_TICK.register(client -> Config.setMobSize((MobHitboxSize.getValue()/100.0)));


        WPlainPanel back = new WPlainPanel();
        setRootPanel(back);
        back.setSize(ExampleScreen.screenWidth, ExampleScreen.screenHeight);

        WButton panic = new WButton();
        WText panicText = new WText(Text.literal("Panic"));
        panicText.setColor(0xf10e3e,0xf10e3e);

        back.add(root, (ExampleScreen.screenWidth/2-135), (ExampleScreen.screenHeight/2-73), 270, 146);
        back.add(panic, ExampleScreen.screenWidth-60, ExampleScreen.screenHeight-30, 50, 20);
        back.add(panicText, ExampleScreen.screenWidth-48, ExampleScreen.screenHeight-24, 50, 0);

        panic.setOnClick(() -> {
            WPlainPanel donePanic = new WPlainPanel();
            donePanic.setBackgroundPainter(BackgroundPainter.VANILLA);
            back.remove(root);
            back.remove(panic);
            back.remove(panicText);
            back.add(donePanic, (ExampleScreen.screenWidth/2-50), (ExampleScreen.screenHeight/2-35), 100, 50);
            WText panicQ = new WText(Text.literal("Panic?"));
            panicQ.setColor(0xc3c3c3,0xc3c3c3);
            WButton done = new WButton(Text.literal("Yes"));
            WButton exit = new WButton(Text.literal("No"));
            donePanic.add(panicQ, 35, 10, 35, 0);
            donePanic.add(done, 10, 25, 35, 20);
            donePanic.add(exit, 55, 25, 35, 20);
            done.setOnClick(() -> {
                Config.pan = !Config.pan;
                if (Config.pan){
                    assert MinecraftClient.getInstance().currentScreen != null;
                    MinecraftClient.getInstance().currentScreen.close();
                }
            });
            exit.setOnClick(() -> {
                back.remove(donePanic);
                back.add(root, (ExampleScreen.screenWidth/2-135), (ExampleScreen.screenHeight/2-73), 270, 146);
                back.add(panic, ExampleScreen.screenWidth-60, ExampleScreen.screenHeight-30, 50, 20);
                back.add(panicText, ExampleScreen.screenWidth-48, ExampleScreen.screenHeight-24, 50, 0);
            });
        });
    }
}
