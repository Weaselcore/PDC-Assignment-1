/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.items;

import pdc.assignment.utilities.GameData;

/**
 *
 * @author Weaselcore
 */
public class ItemFactory {

    public static Item createItem(String itemType, String name, GameData gameData) throws Exception {
        switch (itemType) {
            case "weapon":
                System.out.println("You have recieved a weapon.");
                return new Weapon(name, gameData.getWeaponData());
            case "armour":
                System.out.println("You have recieved a armour.");
                return new Armour(name, gameData.getArmourData());
            case "potion":
                System.out.println("You have recieved a potion.");
                return new Potion(name, gameData.getPotionData());
            default:
                throw new Exception("Invalid item type.");
        }
    }

}
