package com.mycompany.paintframe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class RectangleShape extends Shape {


    public RectangleShape(int x, int y, int width, int height, Color color, boolean filled, boolean dotted) {
        super(x, y, width, height, color, filled, dotted);
    }

    @Override
    public void draw(Graphics g) {
        
                       
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        
        if(dotted){
           g2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2f, super.dash, 2f));
        }else{
           g2d.setStroke(new BasicStroke(2f));
        }

        int drawX = Math.min(x, x + width);       
        int drawY = Math.min(y, y + height);      
        int drawWidth = Math.abs(width);          
        int drawHeight = Math.abs(height);
        
        if (filled) {
            g2d.fillRect(drawX,drawY ,drawWidth, drawHeight);
        } else {
            g2d.drawRect(drawX,drawY ,drawWidth, drawHeight);
        }
    }
}