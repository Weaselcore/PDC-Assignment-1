/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pdc.assignment.gui.components.menucomponent.LowerPanel;
import pdc.assignment.gui.components.menucomponent.MenuViewImage;
import pdc.assignment.gui.controller.MenuController;

/**
 *
 * @author ieuan
 */
public final class MenuView extends Observable {
    
    public JFrame mainFrame;
    public JPanel upperPanel;
    public LowerPanel lowerPanel;

    private JPanel jPanelToShow;
    
    public MenuView(){
        
        this.mainFrame = new JFrame("Kingdom Fighters - Main Menu");
        this.mainFrame.setDefaultCloseOperation(3);
        
        // Setup for the panels that are in the main menu screen.
        this.upperPanel = new JPanel();
        
        // Menu Image setup.
        //RuleViewer ruleViewer = new RuleViewer();
        //upperPanel.add(ruleViewer);
        MenuViewImage menuImage = new MenuViewImage();
        upperPanel.add(menuImage);
        
        // Adding panel to the frame.
        mainFrame.add(upperPanel, BorderLayout.NORTH);
        lowerPanel = new LowerPanel();
        mainFrame.add(lowerPanel, BorderLayout.SOUTH);
        // Setting default panel.
        jPanelToShow = this.lowerPanel.getButtonPanel();
        this.showButtonPanel();
        
        mainFrame.setSize(500, 700);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    public void showButtonPanel() {
        if (jPanelToShow != this.lowerPanel.getButtonPanel()) {
            lowerPanel.cardLayout.show(lowerPanel, lowerPanel.BUTTON_PANEL);
            jPanelToShow = lowerPanel.getButtonPanel();
        }
    }
    
    public void showNewGamePanel() {
        if (jPanelToShow != this.lowerPanel.getNewGamePanel()) {
            lowerPanel.cardLayout.show(lowerPanel, lowerPanel.NEW_GAME_PANEL);
            jPanelToShow = lowerPanel.getNewGamePanel();
        }
    }
  
    public void addController(MenuController menuController) {
        
        ArrayList<JButton> allButtonsArray = new ArrayList();
        allButtonsArray.addAll(lowerPanel.getButtonList());
        
        allButtonsArray.forEach(button -> {
            button.addActionListener(menuController);
        });
    }
    
    public String getNewGameNameTextField() {
        return this.lowerPanel.getNewGameTextField().getText();
    }
}