/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1;

import java.io.IOException;
import pdc.assignment.pkg1.Entities.Enemy;
import pdc.assignment.pkg1.Entities.Player;

/**
 *
 * @author whackaweasel
 */
public class GameSession {
    
    // Keep track of what level it's on 1 - 3.
    // Should load in player info if loading from save.
    // Player player; (Placeholder)
    // Enemy currentEnemy;
    // Level level;
    boolean isNewGame;
    int currentLevel = 1;
    int maxLevel = 3;
    String name;
    Player player;
    Enemy currentEnemy;
    
        GameSession(boolean isNewGame, String name) {
        // Load (deserialise) everything here.
        this.isNewGame = isNewGame;
        this.name = name;
        this.player = new Player(this.name);

        
        // if not new game, create new game.
        // else load a game from save file.
        clearScreen();
        displayLevel();
        player.displayInfo();
        newLevel();
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
    
    private void newLevel() {
        this.currentEnemy = new Enemy("Slime", 10, 2);
        this.currentEnemy.displayInfo();
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
