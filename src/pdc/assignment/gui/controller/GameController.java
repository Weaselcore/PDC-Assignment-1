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
public class GameController implements ActionListener{
    
    GameModel gameModel;
    GameView gameView;
    
    public GameController(GameModel menuModel, GameView menuView) throws SQLException {
        this.gameModel = menuModel;
        this.gameModel.fetchListOfSaves();
        this.gameView = menuView;
        this.gameView.rightPanel.getSaveListPanel().getSavesJlist().populateSaveList(menuModel.getSaveList());
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
            gameView.showHistoryLogPanel();
        }        
        else if (source == gameView.lowerPanel.getNewGameCancelButton()) {
            gameView.showButtonPanel();
            System.out.println("New game cancel button has been pressed.");
        }
        else if (source == gameView.lowerPanel.getRulesButton()) {
            gameView.showRulesPanel();
            System.out.println("New rules button has been pressed.");
        }
        else if (source == gameView.rightPanel.getSaveListPanel().getLoadButton()) {
            System.out.println("Load button has been pressed.");
            int indexLoad = gameView.rightPanel.getSaveListPanel().getSavesJlist().getSelectedOption();
            if (indexLoad != -1) {
                int id = gameModel.getSaveId(indexLoad);
                System.out.println(id);
            }
        }
        else if (source == gameView.rightPanel.getSaveListPanel().getDeleteButton()) {
            System.out.println("Delete button has been pressed.");
            int indexDelete = gameView.rightPanel.getSaveListPanel().getSavesJlist().getSelectedOption();
            if (indexDelete != -1) {
                int id = gameModel.getSaveId(indexDelete);
                System.out.println(id);
            }
        }
        else if (source == gameView.rightPanel.getSaveListPanel().getSavesJlist()) {
            System.out.println("JList has been pressed.");
        }
        else if ((source == gameView.lowerPanel.getExitButton()) || (source == gameView.lowerPanel.getGameButtonPanel().getExitButton())) {
            System.out.println("Exit button has been pressed.");
            System.exit(0);
        }
        else if (!gameModel.hasGameEnded()) {
            if (source == gameView.lowerPanel.getGameButtonPanel().getAttackButton()) {
                try {
                    gameModel.playerAttack();
                } catch (Exception ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Attack button has been pressed.");
                this.updateHistoryLog();
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
        }
    }
    
    private void updateHistoryLog() {
        ArrayList<String> historyLog = HistoryLogger.unload();
        historyLog.forEach(element -> {
            gameView.rightPanel.getHistoryPanel().addText(element);
            gameView.rightPanel.getHistoryPanel().addText("\n");
        });
    }
}
