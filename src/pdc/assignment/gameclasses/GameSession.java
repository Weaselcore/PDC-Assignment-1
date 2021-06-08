/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gameclasses;

import java.util.ArrayList;
import java.util.HashMap;
import pdc.assignment.entities.EntityFactory;
import pdc.assignment.entities.Entity;
import pdc.assignment.databaseclasses.GameData;
import pdc.assignment.databaseclasses.Wrapper;
import pdc.assignment.entities.Player;

/**
 *
 * @author whackaweasel
 */
public final class GameSession {

    boolean isNewGame;
    final int maxLevel = 4;
    private final Entity player;
    private final GameData gameData;
    private final Level level;
    private boolean isWon = false;
    private boolean isLost = false;

    // Constructor for a new game.
    public GameSession(String playerName) throws Exception {
        // Initialise game data from database.
        this.gameData = new GameData();
        this.player = EntityFactory.createNewEntity("player", this.gameData);
        Player playerPlayer = (Player) this.player;
        playerPlayer.setName(playerName);
        this.level = new Level(this.gameData, this.getPlayer());
        this.displayLevel();
        this.level.displayEntityInfo();
    }
    
    // Constructor to loadGameData game from save file.
    public GameSession(int id) throws Exception {
        this.gameData = new GameData();
        HashMap loadData = Wrapper.loadGame(id);
        // Create player passing old data.
        this.player = EntityFactory.createOldEntity("old player", loadData, this.gameData);
        // Passes in the old level value from save.
        this.level = new Level(this.gameData, (Player) this.player, loadData);
        this.displayLevel();
        this.level.displayEntityInfo();
    }

    /**
     * Contains all the logic when the players defeats a monster.
     * 
     */
    private void winRound() throws Exception {
        HistoryLogger.append("** You have defeated the creature!");
        this.level.incrementLevel();
        if (this.level.getCurrentLevel() >= maxLevel) {
                this.isWon = true;
                HistoryLogger.append("You have saved the kingdom."
                        + "\nYOU WON"
                        + "\nPlease EXIT and run the program to try again.");
        }
        else {
            // Gives loot and makes the player equip it.
            ArrayList loot = this.level.rewardPlayer();
            this.level.getPlayer().obtainItems(loot);
            this.level.generateEnemy();
            this.displayLevel();
        }
    }
    
    /**
     * This is trigger when the player presses the attack button.
     * 
     * @throws java.lang.Exception
     */
    public void attack() throws Exception {
        this.level.playerTurn();
        System.out.println("Player attacked");
        if (this.level.getCurrentEnemy().isDead()) {
            this.winRound();
        }
        else if(!isWon) {
            this.level.enemyTurn();
            if (this.level.getPlayer().isDead()) {
                this.isLost = true;
                HistoryLogger.append("You have tried your best but have failed."
                        + "\nGAME OVER"
                        + "\nPlease EXIT and run the program to try again.");
            } else {
                this.level.displayEntityInfo();
            }
        }
    }    

    /**
     * @return the player
     */
    public Player getPlayer() {
        return (Player) this.player;
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return the isWon
     */
    public boolean getIsWon() {
        return isWon;
    }

    /**
     * @return the isLost
     */
    public boolean getIsLost() {
        return isLost;
    }
    
    public HashMap saveGame() {
        return this.level.saveToHashMap();
    }
    
    private void displayLevel() {
        this.level.displayLevel(maxLevel);
    }
    
    
}
