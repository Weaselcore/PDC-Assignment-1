/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pdc.assignment.gui.model.GameModel;
import pdc.assignment.gui.view.GameView;


/**
 *
 * @author wease
 *//**
 *
 * @author wease
 *//**
 *
 * @author wease
 */
public class GameController implements ActionListener{
    
    GameModel menuModel;
    GameView menuView;
    
    public GameController(GameModel menuModel, GameView menuView) {
        this.menuModel = menuModel;
        this.menuView = menuView;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source == menuView.getNewGameButton()){
            System.out.println("New game button has been pressed.");
            menuView.showNewGameField();
        }
        if (source == menuView.getExitButton()) {
            System.out.println("Exit button has been pressed.");
            System.exit(0);
        }
    }
}
