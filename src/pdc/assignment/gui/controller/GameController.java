/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pdc.assignment.gui.model.GameModel;
import pdc.assignment.gui.view.GameView;

/**
 * @author wease
 */
public class GameController implements ActionListener{
    
    GameModel menuModel;
    GameView menuView;
    
    public GameController(GameModel menuModel, GameView menuView) throws SQLException {
        this.menuModel = menuModel;
        this.menuModel.fetchListOfSaves();
        this.menuView = menuView;
        this.menuView.rightPanel.getSavesJList().populateSaveList(menuModel.getSaveList());
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        // Lower panel buttons events.
        if (source == menuView.lowerPanel.getNewGameButton()){
            System.out.println("New game button has been pressed.");
            menuView.showNewGamePanel();
            menuView.showMenuImagePanel();
        }
        else if (source == menuView.lowerPanel.getNewGameConfirmButton()) {
            menuModel.setPlayerName(menuView.getNewGameNameTextField());
            System.out.println(menuModel.getPlayerName());
            try {
                menuModel.newGameSession();
            } catch (Exception ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        else if (source == menuView.lowerPanel.getNewGameCancelButton()) {
            menuView.showButtonPanel();
            System.out.println("New game cancel button has been pressed.");
        }
        else if (source == menuView.lowerPanel.getRulesButton()) {
            menuView.showRulesPanel();
            System.out.println("New rules button has been pressed.");
        }
        else if (source == menuView.lowerPanel.getExitButton()) {
            System.out.println("Exit button has been pressed.");
            System.exit(0);
        }
        else if (source == menuView.rightPanel.getLoadButton()) {
            System.out.println("Load button has been pressed.");
            int indexLoad = menuView.rightPanel.getSavesJList().getSelectedOption();
            if (indexLoad != -1) {
                int id = menuModel.getSaveId(indexLoad);
                System.out.println(id);
            }
        }
        else if (source == menuView.rightPanel.getDeleteButton()) {
            System.out.println("Delete button has been pressed.");
            int indexDelete = menuView.rightPanel.getSavesJList().getSelectedOption();
            if (indexDelete != -1) {
                int id = menuModel.getSaveId(indexDelete);
                System.out.println(id);
            }
        }
        else if (source == menuView.rightPanel.getSavesJList()) {
            System.out.println("JList has been pressed.");
        }
    }
}
