/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Set;
import pdc.assignment.databaseclasses.GameData;
import pdc.assignment.databaseclasses.Wrapper;
import pdc.assignment.gameclasses.GameSession;
import pdc.assignment.items.Potion;

/**
 *
 * @author wease
 */

/**
 * Contains all the data needed for the program.
 * 
 */
public class GameModel extends Observable{
    
    private GameSession gameSession;
    private String playerName = null;
    private LinkedHashMap saveList;
    private GameData gameData;
    private LinkedList potionList;

    /**
     * This function fetches a list of saves from the database and prints out
     * the key for it.
     * 
     */
    public void fetchListOfSaves() throws SQLException {
        LinkedHashMap linkedHashMapOfSaves = Wrapper.retrieveSaveList();
        this.saveList = linkedHashMapOfSaves;
        Set<String> keys = linkedHashMapOfSaves.keySet();
        keys.forEach(key -> {
            System.out.println(key + " " + linkedHashMapOfSaves.get(key));
        });
    }
    
    /**
     * Fetches the potions from the players inventory.
     * 
     */
    public void fetchListofPotions() {
        this.potionList = this.gameSession.getPlayer().getInventory();
    }
    
    public String getPlayerName() {
        return this.playerName;
    }
    
    public void setPlayerName(String playerName) {
        if (!playerName.isEmpty()){
            this.playerName = (String) playerName;
        } else {
            // This is the default heroes name when nothing has been inputted.
            this.playerName = "Bob";
        }
    }
    
    /**
     * Creates a game session when the player starts a new game.
     * 
     * @throws java.lang.Exception
     */
    public void newGameSession() throws Exception {
        this.gameSession = new GameSession(this.playerName);
        this.potionList = this.gameSession.getPlayer().getInventory();
    };

    /**
     * Creates a game session when the player loads an old game.
     * 
     * @throws java.lang.Exception
     */    
    public void oldGameSession(int id) throws Exception {
        this.gameSession = new GameSession(id);
    };
    
    /**
     * Triggers a turn implemented in the game session object.
     * 
     * @throws java.lang.Exception
     */
    public void playerAttack() throws Exception{
        this.gameSession.attack();
    }

    /**
     * @return the saveList
     */
    public LinkedHashMap getSaveList() {
        return saveList;
    }
    
    /**
     * Gets the index from a JList and find the respective auto-generated save ID
     * from the database.
     * 
     * @param index
     * @return
     */
    public int getSaveId(int index) {
        
        if (index != -1) {
            ArrayList<String> list = new ArrayList<>(this.saveList.keySet());
            int id = Integer.parseInt(list.get(index));
            return id;
        }
        return -1;
    }
    
    /**
     * Gives a Boolean that describes if the game is in end state.
     * 
     * @return 
     */
    public boolean hasGameEnded() {
        return this.gameSession.getIsWon() || this.gameSession.getIsLost();
    }
    
    /**
     * Triggers the save game logic to save to the database.
     * 
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public void saveGame() throws IOException, SQLException{
        HashMap saveData = this.gameSession.saveGame();
        Wrapper.saveGame(saveData);
    }
    
    /**
     * Deletes the game using the unique index of the save.
     * 
     * @param index
     * @throws java.sql.SQLException
     */
    public void deleteGame(int index) throws SQLException {
        int saveID = getSaveId(index);
        if (saveID != -1) {
            Wrapper.deleteGame(saveID);
        }
    }
    
    /**
     * Triggers use potion logic implemented in player object.
     * 
     * @param index
     */
    public void usePotion(int index) {
        if (index != -1) {
            this.fetchListofPotions();
            Potion potion = (Potion) this.potionList.remove(index);
            this.gameSession.getPlayer().setInventory("remove", potion);
            this.gameSession.getPlayer().usePotion(potion);
        }
    }
    
    public LinkedList getPotionList() {
        return this.potionList;
    }
    
    public String getCurrentEnemyName() {
        return this.gameSession.getLevel().getCurrentEnemy().getName();
    }
}
