/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1.Entities;

/**
 *
 * @author ieuan
 */
public abstract class AbstractEntity {
    
    public String name;
    int health;
    int damage;
    
    abstract void attack();
    
    abstract void specialAttack();
    
    abstract void openingLine();
    
    abstract void defeatLine();
    
    abstract void victoryLine();
    
    abstract void displayInfo();
    
}
