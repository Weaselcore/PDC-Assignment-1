/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1;

import java.io.IOException;
import pdc.assignment.pkg1.Entities.EntityFactory;
import pdc.assignment.pkg1.Entities.Entity;

/**
 *
 * @author whackaweasel
 */
public final class GameSession {
    
    boolean isNewGame;
    int currentLevel = 1;
    int maxLevel = 3;
    Entity player;
    Entity currentEnemy;
    
        GameSession(boolean isNewGame) throws Exception {
        
        this.isNewGame = isNewGame;
        this.player = EntityFactory.CreateEntity("player");
        this.currentEnemy =  EntityFactory.CreateEntity("enemy");

        // if not new game, create new game.
        // else load a game from save file.
        clearScreen();
        this.displayLevel();
        this.levelLoop();
    }
        
    /**
     * Creates a loops to handle the turns which is uses the Entity interface
     * and type to handle both Player and Enemy.
     */
    public void levelLoop() {
        
        boolean levelInProgress = true;
        // Since there is no parallel assignments in Java,
        // a temp variable is used to swap around the current entityForTurn.
        Entity entityForTurn = this.player;
        Entity entityWaiting = this.currentEnemy;
        Entity temp;
        
        while (levelInProgress) {
            boolean targetHasDied = entityForTurn.turn(entityWaiting);
            if (targetHasDied) {
                levelInProgress = false;
            } else {
                temp = entityForTurn;
                entityForTurn = entityWaiting;
                entityWaiting = temp;
            }
        }
    }
    
    /**
     * Displays the current level the Player is currently on,
     * Example: [ ] [*] [ ] [ ] [ ] [ ]
     * The Player is on the second level out of 6.
     */
    public void displayLevel() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Progress: ");
        for (int i = 0; i < this.maxLevel; i++) {
            if (i != (this.currentLevel - 1)) {
                stringbuilder.append("[ ]");
            }
            else {
                stringbuilder.append("[*]");
            }
        }
        System.out.println(stringbuilder.toString());
    }
    
    /**
     * This is an operating system agnostic way of clearing the terminal.
     */
    public final static void clearScreen()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final IOException e)
        {
            //  Handle any exceptions.
        }

    }
    
}
