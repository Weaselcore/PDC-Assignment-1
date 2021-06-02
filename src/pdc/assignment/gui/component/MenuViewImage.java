/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author ieuan
 */
public class MenuViewImage extends JComponent {
    
    private Image image;
    private final int compWidth = 500;
    private final int compHeight = 500;

    @Override
    public void paintComponent(Graphics g) {
        image = new ImageIcon("dude with a sword.png").getImage();
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(compWidth, compHeight);
    }

}
