/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Logger;
import pdc.assignment.items.Armour;
import pdc.assignment.items.Item;
import pdc.assignment.items.ItemFactory;
import pdc.assignment.items.Potion;
import pdc.assignment.items.Weapon;
import pdc.assignment.gameclasses.GameSession;
import pdc.assignment.database.GameData;
import pdc.assignment.gameclasses.HistoryLogger;

/**
 *
 * @author whackaweasel
 */
public final class Player extends AbstractEntity{

    private final LinkedList<Potion> inventory = new LinkedList<>();
    private int currentSuperAttackLevel;
    private int maxSuperAttackLevel;
    private Armour currentArmour = null;
    private Weapon currentWeapon = null;

    // Initialises player from scratch.
    public Player() throws Exception {
        this.currentHealth = 20;
        this.maxHealth = 25;
        this.damage = 5;
        this.setMaxSuperAttackLevel(5);
        this.setCurrentSuperAttackLevel(0);
    }

    // Initialises player using old saved data.
    public Player(Map loadData, GameData gameData) throws Exception {
        // Set name
        this.name = (String) loadData.get("name");
        // Set health
        this.setCurrentHealth((int) loadData.get("health"));
        // Set default values
        this.maxHealth = 20;
        this.damage = 5;
        // Set currentSuperAttack
        this.setCurrentSuperAttackLevel((int) loadData.get("superAttack"));
        this.setMaxSuperAttackLevel(5);

        // Set potions
        if (!((LinkedList) loadData.get("potions")).isEmpty() || !((LinkedList) loadData.get("potions") == null)){
            ((LinkedList) loadData.get("potions")).forEach(potionString -> {
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
        HistoryLogger.append("\n" + this.name + " has appeared to fight for another day!");
    }

    @Override
    public void displayInfo() {
        HistoryLogger.append("| " + this.name + ": | Health: " + this.currentHealth
                + "/" + this.maxHealth + " | Damage: " + this.damage + " |");
    }

    // When new player is being initialised, it will prompt for a name.
    @Override
    public void create() {
    }
    
    // Calculates the damage by subtracting the armour value as a flat amount.
    @Override
    public void takeDamage(double damage) {
        if (this.getCurrentArmour() != null) {
            // Prevents damage from healing player when armour is higher than incoming damage.
            double result = damage - this.getCurrentArmour().getValue();
            if (result < 0) {
                HistoryLogger.append("Armour has negated all incoming damage!");
            } else {
                this.currentHealth -= result;
                HistoryLogger.append(this.name + "'s armour has reduced damage by " + this.getCurrentArmour().getValue());
            }
        } else {
            this.currentHealth -= damage;
        }
    }

    // Will check if the player is dead.
    @Override
    public boolean isDead() {
        return this.currentHealth <= 0;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    public void setName(String playerName) {
        this.name = playerName;
    }

    // Executes an attack or super attack if available.
    @Override
    public void attack(Entity currentEnemy) {

        double damageToDeal = this.getDamage();
        String attackString = " ~ " + this.name + " has attacked " + currentEnemy.getName() + " with " + damageToDeal + ".";

        // Check if the super attack bar is full and will double damage if so.
        if (this.getCurrentSuperAttackLevel() == this.getMaxSuperAttackLevel()) {
            damageToDeal = damageToDeal * 2;
            attackString = " !!! " + this.name + " has CRIT " + currentEnemy.getName() + " with " + damageToDeal + " !!!";
            // Resets the superAttackLevel back to 0
            this.setCurrentSuperAttackLevel(0);
        } else {
            // If bar isn't full, it'll increment by 1.
            this.setCurrentSuperAttackLevel(this.getCurrentSuperAttackLevel() + 1);
        }

        currentEnemy.takeDamage(damageToDeal);
        HistoryLogger.append(attackString);
    }

    // Builds a string depending on maxSuperAttack value and currentSuperAttack value.
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
        HistoryLogger.append(stringbuilder.toString());
    }
    
    // Calculates damage using base damage value and currentWeapon value.
    public double getDamage() {
        double combinedDamage = this.damage;

        if (getCurrentWeapon() != null) {
            combinedDamage += getCurrentWeapon().getValue();
        }

        return combinedDamage;
    }
    

    // Adds potions to the inventory.
    // Checks if currentWeapon and currentArmour is better, if so replaces the
    // current equipment.
    public void obtainItems(ArrayList<Item> items) throws Exception {
        items.forEach(item -> {
            if (item.getClass() == Potion.class) {
                this.setInventory("add", (Potion) item);
            } else if (item.getClass() == Weapon.class) {
                Weapon newWeapon = (Weapon) item;
                if (this.getCurrentWeapon() == null || this.getCurrentWeapon().getValue() < newWeapon.getValue()) {
                    this.setCurrentWeapon(newWeapon);
                    HistoryLogger.append("You have acquired and equipped a better weapon." + newWeapon.toString());
                } else {
                    HistoryLogger.append("Your current weapon is better, so you've left the loot.");
                }
            } else if (item.getClass() == Armour.class) {
                Armour newArmour = (Armour) item;
                if (this.getCurrentArmour() == null || this.getCurrentWeapon().getValue() < newArmour.getValue()) {
                    this.setCurrentArmour(newArmour);
                    HistoryLogger.append("You have acquired and equipped a better set of armour." + newArmour.toString());
                } else {
                    HistoryLogger.append("Your current armour is better, so you've left the loot.");
                }
            }
        });
    }

    // Checks potion type and sets the corresponding value.
    public void usePotion(Potion potion) {
        String type = potion.getType();
        Integer value = potion.getValue();
        // The currentHealth cannot go above maxHealth.
        // If the health potion is not used efficiently, it'll be wasted.
        if ("health".equals(type)) {
            if ((this.getCurrentHealth() + value) > this.getMaxHealth()){
                this.setCurrentHealth(this.getMaxHealth());
                HistoryLogger.append("Health potion has maxed out your health bar.");               
            } else {
                this.currentHealth += value;
                HistoryLogger.append(this.name + " has healed for " + value + ". Health: " + this.currentHealth);
            }
        } else {
            // The currentSuperAttackLevel cannot go above maxSuperAttaclLevel.
            // If the Super Attack potion is not used efficiently, it'll be wasted.
            if ((this.getCurrentSuperAttackLevel() + value) > this.getMaxSuperAttackLevel()) {
                this.setCurrentSuperAttackLevel(this.getMaxSuperAttackLevel());
                HistoryLogger.append("Attack potion has maxed out your super attack bar.");
            } else {
                this.setCurrentSuperAttackLevel(this.getCurrentSuperAttackLevel() + value);
                HistoryLogger.append("Attack potion has added " + value + " to " + this.name + "'s super attack bar.");
                this.displaySuperAttack();
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
            HistoryLogger.append("Error: You have used a wrong operation in setInventory.");
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

    /**
     *
     * @return the currentHealth
     */
    public int getCurrentHealth() {
        return this.currentHealth;
    }
    
    /**
     *
     * @param health the currentHealth to set
     */
    public void setCurrentHealth(int health) {
        this.currentHealth = health;
    }
    
    public int getMaxHealth() {
        return this.maxHealth;
    }
}
