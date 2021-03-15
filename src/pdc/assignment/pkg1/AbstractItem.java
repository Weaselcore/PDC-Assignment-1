/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1;

/**
 *
 * @author ieuan
 */
public abstract class AbstractItem {
    
    String name;
    String description;
    
    abstract void getDesc();
    
    abstract void setDesc();
    
    abstract void onUse();
    
}
