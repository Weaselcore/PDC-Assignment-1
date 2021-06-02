/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.loot;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A loot table compiles all entries and keeps track of max roll to be used
 * when generating random loot.
 * @author whackaweasel
 */
public class LootTable implements Serializable{
    
    ArrayList<LootEntry> lootTable;
    private int maxRoll = 0;
    
    public LootTable() {
        lootTable = new ArrayList();
    }
    
    public void addEntry(String name, int chance) {
        this.lootTable.add(new LootEntry(name, chance, this.getMaxRoll()));
        maxRoll += chance;      
    }

    /**
     * @return the maxRoll
     */
    public int getMaxRoll() {
        return maxRoll;
    }
    
    public String getName(int roll) {
        
        for (LootEntry entry: this.lootTable) {
            
            int minEntryRoll = entry.getMinRoll();
            int maxEntryRoll = entry.getMaxRoll();
            
            if ((roll >= minEntryRoll) && (roll <= maxEntryRoll)) {
                return entry.getName();
            }  
        }
        return null;
    }    
}
