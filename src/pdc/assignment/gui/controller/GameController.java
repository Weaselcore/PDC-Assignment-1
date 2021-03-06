/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pdc.assignment.gameclasses.HistoryLogger;
import pdc.assignment.gui.model.GameModel;
import pdc.assignment.gui.view.GameView;

/**
 * @author wease
 */

/**
 * A controller class that starts the brings up the program.
 * 
 */
public class GameController implements ActionListener{
    
    GameModel gameModel;
    GameView gameView;
    
    public GameController(GameModel gameModel, GameView gameView) throws SQLException {
        this.gameModel = gameModel;
        this.gameView = gameView;
        // When starting up, the save list needs to be updated from database data.
        this.updateSaveList();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        /**
         * Below are the buttons that are contained in the lower panels.
         * 
         */
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
            gameView.showHistoryLogPanel();
            gameView.showGameImages();
            this.updateHistoryLog();
        }        
        else if (source == gameView.lowerPanel.getNewGameCancelButton()) {
            gameView.showButtonPanel();
            System.out.println("New game cancel button has been pressed.");
        }
        else if (source == gameView.lowerPanel.getRulesButton()) {
            gameView.showRulesPanel();
            System.out.println("New rules button has been pressed.");
        }
        else if ((source == gameView.lowerPanel.getExitButton()) || (source == gameView.lowerPanel.getGameButtonPanel().getExitButton())) {
            System.out.println("Exit button has been pressed.");
            System.exit(0);
        }
        /**
         * Below are the buttons that are contained in the right panels.
         * 
         */
        else if (source == gameView.rightPanel.getSaveListPanel().getLoadButton()) {
            System.out.println("Load button has been pressed.");
            int indexLoad = gameView.rightPanel.getSaveListPanel().getSavesJlist().getSelectedOption();
            if (indexLoad != -1) {
                int loadId = gameModel.getSaveId(indexLoad);
                try {
                    gameModel.oldGameSession(loadId);
                    gameView.showGameButtonPanel();
                    gameView.showHistoryLogPanel();
                    gameView.showGameImages();
                    this.updateGameImage();
                    this.updateHistoryLog();
                } catch (Exception ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else if (source == gameView.rightPanel.getSaveListPanel().getDeleteButton()) {
            System.out.println("Delete button has been pressed.");
            int indexDelete = gameView.rightPanel.getSaveListPanel().getSavesJlist().getSelectedOption();
            try {
                gameModel.deleteGame(indexDelete);
                this.updateSaveList();
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (source == gameView.rightPanel.getSaveListPanel().getSavesJlist()) {
            System.out.println("JList has been pressed.");
        }
        /**
         * Below are the buttons that get disabled when gamesession's isWon and
         * isLose flags are true to prevent any event triggers after the game
         * has finished.
         */
        else if (!gameModel.hasGameEnded()) {
            if (source == gameView.lowerPanel.getGameButtonPanel().getAttackButton()) {
                try {
                    gameModel.playerAttack();
                    this.updateHistoryLog();
                    this.updateGameImage();
                } catch (Exception ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Attack button has been pressed.");
            }
            else if (source == gameView.lowerPanel.getGameButtonPanel().getSaveButton()) {
                try {
                    gameModel.saveGame();
                    this.updateHistoryLog();   
                } catch (IOException ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (source == gameView.lowerPanel.getGameButtonPanel().getPotionButton()) {
                this.updatePotionList();
                gameView.showPotionPanel();
            }
            else if (source == gameView.rightPanel.getPotionPanel().getCancelButton()) {
                gameView.showHistoryLogPanel();
            }
            else if (source == gameView.rightPanel.getPotionPanel().getUseButton()) {
                int potionUseIndex = gameView.rightPanel.getPotionPanel().getOption();
                gameModel.usePotion(potionUseIndex);
                gameView.showHistoryLogPanel();
                this.updatePotionList();
                this.updateHistoryLog();
            }
        }
    }
    
    /**
     * This updates the history when an event is triggered.
     * This is called to add text to the history log panel.
     */
    private void updateHistoryLog() {
        ArrayList<String> historyLog = HistoryLogger.unload();
        historyLog.forEach(element -> {
            gameView.rightPanel.getHistoryPanel().addText(element);
            gameView.rightPanel.getHistoryPanel().addText("\n");
        });
    }
    
    /**
     * This updates the save JList when the user deletes a save.
     * 
     */
    private void updateSaveList() throws SQLException {
        this.gameModel.fetchListOfSaves();
        this.gameView.rightPanel.getSaveListPanel().getSavesJlist().populateListModelHashMap(gameModel.getSaveList());
    }
 
    /**
     * This updates the potion JList when the user gains or loses a potion.
     * 
     */
    private void updatePotionList() {
        this.gameModel.fetchListofPotions();
        this.gameView.rightPanel.getPotionPanel().getPotionJlist().populateListModelLinkedList(gameModel.getPotionList());
    }
    
    /**
     * This updates the image every time the user attacks.
     * 
     */    
    private void updateGameImage() {
        String enemyName = this.gameModel.getCurrentEnemyName();
        this.gameView.upperPanel.getGameImages().changeImage(enemyName);
    }
}
