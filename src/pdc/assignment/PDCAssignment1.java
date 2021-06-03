/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment;

import pdc.assignment.gui.controller.MenuController;
import pdc.assignment.gui.model.MenuModel;
import pdc.assignment.gui.view.MenuView;

/**
 *
 * @author Weaselcore
 */
public class PDCAssignment1 {

    /**
     * Entry point to start a game.
     *
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        MenuView menuView = new MenuView();
        MenuModel menuModel = new MenuModel();
        MenuController menuController = new MenuController(menuModel, menuView);
        menuView.addController(menuController);
        
        menuModel.fetchListOfSaves();

}
