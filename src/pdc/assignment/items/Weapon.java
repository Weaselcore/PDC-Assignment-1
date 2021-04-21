/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.items;

import java.util.HashMap;

/**
 *
 * @author whackaweasel
 */
public final class Weapon extends AbstractItem implements Item{
    
    public Weapon(String name, HashMap info){
        this.name = name;
        this.value = getIntegerData(info, "value");
        this.description =  getStringData(info,"description");
    }
    
    public int getValue(){
        return value;
    }
    
    @Override
    public String toString(){
        return "\n" + this.name + ": " + this.description + "\nAttack damage: " + this.value;
    }
    
    @Override
    public int getIntegerData(HashMap map, String key) {
        HashMap mapNeeded = (HashMap) map.get(this.name);
        int entry = (int) mapNeeded.get(key);
        return entry;
    }
    
    @Override
    public String getStringData(HashMap map, String key) {
        HashMap mapNeeded = (HashMap) map.get(this.name);
        String entry = (String) mapNeeded.get(key);
        return entry;
    }
    
}
