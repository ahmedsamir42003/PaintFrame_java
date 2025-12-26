package com.mycompany.paintframe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class DrawingPanel extends JPanel {

    private ArrayList<Shape> shapes = new ArrayList<>();
    private Shape currentShape;
    private ToolManager toolManager;
    private int startX, startY;
    private BufferedImage backgroundImage = null;

    public DrawingPanel(ToolManager toolManager,FileManager fileManager) {

        this.toolManager = toolManager;

        // Functions
        JLabel functionsBtn = new JLabel("Functions:");
        JButton undoBtn = new JButton("Undo");
        JButton clearBtn = new JButton("Clear");
        JButton saveBtn = new JButton("Save");
        JButton openBtn = new JButton("Open");

        //paint mode
        JLabel paintModeBtn = new JLabel("Paint Mode:");
        JButton lineBtn = new JButton("Line");
        JButton rectBtn = new JButton("Rectangle");
        JButton ovalBtn = new JButton("Oval");
        JButton pencilBtn = new JButton("Pencil");
        JButton eraserBtn = new JButton("Eraser");

        //check boxes
        JCheckBox filledBtn = new JCheckBox("Filled");
        JCheckBox dottedBtn = new JCheckBox("Dotted");

        //colors
        JLabel colorsBtn = new JLabel("Colors:");

        JButton blackBtn = new JButton("Black");
        blackBtn.setBackground(Color.BLACK);
        blackBtn.setForeground(Color.WHITE);

        JButton redBtn = new JButton("Red");
        redBtn.setBackground(Color.RED);
        redBtn.setForeground(Color.WHITE);

        JButton greenBtn = new JButton("Green");
        greenBtn.setBackground(Color.GREEN);
        greenBtn.setForeground(Color.WHITE);

        JButton blueBtn = new JButton("Blue");
        blueBtn.setBackground(Color.BLUE);
        blueBtn.setForeground(Color.WHITE);

        // Functions
        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileManager.saveImage(DrawingPanel.this);
            }
        });
        
        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileManager.openImage(DrawingPanel.this);
            }
        });
            
        // shapes
        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setTool("line");
            }
        });

        rectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setTool("rectangle");
            }
        });

        ovalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setTool("oval");
            }
        });

        pencilBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setTool("pencil");
            }
        });

        eraserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setTool("eraser");
            }
        });

        // checkbox
        filledBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setFilled(filledBtn.isSelected());
            }
        });

        dottedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setDotted(dottedBtn.isSelected());
            }
        });

        // colors
        blackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setColor(Color.BLACK);
            }
        });

        redBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setColor(Color.RED);
            }
        });

        greenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setColor(Color.GREEN);
            }
        });

        blueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setColor(Color.BLUE);
            }
        });

        add(functionsBtn);
        add(undoBtn);
        add(clearBtn);
        add(saveBtn);
        add(openBtn);

        add(paintModeBtn);
        add(lineBtn);
        add(rectBtn);
        add(ovalBtn);
        add(pencilBtn);
        add(eraserBtn);

        add(filledBtn);
        add(dottedBtn);

        add(colorsBtn);
        add(blackBtn);
        add(redBtn);
        add(greenBtn);
        add(blueBtn);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                startX = e.getX();
                startY = e.getY();

                switch (toolManager.getCurrentTool()) {
                    case "rectangle":
                        currentShape = new RectangleShape(startX, startY, 0, 0, toolManager.getCurrentColor(), toolManager.isFilled(), toolManager.isDotted() );
                        break;

                    case "oval":
                        currentShape = new OvalShape( startX , startY , 0 , 0, toolManager.getCurrentColor(), toolManager.isFilled(), toolManager.isDotted());
                        break;

                    case "line":
                        currentShape = new LineShape( startX , startY , 0 , 0, toolManager.getCurrentColor(), toolManager.isFilled(), toolManager.isDotted());
                        break;
                    case "pencil":
                        currentShape = new PencilShape(startX, startY, 0, 0, toolManager.getCurrentColor(), false , false );
                        break;
                    case "eraser":
                        currentShape = new EraserShape( startX , startY , 0 , 0, Color.WHITE , false , false );
                        break;
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                shapes.add(currentShape);
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            
            @Override
            public void mouseDragged(MouseEvent e) {
                
                if (toolManager.getCurrentTool().equals("pencil")) {
                            ((PencilShape) currentShape).addPoint(e.getX(), e.getY());
                            repaint();
                }else if (toolManager.getCurrentTool().equals("eraser")) {      
                            ((EraserShape) currentShape).addPoint(e.getX(), e.getY());
                            repaint();
                }else{
                currentShape.setWidth(e.getX() - startX);
                currentShape.setHeight(e.getY()- startY);
                repaint();
                }
            }
        });
    }


    @Override
    public void paint(Graphics g) {

        super.paint(g);
        
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), DrawingPanel.this);
        }
        
        for (Shape s : shapes) {
            s.draw(g);
        }
        
        if(currentShape != null ){
            currentShape.draw(g);
        }

    }


    public void undo() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            currentShape=null;
            repaint();
        }
    }

    public void clearAll() {
        shapes.clear();
        currentShape=null;
        repaint();
    }
    
    public void setBackgroundImage(BufferedImage image) {
    this.backgroundImage = image;
}
    
}
