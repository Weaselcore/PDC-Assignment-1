/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.gamecomponent;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author wease
 */
public final class GameButtonPanel extends JPanel{
    
    private final JButton attackButton;
    private final JButton potionButton;
    private final JButton saveButton;
    private final JButton exitButton;
    private ArrayList buttonList;
    
    public GameButtonPanel() {

        this.setLayout(new GridLayout(1, 4));
        this.attackButton = new JButton("ATTACK");
        this.add(this.attackButton);
        this.potionButton = new JButton("POTIONS");
        this.add(this.potionButton);
        this.saveButton = new JButton("SAVE");
        this.add(this.saveButton);
        this.exitButton = new JButton("EXIT");
        this.add(this.exitButton);
        
        this.buttonList = new ArrayList();

        this.buttonList.add(this.attackButton);
        this.buttonList.add(this.potionButton);
        this.buttonList.add(this.saveButton);
        this.buttonList.add(this.exitButton);
    }
    
    /**
     * @return the buttonList
     */
    public ArrayList getButtonList() {
        return buttonList;
    } 
    
}
