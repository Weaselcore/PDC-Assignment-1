/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.gamecomponent;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author wease
 */

/**
 * This JPanel shows the images of each monster when they appear.
 * It dynamically changes when the monster gets defeated.
 * 
 */
public class GameImages extends JPanel{
    
    private final Image thief;
    private final Image orc;
    private final Image wolf;
    private final Image korg;
    private Image imageToShow;
    
    public GameImages() {
        this.thief = new ImageIcon("thief.png").getImage();
        this.orc = new ImageIcon("orc.png").getImage();
        this.wolf = new ImageIcon("wolf.png").getImage();
        this.korg = new ImageIcon("korg.png").getImage();
        // This is the default enemy.
        this.imageToShow = orc;
    }
    
    public void changeImage(String imageName) {
        if(imageName == "Thief") {
            this.imageToShow = this.thief;
        }
        else if(imageName == "Orc") {
            this.imageToShow = this.orc;
        }
        else if(imageName == "Wolf"){
            this.imageToShow = this.wolf;
        }
        else if(imageName == "Korg"){
            this.imageToShow = this.korg;
        }
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        G.drawImage(this.imageToShow, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}

