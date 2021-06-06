/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.menucomponent;

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
public final class SaveListPanel extends JPanel{
    

    private final JPanel lowerSaveListPanel;
    private final JButton loadButton;
    private final JButton deleteButton;
    
    private final JScrollPane saveListScrollPanel;
    private final CustomJList savesJlist;
    
    private final ArrayList buttonList = new ArrayList();
    
    public SaveListPanel() {
        
        this.saveListScrollPanel = new JScrollPane();
        this.savesJlist = new CustomJList();
        
        this.deleteButton = new JButton("DELETE"); 
        this.loadButton = new JButton("LOAD");   
        this.saveListScrollPanel.add(this.savesJlist);
        this.saveListScrollPanel.setViewportView(this.savesJlist);
             
        this.setLayout(new GridLayout(2, 1));
        this.lowerSaveListPanel = new JPanel();
        
        this.add(this.saveListScrollPanel);
        this.add(this.lowerSaveListPanel);
        this.lowerSaveListPanel.add(loadButton);       
        this.lowerSaveListPanel.add(deleteButton);
        this.buttonList.add(this.deleteButton);
        this.buttonList.add(this.loadButton);
    }

    /**
     * @return the loadButton
     */
    public JButton getLoadButton() {
        return loadButton;
    }

    /**
     * @return the deleteButton
     */
    public JButton getDeleteButton() {
        return deleteButton;
    }

    /**
     * @return the savesJlist
     */
    public CustomJList getSavesJlist() {
        return savesJlist;
    }

    /**
     * @return the buttonList
     */
    public ArrayList getButtonList() {
        return buttonList;
    }
    
    
    
}
