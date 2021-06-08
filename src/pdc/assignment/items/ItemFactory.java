/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.items;

import pdc.assignment.databaseclasses.GameData;

/**
 * Returns the appropriate item using factory pattern to improve scalability.
 * @author Weaselcore
 */
public class ItemFactory {
    
    public static Item createItem(String itemType, String name, GameData gameData) throws Exception {
        switch (itemType) {
            case "weapon":
                return new Weapon(name, gameData.getWeaponData());
            case "armour":
                return new Armour(name, gameData.getArmourData());
            case "potion":
                return new Potion(name, gameData.getPotionData());
            default:
                throw new Exception("Invalid item type.");
        }
    }

}
