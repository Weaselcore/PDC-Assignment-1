/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.loot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import pdc.assignment.items.Item;
import pdc.assignment.utilities.GameData;

/**
 *
 * @author whackaweasel
 */
public class LootTableGenerator {
    
    GameData gameData;
    LootTable potionLootTable;
    LootTable weaponLootTable;
    LootTable armourLootTable;
    
    public LootTableGenerator(GameData gameData) {
        this.gameData = gameData;
        
        HashMap potions = this.gameData.getPotionData();
        HashMap armours = this.gameData.getArmourData();
        HashMap weapons = this.gameData.getWeaponData();
        
        potionLootTable = this.generateLootTable(potions);
        weaponLootTable = this.generateLootTable(weapons);
        armourLootTable = this.generateLootTable(armours);
    }
    
    public ArrayList<Item> generateItem(HashMap map) {
        
        ArrayList<Item> itemsToReturn = new ArrayList();
        

        
        

        return itemsToReturn;
        
    }
    
    private LootTable generateLootTable(HashMap map) {
        
        double chance = 0;
        String name = null;
        LootTable newTable = new LootTable();
        
        Set set = map.entrySet();
        
        for (Iterator i = set.iterator(); i.hasNext();) {

            Map.Entry entry = (Map.Entry) i.next();
            name = (String) entry.getKey();
            
            HashMap nestedMap = (HashMap) entry.getValue();
            
            for (Iterator j = nestedMap.entrySet().iterator(); j.hasNext();){
                Map.Entry nestedHashMapEntry = (Map.Entry) j.next();
                if (nestedHashMapEntry.getKey() == "chance") {
                    newTable.addEntry(name, (Integer) nestedHashMapEntry.getValue());
                }
            }
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }              
        return newTable;
    }
    
    public Item rollItem(LootTable lootTable) {
            
        Random randomiser = new Random();
        int roll = randomiser.nextInt(lootTable.getMaxRoll());
        
        itemData = lootTable.getItemData(roll);
        
    }
}
