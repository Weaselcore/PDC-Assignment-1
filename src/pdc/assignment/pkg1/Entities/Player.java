/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1.Entities;

import java.util.Scanner;

/**
 *
 * @author whackaweasel
 */
public class Player extends AbstractEntity {
    
    // TODO Create two constructors for new and old players.
    
    // New player.
    public Player() {
        health = 10;
        damage = 3;
    };

    // Import save game file here.
    // public Player() {
    //  Retrieve data from file and assign name, health and damage.
    // };
    
    public void attack(Enemy enemy) {
        // TODO randomise damage in range.
        enemy.displayInfo();
        enemy.takeDamage(this.damage);
        System.out.println("You have attacked, Roger, with " + this.damage + ".");
        enemy.displayInfo();
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
        System.out.println("You: " + this.getName() + " | Health: " + this.health + "| Damage: " + this.damage);
    }

    // When new player is being initialised, it will prompt for a name.
    @Override
    public void create() {
        boolean confirmed = false;
        while (!confirmed) {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter a name for your adventurer: ");
            String char_name = input.nextLine();
            System.out.println("Are you happy with this name? " + char_name);
            System.out.println("Press y to continue or n to choose another name: ");
            String confirmation = input.nextLine();
            switch (confirmation.strip()) {
                case "y":
                    this.name = char_name;
                    confirmed = true;
                    break;
                case "n":
                    System.out.println("Scrapping previous name.");
                    break;
                default:
                    System.out.println("Please input a valid option, restarting character creation.");
                    break;
            }
        }
    }

    @Override
    void takeDamage(int damage) {
        this.health -= damage;
    }
}
