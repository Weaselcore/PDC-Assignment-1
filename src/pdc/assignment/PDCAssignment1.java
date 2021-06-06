/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment;

import pdc.assignment.gui.controller.GameController;
import pdc.assignment.gui.model.GameModel;
import pdc.assignment.gui.view.GameView;

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

        GameView menuView = new GameView();
        GameModel menuModel = new GameModel();
        GameController menuController = new GameController(menuModel, menuView);
        menuView.addController(menuController);
        
        menuModel.fetchListOfSaves();
    }
}
