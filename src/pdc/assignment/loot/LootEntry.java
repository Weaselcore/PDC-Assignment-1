/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.loot;

/**
 *
 * @author whackaweasel
 */
public class LootEntry {
    
    String name;
    private Integer minRoll;
    private Integer maxRoll;
    
    public LootEntry(String name, Integer chance, Integer maxRoll) {
        this.name = name;
        this.minRoll = maxRoll;
        this.maxRoll = maxRoll + chance;
    }

    /**
     * @return the minRoll
     */
    public Integer getMinRoll() {
        return minRoll;
    }

    /**
     * @return the maxRoll
     */
    public Integer getMaxRoll() {
        return maxRoll;
    }
    
    
    
}
