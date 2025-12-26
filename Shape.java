package com.mycompany.paintframe;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected Color color;
    protected boolean dotted;
    protected boolean filled;

    protected float[] dash = {2f, 2f};
    
    public Shape(int x, int y, int width, int height, Color color, boolean filled, boolean dotted) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.filled = filled;
        this.dotted = dotted;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public void setDotted(boolean dotted) {
        this.dotted = dotted;
    }
    
    public abstract void draw(Graphics g);
    
}    

