package ru.feytox.zoomify.clicker;

import io.github.cottonmc.cotton.gui.client.CottonHud;
import ru.feytox.zoomify.Config;
import ru.feytox.zoomify.Zoomify;
import ru.feytox.zoomify.clicker.gui.ACFHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class cContriller {
    public static int lClickDelayInt = 0;
    public static int rClickDelayInt = 0;
    private static boolean Active = false;
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

    public static double getSize() {
        return Math.abs(size);
    }
    public static void setSize(double size) {
        cContriller.size = size;
    }
    public static double getMobSize() {
        return Math.abs(mobSize);
    }
    public static void setMobSize(double mobSize) {
        cContriller.mobSize = mobSize;
    }

    public static int getLClickDelayInt(){
        return lClickDelayInt;
    }
    public static int getRClickDelayInt(){
        return rClickDelayInt;
    }

    public static void Update() {
        if (getActive()) {
            assert MinecraftClient.getInstance().player != null;
            if (lActive) {
                CottonHud.add(ACFHud.item, ACFHud.itemPositioner());
                if (!lHoldMode) {
                    if (!lTargetEntityMode) {
                        if (lCooldownAttackMode) {
                            if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1) {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                            }
                        } else {
                            if (lTimer < lClickDelayInt) lTimer++;
                            else {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                lTimer = 0;
                            }
                        }
                    } else {
                        if (MinecraftClient.getInstance().targetedEntity != null) {
                            if (lCooldownAttackMode) {
                                if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1) {
                                    KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                }
                            } else {
                                if (lTimer < lClickDelayInt) lTimer++;
                                else {
                                    KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                    lTimer = 0;
                                }
                            }
                        }
                    }
                } else {
                    KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1), true);
                }
            }
            if (rActive) {
                CottonHud.add(ACFHud.item, ACFHud.itemPositioner());
                if (!rHoldMode) {
                    if (!rTargetEntityMode) {
                        if (rTimer < rClickDelayInt) rTimer++;
                        else {
                            KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2));
                            rTimer = 0;
                        }
                    } else {
                            if (MinecraftClient.getInstance().targetedEntity != null) {
                            if (rTimer < rClickDelayInt) rTimer++;
                            else {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2));
                                rTimer = 0;
                            }
                        }
                    }
                } else {
                    KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2), true);
                }
            }
        }
    }

    public static void setActive(boolean active) {
        if (!active) {
            if (lHoldMode) KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1), false);
            if (rHoldMode) KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2), false);
        }
        Active = active;
        if (!Active) CottonHud.remove(ACFHud.item);
    }
    public static boolean getActive() { return Active; }
    public static void saveValues() {
        Zoomify.config.set(
                lActive,
                rActive,
                lClickDelayInt,
                rClickDelayInt,
                lHoldMode,
                rHoldMode,
                lTargetEntityMode,
                rTargetEntityMode,
                lCooldownAttackMode,
                size,
                mobSize,
                flag,
                HideH,
                HideMobH,
                OnlySmallMob
        );
    }
}
