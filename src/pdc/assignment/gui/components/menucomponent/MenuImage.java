/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.menucomponent;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author ieuan
 */
public final class MenuImage extends JPanel {
    
    Image image;  
    public MenuImage() {
        image = new ImageIcon("hero.png").getImage();
    }
    
    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        G.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
    }

}
