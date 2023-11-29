package ru.feytox.zoomify.util;

public class Color {
    public int r;
    public int g;
    public int b;

    public Color(int red, int green, int blue){
        this.r = red;
        this.g = green;
        this.b = blue;
    }

    public Color(int colorvalue){
        this.r = colorvalue >> 16 & 255;
        this.g = colorvalue >> 8 & 255;
        this.b = colorvalue & 255;
    }
    public int getColorValue(){
        return (r << 16) | (g << 8) | (b);
    }
}
