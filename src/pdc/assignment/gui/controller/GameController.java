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
    
    GameModel gameModel;
    GameView gameView;
    
    public GameController(GameModel menuModel, GameView menuView) throws SQLException {
        this.gameModel = menuModel;
        this.gameModel.fetchListOfSaves();
        this.gameView = menuView;
        this.gameView.rightPanel.getSavesJList().populateSaveList(menuModel.getSaveList());
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        // Lower panel buttons events.
        if (source == gameView.lowerPanel.getNewGameButton()){
            System.out.println("New game button has been pressed.");
            gameView.showNewGamePanel();
            gameView.showMenuImagePanel();
        }
        else if (source == gameView.lowerPanel.getNewGameConfirmButton()) {
            gameModel.setPlayerName(gameView.getNewGameNameTextField());
            System.out.println(gameModel.getPlayerName());
            try {
                gameModel.newGameSession();
            } catch (Exception ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            gameView.showGameButtonPanel();
        }        
        else if (source == gameView.lowerPanel.getNewGameCancelButton()) {
            gameView.showButtonPanel();
            System.out.println("New game cancel button has been pressed.");
        }
        else if (source == gameView.lowerPanel.getRulesButton()) {
            gameView.showRulesPanel();
            System.out.println("New rules button has been pressed.");
        }
        else if (source == gameView.lowerPanel.getExitButton()) {
            System.out.println("Exit button has been pressed.");
            System.exit(0);
        }
        else if (source == gameView.rightPanel.getLoadButton()) {
            System.out.println("Load button has been pressed.");
            int indexLoad = gameView.rightPanel.getSavesJList().getSelectedOption();
            if (indexLoad != -1) {
                int id = gameModel.getSaveId(indexLoad);
                System.out.println(id);
            }
        }
        else if (source == gameView.rightPanel.getDeleteButton()) {
            System.out.println("Delete button has been pressed.");
            int indexDelete = gameView.rightPanel.getSavesJList().getSelectedOption();
            if (indexDelete != -1) {
                int id = gameModel.getSaveId(indexDelete);
                System.out.println(id);
            }
        }
        else if (source == gameView.rightPanel.getSavesJList()) {
            System.out.println("JList has been pressed.");
        }
    }
}
