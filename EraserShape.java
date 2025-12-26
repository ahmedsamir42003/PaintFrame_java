package com.mycompany.paintframe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class EraserShape extends Shape {

  
    private ArrayList<Integer> pointsX = new ArrayList<>();
    private ArrayList<Integer> pointsY = new ArrayList<>();

    public EraserShape(int startX, int startY, int width, int height, Color color, boolean filled, boolean dotted) {
        super(startX, startY, width, height, color, filled,dotted);
        pointsX.add(startX);
        pointsY.add(startY);
    }

    public void addPoint(int x, int y) {
        pointsX.add(x);
        pointsY.add(y);
    }

    @Override
    public void draw(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(30f));
        g2d.setColor(Color.WHITE);
        
        for (int i = 1; i < pointsX.size(); i++) {
            int x1 = pointsX.get(i - 1);
            int y1 = pointsY.get(i - 1);
            int x2 = pointsX.get(i);
            int y2 = pointsY.get(i);
            
            g2d.drawLine(x1, y1, x2, y2);
        }
    }
}
