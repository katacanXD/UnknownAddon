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
import ru.feytox.zoomify.test;
import ru.feytox.zoomify.Zoomify;
import ru.feytox.zoomify.test;

public class SettingsGui extends LightweightGuiDescription {
    public static final Identifier SUPER_OFF_IMAGE = new Identifier(Zoomify.MOD_ID, "textures/image_off.png");
    public static final Identifier SUPER_ON_IMAGE = new Identifier(Zoomify.MOD_ID, "textures/image_on.png");

    @Override
    public TriState isDarkMode() {
        return TriState.TRUE;
    }
    public SettingsGui() {


        WPlainPanel donePanic = new WPlainPanel();
        WPlainPanel root = new WPlainPanel();
        WPlainPanel hitBoxSettings = new WPlainPanel();
        WPlainPanel GlowSettings = new WPlainPanel();
        WPlainPanel ClickerSettings = new WPlainPanel();
        WPlainPanel back = new WPlainPanel();
        setRootPanel(back);

        back.setSize(ExampleScreen.screenWidth, ExampleScreen.screenHeight);
        back.add(hitBoxSettings, (ExampleScreen.screenWidth/2-25), (ExampleScreen.screenHeight/2-73), 171, 146);
        back.add(GlowSettings, (ExampleScreen.screenWidth/2-25), (ExampleScreen.screenHeight/2-73), 105, 146);
        back.add(ClickerSettings, (ExampleScreen.screenWidth/2-25), (ExampleScreen.screenHeight/2-73), 250, 146);

        hitBoxSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
        GlowSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
        ClickerSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));

        root.setBackgroundPainter(BackgroundPainter.VANILLA);
        donePanic.setBackgroundPainter(BackgroundPainter.VANILLA);
        hitBoxSettings.setBackgroundPainter(BackgroundPainter.VANILLA);
        GlowSettings.setBackgroundPainter(BackgroundPainter.VANILLA);
        ClickerSettings.setBackgroundPainter(BackgroundPainter.VANILLA);


        back.add(donePanic, (ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight), 100, 50);
        back.add(root, (ExampleScreen.screenWidth/2-135), (ExampleScreen.screenHeight/2-73), 106, 146);


        //
        //
        // HitBox
        //
        //
        WDynamicLabel hitSize = new WDynamicLabel(() -> I18n.translate("text.size", test.getSize()));
        hitSize.setColor(0xc3c3c3,0xc3c3c3);

        WToggleButton HitboxSizeText = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Plауеr mоre"));
        HitboxSizeText.setColor(0xc3c3c3,0xc3c3c3);
        if (test.toggleH) {
            HitboxSizeText.setToggle(true);
        }
        HitboxSizeText.setOnToggle(on -> {
            test.toggleH = !test.toggleH;
        });

        int sizeBox = (int) (test.getSize() * 100);

        WSlider HitboxSize = new WSlider(0, 25, Axis.HORIZONTAL);
        HitboxSize.setValue(sizeBox);


        WToggleButton HideHits = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Shоw Plауеr mоre"));
        HideHits.setColor(0xc3c3c3,0xc3c3c3);
        if (test.HideH) {
            HideHits.setToggle(true);
        }
        HideHits.setOnToggle(on -> {
            test.HideH = !test.HideH;
        });


        WToggleButton MobHitboxSizeText = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Mоb mоre"));
        MobHitboxSizeText.setColor(0xc3c3c3,0xc3c3c3);
        if (test.toggleMobH) {
            MobHitboxSizeText.setToggle(true);
        }
        MobHitboxSizeText.setOnToggle(on -> {
            test.toggleMobH = !test.toggleMobH;
        });

        int sizeMobBox = (int) (test.getMobSize() * 100);

        WSlider MobHitboxSize = new WSlider(0, 50, Axis.HORIZONTAL);
        MobHitboxSize.setValue(sizeMobBox);

        WDynamicLabel MobHitSize = new WDynamicLabel(() -> I18n.translate("text.size", test.getMobSize()));
        MobHitSize.setColor(0xc3c3c3,0xc3c3c3);


        WToggleButton HideMobHits = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Shоw Mоb Моre"));
        HideMobHits.setColor(0xc3c3c3,0xc3c3c3);
        if (test.HideMobH) {
            HideMobHits.setToggle(true);
        }
        HideMobHits.setOnToggle(on -> {
            test.HideMobH = !test.HideMobH;
        });


        WToggleButton OnlySmallMobs = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Only Smаll Мobs"));
        OnlySmallMobs.setColor(0xc3c3c3,0xc3c3c3);
        if (test.OnlySmallMob) {
            OnlySmallMobs.setToggle(true);
        }
        OnlySmallMobs.setOnToggle(on -> {
            test.OnlySmallMob = !test.OnlySmallMob;
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

        ClientTickEvents.END_CLIENT_TICK.register(client -> test.setSize((HitboxSize.getValue()/100.0)));
        ClientTickEvents.END_CLIENT_TICK.register(client -> test.setMobSize((MobHitboxSize.getValue()/100.0)));


        //
        //
        //Glow
        //
        //
        WToggleButton PlayerGlow = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Рlаyеr Light"));
        PlayerGlow.setColor(0xc3c3c3,0xc3c3c3);
        if (test.PlayerGlow) {
            PlayerGlow.setToggle(true);
        }
        PlayerGlow.setOnToggle(on -> {
            test.PlayerGlow = !test.PlayerGlow;
        });

        WToggleButton ClanGlow = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Сlаn Light"));
        ClanGlow.setColor(0xc3c3c3,0xc3c3c3);
        if (test.ClanGlow) {
            ClanGlow.setToggle(true);
        }
        ClanGlow.setOnToggle(on -> {
            test.ClanGlow = !test.ClanGlow;
        });

        WToggleButton EnemyGlow = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Еnemy Light"));
        EnemyGlow.setColor(0xc3c3c3,0xc3c3c3);
        if (test.EnemyGlow) {
            EnemyGlow.setToggle(true);
        }
        EnemyGlow.setOnToggle(on -> {
            test.EnemyGlow = !test.EnemyGlow;
        });

        WToggleButton SpiderGlow = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Spidеr Light"));
        SpiderGlow.setColor(0xc3c3c3,0xc3c3c3);
        if (test.SpiderGlow) {
            SpiderGlow.setToggle(true);
        }
        SpiderGlow.setOnToggle(on -> {
            test.SpiderGlow = !test.SpiderGlow;
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
        WToggleButton ToggleLeftClick = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Тоggle Lеft"));
        ToggleLeftClick.setColor(0xc3c3c3,0xc3c3c3);
        if (test.lActive) {
            ToggleLeftClick.setToggle(true);
        }
        ToggleLeftClick.setOnToggle(on -> {
            test.lActive = !test.lActive;});

        WTextField lClickDelay = new WTextField(Text.literal(Integer.toString(test.getLClickDelayInt())));

        WToggleButton lHoldMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Ноld Mоd"));
        lHoldMode.setColor(0xc3c3c3,0xc3c3c3);
        if (test.lHoldMode) {
            lHoldMode.setToggle(true);
        }
        lHoldMode.setOnToggle(on -> {
            test.lHoldMode = !test.lHoldMode;});

        WToggleButton lTargetEntityMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Еntitу Mоd"));
        lTargetEntityMode.setColor(0xc3c3c3,0xc3c3c3);
        if (test.lTargetEntityMode) {
            lTargetEntityMode.setToggle(true);
        }
        lTargetEntityMode.setOnToggle(on -> {
            test.lTargetEntityMode = !test.lTargetEntityMode;});

        WToggleButton lCooldownAttackMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Сооldown Mоd"));
        lCooldownAttackMode.setColor(0xc3c3c3,0xc3c3c3);
        if (test.lCooldownAttackMode) {
            lCooldownAttackMode.setToggle(true);
        }
        lCooldownAttackMode.setOnToggle(on -> {
            test.lCooldownAttackMode = !test.lCooldownAttackMode;});


        WToggleButton ToggleRightClick = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Тоggle Right"));
        ToggleRightClick.setColor(0xc3c3c3,0xc3c3c3);
        if (test.rActive) {
            ToggleRightClick.setToggle(true);
        }
        ToggleRightClick.setOnToggle(on -> {
            test.rActive = !test.rActive;});

        WTextField rClickDelay = new WTextField(Text.literal(Integer.toString(test.getRClickDelayInt())));


        WToggleButton rHoldMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Ноld Mоd"));
        rHoldMode.setColor(0xc3c3c3,0xc3c3c3);
        if (test.rHoldMode) {
            rHoldMode.setToggle(true);
        }
        rHoldMode.setOnToggle(on -> {
            test.rHoldMode = !test.rHoldMode;});

        WToggleButton rTargetEntityMode = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("Еntity Mоd"));
        rTargetEntityMode.setColor(0xc3c3c3,0xc3c3c3);
        if (test.rTargetEntityMode) {
            rTargetEntityMode.setToggle(true);
        }
        rTargetEntityMode.setOnToggle(on -> {
            test.rTargetEntityMode = !test.rTargetEntityMode;});



        WButton OK = new WButton(Text.literal("Ok"));
        OK.setOnClick(() -> {
            if (!lClickDelay.getText().equals("") && !lClickDelay.getText().equals("0")) {
                test.lClickDelayInt = convertStringToInt(lClickDelay.getText());
            }
        });
        WButton OKr = new WButton(Text.literal("Ok"));
        OKr.setOnClick(() -> {
            if (!rClickDelay.getText().equals("")) {
                test.rClickDelayInt = convertStringToInt(rClickDelay.getText());
            }
        });

        WText command = new WText(Text.literal("Включить - /h cl"));
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

        ClickerSettings.add(command, 47, 127, 200, 1);

        //
        //
        // Menu
        //
        //
        WToggleButton clanHide = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("ShоwСlаn"));
        clanHide.setColor(0xc3c3c3,0xc3c3c3);
        if (test.clanHideToggle) {
            clanHide.setToggle(true);
        }
        clanHide.setOnToggle(on -> {
            test.clanHideToggle = !test.clanHideToggle;});
        root.add(clanHide, 10, 77);


        WToggleButton NoBlindness = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("NоВlindnеss"));
        NoBlindness.setColor(0xc3c3c3,0xc3c3c3);
        if (test.flag) {
            NoBlindness.setToggle(true);
        }
        NoBlindness.setOnToggle(on -> {
            test.flag = !test.flag;});
        root.add(NoBlindness, 10, 92);

        WToggleButton HideAll = new WToggleButton(SUPER_ON_IMAGE, SUPER_OFF_IMAGE, Text.literal("ShowАll"));
        HideAll.setColor(0xc3c3c3,0xc3c3c3);
        if (test.HideToggle) {
            HideAll.setToggle(true);
        }
        HideAll.setOnToggle(on -> {
            test.HideToggle = !test.HideToggle;});
        root.add(HideAll, 10, 107);


        WButton hitbox = new WButton();
        WText hitboxText = new WText(Text.literal("More"));
        hitboxText.setColor(0xc3c3c3,0xc3c3c3);
        root.add(hitbox, 4, 15, 96, 20);
        root.add(hitboxText, 36, 21, 60, 0);

        WButton Glow = new WButton();
        WText glowText = new WText(Text.literal("Light"));
        glowText.setColor(0xc3c3c3,0xc3c3c3);
        root.add(Glow, 4, 36, 96, 20);
        root.add(glowText, 40, 42, 60, 0);

        WButton clicker = new WButton();
        WText clickerText = new WText(Text.literal("HitBox"));
        clickerText.setColor(0xc3c3c3,0xc3c3c3);
        root.add(clicker, 4, 57, 96, 20);
        root.add(clickerText, 35, 63, 60, 0);

        if (test.hits) {hitBoxSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));}
        else if (test.glow) {GlowSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));}
        else if (test.click) {ClickerSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));}

        hitbox.setOnClick(() -> {
            if (!test.hits){
                test.hits = true;
                test.click = false;
                test.glow = false;
                hitBoxSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));
                GlowSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
                ClickerSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
            } else {
                test.hits = false;
                hitBoxSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
            }
        });
        Glow.setOnClick(() -> {
            if (!test.glow){
                test.glow = true;
                test.click = false;
                test.hits = false;
                hitBoxSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
                GlowSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));
                ClickerSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
            } else {
                test.glow = false;
                GlowSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
            }

        });
        clicker.setOnClick(() -> {
            if (!test.click) {
                test.click = true;
                test.hits = false;
                test.glow = false;
                hitBoxSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
                GlowSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
                ClickerSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));
            } else {
                test.click = false;
                ClickerSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
            }
        });


        //
        //
        // Panic
        //
        //
        WButton panic = new WButton();
        WText panicText = new WText(Text.literal("Pаnic"));
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
            hitBoxSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
            GlowSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
            ClickerSettings.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
            back.remove(panic);
            back.remove(panicText);
            WText panicQ = new WText(Text.literal("Panic?"));
            panicQ.setColor(0xc3c3c3,0xc3c3c3);
            donePanic.add(panicQ, 35, 10, 35, 0);
            donePanic.add(done, 10, 25, 35, 20);
            donePanic.add(exit, 55, 25, 35, 20);
            done.setOnClick(() -> {
                test.pan = !test.pan;
                if (test.pan){
                    assert MinecraftClient.getInstance().currentScreen != null;
                    MinecraftClient.getInstance().currentScreen.close();
                }
            });
            exit.setOnClick(() -> {
                if (test.hits) {hitBoxSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));}
                else if (test.glow) {GlowSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));}
                else if (test.click) {ClickerSettings.setLocation((ExampleScreen.screenWidth/2-28), (ExampleScreen.screenHeight/2-73));}
                donePanic.setLocation((ExampleScreen.screenWidth+9999), (ExampleScreen.screenHeight));
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
