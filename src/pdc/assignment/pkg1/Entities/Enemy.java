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
    
    public Enemy() {
    // This will be randomly generated unlike the players name.
    };
        
    @Override
    public void create() {
        this.name = "Rog-aarrgh";
        this.health = 10;
        this.damage = 2;
    }
    
    // Pass in target entity to reduce their health.
    public void attack(Player player) {
        // TODO randomise damage in range.
        player.takeDamage(this.damage);
        System.out.println("Roger has attacked you with " + this.damage + ".");
        player.displayInfo();
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
        System.out.println("Enemy: " + this.getName() + " | Health: " + this.health + "| Damage: " + this.damage);
    }

    @Override
    void takeDamage(int damage) {
        this.health -= damage;
    }    
}
