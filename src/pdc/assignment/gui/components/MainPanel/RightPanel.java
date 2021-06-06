/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.mainpanel;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import pdc.assignment.gui.components.sharedcomponents.CustomJList;


/**
 *
 * @author wease
 */
public final class RightPanel extends JPanel{
    
    public final CardLayout cardLayout;
    
    public final String HISTORY_PANEL = "history panel";
    private final JPanel historyPanel;
    public final String SAVES_LIST_PANEL = "save list panel";
    private final JPanel saveListPanel;
    private final JPanel lowerSaveListPanel;
 
    private final JButton loadButton;
    private final JButton deleteButton;
    
    private final JScrollPane historyScrollPane;
    private final JScrollPane saveListScrollPane;
    
    private final CustomJList savesJlist;
    
    private final ArrayList buttonList = new ArrayList();
    
    public RightPanel() {
        //this.setPreferredSize(new Dimension(200,75));
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        this.historyScrollPane = new JScrollPane();
        this.saveListScrollPane = new JScrollPane();
        
        this.savesJlist = new CustomJList();
        this.saveListScrollPane.add(this.savesJlist);
        this.saveListScrollPane.setViewportView(this.savesJlist);
             
        this.historyPanel = new JPanel();
        this.saveListPanel = new JPanel();
        this.saveListPanel.setLayout(new GridLayout(2, 1));
        this.lowerSaveListPanel = new JPanel();
        
        this.historyPanel.add(this.historyScrollPane);
        this.saveListPanel.add(this.saveListScrollPane);
        this.saveListPanel.add(this.lowerSaveListPanel);
        
        this.deleteButton = new JButton("DELETE"); 
        this.loadButton = new JButton("LOAD");
        
        this.lowerSaveListPanel.add(loadButton);       
        this.lowerSaveListPanel.add(deleteButton);
        
        this.add(SAVES_LIST_PANEL, saveListPanel);
        
        this.buttonList.add(this.deleteButton);
        this.buttonList.add(this.loadButton);
    }

    /**
     * @return the buttonList
     */
    public ArrayList getButtonList() {
        return buttonList;
    } 

    /**
     * @return the historyPanel
     */
    public JPanel getHistoryPanel() {
        return historyPanel;
    }

    /**
     * @return the saveListPanel
     */
    public JPanel getSaveListPanel() {
        return saveListPanel;
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
     * @return the savesJList
     */
    public CustomJList getSavesJList() {
        return savesJlist;
    }

}
