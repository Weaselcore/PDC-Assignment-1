/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.entities;

/**
 *
 * @author whackaweasel
 */
public interface Entity {
    
    public void create();

    public boolean isDead();

    public void attack(Entity entity);

    public void displayInfo();

    public void takeDamage(double damage);
    
    public boolean turn(Entity entity);
    
    public String getName();
    
}
