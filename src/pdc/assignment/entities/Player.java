/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.entities;

import java.util.ArrayList;
import java.util.Scanner;
import pdc.assignment.items.Armour;
import pdc.assignment.items.Item;
import pdc.assignment.items.Weapon;

/**
 *
 * @author whackaweasel
 */
public final class Player extends AbstractEntity {
    
    private ArrayList<Item> inventory;
    private int currentSuperAttackLevel;
    private final int maxSuperAttackLevel;
    private Armour currentArmour = null;
    private Weapon currentWeapon = null;
    
    // TODO Create two constructors for new and old players.
    
    // New player.
    public Player() throws Exception {
        this.create();
        this.currentHealth = 10;
        this.maxHealth = 10;
        this.damage = 3;
        this.maxSuperAttackLevel = 4;
        this.currentSuperAttackLevel = 0;
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
        System.out.println(this.name+ ": | Health: " + this.currentHealth 
                + "/" +this.maxHealth + " | Damage: " + this.damage);
    }

    // When new player is being initialised, it will prompt for a name.
    @Override
    public void create() {
        boolean confirmed = false;
        while (!confirmed) {
            Scanner input = new Scanner(System.in);
            System.out.print("\nPlease enter a name for your adventurer: ");
            String char_name = input.nextLine();
            System.out.println("\nAre you happy with this name?: " + char_name);
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
    public void takeDamage(double damage) {
        this.currentHealth -= damage;
    }
    
    @Override
    public boolean isDead() {
        return this.currentHealth <= 0;
    }

    @Override
    public void attack(Entity currentEnemy) {
       
        double damageToDeal = this.damage;
        String attackString = this.name +" has attacked " + currentEnemy.getName() + " with " + damageToDeal + ".";
        
        if (this.currentSuperAttackLevel == this.maxSuperAttackLevel) {
            damageToDeal = damageToDeal * 2;
            attackString = this.name + " has CRIT " + currentEnemy.getName() + " with " + damageToDeal + ".";
            this.currentSuperAttackLevel = 0;
        } else {
            this.currentSuperAttackLevel += 1;
        }
       
        currentEnemy.takeDamage(damageToDeal);
        System.out.println(attackString);
    }

    @Override
    public boolean turn(Entity currentEnemy) {
        Scanner input = new Scanner(System.in);
        
        this.displaySuperAttack();
        
        System.out.println(
                "1. Attack 2. Use Potions 3. Run Away");
        System.out.println("Option (#): ");

        boolean chosen = false;
        boolean toReturn = false;

        while (!chosen) {

            String chosenOption = input.next();

            if ("1".equals(chosenOption)) {
                this.attack(currentEnemy);
                boolean targetDead = currentEnemy.isDead();
                if (targetDead) {
                    System.out.println("\n" + this.getName() + " has slain " + currentEnemy.getName() + "!\n");
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
    
    public void displaySuperAttack() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("\nSuper Attack  : ");
        for (int i = 0; i < maxSuperAttackLevel; i++) {
            if (i >= this.currentSuperAttackLevel) {
                stringbuilder.append("[ ]");
            } else {
                stringbuilder.append("[*]");
            }
        }
        System.out.println(stringbuilder.toString());
        System.out.println("");
    }

    @Override
    public String getName() {
        return this.name;
    }
}
