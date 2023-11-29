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
import ru.feytox.zoomify.clicker.cContriller;

public class SettingsGui extends LightweightGuiDescription {
    public static final Identifier SUPER_OFF_IMAGE = new Identifier(Zoomify.MOD_ID, "textures/image_off.png");
    public static final Identifier SUPER_ON_IMAGE = new Identifier(Zoomify.MOD_ID, "textures/image_on.png");

    @Override
    public TriState isDarkMode() {
        return TriState.TRUE;
    }

    public boolean hits = false;
    public boolean click = false;
    public boolean glow = false;

    public SettingsGui() {
        WPlainPanel donePanic = new WPlainPanel();
        WPlainPanel root = new WPlainPanel();
        WPlainPanel hitBoxSettings = new WPlainPanel();
        WPlainPanel GlowSettings = new WPlainPanel();
        WPlainPanel ClickerSettings = new WPlainPanel();
        WPlainPanel back = new WPlainPanel();
        setRootPanel(back);

        back.setSize(ExampleScreen.screenWidth, ExampleScreen.screenHeight);
        back.add(hitBoxSettings, (ExampleScreen.screenWidth/2-25), (ExampleScreen.screenHeight/2-73), 165, 146);
        back.add(GlowSettings, (ExampleScreen.screenWidth/2-25), (ExampleScreen.screenHeight/2-73), 105, 146);
        back.add(ClickerSettings, (ExampleScreen.screenWidth/2-25), (ExampleScreen.screenHeight/2-73), 250, 146);

        hitBoxSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
        GlowSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
        ClickerSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));

        root.setBackgroundPainter(BackgroundPainter.VANILLA);
        donePanic.setBackgroundPainter(BackgroundPainter.VANILLA);
        hitBoxSettings.setBackgroundPainter(BackgroundPainter.VANILLA);
        GlowSettings.setBackgroundPainter(BackgroundPainter.VANILLA);
        ClickerSettings.setBackgroundPainter(BackgroundPainter.VANILLA);


        back.add(donePanic, (ExampleScreen.screenWidth), (ExampleScreen.screenHeight), 100, 50);
        back.add(root, (ExampleScreen.screenWidth/2-135), (ExampleScreen.screenHeight/2-73), 106, 146);


        //
        //
        // HitBox
        //
        //
        WDynamicLabel hitSize = new WDynamicLabel(() -> I18n.translate("text.size", cContriller.getSize()));
        hitSize.setColor(0xc3c3c3,0xc3c3c3);

        WToggleButton HitboxSizeText = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Player HitBox"));
        HitboxSizeText.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.toggleH) {
            HitboxSizeText.setToggle(true);
        }
        HitboxSizeText.setOnToggle(on -> {
            Config.toggleH = !Config.toggleH;
        });

        int sizeBox = (int) (cContriller.getSize() * 100);

        WSlider HitboxSize = new WSlider(0, 25, Axis.HORIZONTAL);
        HitboxSize.setValue(sizeBox);


        WToggleButton HideHits = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Hide Player HitBox"));
        HideHits.setColor(0xc3c3c3,0xc3c3c3);
        if (cContriller.HideH) {
            HideHits.setToggle(true);
        }
        HideHits.setOnToggle(on -> {
            cContriller.HideH = !cContriller.HideH;
        });


        WToggleButton MobHitboxSizeText = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Mob HitBox"));
        MobHitboxSizeText.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.toggleMobH) {
            MobHitboxSizeText.setToggle(true);
        }
        MobHitboxSizeText.setOnToggle(on -> {
            Config.toggleMobH = !Config.toggleMobH;
        });

        int sizeMobBox = (int) (cContriller.getMobSize() * 100);

        WSlider MobHitboxSize = new WSlider(0, 50, Axis.HORIZONTAL);
        MobHitboxSize.setValue(sizeMobBox);

        WDynamicLabel MobHitSize = new WDynamicLabel(() -> I18n.translate("text.size", cContriller.getMobSize()));
        MobHitSize.setColor(0xc3c3c3,0xc3c3c3);


        WToggleButton HideMobHits = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Hide Mob HitBox"));
        HideMobHits.setColor(0xc3c3c3,0xc3c3c3);
        if (cContriller.HideMobH) {
            HideMobHits.setToggle(true);
        }
        HideMobHits.setOnToggle(on -> {
            cContriller.HideMobH = !cContriller.HideMobH;
        });


        WToggleButton OnlySmallMobs = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Only Small Mobs"));
        OnlySmallMobs.setColor(0xc3c3c3,0xc3c3c3);
        if (cContriller.OnlySmallMob) {
            OnlySmallMobs.setToggle(true);
        }
        OnlySmallMobs.setOnToggle(on -> {
            cContriller.OnlySmallMob = !cContriller.OnlySmallMob;
        });


        hitBoxSettings.add(hitSize, 115, 30, 6, 1);
        hitBoxSettings.add(HitboxSizeText, 10, 10);
        hitBoxSettings.add(HitboxSize, 10, 30, 100, 10);
        hitBoxSettings.add(HideHits, 10, 40);
        hitBoxSettings.add(MobHitboxSizeText, 10, 70);
        hitBoxSettings.add(MobHitboxSize, 10, 90, 100, 10);
        hitBoxSettings.add(MobHitSize, 115, 90, 6, 1);
        hitBoxSettings.add(HideMobHits, 10, 100);
        hitBoxSettings.add(OnlySmallMobs, 10, 115);

        ClientTickEvents.END_CLIENT_TICK.register(client -> cContriller.setSize((HitboxSize.getValue()/100.0)));
        ClientTickEvents.END_CLIENT_TICK.register(client -> cContriller.setMobSize((MobHitboxSize.getValue()/100.0)));


        //
        //
        //Glow
        //
        //
        WToggleButton PlayerGlow = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Player Light"));
        PlayerGlow.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.PlayerGlow) {
            PlayerGlow.setToggle(true);
        }
        PlayerGlow.setOnToggle(on -> {
            Config.PlayerGlow = !Config.PlayerGlow;
        });

        WToggleButton ClanGlow = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Clan Light"));
        ClanGlow.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.ClanGlow) {
            ClanGlow.setToggle(true);
        }
        ClanGlow.setOnToggle(on -> {
            Config.ClanGlow = !Config.ClanGlow;
        });

        WToggleButton EnemyGlow = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Enemy Light"));
        EnemyGlow.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.EnemyGlow) {
            EnemyGlow.setToggle(true);
        }
        EnemyGlow.setOnToggle(on -> {
            Config.EnemyGlow = !Config.EnemyGlow;
        });

        WToggleButton SpiderGlow = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Spider Light"));
        SpiderGlow.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.SpiderGlow) {
            SpiderGlow.setToggle(true);
        }
        SpiderGlow.setOnToggle(on -> {
            Config.SpiderGlow = !Config.SpiderGlow;
        });

        GlowSettings.add(PlayerGlow, 10, 10);
        GlowSettings.add(ClanGlow, 10, 25);
        GlowSettings.add(EnemyGlow, 10, 40);
        GlowSettings.add(SpiderGlow, 10, 55);


        //
        //
        // Clicker
        //
        //
        WToggleButton ToggleLeftClick = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Toggle Left Click"));
        ToggleLeftClick.setColor(0xc3c3c3,0xc3c3c3);
        if (cContriller.lActive) {
            ToggleLeftClick.setToggle(true);
        }
        ToggleLeftClick.setOnToggle(on -> {cContriller.lActive = !cContriller.lActive;});

        WTextField lClickDelay = new WTextField(Text.literal(Integer.toString(cContriller.getLClickDelayInt())));

        WToggleButton lHoldMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Hold Mod"));
        lHoldMode.setColor(0xc3c3c3,0xc3c3c3);
        if (cContriller.lHoldMode) {
            lHoldMode.setToggle(true);
        }
        lHoldMode.setOnToggle(on -> {cContriller.lHoldMode = !cContriller.lHoldMode;});

        WToggleButton lTargetEntityMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Target Mod"));
        lTargetEntityMode.setColor(0xc3c3c3,0xc3c3c3);
        if (cContriller.lTargetEntityMode) {
            lTargetEntityMode.setToggle(true);
        }
        lTargetEntityMode.setOnToggle(on -> {cContriller.lTargetEntityMode = !cContriller.lTargetEntityMode;});

        WToggleButton lCooldownAttackMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Cooldown Mod"));
        lCooldownAttackMode.setColor(0xc3c3c3,0xc3c3c3);
        if (cContriller.lCooldownAttackMode) {
            lCooldownAttackMode.setToggle(true);
        }
        lCooldownAttackMode.setOnToggle(on -> {cContriller.lCooldownAttackMode = !cContriller.lCooldownAttackMode;});


        WToggleButton ToggleRightClick = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Toggle Right Click"));
        ToggleRightClick.setColor(0xc3c3c3,0xc3c3c3);
        if (cContriller.rActive) {
            ToggleRightClick.setToggle(true);
        }
        ToggleRightClick.setOnToggle(on -> {cContriller.rActive = !cContriller.rActive;});

        WTextField rClickDelay = new WTextField(Text.literal(Integer.toString(cContriller.getRClickDelayInt())));


        WToggleButton rHoldMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Hold Mod"));
        rHoldMode.setColor(0xc3c3c3,0xc3c3c3);
        if (cContriller.rHoldMode) {
            rHoldMode.setToggle(true);
        }
        rHoldMode.setOnToggle(on -> {cContriller.rHoldMode = !cContriller.rHoldMode;});

        WToggleButton rTargetEntityMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Target Mod"));
        rTargetEntityMode.setColor(0xc3c3c3,0xc3c3c3);
        if (cContriller.rTargetEntityMode) {
            rTargetEntityMode.setToggle(true);
        }
        rTargetEntityMode.setOnToggle(on -> {cContriller.rTargetEntityMode = !cContriller.rTargetEntityMode;});



        WButton OK = new WButton(Text.literal("Ok"));
        OK.setOnClick(() -> {
            if (!lClickDelay.getText().equals("")) {
                cContriller.lClickDelayInt = convertStringToInt(lClickDelay.getText());
            }
        });
        WButton OKr = new WButton(Text.literal("Ok"));
        OKr.setOnClick(() -> {
            if (!rClickDelay.getText().equals("")) {
                cContriller.rClickDelayInt = convertStringToInt(rClickDelay.getText());
            }
        });

        WText command = new WText(Text.literal("Включить кликер - /h click"));
        command.setColor(0x828282,0x828282);

        ClickerSettings.add(ToggleLeftClick, 10, 10);
        ClickerSettings.add(lClickDelay, 10, 30, 70, 10);
        ClickerSettings.add(OK, 83, 30, 20, 20);
        ClickerSettings.add(lHoldMode, 10, 50);
        ClickerSettings.add(lTargetEntityMode, 10, 65);
        ClickerSettings.add(lCooldownAttackMode, 10, 80);

        ClickerSettings.add(ToggleRightClick, 130, 10);
        ClickerSettings.add(rClickDelay, 130, 30, 70, 10);
        ClickerSettings.add(OKr, 203, 30, 20, 20);
        ClickerSettings.add(rHoldMode, 130, 50);
        ClickerSettings.add(rTargetEntityMode, 130, 65);

        ClickerSettings.add(command, 43, 127, 200, 1);

        //
        //
        // Menu
        //
        //
        WToggleButton clanHide = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("ClanHide"));
        clanHide.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.clanHideToggle) {
            clanHide.setToggle(true);
        }
        clanHide.setOnToggle(on -> {Config.clanHideToggle = !Config.clanHideToggle;});
        root.add(clanHide, 10, 77);


        WToggleButton NoBlindness = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("NoBlindness"));
        NoBlindness.setColor(0xc3c3c3,0xc3c3c3);
        if (cContriller.flag) {
            NoBlindness.setToggle(true);
        }
        NoBlindness.setOnToggle(on -> {cContriller.flag = !cContriller.flag;});
        root.add(NoBlindness, 10, 92);

        WToggleButton HideAll = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("HideAll"));
        HideAll.setColor(0xc3c3c3,0xc3c3c3);
        if (Config.HideToggle) {
            HideAll.setToggle(true);
        }
        HideAll.setOnToggle(on -> {Config.HideToggle = !Config.HideToggle;});
        root.add(HideAll, 10, 107);


        WButton hitbox = new WButton();
        WText hitboxText = new WText(Text.literal("HitBox"));
        hitboxText.setColor(0xc3c3c3,0xc3c3c3);
        root.add(hitbox, 4, 15, 96, 20);
        root.add(hitboxText, 36, 21, 60, 0);

        WButton Glow = new WButton();
        WText glowText = new WText(Text.literal("Light"));
        glowText.setColor(0xc3c3c3,0xc3c3c3);
        root.add(Glow, 4, 36, 96, 20);
        root.add(glowText, 40, 42, 60, 0);

        WButton clicker = new WButton();
        WText clickerText = new WText(Text.literal("Clicker"));
        clickerText.setColor(0xc3c3c3,0xc3c3c3);
        root.add(clicker, 4, 57, 96, 20);
        root.add(clickerText, 35, 63, 60, 0);


        hitbox.setOnClick(() -> {
            if (!hits){
                hits = true;
                click = false;
                glow = false;
                hitBoxSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));
                GlowSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
                ClickerSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
            }
        });
        Glow.setOnClick(() -> {
            if (!glow){
                glow = true;
                click = false;
                hits = false;
                hitBoxSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
                GlowSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));
                ClickerSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
            }
        });
        clicker.setOnClick(() -> {
            if (!click) {
                click = true;
                hits = false;
                glow = false;
                hitBoxSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
                GlowSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
                ClickerSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));
            }
        });


        //
        //
        // Panic
        //
        //
        WButton panic = new WButton();
        WText panicText = new WText(Text.literal("Panic"));
        panicText.setColor(0xf10e3e,0xf10e3e);

        back.add(panic, ExampleScreen.screenWidth-60, ExampleScreen.screenHeight-30, 50, 20);
        back.add(panicText, ExampleScreen.screenWidth-48, ExampleScreen.screenHeight-24, 50, 0);

        WButton done = new WButton(Text.literal("Yes"));
        WButton exit = new WButton(Text.literal("No"));

        donePanic.add(done, 10, 25, 35, 20);
        donePanic.add(exit, 55, 25, 35, 20);


        panic.setOnClick(() -> {
            donePanic.setLocation((ExampleScreen.screenWidth/2-50), (ExampleScreen.screenHeight/2-35));
            back.remove(root);
            hitBoxSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
            GlowSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
            ClickerSettings.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
            back.remove(panic);
            back.remove(panicText);
            WText panicQ = new WText(Text.literal("Panic?"));
            panicQ.setColor(0xc3c3c3,0xc3c3c3);
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
                if (hits) {hitBoxSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));}
                else if (glow) {GlowSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));}
                else if (click) {ClickerSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));}
                donePanic.setLocation((ExampleScreen.screenWidth), (ExampleScreen.screenHeight));
                back.add(root, (ExampleScreen.screenWidth/2-135), (ExampleScreen.screenHeight/2-73), 104, 146);
                back.add(panic, ExampleScreen.screenWidth-60, ExampleScreen.screenHeight-30, 50, 20);
                back.add(panicText, ExampleScreen.screenWidth-48, ExampleScreen.screenHeight-24, 50, 0);
            });
        });
    }

    private int convertStringToInt(String str) {
        int value = 0;
        try {
            value = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            System.out.println("Convert String To Int Exception: " + e);
        }
        return value;
    }
}
