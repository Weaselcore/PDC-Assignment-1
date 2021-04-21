/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.main;

import pdc.assignment.entities.EntityFactory;
import pdc.assignment.entities.Entity;
import pdc.assignment.utilities.GameData;

/**
 *
 * @author whackaweasel
 */
public final class GameSession {

    boolean isNewGame;
    final int maxLevel = 2;
    Entity player;
    GameData gameData;
    Level level;

    GameSession(boolean isNewGame) throws Exception {

        this.isNewGame = isNewGame;
        // Initialise game data from json.
        gameData = new GameData();
        this.player = EntityFactory.createEntity("player", this.gameData);
        this.level = new Level(this.gameData, this.player, this.maxLevel);

        // if not new game, create new game.
        // else load a game from save file.
        this.gameSessionLoop();
    }

    public void gameSessionLoop() throws Exception {
        
        int currentLevel;
        
        while (true) {
            this.level.clearScreen();
            this.level.displayLevel(this.maxLevel);
            currentLevel = this.level.run();
            if (currentLevel > maxLevel) {
                System.out.println("\nYou have beaten the game!\n");
                System.exit(0);
            } else if (currentLevel == -1) {
                System.out.println("\nYou have been defeated!\n");
                System.exit(0);
            }
        }
    }



}
