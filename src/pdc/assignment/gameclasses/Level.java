/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gameclasses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import pdc.assignment.loot.LootTableGenerator;
import pdc.assignment.entities.Entity;
import pdc.assignment.entities.EntityFactory;
import pdc.assignment.entities.Player;
import pdc.assignment.database.GameData;

/**
 *
 * @author whackaweasel
 */
public final class Level implements Serializable{
    
    private int currentLevel;
    private Entity currentEnemy;
    private Entity player;
    int maxLevel;
    GameData gameData;
    LootTableGenerator lootGenerator;
    private boolean isEnemyDead;
    private boolean isplayerDead;
    
    // If it's a new game.
    public Level(GameData gameData, Entity player, int maxLevel) throws Exception {
        this.gameData = gameData;
        this.currentLevel = 1;
        this.player = player;
        this.isplayerDead = false;
        this.lootGenerator = new LootTableGenerator(this.gameData);
        this.maxLevel = maxLevel;
        this.generateEnemy();
        this.isEnemyDead = false;
    }  
    
    // If loading from an old save.
//    public Level(GameData gameData, Entity player, int maxLevel, int currentLevel) throws Exception {
//        this.gameData = gameData;
//        this.currentLevel = currentLevel;
//        this.player = player;
//        this.lootGenerator = new LootTableGenerator(this.gameData);
//        this.maxLevel = maxLevel;
//    }  
    
    public void generateEnemy() throws Exception {
        this.currentEnemy = EntityFactory.createNewEntity("enemy", this.gameData);
        this.isEnemyDead = false;
    }
    
    public boolean isEnemyDead() {
        return this.isEnemyDead;
    }
    
    public void enemyTurn() {          
        this.getCurrentEnemy().attack(this.getPlayer());
        this.isplayerDead = this.getPlayer().isDead();
    }
    
    // Level object is responsible for taking in player input.
    public void playerTurn(){
//      boolean chosen = false;
        this.getCurrentEnemy().displayInfo();
        this.getPlayer().displayInfo();
        Player playerPlayer = (Player) this.getPlayer();
        playerPlayer.displaySuperAttack();
        this.getPlayer().attack(this.getCurrentEnemy());
        this.isEnemyDead = this.getCurrentEnemy().isDead();
//
//        while (!chosen) {
//            Player currentPlayer = ((Player) this.player);
//            currentPlayer.displaySuperAttack();
//            
//
//            if ("1".equals(chosenOption)) {
//                currentPlayer.attack(currentEnemy);
//                boolean targetDead = currentEnemy.isDead();
//                if (targetDead) {
//                    System.out.println("\n" + this.player.getName() + " has slain " + currentEnemy.getName() + "!\n");
//                    isDead = true;
//                }
//                else {
//                    isDead = false;
//                }
//                chosen = true;
//            }
//            else if ("2".equals(chosenOption)) {
//                if (!currentPlayer.getInventory().isEmpty()) {
//                    Integer count = 0;
//                    
//                    for (Potion item: currentPlayer.getInventory()) {
//                        System.out.println("Count: " + "[#" + (count + 1) + "] " + item);
//                        count += 1;
//                    }
//                    
//                    boolean potionChosen = false;
//                    Scanner potionScanner = new Scanner(System.in);
//                    
//                    while (!potionChosen) {
//                        
//                        System.out.println("");
//                        this.player.displayInfo();
//                        ((Player) this.player).displaySuperAttack();
//                        
//                        System.out.println("Please select potion using corresponding # or any input to exit:");
//                        Integer potionChoice = potionScanner.nextInt();
//                        
//                        if (potionChoice <= count) {
//                            Potion potion = currentPlayer.getInventory().get(potionChoice - 1);
//                            currentPlayer.usePotion(potion);
//                            currentPlayer.setInventory("remove", potion);
//                            potionChosen = true;
//                        } else {
//                            // Goes back to main options for turn.
//                            System.out.println("Please input a valid #.");
//                            break;
//                        }
//                    } 
//                } else {
//                    System.out.println("You have no potions.");
//                }
//            }
//            else if ("3".equals(chosenOption)) {
//                Wrapper.saveGame(this.saveToHashMap());
//                System.out.println("Your game has been saved to " + this.player.getName() + ".json");
//                System.exit(0);                
//            }
//            else if ("4".equals(chosenOption)) {
//                System.out.println("Thanks for playing");
//                System.exit(0);
//            }
//            else {
//                System.out.println("Please input a proper input!");
//            }
//        } 
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
     * @return the currentLevel
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    public Entity getPlayer() {
        return this.player;
    }
    
    public HashMap saveToHashMap() {
        
        HashMap map = new HashMap(); 
        Player playerCasted = (Player) this.getPlayer();
        
        // Player name
        String name = getPlayer().getName();
        map.put("name", name);
        // Health
        int health = (int) playerCasted.getCurrentHealth();
        map.put("health", health);
        // Super Attack
        int superAttack = playerCasted.getCurrentSuperAttackLevel();
        map.put("superAttack", superAttack);
        // Level
        int currentLevelToSave = this.getCurrentLevel();
        map.put("level", currentLevelToSave);
        // Weapon
        if (playerCasted.getCurrentWeapon() != null) {         
            String weapon = playerCasted.getCurrentWeapon().getName();
            map.put("weapon", weapon);
        } else {
            map.put("weapon", null);
        }
        // Armour
        if (playerCasted.getCurrentArmour() != null) {         
            String armour = playerCasted.getCurrentArmour().getName();
            map.put("armour", armour);
        } else {
            map.put("armour", null);
        }
        // Potions
        if (!playerCasted.getInventory().isEmpty()) {
            LinkedList<String> potionList = new LinkedList();

            playerCasted.getInventory().forEach(potion -> {
                potionList.add(potion.getName());
            });
        
            map.put("potions", potionList);
        } else {
            map.put("potions", null);
        }
        return map;
    }

    /**
     * @return the currentEnemy
     */
    public Entity getCurrentEnemy() {
        return currentEnemy;
    }

    public void incrementLevel() {
        this.currentLevel += 1;
    }
    
    
}
