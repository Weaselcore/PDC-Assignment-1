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
import pdc.assignment.database.GameData;
import pdc.assignment.database.Wrapper;
import pdc.assignment.gameclasses.GameSession;
import pdc.assignment.items.Potion;

/**
 *
 * @author wease
 */
public class GameModel extends Observable{
    
    private GameSession gameSession;
    private String playerName = null;
    private LinkedHashMap saveList;
    private GameData gameData;
    private LinkedList potionList;

    public void fetchListOfSaves() throws SQLException {
        LinkedHashMap linkedHashMapOfSaves = Wrapper.retrieveSaveList();
        this.saveList = linkedHashMapOfSaves;
        Set<String> keys = linkedHashMapOfSaves.keySet();
        keys.forEach(key -> {
            System.out.println(key + " " + linkedHashMapOfSaves.get(key));
        });
    }
    
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
            this.playerName = "Bob";
        }
    }
    
    public void newGameSession() throws Exception {
        this.gameSession = new GameSession(this.playerName);
        this.potionList = this.gameSession.getPlayer().getInventory();
    };
    
    public void oldGameSession(int id) throws Exception {
        this.gameSession = new GameSession(id);
    };
    
    public void playerAttack() throws Exception{
        this.gameSession.attack();
    }

    /**
     * @return the saveList
     */
    public LinkedHashMap getSaveList() {
        return saveList;
    }
    
    public int getSaveId(int index) {
        
        if (index != -1) {
            ArrayList<String> list = new ArrayList<>(this.saveList.keySet());
            int id = Integer.parseInt(list.get(index));
            return id;
        }
        return -1;
    }
    
    public boolean hasGameEnded() {
        return this.gameSession.getIsWon() || this.gameSession.getIsLost();
    }
    
    public void saveGame() throws IOException, SQLException{
        HashMap saveData = this.gameSession.saveGame();
        Wrapper.saveGame(saveData);
    }
    
    public void deleteGame(int index) throws SQLException {
        int saveID = getSaveId(index);
        if (saveID != -1) {
            Wrapper.deleteGame(saveID);
        }
    }
    
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
}
