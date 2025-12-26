package com.mycompany.paintframe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class LineShape extends Shape {
     
    public LineShape(int x, int y, int width, int height, Color color, boolean filled, boolean dotted) {
        super(x, y, width, height, color, filled,dotted);
        
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
        
        int x1 = x;
        int y1 = y;


        int x2 = x + width;
        int y2 = y + height;

        g2d.drawLine(x1, y1, x2, y2);
    }
}
