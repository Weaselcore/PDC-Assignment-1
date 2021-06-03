/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.menucomponent;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author wease
 */
public final class RightPanel extends JPanel{
    
    public final CardLayout cardLayout;
    public final String HISTORY_PANEL = "history panel";
    private final JPanel historyPanel;
    public final String LOAD_PANEL = "load panel";
    private final JPanel loadPanel;
    public final String DELETE_PANEL = "delete panel";
    private final JPanel deletePanel;
    
    public RightPanel() {
        this.setPreferredSize(new Dimension(200,50));
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        this.historyPanel = new JPanel();
        this.loadPanel = new JPanel();
        this.deletePanel = new JPanel();
        
        this.add(historyPanel);
        this.add(loadPanel);
        this.add(deletePanel);
    }

 
    
}
