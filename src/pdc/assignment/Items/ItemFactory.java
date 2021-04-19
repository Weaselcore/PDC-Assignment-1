/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.Items;

import pdc.assignment.Utilities.GameData;

/**
 *
 * @author Weaselcore
 */
public class ItemFactory {

    public static Item createItem(String itemType, GameData gameData) throws Exception {
        switch (itemType) {
            case "weapon":
                System.out.println("You have recieved a weapon.");
            case "armour":
                System.out.println("You have recieved a armour.");
            case "potion":
                System.out.println("You have recieved a potion.");
            case "starter":
                return new Potion("Small Health Potion", gameData.getPotionData());
                
            default:
                throw new Exception("Invalid item type.");
        }
    }

}
