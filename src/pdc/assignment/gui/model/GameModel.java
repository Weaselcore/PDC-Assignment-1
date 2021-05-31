/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.model;

import java.util.Observable;
import pdc.assignment.gameclasses.GameSession;

/**
 *
 * @author wease
 */
public class GameModel extends Observable{
    
    private GameSession gameSession;
    private String playerName = null;
    private final String rules = "\n* This is a level based game that has you fight monsters."
        + "\n* There is a level bar that shows up every new level."
        + "\n* Each monster is randomly generated with different stats."
        + "\n* You must defeat them in order to move on."
        + "\n* When defeated, monsters will drop loot that will boost stats."
        + "\n* Potions will go into your inventory' armour/weapons will auto-equip if better."
        + "\n* Each turn will charge up a super attack bar."
        + "\n* When maxed out, your next attack will deal double damage."
        + "\n* You cannot use a potion to go over max health/super attack."
        + "\n* You can use as many potions before attacking."
        + "\n* Enjoy!";
        
    public String getRules() {
        return this.rules;
    }
    
    public String getPlayerName() {
        return this.playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
