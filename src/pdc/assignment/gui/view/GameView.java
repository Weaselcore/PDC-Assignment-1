/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.view;

import pdc.assignment.gui.component.MenuViewImage;
import java.awt.BorderLayout;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pdc.assignment.gui.controller.GameController;

/**
 *
 * @author ieuan
 */
public class GameView extends Observable {
    
    public JFrame mainFrame;
    public JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel newGamePanel;
    private JPanel loadGamePanel;
    private JPanel ruleGamePanel;
    private JPanel deleteSavePanel;
    private JPanel jPanelToShow;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton rulesButton;
    private JButton deleteSavesButton;
    private JButton exitButton;
    
    public GameView(){
        
        mainFrame = new JFrame("Kingdom Fighters - Main Menu");
        
        // Setup for the panels that are in the main menu screen.
        this.mainPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.newGamePanel = new JPanel();
        this.loadGamePanel = new JPanel();
        this.ruleGamePanel = new JPanel();
        this.deleteSavePanel = new JPanel();
        
        // Makes the button panel the default panel.
        buttonPanel.setVisible(true);
        newGamePanel.setVisible(false);
        loadGamePanel.setVisible(false);
        newGamePanel.setVisible(false);
        ruleGamePanel.setVisible(false);
        deleteSavePanel.setVisible(false);
        
        // Menu Image setup.
        MenuViewImage menuImage = new MenuViewImage();
        mainPanel.add(menuImage);
        
        // Button initialisation.
        newGameButton = new JButton("NEW GAME");
        loadGameButton = new JButton("LOAD GAME");
        rulesButton = new JButton("RULES");
        deleteSavesButton = new JButton("DELETE SAVE");
        exitButton = new JButton("EXIT");
        
        // Adding buttons to the buttons panel.
        buttonPanel.add(newGameButton);
        buttonPanel.add(loadGameButton);
        buttonPanel.add(rulesButton);
        buttonPanel.add(deleteSavesButton);
        buttonPanel.add(exitButton);

        // Adding panel to the frame.
        mainFrame.add(mainPanel, BorderLayout.NORTH);
        jPanelToShow = buttonPanel;
        mainFrame.add(jPanelToShow, BorderLayout.SOUTH);
        
        mainFrame.setSize(500, 700);
        mainFrame.setVisible(true);
    }
    
    public void showNewGameField() {
        jPanelToShow.setVisible(false);
        jPanelToShow = newGamePanel;
        jPanelToShow.setVisible(true);
    }
  
    public void addController(GameController menuController) {
        getNewGameButton().addActionListener(menuController);
        getLoadGameButton().addActionListener(menuController);
        getRulesButton().addActionListener(menuController);
        getDeleteSavesButton().addActionListener(menuController);
        getExitButton().addActionListener(menuController);
    }
    
    // These are all the button getters for registration.
    /**
     * @return the newGameButton
     */
    public JButton getNewGameButton() {
        return newGameButton;
    }

    /**
     * @return the loadGameButton
     */
    public JButton getLoadGameButton() {
        return loadGameButton;
    }

    /**
     * @return the rulesButton
     */
    public JButton getRulesButton() {
        return rulesButton;
    }

    /**
     * @return the deleteSavesButton
     */
    public JButton getDeleteSavesButton() {
        return deleteSavesButton;
    }

    /**
     * @return the exitButton
     */
    public JButton getExitButton() {
        return exitButton;
    }
}