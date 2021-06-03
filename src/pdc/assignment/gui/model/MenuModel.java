/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.model;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Set;
import pdc.assignment.database.Wrapper;
import pdc.assignment.gameclasses.GameSession;

/**
 *
 * @author wease
 */
public class MenuModel extends Observable{
    
    private GameSession gameSession;
    private String playerName = null;
    private LinkedHashMap saveList;

    public void getListOfSaves() throws SQLException {
        LinkedHashMap linkedHashMapOfSaves = Wrapper.retrieveSaveList();
        this.saveList = linkedHashMapOfSaves;
        Set<String> keys = linkedHashMapOfSaves.keySet();
        for (String key : keys) {
            System.out.println(key + " " + linkedHashMapOfSaves.get(key));
        }
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
    
//    public GameSession getGameSessionFromSave() {
//        this.gameSession = new GameSession("Something");
//        return gameSession
//    };
    
    public GameSession newGameSession() throws Exception {
        this.gameSession = new GameSession();
        return gameSession;
    };
       
}
