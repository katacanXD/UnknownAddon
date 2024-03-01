package ru.feytox.zoomify.c.gui;

import io.github.cottonmc.cotton.gui.client.CottonHud;
import io.github.cottonmc.cotton.gui.widget.WItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Hud {
    public static CottonHud.Positioner itemPositioner() {
        return (widget, hudWidth, hudHeight) -> widget.setLocation((hudWidth - (widget.getWidth() - 2)) / 2, (hudHeight - widget.getHeight()) - 35);
    }
    public static WItem item = new WItem(new ItemStack(Items.STONE_BUTTON));
    public Hud() {
        CottonHud.add(item, itemPositioner());
    }
}