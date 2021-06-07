/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Set;
import pdc.assignment.database.GameData;
import pdc.assignment.database.Wrapper;
import pdc.assignment.gameclasses.GameSession;

/**
 *
 * @author wease
 */
public class GameModel extends Observable{
    
    private GameSession gameSession;
    private String playerName = null;
    private LinkedHashMap saveList;
    private GameData gameData;

    public void fetchListOfSaves() throws SQLException {
        LinkedHashMap linkedHashMapOfSaves = Wrapper.retrieveSaveList();
        this.saveList = linkedHashMapOfSaves;
        Set<String> keys = linkedHashMapOfSaves.keySet();
        keys.forEach(key -> {
            System.out.println(key + " " + linkedHashMapOfSaves.get(key));
        });
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
    
    public GameSession newGameSession() throws Exception {
        this.gameSession = new GameSession(this.playerName);
        return gameSession;
    };
    
    public GameSession oldGameSession(int id) throws Exception {
        this.gameSession = new GameSession(id);
        return gameSession;
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

}
