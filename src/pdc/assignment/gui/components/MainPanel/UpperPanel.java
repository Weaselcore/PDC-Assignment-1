/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.mainpanel;

import java.awt.CardLayout;
import javax.swing.JPanel;
import pdc.assignment.gui.components.menucomponent.MenuImage;
import pdc.assignment.gui.components.menucomponent.RuleViewer;

/**
 *
 * @author wease
 */

public final class UpperPanel extends JPanel{
    
    public final CardLayout cardLayout;
    private final RuleViewer ruleViewer;
    public final String RULES_VIEWER = "rules viewer";
    private final MenuImage menuImage;
    public final String MENU_IMAGE = "menu image";
    
    public UpperPanel() {
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        this.ruleViewer = new RuleViewer();
        this.menuImage = new MenuImage();
        
        this.add(this.MENU_IMAGE, this.menuImage);       
        this.add(this.RULES_VIEWER, this.ruleViewer); 
    }

    public JPanel getRulesViewer() {
        return this.ruleViewer;
    }
    
    public JPanel getMenuImage() {
        return this.menuImage;
    }
}
