/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.Utilities;

/**
 *
 * @author whackaweasel
 */
public class ItemData {
    
    private String name;
    private String description;
    private double value;
    private double chance;

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @param chance the chance to set
     */
    public void setChance(double chance) {
        this.chance = chance;
    }
    
}
