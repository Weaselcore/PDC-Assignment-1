/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.view;

import pdc.assignment.gui.component.MenuViewImage;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pdc.assignment.gui.controller.MenuController;

/**
 *
 * @author ieuan
 */
public final class MenuView extends Observable {
    
    public JFrame mainFrame;
    public JPanel upperPanel;
    public JPanel lowerPanel;
    private final CardLayout cardLayout;
    
    public final String BUTTON_PANEL = "button panel";
    private final JPanel buttonPanel;
    public final String NEW_GAME_PANEL = "new game panel";
    private final JPanel newGamePanel;
    public final String LOAD_GAME_PANEL = "load game panel";
    private final JPanel loadGamePanel;
    public final String RULES_PANEL = "rules panel";
    private final JPanel ruleGamePanel;
    public final String DELETE_SAVE_PANEL = "delete save panel";
    private JPanel deleteSavePanel;

    private JPanel jPanelToShow;
    
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton rulesButton;
    private JButton deleteSavesButton;
    private JButton exitButton;
    
    private JTextField newGameTextField;
    private JButton newGameConfirm;
    private JButton newGameCancel;
    
    public MenuView(){
        
        this.mainFrame = new JFrame("Kingdom Fighters - Main Menu");
        this.mainFrame.setDefaultCloseOperation(3);
        
        // Setup for the panels that are in the main menu screen.
        this.upperPanel = new JPanel();
        // This is the lower panel that will hold all the other panels as cards.
        this.cardLayout = new CardLayout();
        this.lowerPanel = new JPanel();
        this.lowerPanel.setLayout(cardLayout);
        this.lowerPanel.setPreferredSize(new Dimension(250,150));
        
        // This is the default panel.
        this.buttonPanel = new JPanel();

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
        
        // Adding button panel to the card manager panel.
        lowerPanel.add(BUTTON_PANEL, buttonPanel);
        
        //Setup for alternate panels on the starting JFrame.
        this.newGamePanel = new JPanel();
        this.newGamePanel.add(new JLabel("Adventurer's Name:"));
        this.newGameTextField = new JTextField("", 20);
        this.newGamePanel.add(newGameTextField);
        this.newGameConfirm = new JButton("Confirm");
        this.newGamePanel.add(this.newGameConfirm);
        this.newGameCancel = new JButton("Cancel");
        this.newGamePanel.add(this.newGameCancel);
        lowerPanel.add(NEW_GAME_PANEL, newGamePanel);
        
        this.loadGamePanel = new JPanel();
        this.ruleGamePanel = new JPanel();
        this.deleteSavePanel = new JPanel();
        
        // Menu Image setup.
        MenuViewImage menuImage = new MenuViewImage();
        upperPanel.add(menuImage);
        
        // Adding panel to the frame.
        mainFrame.add(upperPanel, BorderLayout.NORTH);
        mainFrame.add(lowerPanel, BorderLayout.SOUTH);
        // Setting default panel.
        jPanelToShow = buttonPanel;
        this.showButtonPanel();
        
        mainFrame.setSize(500, 700);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    public void showButtonPanel() {
        if (jPanelToShow != buttonPanel) {
            this.cardLayout.show(lowerPanel, BUTTON_PANEL);
            jPanelToShow = buttonPanel;
        }
    }
    
    public void showNewGamePanel() {
        if (jPanelToShow != newGamePanel) {
            this.cardLayout.show(lowerPanel, NEW_GAME_PANEL);
            jPanelToShow = newGamePanel;
        }
    }
  
    public void addController(MenuController menuController) {
        getNewGameButton().addActionListener(menuController);
        getLoadGameButton().addActionListener(menuController);
        getRulesButton().addActionListener(menuController);
        getDeleteSavesButton().addActionListener(menuController);
        getExitButton().addActionListener(menuController);
        getNewGameConfirmButton().addActionListener(menuController);
        getNewGameCancelButton().addActionListener(menuController);
    }
    
    // Should implement some sort of error checking.
    public String getNewGameNameTextField() {
        String text = null;
        if (!this.newGameTextField.getText().isEmpty()) {
            text = newGameTextField.getText();
        } else {
            text = "Bob";
        }
        return text;
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

    /**
     * @return the newGameConfirm
     */
    public JButton getNewGameConfirmButton() {
        return newGameConfirm;
    }

    /**
     * @return the newGameCancel
     */
    public JButton getNewGameCancelButton() {
        return newGameCancel;
    }
}