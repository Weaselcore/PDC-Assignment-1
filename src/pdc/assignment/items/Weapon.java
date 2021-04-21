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
public class Weapon implements Item{

    private final String name;
    private final Integer value;
    private final String description;
    
    public Weapon(String name, HashMap info){
        this.name = name;
        this.value = (Integer) info.get("value");
        this.description = (String) info.get("description");
    }
    
    @Override
    public void displayInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Integer getValue(){
        return value;
    }
    
    @Override
    public String toString(){
        return "\n" + this.name + ": " + this.description + "\nAttack damage: " + this.value;
    }
    
}
