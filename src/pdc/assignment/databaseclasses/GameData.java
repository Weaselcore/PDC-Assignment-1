/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.databaseclasses;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * This class reads and stores information for enemies and items.
 * This object is used to pass information to other classes that require data
 * to construct objects.
 * @author whackaweasel
 */
public class GameData implements Serializable{
    
    private final HashMap armourData;
    private final HashMap potionData;
    private final HashMap weaponData;
    private final HashMap enemyData;
    
    public GameData() throws SQLException {
        Wrapper.connect();
        this.armourData = Wrapper.loadGameData("armour");
        this.potionData = Wrapper.loadGameData("potion");
        this.weaponData = Wrapper.loadGameData("weapon");
        this.enemyData = Wrapper.loadGameData("enemy");
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
