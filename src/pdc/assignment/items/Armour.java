/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.items;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author whackaweasel
 */
public final class Armour extends AbstractItem implements Item{
    
    private final String name;
    private final String description;
    private final Integer value;

    public Armour(String name, HashMap info){
        this.name = name;
        this.value = getIntegerData(info, "value");
        this.description = getStringData(info, "description");
    }

    @Override
    void getDesc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void setDesc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void onUse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public double getValue() {
        return value;
    }
    
    @Override
    public String toString(){
        return "\n" + this.name + ": " + this.description + "\nDamage Reduction: " + this.value;
    }

    @Override
    public void displayInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getIntegerData(HashMap map, String key) {
        HashMap mapNeeded = (HashMap) map.get(this.name);
        Integer entry = (Integer) mapNeeded.get(key);
        return entry;
    }
    
    @Override
    public String getStringData(HashMap map, String key) {
        HashMap mapNeeded = (HashMap) map.get(this.name);
        String entry = (String) mapNeeded.get(key);
        return entry;
    }
  
}
