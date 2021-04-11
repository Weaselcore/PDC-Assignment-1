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
public final class Player extends AbstractEntity {
    
    // TODO Create two constructors for new and old players.
    
    // New player.
    public Player() {
        this.create();
        health = 10;
        damage = 3;
    };

    // Import save game file here.
    // public Player() {
    //  Retrieve data from file and assign name, health and damage.
    // };

     
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
        System.out.println("You: " + this.name+ " | Health: " + this.health + " | Damage: " + this.damage);
    }

    // When new player is being initialised, it will prompt for a name.
    @Override
    public void create() {
        boolean confirmed = false;
        while (!confirmed) {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter a name for your adventurer: ");
            String char_name = input.nextLine();
            System.out.println("Are you happy with this name?: " + char_name);
            System.out.println("Press y to continue or n to choose another name: ");
            String confirmation = input.nextLine();
            switch (confirmation.trim()) {
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
    public void takeDamage(int damage) {
        this.health -= damage;
    }
    
    @Override
    public boolean isDead() {
        return this.health <= 0;
    }

    @Override
    public void attack(Entity currentEnemy) {
        currentEnemy.displayInfo();
        currentEnemy.takeDamage(this.damage);
        System.out.println("You have attacked " + currentEnemy.getName() + " with " + this.damage + ".");
        currentEnemy.displayInfo();
    }

    @Override
    public boolean turn(Entity currentEnemy) {
        Scanner input = new Scanner(System.in);
        
        System.out.println(
                "1. Attack \n2. Use Item\n3. Run Away");

        boolean chosen = false;
        boolean toReturn = false;

        while (!chosen) {

            String chosenOption = input.next();

            if ("1".equals(chosenOption)) {
                this.attack(currentEnemy);
                boolean targetDead = currentEnemy.isDead();
                if (targetDead) {
                    System.out.println("\n" + this.getName() + " has slain " + currentEnemy.getName() + "!");
                    toReturn = true;
                }
                else {
                    toReturn = false;
                }
                chosen = true;
            }
            else if ("2".equals(chosenOption)) {
                System.out.println("Items are not implemented.");
            }
            else if ("3".equals(chosenOption)) {
                System.out.println("Running away is not implemented.");
            }
            else {
                System.out.println("Please input a proper input!");
            }
        } 
        return toReturn;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
