package com.mycompany.paintframe;

import java.awt.Color;
import javax.swing.JFrame;


public class PaintFrame {

        public static void main(String[] args) {

            
                    ToolManager toolManager = new ToolManager();
                    FileManager fileManager = new FileManager();
                    DrawingPanel drawingPanel = new DrawingPanel(toolManager,fileManager); 
                    
                    JFrame f= new JFrame();     
                    
                    f.setTitle("Paint Brush Application");
                    f.setSize(1500,700);
                    f.setLocationRelativeTo(null);  // center the frame
                    drawingPanel.setBackground(Color.WHITE);
                    f.add(drawingPanel);
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    f.setVisible(true);
                                 
                                
        }
    }
