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
public final class Enemy extends AbstractEntity{
    
    public Enemy() {
    // This will be randomly generated unlike the players name.
        this.create();
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

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
    }
    
    @Override
    public boolean isDead() {
        if (this.health <= 0) {
          return true;
        } else {
            return false;
        }
    }

    @Override
    public void attack(Entity player) {
        player.takeDamage(this.damage);
        System.out.println("Roger has attacked you with " + this.damage + ".");
        player.displayInfo();
    }
}
