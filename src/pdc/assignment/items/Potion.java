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
     
    private final String name;
    private final Integer value;
    private final String description;
    
    // Specific constructor for loading up saves.
    Potion(String name, HashMap info) {
        this.name = name;
        this.value = (Integer) info.get("value");
        this.description = (String) info.get("description");
    }

    @Override
    void getDesc() {
        
    }

    @Override
    void setDesc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    // Pass player object to edit values. Either health or super attack.
    void onUse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
