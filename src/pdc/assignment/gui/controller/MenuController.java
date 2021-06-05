/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import pdc.assignment.gui.model.MenuModel;
import pdc.assignment.gui.view.MenuView;


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
public class MenuController implements ActionListener{
    
    MenuModel menuModel;
    MenuView menuView;
    
    public MenuController(MenuModel menuModel, MenuView menuView) throws SQLException {
        this.menuModel = menuModel;
        this.menuModel.fetchListOfSaves();
        this.menuView = menuView;
        this.menuView.rightPanel.populateSaveList(menuModel.getSaveList());
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        // Lower panel buttons events.
        if (source == menuView.lowerPanel.getNewGameButton()){
            System.out.println("New game button has been pressed.");
            menuView.showNewGamePanel();
        }
        else if (source == menuView.lowerPanel.getNewGameConfirmButton()) {
            menuModel.setPlayerName(menuView.getNewGameNameTextField());
            System.out.println(menuModel.getPlayerName());
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
            // Wrapper database logic.
        }
        else if (source == menuView.rightPanel.getDeleteButton()) {
            System.out.println("Delete button has been pressed.");
            // Wrapper database logic.
        }
    }
}
