/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.loot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import pdc.assignment.items.Armour;
import pdc.assignment.items.Item;
import pdc.assignment.items.ItemFactory;
import pdc.assignment.items.Potion;
import pdc.assignment.items.Weapon;
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
    
    public ArrayList<Item> generateItem() throws Exception {
        
        ArrayList<Item> itemsToReturn = new ArrayList();
        
        Potion potion;
        Weapon weapon;
        Armour armour;
        
        potion = (Potion) this.rollItem(this.potionLootTable,"potion");
        weapon = (Weapon) this.rollItem(this.weaponLootTable, "weapon");
        armour = (Armour) this.rollItem(this.armourLootTable, "armour");
        
        itemsToReturn.add(potion);
        itemsToReturn.add(weapon);
        itemsToReturn.add(armour);

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
        }              
        return newTable;
    }
    
    public Item rollItem(LootTable lootTable, String type) throws Exception {
            
        Random randomiser = new Random();
        int roll = randomiser.nextInt(lootTable.getMaxRoll());
        
        String itemName = lootTable.getName(roll);
        
        return ItemFactory.createItem(type, itemName, gameData);
    }
}
