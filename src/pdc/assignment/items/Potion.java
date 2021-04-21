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
public final class Potion extends AbstractItem{
     
    private final String type;
    
    // Specific constructor for loading up saves.
    Potion(String name, HashMap info) {
        this.name = name;
        
        this.value = getIntegerData(info, "value");
        this.description = getStringData(info, "description");
        this.type = getStringData(info, "type");
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
    
    @Override
    public String toString() {
        return "\nName: " + this.name + "\nDescription: " + this.description;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the value
     */
    public Integer getValue() {
        return value;
    }
    
}
