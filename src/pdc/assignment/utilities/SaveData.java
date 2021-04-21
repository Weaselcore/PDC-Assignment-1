/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.utilities;

import java.util.HashMap;

/**
 *
 * @author whackaweasel
 */
public class SaveData {
    
    private String playerName;
    private int level;
    private HashMap Inventory;
    
    /**
     * @param playerName the playerName to set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @param Inventory the Inventory to set
     */
    public void setInventory(HashMap Inventory) {
        this.Inventory = Inventory;
    }
    
    @Override
    public String toString() {
        return this.playerName + " | Level: " + this.level + "/4"; 
    }   
}