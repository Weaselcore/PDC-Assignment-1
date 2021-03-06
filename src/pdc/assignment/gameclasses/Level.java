/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gameclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import pdc.assignment.loot.LootTableGenerator;
import pdc.assignment.entities.Entity;
import pdc.assignment.entities.EntityFactory;
import pdc.assignment.entities.Player;
import pdc.assignment.databaseclasses.GameData;
import pdc.assignment.entities.Enemy;

/**
 *
 * @author whackaweasel
 */
public final class Level implements Serializable{
    
    private int currentLevel;
    private Enemy currentEnemy;
    private final Player player;
    int maxLevel;
    GameData gameData;
    LootTableGenerator lootGenerator;
    private boolean isEnemyDead;
    private boolean isplayerDead;
    
    // If it's a new game.
    public Level(GameData gameData, Entity player) throws Exception {
        this.gameData = gameData;
        this.currentLevel = 1;
        this.player = (Player) player;
        this.isplayerDead = false;
        this.lootGenerator = new LootTableGenerator(this.gameData);
        this.generateEnemy();
    }  
    
    // If loading from an old save.
    public Level(GameData gameData, Player player, HashMap loadData) throws Exception {
        this.gameData = gameData;
        this.currentLevel = (int) loadData.get("level");
        this.player = player;
        this.lootGenerator = new LootTableGenerator(this.gameData);
        this.currentEnemy = (Enemy) EntityFactory.createOldEntity("old enemy", loadData, gameData);
    }  
    
    /**
     * Generates an enemy and resets the isEnemyDead flag.
     * 
     */
    public void generateEnemy() throws Exception {
        this.currentEnemy = (Enemy) EntityFactory.createNewEntity("enemy", this.gameData);
        this.isEnemyDead = false;
    }
    
    /**
     * @return the isEnemyDead
     */
    public boolean isEnemyDead() {
        return this.isEnemyDead;
    }
    
    /**
     * @return the isplayerDead
     */
    public boolean isIsplayerDead() {
        return isplayerDead;
    } 
    
    /**
     * This is the logic for the enemies turn.
     */
    public void enemyTurn() {          
        this.getCurrentEnemy().turn(this.getPlayer());
        this.isplayerDead = this.getPlayer().isDead();
    }
    
    /**
     * This is the logic for the players turn.
     */
    public void playerTurn(){
        this.getPlayer().displaySuperAttack();
        this.getPlayer().attack(this.getCurrentEnemy());
        this.isEnemyDead = this.getCurrentEnemy().isDead();
    }
    
    /**
     * This is the logic to generate 3 item objects (potion, weapon and armor)
     * @return 
     * @throws java.lang.Exception
     */
    public ArrayList rewardPlayer() throws Exception {
        return this.lootGenerator.generateItem();
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
                stringbuilder.append("[  ]");
            } else {
                stringbuilder.append("[*]");
            }
        }
        HistoryLogger.append(stringbuilder.toString());
    }

    /**
     * @return the currentLevel
     */
    public int getCurrentLevel() {
        return currentLevel;
    }
    
    /**
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }
    
    /**
     * This method turns all the data of the player and level to save it into a
     * HashMap
     * 
     * @return 
     */
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
            LinkedList<String> newPotionList = new LinkedList();
            map.put("potions", newPotionList);
        }
        HistoryLogger.append("\n* THIS SESSION HAS BEEN SAVED.");
        return map;
    }

    /**
     * @return the currentEnemy
     */
    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }
    
    /**
     * Increments the level by 1.
     */
    public void incrementLevel() {
        this.currentLevel += 1;
    }
    
    /**
     * Display information for both entities for each turn.
     */
    public void displayEntityInfo() {
        this.getPlayer().displayInfo();
        this.getCurrentEnemy().displayInfo();
    }
}
