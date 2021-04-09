/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1;

import java.io.IOException;
import pdc.assignment.pkg1.Entities.Enemy;
import pdc.assignment.pkg1.Entities.EntityFactory;
import pdc.assignment.pkg1.Entities.Player;

/**
 *
 * @author whackaweasel
 */
public class GameSession {
    
    boolean isNewGame;
    int currentLevel = 1;
    int maxLevel = 3;
    Player player;
    Enemy currentEnemy;
    
        GameSession(boolean isNewGame) throws Exception {
        
        this.isNewGame = isNewGame;
        this.player = (Player) EntityFactory.CreateEntity("player");
        this.currentEnemy = (Enemy) EntityFactory.CreateEntity("enemy");

        // if not new game, create new game.
        // else load a game from save file.
        clearScreen();
        displayLevel();
    }
        
    // Initialise Level
    
    // Display level on command.
    private void displayLevel() {
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
    
    
    public void playerTurn() {
        this.player.attack(this.currentEnemy);
    }
    
    public void enemyTurn() {
        this.currentEnemy.attack(this.player);
        
    }
    
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
