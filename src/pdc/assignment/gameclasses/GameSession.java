/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gameclasses;

import java.sql.SQLException;
import java.util.HashMap;
import pdc.assignment.entities.EntityFactory;
import pdc.assignment.entities.Entity;
import pdc.assignment.database.GameData;
import pdc.assignment.entities.Player;

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
    public GameSession(String playerName) throws Exception {
        // Initialise game data from database.
        this.gameData = new GameData();
        this.player = EntityFactory.createNewEntity("player", this.gameData);
        Player playerPlayer = (Player) this.player;
        playerPlayer.setName(playerName);
        this.level = new Level(this.gameData, this.getPlayer(), this.maxLevel);
        this.gameSessionLoop();
    }
    
    // Constructor to load game from save file.
    public GameSession(int id) throws Exception {
        this.gameData = new GameData();
//        Map loadData = Deserialiser.readSave(file);
//        // Create player passing old data.
//        this.player = EntityFactory.createOldEntity("old player", loadData, this.gameData);
//        // Passes in the old level value from save.
//        this.level = new Level(this.gameData, this.player, maxLevel, (int) loadData.get("level"));
//        this.gameSessionLoop();
    }

    public GameSession(HashMap loadData) throws SQLException, Exception {
        this.gameData = new GameData();
        // Create player passing old data.
        this.player = EntityFactory.createOldEntity("old player", loadData, this.gameData);
        // Passes in the old level value from save.
        this.level = new Level(this.gameData, this.player, maxLevel, (int) loadData.get("level"));
        this.gameSessionLoop();
    }

    public void gameSessionLoop() throws Exception {
        
        int currentLevel;
        
        while (true) {
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
