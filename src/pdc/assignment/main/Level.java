/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.main;

import pdc.assignment.entities.Entity;
import pdc.assignment.entities.EntityFactory;
import pdc.assignment.entities.Player;
import pdc.assignment.utilities.GameData;

/**
 *
 * @author whackaweasel
 */
public final class Level {
    
    private int currentLevel;
    Entity currentEnemy;
    Entity player;
    GameData gameData;
    
    // If it's a new game.
    public Level(GameData gameData, Entity player) throws Exception {
        this.gameData = gameData;
        this.currentLevel = 1;
        this.player = player;
    }
    
    // If it's a loaded game.
     public Level(GameData gameData, Entity player, int Level) throws Exception {
        this.currentLevel = 1;
        this.player = player;
    }   
       
    
        /**
     * Creates a loops to handle the turns which is uses the Entity interface
     * and type to handle both Player and Enemy.
     * @return current level.
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
            boolean targetHasDied = entityForTurn.turn(entityWaiting);
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
        if (entityForTurn.getClass() == Player.class ) {    
            this.currentLevel += 1;
        }
        else {
            this.currentLevel = -1;
        }
        return this.currentLevel;
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
        this.currentEnemy = EntityFactory.createEntity("enemy", this.gameData);
    }
    
    
    
    
}
