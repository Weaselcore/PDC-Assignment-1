/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.entities;

import pdc.assignment.utilities.GameData;

/**
 *
 * @author whackaweasel
 */
public class EntityFactory {
    
        public static Entity createEntity(String entityType, GameData gameData) throws Exception {
        switch(entityType) {
            case "player":
                Player player = new Player();
                return player;
            case "enemy":
                Enemy enemy = new Enemy(gameData);
                return enemy;
            default:
                throw new Exception("Invalid entity type.");
        }
    }
}
