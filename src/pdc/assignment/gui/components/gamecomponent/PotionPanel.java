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
import javax.swing.JScrollPane;
import pdc.assignment.gui.components.sharedcomponents.CustomJList;

/**
 *
 * @author wease
 */
public final class PotionPanel extends JPanel{
    private final JPanel lowerPotionPanel;
    private final JButton useButton;
    private final JButton cancelButton;
    
    private final JScrollPane potionListScrollPanel;
    private final CustomJList potionJlist;
    
    private final ArrayList buttonList = new ArrayList();
    
    public PotionPanel() {
        
        this.potionListScrollPanel = new JScrollPane();
        this.potionJlist = new CustomJList();
        
        this.useButton = new JButton("USE"); 
        this.cancelButton = new JButton("CANCEL");   
        this.potionListScrollPanel.add(this.potionJlist);
        this.potionListScrollPanel.setViewportView(this.potionJlist);
             
        this.setLayout(new GridLayout(2, 1));
        this.lowerPotionPanel = new JPanel();
        
        this.add(this.potionListScrollPanel);
        this.add(this.lowerPotionPanel);
        this.lowerPotionPanel.add(useButton);       
        this.lowerPotionPanel.add(cancelButton);
        this.buttonList.add(this.cancelButton);
        this.buttonList.add(this.useButton);
    }
    
    /**
     * @return the buttonList
     */
    public ArrayList getButtonList() {
        return buttonList;
    }

    /**
     * @return the useButton
     */
    public JButton getUseButton() {
        return useButton;
    }

    /**
     * @return the cancelButton
     */
    public JButton getCancelButton() {
        return cancelButton;
    }
    
    
    
    public int getOption() {
        return this.getPotionJlist().getSelectedOption();
    }

    /**
     * @return the potionJlist
     */
    public CustomJList getPotionJlist() {
        return potionJlist;
    }
    
    
}
