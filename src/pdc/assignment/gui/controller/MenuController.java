/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    public MenuController(MenuModel menuModel, MenuView menuView) {
        this.menuModel = menuModel;
        this.menuView = menuView;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source == menuView.getNewGameButton()){
            System.out.println("New game button has been pressed.");
            menuView.showNewGamePanel();
        }
        else if (source == menuView.getNewGameConfirmButton()) {
            menuModel.setPlayerName(menuView.getNewGameNameTextField());
            System.out.println(menuModel.getPlayerName());
        }        
        else if (source == menuView.getNewGameCancelButton()) {
            menuView.showButtonPanel();
            System.out.println("New game cancel button has been pressed.");
        }
        else if (source == menuView.getExitButton()) {
            System.out.println("Exit button has been pressed.");
            System.exit(0);
        }
    }
}