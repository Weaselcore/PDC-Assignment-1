/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1.Entities;

/**
 *
 * @author whackaweasel
 */
public class Player extends AbstractEntity {

    // TODO create interface to have default health and damage.
    public Player(String name) {
        this.name = name;
        health = 10;
        damage = 3;
    };
    
    @Override
    // Pass in target entity to reduce their health.
    void attack() {
        System.out.println("You have attacked.");
        // Attacc
    }
     
    @Override
    void specialAttack() {

    }
    
    @Override
    void openingLine() {
        
    }
    
    @Override
    void defeatLine() {
        
    }
    
    @Override
    void victoryLine() {
        
    }
    
    @Override
    public void displayInfo() {
        System.out.println("You: " + this.name + " | Health: " + this.health + "| Damage: " + this.damage);
    }
    
}
