/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.components.mainpanel;

import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author wease
 */
public final class LowerPanel extends JPanel{

    public final CardLayout cardLayout;
    
    public final String BUTTON_PANEL = "button panel";
    private final JPanel buttonPanel;
    public final String NEW_GAME_PANEL = "new game panel";
    private final JPanel newGamePanel;
    
    private final JButton newGameButton;
    private final JButton rulesButton;
    private final JButton exitButton;
    private final JButton newGameConfirmButton;
    private final JButton newGameCancelButton;
    
    private final JTextField newGameTextField;
    
    private final ArrayList buttonList = new ArrayList();
    
    public LowerPanel() {
        // This is the lower panel that will hold all the other panels as cards.
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        // This is the default panel.
        this.buttonPanel = new JPanel();

        // Button initialisation.
        this.newGameButton = new JButton("NEW GAME");
        this.rulesButton = new JButton("RULES");
        this.exitButton = new JButton("EXIT");
        
        // Adding buttons to the buttons panel.
        this.buttonPanel.add(newGameButton);
        this.buttonPanel.add(rulesButton);
        this.buttonPanel.add(exitButton);
        
        // Adding button panel to the card manager panel.
        this.add(BUTTON_PANEL, buttonPanel);
        
        //Setup for new game panel.
        this.newGamePanel = new JPanel();
        this.newGamePanel.add(new JLabel("Adventurer's Name:"));
        this.newGameTextField = new JTextField("", 20);
        this.newGamePanel.add(newGameTextField);
        this.newGameConfirmButton = new JButton("Confirm");
        this.newGamePanel.add(this.newGameConfirmButton);
        this.newGameCancelButton = new JButton("Cancel");
        this.newGamePanel.add(this.newGameCancelButton);
        this.add(NEW_GAME_PANEL, newGamePanel);
        
        // Add all buttons to an arraylist to make it easier to register in view.
        this.buttonList.add(this.newGameButton);
        this.buttonList.add(this.rulesButton);
        this.buttonList.add(this.exitButton);
        this.buttonList.add(this.newGameConfirmButton);
        this.buttonList.add(this.newGameCancelButton);
    }
    
    /**
     * @return the newGameButton
     */
    public JButton getNewGameButton() {
        return newGameButton;
    }

    /**
     * @return the rulesButton
     */
    public JButton getRulesButton() {
        return rulesButton;
    }

    /**
     * @return the exitButton
     */
    public JButton getExitButton() {
        return exitButton;
    }

    /**
     * @return the newGameConfirmButton
     */
    public JButton getNewGameConfirmButton() {
        return newGameConfirmButton;
    }

    /**
     * @return the newGameCancel
     */
    public JButton getNewGameCancelButton() {
        return newGameCancelButton;
    }

    /**
     * @return the buttonPanel
     */
    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    /**
     * @return the newGamePanel
     */
    public JPanel getNewGamePanel() {
        return newGamePanel;
    }

    /**
     * @return the newGameTextField
     */
    public JTextField getNewGameTextField() {
        return newGameTextField;
    }

    /**
     * @return the buttonList
     */
    public ArrayList getButtonList() {
        return buttonList;
    }
    
}
