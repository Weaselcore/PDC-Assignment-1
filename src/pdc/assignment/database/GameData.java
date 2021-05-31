/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.database;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * This class reads and stores information for enemies and items.
 * This object is used to pass information to other classes that require data
 * to construct objects.
 * @author whackaweasel
 */
public class GameData {
    
//    static String armourPath = "src/pdc/assignment/resources/armour.json";
//    static String potionPath = "src/pdc/assignment/resources/potion.json";
//    static String weaponPath = "src/pdc/assignment/resources/weapon.json";
//    static String enemyPath = "src/pdc/assignment/resources/enemy.json";
    
    private HashMap armourData;
    private HashMap potionData;
    private HashMap weaponData;
    private HashMap enemyData;
    
    public GameData() throws SQLException {
//        this.armourData = Deserialiser.jsonDataToHashmap(GameData.armourPath);
//        this.potionData = Deserialiser.jsonDataToHashmap(GameData.potionPath);
//        this.weaponData = Deserialiser.jsonDataToHashmap(GameData.weaponPath);
//        this.enemyData = Deserialiser.jsonDataToHashmap(GameData.enemyPath);

        Wrapper.connect();
        this.armourData = Wrapper.load("armour");
        this.potionData = Wrapper.load("potion");
        this.weaponData = Wrapper.load("weapon");
        this.enemyData = Wrapper.load("enemy");
        Wrapper.close();
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
