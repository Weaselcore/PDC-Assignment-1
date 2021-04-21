/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.Entities;

/**
 *
 * @author ieuan
 */
public abstract class AbstractEntity implements Entity{
    
    String name;
    String description;
    double currentHealth;
    double damage;
    double armour;
    double maxHealth;
    
    abstract void specialAttack();
    
    abstract void openingLine();
    
    abstract void defeatLine();
    
    abstract void victoryLine();
    
}