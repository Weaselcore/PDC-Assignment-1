/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.loot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import pdc.assignment.items.Armour;
import pdc.assignment.items.Item;
import pdc.assignment.items.ItemFactory;
import pdc.assignment.items.Potion;
import pdc.assignment.items.Weapon;
import pdc.assignment.database.GameData;

/**
 * A generator that creates a loot table for each item type from game data.
 *
 * @author whackaweasel
 */
public class LootTableGenerator implements Serializable{

    GameData gameData;
    private final LootTable potionLootTable;
    private final LootTable weaponLootTable;
    private final LootTable armourLootTable;

    public LootTableGenerator(GameData gameData) {
        this.gameData = gameData;

        HashMap potions = this.gameData.getPotionData();
        HashMap armours = this.gameData.getArmourData();
        HashMap weapons = this.gameData.getWeaponData();

        potionLootTable = this.generateLootTable(potions);
        weaponLootTable = this.generateLootTable(weapons);
        armourLootTable = this.generateLootTable(armours);
    }

    // Generates loot table in the constructor for each item type.
    private LootTable generateLootTable(HashMap map) {

        LootTable newTable = new LootTable();
        // Creates a set to iterate over.
        Set set = map.entrySet();

        for (Iterator i = set.iterator(); i.hasNext();) {

            Map.Entry entry = (Map.Entry) i.next();
            String name = (String) entry.getKey();
            // Assigns the nested map to be iterated over.
            HashMap nestedMap = (HashMap) entry.getValue();
            // Iterates over the inner map to get the chance value in the json file.
            for (Iterator j = nestedMap.entrySet().iterator(); j.hasNext();) {
                Map.Entry nestedHashMapEntry = (Map.Entry) j.next();
                if (nestedHashMapEntry.getKey() == "chance") {
                    // Adds item in the new loot table so a random item can be easily generated.
                    newTable.addEntry(name, (Integer) nestedHashMapEntry.getValue());
                }
            }
        }
        // Returns loot table to the corresponding private field.
        return newTable;
    }

    // This is used to roll for an item and uses the Itemfactory to return an item.
    private Item rollItem(LootTable lootTable, String type) throws Exception {

        // The loot table rolls start from 1.
        int lowerBound = 1;
        // Each loot table has a different max roll depending on amount of items
        // and their individual weighting.
        int upperBound = lootTable.getMaxRoll();

        // LowerBound prevents the roll to be 0 as minRoll for entries start at 1.
        int roll = (int) Math.floor(Math.random() * (upperBound - lowerBound + 1) + lowerBound);

        String itemName = lootTable.getName(roll);

        return ItemFactory.createItem(type, itemName, gameData);
    }

    // Generates a list of loot to be passed back to the player.
    public ArrayList<Item> generateItem() throws Exception {

        ArrayList<Item> itemsToReturn = new ArrayList();

        Potion potion;
        Weapon weapon;
        Armour armour;

        // Rolls an item from each item type's to be given back to the player.
        potion = (Potion) this.rollItem(this.potionLootTable, "potion");
        weapon = (Weapon) this.rollItem(this.weaponLootTable, "weapon");
        armour = (Armour) this.rollItem(this.armourLootTable, "armour");

        itemsToReturn.add(potion);
        itemsToReturn.add(weapon);
        itemsToReturn.add(armour);

        return itemsToReturn;

    }

}
