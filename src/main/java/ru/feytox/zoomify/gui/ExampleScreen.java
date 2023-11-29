package ru.feytox.zoomify.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.fabricmc.fabric.api.util.TriState;

public class ExampleScreen extends CottonClientScreen{
    public static int screenWidth, screenHeight;
    public ExampleScreen(GuiDescription description) {
        super(description);
    }
    public void init() {
        screenWidth = this.width; screenHeight = this.height;
    }
    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (!(chr >= '0' && chr <= '9')) {
            return false;
        }
        return super.charTyped(chr, modifiers);
    }
}
