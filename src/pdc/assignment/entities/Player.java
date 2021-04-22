/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import pdc.assignment.items.Armour;
import pdc.assignment.items.Item;
import pdc.assignment.items.Potion;
import pdc.assignment.items.Weapon;

/**
 *
 * @author whackaweasel
 */
public final class Player extends AbstractEntity {
    
    public LinkedList<Potion> inventory;
    public int currentSuperAttackLevel;
    public final int maxSuperAttackLevel;
    public Armour currentArmour = null;
    public Weapon currentWeapon = null;
    
    // TODO Create two constructors for new and old players.
    
    // New player.
    public Player() throws Exception {
        this.create();
        this.currentHealth = 10;
        this.maxHealth = 10;
        this.damage = 20;
        this.maxSuperAttackLevel = 4;
        this.currentSuperAttackLevel = 0;
        this.inventory = new LinkedList<>();
    };

    // Import save game file here.
    // public Player() {
    //  Retrieve data from file and assign name, health and damage.
    // };
    
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
        if (this.currentArmour != null) {
            this.currentHealth -= (damage-this.armour);
            System.out.println(this.name + "'s armour has reduced damage by " + this.armour);
        } else {
            this.currentHealth -= damage;
        }
    }
    
    @Override
    public boolean isDead() {
        return this.currentHealth <= 0;
    }

    @Override
    public void attack(Entity currentEnemy) {
       
        double damageToDeal = this.getDamage();
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
        
        Scanner inputScanner = new Scanner(System.in);

        boolean chosen = false;
        boolean toReturn = false;

        while (!chosen) {
                    
            this.displaySuperAttack();
            
            System.out.println(
                "[#1]. Attack [#2]. Use Potions [#3]. Run Away");
            System.out.println("Option (#): ");

            String chosenOption = inputScanner.nextLine();

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
                if (!this.inventory.isEmpty()) {
                    Integer count = 0;
                    
                    for (Potion item: this.inventory) {
                        System.out.println("Count: " + "[#" + (count + 1) + "]" + item);
                        count += 1;
                    }
                    
                    boolean potionChosen = false;
                    Scanner potionScanner = new Scanner(System.in);
                    
                    while (!potionChosen) {
                        
                        System.out.println("Please select potion using corresponding #:");
                        
                        
                        Integer potionChoice = potionScanner.nextInt();
                        
                        if (potionChoice <= count) {
                            Potion potion = this.inventory.get(potionChoice - 1);
                            this.inventory.remove(potion);
                            this.usePotion(potion);
                            potionChosen = true;
                        } else {
                            System.out.println("Please input a valid #.");
                        }
                    }
                    
                } else {
                    System.out.println("You have no potions.");
                }
            }
            else if ("3".equals(chosenOption)) {
                System.out.println("Thanks for playing");
                System.exit(0);
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
    
    public double getDamage(){
        double combinedDamage = this.damage;
        
        if (currentWeapon != null){
            combinedDamage += currentWeapon.getValue();
        }
        
        return combinedDamage;
    }
    
    public void obtainItems(ArrayList<Item> items) throws Exception{
        for (Item item: items){
            if (item.getClass() == Potion.class){
                this.inventory.add((Potion) item);
            }
            else if (item.getClass() == Weapon.class){
                Weapon newWeapon = (Weapon) item;
                if (this.currentWeapon == null || this.currentWeapon.getValue() < newWeapon.getValue() ){
                    this.currentWeapon = newWeapon;
                    System.out.println("You have aquired a better weapon." + newWeapon.toString());
                }
                else{
                    System.out.println("Your current weapon is better.");
                }
            }
            else if (item.getClass() == Armour.class){
                Armour newArmour = (Armour) item;
                if (this.currentArmour == null || this.currentWeapon.getValue() < newArmour.getValue() ){
                    this.currentArmour = newArmour;
                    System.out.println("You have aquired a better set of armour." + newArmour.toString());
                }
                else{
                    System.out.println("Your current armour is better.");
                }
            }
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    private void usePotion(Potion potion) {
        String type = potion.getType();
        Integer value = potion.getValue();
        
        if ("health".equals(type)) {
            this.currentHealth += value;
            System.out.println(this.name + "has healed for " + value + ". Health: " + this.currentHealth);
        } else {
            if ((this.currentSuperAttackLevel + value) > this.maxSuperAttackLevel) {
                this.currentSuperAttackLevel = this.maxSuperAttackLevel;
                System.out.println("Attack potion has maxed out your super attack bar.");
            } else {
                this.currentSuperAttackLevel += value;
                System.out.println("Attack potion has added " + value + " to " + this.name + "'s super attack bar.");
            }
        }
    }
}
