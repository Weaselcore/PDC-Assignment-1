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
import pdc.assignment.gui.components.mainpanel.LowerPanel;
import pdc.assignment.gui.components.menucomponent.MenuImage;
import pdc.assignment.gui.components.mainpanel.RightPanel;
import pdc.assignment.gui.components.mainpanel.UpperPanel;
import pdc.assignment.gui.controller.GameController;

/**
 *
 * @author ieuan
 */
public final class GameView extends Observable{
    
    public JFrame mainFrame;
    public UpperPanel upperPanel;
    public LowerPanel lowerPanel;
    public RightPanel rightPanel;

    private JPanel lowerJPanelToShow;
    private JPanel rightJPanelToShow;
    private JPanel upperJPanelToShow;
    
    public MenuImage menuImage;

    
    public GameView(){
        
        this.mainFrame = new JFrame("Kingdom Fighters - Main Menu");
        this.mainFrame.setDefaultCloseOperation(3);

        // Adding panel to the frame.
        this.upperPanel = new UpperPanel();
        this.mainFrame.add(upperPanel, BorderLayout.CENTER);
        this.lowerPanel = new LowerPanel();
        this.mainFrame.add(lowerPanel, BorderLayout.SOUTH);
        this.rightPanel = new RightPanel();
        this. mainFrame.add(rightPanel, BorderLayout.EAST);
        // Setting default panel.
        this.upperJPanelToShow = this.upperPanel.getMenuImage();
        this.lowerJPanelToShow = this.lowerPanel.getButtonPanel();
        this.rightJPanelToShow = this.rightPanel.getSaveListPanel();
        this.showButtonPanel();
        this.showMenuImagePanel();
        
        this.mainFrame.setSize(700, 900);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }
    
    public void showButtonPanel() {
        if (getLowerJPanelToShow() != this.lowerPanel.getButtonPanel()) {
            lowerPanel.cardLayout.show(lowerPanel, lowerPanel.BUTTON_PANEL);
            lowerJPanelToShow = lowerPanel.getButtonPanel();
        }
    }
    
    public void showNewGamePanel() {
        if (getLowerJPanelToShow() != this.lowerPanel.getNewGamePanel()) {
            lowerPanel.cardLayout.show(lowerPanel, lowerPanel.NEW_GAME_PANEL);
            lowerJPanelToShow = lowerPanel.getNewGamePanel();
        }
    }
    
    public void showRulesPanel() {
        if (getUpperJPanelToShow() != this.upperPanel.getRulesViewer()) {
            upperPanel.cardLayout.show(upperPanel, upperPanel.RULES_VIEWER);
            upperJPanelToShow = upperPanel.getRulesViewer();
        }
    }
    
    public void showMenuImagePanel() {
        if (getUpperJPanelToShow() != this.upperPanel.getMenuImage()) {
            upperPanel.cardLayout.show(upperPanel, upperPanel.MENU_IMAGE);
            upperJPanelToShow = upperPanel.getMenuImage();
        }
    }
    
    public void showGameButtonPanel() {
        if (getLowerJPanelToShow() != this.lowerPanel.getGameButtonPanel()) {
            lowerPanel.cardLayout.show(lowerPanel, lowerPanel.GAME_BUTTON_PANEL);
            lowerJPanelToShow = lowerPanel.getGameButtonPanel();
        }
    }
    
    public void showHistoryLogPanel() {
        if (getRightJPanelToShow() != this.rightPanel.getHistoryPanel()) {
            rightPanel.cardLayout.show(rightPanel, rightPanel.HISTORY_PANEL);
            rightJPanelToShow = rightPanel.getHistoryPanel();
        }
    }
    
    public void showPotionPanel() {
        if (getRightJPanelToShow() != this.rightPanel.getPotionPanel()) {
            rightPanel.cardLayout.show(rightPanel, rightPanel.POTION_PANEL);
            rightJPanelToShow = rightPanel.getPotionPanel();
        }
    }
    
    public void showGameImages() {
        if (getUpperJPanelToShow() != this.upperPanel.getGameImages()) {
            upperPanel.cardLayout.show(upperPanel, upperPanel.GAME_IMAGE);
            upperJPanelToShow = upperPanel.getGameImages();
        }
    }
  
    public void addController(GameController menuController) {
        
        ArrayList<JButton> allButtonsArray = new ArrayList();
        allButtonsArray.addAll(lowerPanel.getButtonList());
        allButtonsArray.addAll(lowerPanel.getGameButtonPanel().getButtonList());
        allButtonsArray.addAll(rightPanel.getSaveListPanel().getButtonList());
        allButtonsArray.addAll(rightPanel.getPotionPanel().getButtonList());
        
        allButtonsArray.forEach(button -> {
            button.addActionListener(menuController);
        });
    }
    
    public String getNewGameNameTextField() {
        return this.lowerPanel.getNewGameTextField().getText();
    }

    /**
     * @return the lowerJPanelToShow
     */
    public JPanel getLowerJPanelToShow() {
        return lowerJPanelToShow;
    }

    /**
     * @return the rightJPanelToShow
     */
    public JPanel getRightJPanelToShow() {
        return rightJPanelToShow;
    }

    /**
     * @return the upperJPanelToShow
     */
    public JPanel getUpperJPanelToShow() {
        return upperJPanelToShow;
    }
    
}