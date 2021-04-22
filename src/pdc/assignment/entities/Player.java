/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import pdc.assignment.items.Armour;
import pdc.assignment.items.Item;
import pdc.assignment.items.ItemFactory;
import pdc.assignment.items.Potion;
import pdc.assignment.items.Weapon;
import pdc.assignment.main.GameSession;
import pdc.assignment.utilities.GameData;

/**
 *
 * @author whackaweasel
 */
public final class Player extends AbstractEntity {

    private LinkedList<Potion> inventory = new LinkedList<>();
    private int currentSuperAttackLevel;
    private int maxSuperAttackLevel;
    private Armour currentArmour = null;
    private Weapon currentWeapon = null;

    // TODO Create two constructors for new and old players.
    // New player.
    public Player() throws Exception {
        this.create();
        this.currentHealth = 10;
        this.maxHealth = 10;
        this.damage = 50;
        this.armour = 0;
        this.setMaxSuperAttackLevel(4);
        this.setCurrentSuperAttackLevel(0);
    }

    ;

    public Player(Map loadData, GameData gameData) throws Exception {
        this.name = (String) loadData.get("name");
        // Set health
        this.setCurrentHealth((int) loadData.get("health"));
        this.maxHealth = 10;
        this.damage = 50;
        this.armour = 0;
        // Set currentSuperAttack
        this.setCurrentSuperAttackLevel((int) loadData.get("superAttack"));
        this.setMaxSuperAttackLevel(4);

        // Set potions
        if (!((ArrayList) loadData.get("potions")).isEmpty()) {
            ((ArrayList) loadData.get("potions")).forEach(potionString -> {
                Potion potion;
                try {
                    potion = (Potion) ItemFactory.createItem("potion", (String) potionString, gameData);
                    this.setInventory("add", potion);
                } catch (Exception ex) {
                    Logger.getLogger(GameSession.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

            });
        }
        // Set weapons
        if (!(loadData.get("weapon") == null)) {
            Weapon weapon = (Weapon) ItemFactory.createItem("weapon", (String) loadData.get("weapon"), gameData);
            this.setCurrentWeapon(weapon);
        }
        // Set armour
        if (!(loadData.get("armour") == null)) {
            Armour loadedArmour = (Armour) ItemFactory.createItem("armour", (String) loadData.get("armour"), gameData);
            this.setCurrentArmour(loadedArmour);
        }
        System.out.println("\n" + this.name + " has appeared to fight for another day!");
    }

    @Override
    public void displayInfo() {
        System.out.println(this.name + ": | Health: " + this.currentHealth
                + "/" + this.maxHealth + " | Damage: " + this.damage);
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
        if (this.getCurrentArmour() != null) {
            this.currentHealth -= (damage - this.armour);
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
        String attackString = this.name + " has attacked " + currentEnemy.getName() + " with " + damageToDeal + ".";

        if (this.getCurrentSuperAttackLevel() == this.getMaxSuperAttackLevel()) {
            damageToDeal = damageToDeal * 2;
            attackString = this.name + " has CRIT " + currentEnemy.getName() + " with " + damageToDeal + ".";
            this.setCurrentSuperAttackLevel(0);
        } else {
            this.setCurrentSuperAttackLevel(this.getCurrentSuperAttackLevel() + 1);
        }

        currentEnemy.takeDamage(damageToDeal);
        System.out.println(attackString);
    }

    public void displaySuperAttack() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("\nSuper Attack  : ");
        for (int i = 0; i < this.getMaxSuperAttackLevel(); i++) {
            if (i >= this.getCurrentSuperAttackLevel()) {
                stringbuilder.append("[ ]");
            } else {
                stringbuilder.append("[*]");
            }
        }
        System.out.println(stringbuilder.toString());
        System.out.println("");
    }

    public double getDamage() {
        double combinedDamage = this.damage;

        if (getCurrentWeapon() != null) {
            combinedDamage += getCurrentWeapon().getValue();
        }

        return combinedDamage;
    }

    public void obtainItems(ArrayList<Item> items) throws Exception {
        for (Item item : items) {
            if (item.getClass() == Potion.class) {
                this.setInventory("add", (Potion) item);
            } else if (item.getClass() == Weapon.class) {
                Weapon newWeapon = (Weapon) item;
                if (this.getCurrentWeapon() == null || this.getCurrentWeapon().getValue() < newWeapon.getValue()) {
                    this.setCurrentWeapon(newWeapon);
                    System.out.println("You have acquired and equipped a better weapon." + newWeapon.toString());
                } else {
                    System.out.println("Your current weapon is better, so you've left the loot.");
                }
            } else if (item.getClass() == Armour.class) {
                Armour newArmour = (Armour) item;
                if (this.getCurrentArmour() == null || this.getCurrentWeapon().getValue() < newArmour.getValue()) {
                    this.setCurrentArmour(newArmour);
                    System.out.println("You have acquired and equipped a better set of armour." + newArmour.toString());
                } else {
                    System.out.println("Your current armour is better, so you've left the loot.");
                }
            }
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void usePotion(Potion potion) {
        String type = potion.getType();
        Integer value = potion.getValue();

        if ("health".equals(type)) {
            this.currentHealth += value;
            System.out.println(this.name + "has healed for " + value + ". Health: " + this.currentHealth);
        } else {
            if ((this.getCurrentSuperAttackLevel() + value) > this.getMaxSuperAttackLevel()) {
                this.setCurrentSuperAttackLevel(this.getMaxSuperAttackLevel());
                System.out.println("Attack potion has maxed out your super attack bar.");
            } else {
                this.setCurrentSuperAttackLevel(this.getCurrentSuperAttackLevel() + value);
                System.out.println("Attack potion has added " + value + " to " + this.name + "'s super attack bar.");
            }
        }
    }

    /**
     * @return the inventory
     */
    public LinkedList<Potion> getInventory() {
        return inventory;
    }

    /**
     * @param operation
     * @param potion
     */
    public void setInventory(String operation, Potion potion) {
        if (operation.equals("remove")) {
            this.inventory.remove(potion);
        } else if (operation.equals("add")) {
            this.inventory.add(potion);
        } else {
            System.out.println("Error: You have used a wrong operation in setInventory.");
        }
    }

    /**
     * @return the currentSuperAttackLevel
     */
    public int getCurrentSuperAttackLevel() {
        return currentSuperAttackLevel;
    }

    /**
     * @param currentSuperAttackLevel the currentSuperAttackLevel to set
     */
    public void setCurrentSuperAttackLevel(int currentSuperAttackLevel) {
        this.currentSuperAttackLevel = currentSuperAttackLevel;
    }

    /**
     * @return the maxSuperAttackLevel
     */
    public int getMaxSuperAttackLevel() {
        return maxSuperAttackLevel;
    }

    /**
     * @param maxSuperAttackLevel the maxSuperAttackLevel to set
     */
    public void setMaxSuperAttackLevel(int maxSuperAttackLevel) {
        this.maxSuperAttackLevel = maxSuperAttackLevel;
    }

    /**
     * @return the currentArmour
     */
    public Armour getCurrentArmour() {
        return currentArmour;
    }

    /**
     * @param currentArmour the currentArmour to set
     */
    public void setCurrentArmour(Armour currentArmour) {
        this.currentArmour = currentArmour;
    }

    /**
     * @return the currentWeapon
     */
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    /**
     * @param currentWeapon the currentWeapon to set
     */
    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public void setCurrentHealth(int health) {
        this.currentHealth = health;
    }
}
