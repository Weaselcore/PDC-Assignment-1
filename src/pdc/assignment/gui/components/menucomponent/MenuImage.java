/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.menucomponent;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ieuan
 */
public final class MenuImage extends JPanel {
    
    private final JLabel imageLabel;
    
    public MenuImage() {
        Image image = new ImageIcon("dude with a sword.png").getImage();    
        this.imageLabel = new JLabel(new ImageIcon(image)) ;
        this.add(imageLabel);
    }
}
