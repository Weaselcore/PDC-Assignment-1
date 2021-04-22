/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import pdc.assignment.entities.Enemy;
import pdc.assignment.loot.LootTableGenerator;
import pdc.assignment.entities.Entity;
import pdc.assignment.entities.EntityFactory;
import pdc.assignment.entities.Player;
import pdc.assignment.items.Item;
import pdc.assignment.items.Potion;
import pdc.assignment.utilities.GameData;
import pdc.assignment.utilities.Serialiser;

/**
 *
 * @author whackaweasel
 */
public final class Level {
    
    private int currentLevel;
    Entity currentEnemy;
    Entity player;
    int maxLevel;
    GameData gameData;
    LootTableGenerator lootGenerator;
    
    // If it's a new game.
    public Level(GameData gameData, Entity player, int maxLevel) throws Exception {
        this.gameData = gameData;
        this.currentLevel = 1;
        this.player = player;
        this.lootGenerator = new LootTableGenerator(this.gameData);
        this.maxLevel = maxLevel;
    }
       
    
        /**
     * Creates a loops to handle the turns which is uses the Entity interface
     * and type to handle both Player and Enemy.
     * @return current level.
     * @throws java.lang.Exception
     */
    public int run() throws Exception { 
        
        this.generateEnemy();

        boolean levelInProgress = true;
        // Since there is no parallel assignments in Java,
        // a temp variable is used to swap around the current entityForTurn.
        Entity entityForTurn = this.player;
        Entity entityWaiting = this.currentEnemy;
        Entity temp;

        while (levelInProgress) {
            boolean targetHasDied = false;
            if (entityForTurn instanceof Player){
                // Player
                targetHasDied = this.playerTurn(entityWaiting); 
            } else if (entityForTurn instanceof Enemy) {
                // Enemy
                targetHasDied = ((Enemy) entityForTurn).turn(entityWaiting);
            }
            if (targetHasDied) {
                levelInProgress = false;
            } else {
                entityWaiting.displayInfo();
                temp = entityForTurn;
                entityForTurn = entityWaiting;
                entityWaiting = temp;
            }
        }
        
        // After fight, if the player is left standing, create new enemy.
        if (entityForTurn instanceof Player ) {
            if (this.currentLevel < this.maxLevel) {
                // Player will check if weapon/armour is better, equip.
                ArrayList<Item> newLoot = this.lootGenerator.generateItem();
                Player currentPlayer = (Player) this.player;
                currentPlayer.obtainItems(newLoot);
            }
            this.currentLevel += 1;
        }
        else {
            // 0 will be finished game.
            this.currentLevel = 0;
        }
        return this.currentLevel;
    }
    
    public boolean playerTurn(Entity currentEnemy) throws IOException {
        
        Scanner inputScanner = new Scanner(System.in);

        boolean chosen = false;
        boolean isDead = false;

        while (!chosen) {
            Player currentPlayer = ((Player) this.player);
            currentPlayer.displaySuperAttack();
            
            System.out.println(
                "[#1. Attack] [#2. Use Potions] [#3. Save and Exit] [#4. Run Away]");
            System.out.println("Option (#): ");

            String chosenOption = inputScanner.nextLine();

            if ("1".equals(chosenOption)) {
                currentPlayer.attack(currentEnemy);
                boolean targetDead = currentEnemy.isDead();
                if (targetDead) {
                    System.out.println("\n" + this.player.getName() + " has slain " + currentEnemy.getName() + "!\n");
                    isDead = true;
                }
                else {
                    isDead = false;
                }
                chosen = true;
            }
            else if ("2".equals(chosenOption)) {
                if (!currentPlayer.getInventory().isEmpty()) {
                    Integer count = 0;
                    
                    for (Potion item: currentPlayer.getInventory()) {
                        System.out.println("Count: " + "[#]" + (count + 1) + item);
                        count += 1;
                    }
                    
                    boolean potionChosen = false;
                    Scanner potionScanner = new Scanner(System.in);
                    
                    while (!potionChosen) {
                        
                        System.out.println("Please select potion using corresponding #:");
                        Integer potionChoice = potionScanner.nextInt();
                        
                        if (potionChoice <= count) {
                            Potion potion = currentPlayer.getInventory().get(potionChoice - 1);
                            currentPlayer.usePotion(potion);
                            currentPlayer.setInventory("remove", potion);
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
                Serialiser.saveToFile(this);
                System.out.println("Your game has been saved to " + this.player.getName() + ".json");
                System.exit(0);                
            }
            else if ("4".equals(chosenOption)) {
                System.out.println("Thanks for playing");
                System.exit(0);
            }
            else {
                System.out.println("Please input a proper input!");
            }
        } 
        return isDead;
    }
    
        /**
     * Displays the current level the Player is currently on, Example: [ ] [*] [
     * ] [ ] [ ] [ ] The Player is on the second level out of 6.
     * @param maxLevel - Gets the maximum levels allowed from the game session.
     */
    public void displayLevel(int maxLevel) {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Level Progress: ");
        for (int i = 0; i < maxLevel; i++) {
            if (i != (this.currentLevel - 1)) {
                stringbuilder.append("[ ]");
            } else {
                stringbuilder.append("[*]");
            }
        }
        System.out.println(stringbuilder.toString());
    }

    /**
     * This is an operating system agnostic way of clearing the terminal. 033[H:
     * It moves the cursor at the top left corner of the screen or console.
     * 033[2J: It clears the screen from the cursor to the end of the screen.
     * System.out.flush() resets cursor position.
     * ONLY WORKS WHEN RUNNING JAR in terminal/console, not in IDE.
     */
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * @return the currentLevel
     */
    public int getCurrentLevel() {
        return currentLevel;
    }
    
    private void generateEnemy() throws Exception {
        this.currentEnemy = EntityFactory.createNewEntity("enemy", this.gameData);
    }

    public Entity getPlayer() {
        return this.player;
    }
    
    
    
    
}
