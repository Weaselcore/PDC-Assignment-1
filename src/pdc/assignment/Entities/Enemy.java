/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import pdc.assignment.Utilities.GameData;

/**
 *
 * @author whackaweasel
 */
public final class Enemy extends AbstractEntity{
    
    HashMap enemyData;
    
    public Enemy(GameData gameData) {
    // This will be randomly generated unlike the players name.
        this.enemyData = gameData.getEnemyData();
        this.create();
        
    };
        
    @Override
    public void create() {
        this.name = randomisedEnemy();
        HashMap enemyDetails = (HashMap) this.enemyData.get(this.name);
        this.health =  (double) enemyDetails.get("health");
        this.description = (String) enemyDetails.get("description");
        //this.armour = this.enemyData.get(this.name).get("armour");
        //this.description = this.enemyData.get(this.name).get("description");
    }
    
    // Pass in target entity to reduce their health.
    public void attack(Player player) {
        // TODO randomise damage in range.
        player.takeDamage(this.damage);
        System.out.println(this.name + " has attacked Roger with " + this.damage + ".");
    }
    
    private String randomisedEnemy() {
        ArrayList<String> enemyList = new ArrayList<>(Arrays.asList("Orc", "Wolf", "Thief", "Korg"));
        Random generator = new Random();
        int randomNumber = generator.nextInt(4);
        return enemyList.get(randomNumber);
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
        System.out.println("Enemy: " + this.name + " | Health: " + this.health + " | Damage: " + this.damage);
    }

    @Override
    public void takeDamage(double damage) {
        this.health -= damage;
    }
    
    @Override
    public boolean isDead() {
        return this.health <= 0;
    }

    @Override
    public void attack(Entity player) {
        player.takeDamage(this.damage);
        System.out.println("Roger has attacked you with " + this.damage + ".");
    }

    @Override
    public boolean turn(Entity player) {
        this.attack(player);
        boolean targetDead = player.isDead();
        if (targetDead) {
            System.out.println(this.getName() + " has slain " + player.getName() + "!");
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public String getName() {
        return this.name;
    }
}
