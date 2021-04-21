/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.Utilities;

import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author whackaweasel
 */
public class GameData {
    
    static String armourPath = "src/Resources/armour.json";
    static String potionPath = "src/Resources/potion.json";
    static String weaponPath = "src/Resources/weapon.json";
    static String enemyPath = "src/Resources/enemy.json";
    
    private HashMap armourData;
    private HashMap potionData;
    private HashMap weaponData;
    private HashMap enemyData;
    
    public GameData() throws JsonMappingException, IOException {
        this.armourData = Deserialiser.jsonDataToHashmap(GameData.armourPath);
        this.potionData = Deserialiser.jsonDataToHashmap(GameData.potionPath);
        this.weaponData = Deserialiser.jsonDataToHashmap(GameData.weaponPath);
        this.enemyData = Deserialiser.jsonDataToHashmap(GameData.enemyPath);
    }  

    public HashMap getArmourData() {
        return this.armourData;
    }    
    
    public HashMap getPotionData() {
        return this.potionData;
    }
    
    public HashMap getWeaponData() {
        return this.weaponData;
    }    
    
    public HashMap getEnemyData() {
        return this.enemyData;
    }
    
}
