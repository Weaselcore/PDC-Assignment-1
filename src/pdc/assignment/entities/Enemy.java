/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import pdc.assignment.database.GameData;
import pdc.assignment.gameclasses.HistoryLogger;

/**
 *
 * @author whackaweasel
 */
public final class Enemy extends AbstractEntity {
    
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
        this.currentHealth =  (int) enemyDetails.get("health");
        this.maxHealth = (int) enemyDetails.get("health");
        this.description = (String) enemyDetails.get("description");
        this.armour = (int) enemyDetails.get("armour");
        this.damage = (int) enemyDetails.get("attack");
        
        HistoryLogger.append("\n ~ Some " + this.name + " has appeared! ~ ");
        HistoryLogger.append(" * " + this.description);
    }
    
    // Displays all information for the enemy.
    @Override
    public void displayInfo() {
        HistoryLogger.append("| " + this.name + ": | Health: " 
                + this.currentHealth + "/" + this.maxHealth + " | Damage: " 
                + this.damage + " |");
    }

    // Calculates the damage by subtracting the armour value as a flat amount.
    @Override
    public void takeDamage(double damage) {
        this.currentHealth -= (damage-this.armour);
        HistoryLogger.append(this.name + "'s armour has reduced damage by " + this.armour);
    }
    
    // Will check if the enemy is dead.
    @Override
    public boolean isDead() {
        return this.currentHealth <= 0;
    }

    // Takes in a player to attack by making them take damage via a method.
    @Override
    public void attack(Entity player) {
        player.takeDamage(this.damage);
        HistoryLogger.append(" ~ " + this.name + " has attacked you with " + this.damage + ".");
    }
    
    // Returns the name.
    @Override
    public String getName() {
        return this.name;
    }

    // Enemy has an inbuilt turn method to automate a turn.
    public boolean turn(Entity player) {
        this.attack(player);
        boolean targetDead = player.isDead();
        if (targetDead) {
            HistoryLogger.append(" ~~~ " + this.getName() + " has slain " + player.getName() + "!");
            return true;
        }
        else {
            return false;
        }
    }
    
    // Pass in target entity to reduce their health.
    public void attack(Player player) {
        player.takeDamage(this.damage);
        HistoryLogger.append(" ~ " + this.name + " has attacked " + this.name + " with " + this.damage + ".");
    }
    
    // Generator that will return a name of a random enemy.
    private String randomisedEnemy() {
        ArrayList<String> enemyList = new ArrayList<>(Arrays.asList("Orc", "Wolf", "Thief", "Korg"));
        Random generator = new Random();
        int randomNumber = generator.nextInt(4);
        return enemyList.get(randomNumber);
    }
}
