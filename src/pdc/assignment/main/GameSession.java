/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.main;

import java.io.File;
import java.util.Map;
import pdc.assignment.entities.EntityFactory;
import pdc.assignment.entities.Entity;
import pdc.assignment.utilities.Deserialiser;
import pdc.assignment.utilities.GameData;

/**
 *
 * @author whackaweasel
 */
public final class GameSession {

    boolean isNewGame;
    final int maxLevel = 8;
    private Entity player;
    GameData gameData;
    private Level level;

    // Constructor for a new game.
    GameSession() throws Exception {
        // Initialise game data from json.
        this.gameData = new GameData();
        this.player = EntityFactory.createNewEntity("player", this.gameData);
        this.level = new Level(this.gameData, this.getPlayer(), this.maxLevel);
        this.gameSessionLoop();
    }
    
    // Constructor to load game from save file.
    GameSession(File file) throws Exception {
        this.gameData = new GameData();
        Map loadData = Deserialiser.readSave(file);
        // Create player passing old data.
        this.player = EntityFactory.createOldEntity("old player", loadData, this.gameData);
        // Passes in the old level value from save.
        this.level = new Level(this.gameData, this.player, maxLevel, (int) loadData.get("level"));
        this.gameSessionLoop();
    }

    public void gameSessionLoop() throws Exception {
        
        int currentLevel;
        
        while (true) {
            // This will only clear when run in a terminal.
            this.getLevel().clearScreen();
            // Every level will display the level progress at the start.
            this.getLevel().displayLevel(this.maxLevel);
            
            currentLevel = this.getLevel().run();
            
            // If the current level is higher than max level, it'll end the game.
            if (currentLevel > maxLevel) {
                System.out.println("\nYou have beaten the game!\n");
                System.exit(0);
            // 0 is the result for a defeat.
            } else if (currentLevel == 0) {
                System.out.println("\nYou have been defeated!\n");
                System.exit(0);
            }
        }
    }

    /**
     * @return the player
     */
    public Entity getPlayer() {
        return player;
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }
}
