/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.items;

/**
 *
 * @author ieuan
 */
public abstract class AbstractItem implements Item{

    
    abstract void getDesc();
    
    abstract void setDesc();
    
    abstract void onUse();
    
}
