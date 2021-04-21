/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.loot;

import java.util.ArrayList;

/**
 *
 * @author whackaweasel
 */
public class LootTable {
    
    ArrayList<LootEntry> lootTable;
    private Integer maxRoll = 0;
    
    public LootTable() {
        lootTable = new ArrayList();
    }
    
    public void addEntry(String name, Integer chance) {
        this.lootTable.add(new LootEntry(name, chance, this.getMaxRoll()));
        maxRoll += chance;      
    }

    /**
     * @return the maxRoll
     */
    public Integer getMaxRoll() {
        return maxRoll;
    }
    
    public getItemData(Integer roll) {
        
        for (LootEntry entry: this.lootTable) {
            
            if (entry.get)
            
        }
        
    }
    
}
