/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.loot;

/**
 * An item that resides in the loot table, will keep track of weighting.
 * @author whackaweasel
 */
public class LootEntry {
    
    private String name;
    private int minRoll;
    private int maxRoll;
    
    public LootEntry(String name, int chance, int maxRoll) {
        this.name = name;
        this.minRoll = maxRoll + 1;
        this.maxRoll = maxRoll + chance;
    }

    /**
     * @return the minRoll
     */
    public int getMinRoll() {
        return minRoll;
    }

    /**
     * @return the maxRoll
     */
    public int getMaxRoll() {
        return maxRoll;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}