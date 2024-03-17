package ru.feytox.zoomify.c;

import io.github.cottonmc.cotton.gui.client.CottonHud;
import ru.feytox.zoomify.c.gui.Hud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.feytox.zoomify.test;

public class cControls {

    public static void Update() {
        if (getActive() && !test.pan) {
            assert MinecraftClient.getInstance().player != null;
            if (test.lActive) {
                CottonHud.add(Hud.item, Hud.itemPositioner());
                if (!test.lHoldMode) {
                    if (!test.lTargetEntityMode) {
                        if (test.lCooldownAttackMode) {
                            if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1) {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                            }
                        } else {
                            if (test.lTimer < test.lClickDelayInt) test.lTimer++;
                            else {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                test.lTimer = 0;
                            }
                        }
                    } else {
                        if (MinecraftClient.getInstance().targetedEntity != null) {
                            if (test.lCooldownAttackMode) {
                                if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1) {
                                    KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                }
                            } else {
                                if (test.lTimer < test.lClickDelayInt) test.lTimer++;
                                else {
                                    KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                    test.lTimer = 0;
                                }
                            }
                        }
                    }
                } else {
                    KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1), true);
                }
            }
            if (test.rActive) {
                CottonHud.add(Hud.item, Hud.itemPositioner());
                if (!test.rHoldMode) {
                    if (!test.rTargetEntityMode) {
                        if (test.rTimer < test.rClickDelayInt) test.rTimer++;
                        else {
                            KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2));
                            test.rTimer = 0;
                        }
                    } else {
                            if (MinecraftClient.getInstance().targetedEntity != null) {
                            if (test.rTimer < test.rClickDelayInt) test.rTimer++;
                            else {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2));
                                test.rTimer = 0;
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
            if (test.lHoldMode) KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1), false);
            if (test.rHoldMode) KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2), false);
        }
        test.Active = active;
        if (!test.Active) CottonHud.remove(Hud.item);
    }
    public static boolean getActive() { return test.Active; }
}
