/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.mainpanel;

import java.awt.CardLayout;
import javax.swing.JPanel;
import pdc.assignment.gui.components.gamecomponent.HistoryLog;
import pdc.assignment.gui.components.gamecomponent.PotionPanel;
import pdc.assignment.gui.components.menucomponent.SaveListPanel;


/**
 *
 * @author wease
 */
public final class RightPanel extends JPanel{
    
    public final CardLayout cardLayout;
    
    public final String HISTORY_PANEL = "history panel";
    private final HistoryLog historyPanel;
    public final String SAVES_LIST_PANEL = "save list panel";
    private final SaveListPanel saveListPanel;
    public final String POTION_PANEL = "potion panel";
    private final PotionPanel potionPanel;
    
    public RightPanel() {
        //this.setPreferredSize(new Dimension(200,75));
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        this.historyPanel = new HistoryLog();  
        this.saveListPanel = new SaveListPanel();
        this.potionPanel = new PotionPanel();

        this.add(SAVES_LIST_PANEL, saveListPanel);
        this.add(HISTORY_PANEL, historyPanel);
        this.add(POTION_PANEL, potionPanel);
    }

    /**
     * @return the historyPanel
     */
    public HistoryLog getHistoryPanel() {
        return historyPanel;
    }

    /**
     * @return the saveListPanel
     */
    public SaveListPanel getSaveListPanel() {
        return saveListPanel;
    }

    /**
     * @return the potionPanel
     */
    public PotionPanel getPotionPanel() {
        return potionPanel;
    }
}
