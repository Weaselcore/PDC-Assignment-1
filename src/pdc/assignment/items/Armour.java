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
public class Armour extends AbstractItem implements Item{
    
    private final String name;
    private final String description;
    private final double value;

    public Armour(String name, HashMap info){
        this.name = name;
        this.value = (Integer) info.get("value");
        this.description = (String) info.get("description");
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
    
    
    
}
