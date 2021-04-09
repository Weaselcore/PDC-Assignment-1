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
public class Enemy extends AbstractEntity{
    
        public Enemy(String name, int health, int damage) {
        // This will be randomly generated unlike the players name.
        this.name = name;
        this.health = health;
        this.damage = damage;
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
        System.out.println("Enemy: " + this.name + " | Health: " + this.health + "| Damage: " + this.damage);
    }
}
