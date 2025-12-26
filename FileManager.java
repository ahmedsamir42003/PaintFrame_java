package com.mycompany.paintframe;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManager {


    public void saveImage(DrawingPanel drawingPanel) {
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Drawing As PNG");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images (*.png)", "png"));

        int result = fileChooser.showSaveDialog(drawingPanel); 

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            if (!file.getName().toLowerCase().endsWith(".png")) {
                file = new File(file.getParentFile(), file.getName() + ".png");
            }

            BufferedImage image = new BufferedImage(
                drawingPanel.getWidth(),
                drawingPanel.getHeight(),
                BufferedImage.TYPE_INT_ARGB
            );

            Graphics2D g2d = image.createGraphics();

            drawingPanel.paint(g2d);
            g2d.dispose();

            try {
                ImageIO.write(image, "png", file);
                JOptionPane.showMessageDialog(drawingPanel, "Image saved successfully to:\n" + file.getAbsolutePath(),
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(drawingPanel, "Error saving image:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void openImage(DrawingPanel drawingPanel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Image");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg", "gif", "bmp"));

        int result = fileChooser.showOpenDialog(drawingPanel);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                BufferedImage backgroundImage = ImageIO.read(file);

                if (backgroundImage != null) {
                    drawingPanel.setBackgroundImage(backgroundImage);
                    drawingPanel.repaint();

                    JOptionPane.showMessageDialog(drawingPanel, "Image opened successfully:\n" + file.getName(),
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(drawingPanel, "Could not load the image.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(drawingPanel, "Error opening image:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}