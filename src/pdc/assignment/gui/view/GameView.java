/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.view;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;
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

    private JPanel bottomJPanelToShow;
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
        this.bottomJPanelToShow = this.lowerPanel.getButtonPanel();
        this.rightJPanelToShow = this.rightPanel.getSaveListPanel();
        this.showButtonPanel();
        this.showMenuImagePanel();
        
        this.mainFrame.setSize(700, 900);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }
    
    public void showButtonPanel() {
        if (getBottomJPanelToShow() != this.lowerPanel.getButtonPanel()) {
            lowerPanel.cardLayout.show(lowerPanel, lowerPanel.BUTTON_PANEL);
            bottomJPanelToShow = lowerPanel.getButtonPanel();
        }
    }
    
    public void showNewGamePanel() {
        if (getBottomJPanelToShow() != this.lowerPanel.getNewGamePanel()) {
            lowerPanel.cardLayout.show(lowerPanel, lowerPanel.NEW_GAME_PANEL);
            bottomJPanelToShow = lowerPanel.getNewGamePanel();
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
  
    public void addController(GameController menuController) {
        
        ArrayList<JButton> allButtonsArray = new ArrayList();
        allButtonsArray.addAll(lowerPanel.getButtonList());
        allButtonsArray.addAll(rightPanel.getButtonList());
        
        allButtonsArray.forEach(button -> {
            button.addActionListener(menuController);
        });
    }
    
    public String getNewGameNameTextField() {
        return this.lowerPanel.getNewGameTextField().getText();
    }

    /**
     * @return the bottomJPanelToShow
     */
    public JPanel getBottomJPanelToShow() {
        return bottomJPanelToShow;
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