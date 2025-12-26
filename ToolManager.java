package com.mycompany.paintframe;

import java.awt.Color;

public class ToolManager {
    
    private Color currentColor;
    private String currentTool;
    private boolean dotted;
    private boolean filled;

    
    public ToolManager() {
        currentColor = Color.BLACK;
        currentTool="pencil";
        dotted = false;
        filled = false;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public String getCurrentTool() {
        return currentTool;
    }

    public boolean isDotted() {
        return dotted;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setColor(Color color) {
       this.currentColor = color; 
    }

    public void setTool(String tool) {
            this.currentTool = tool;
    }

    public void setDotted(boolean dotted) {
        this.dotted = dotted;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    
}
