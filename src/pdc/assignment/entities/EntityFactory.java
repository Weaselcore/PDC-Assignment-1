/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.entities;

import java.util.Map;
import pdc.assignment.database.GameData;

/**
 * Returns the appropriate entity using factory pattern to improve scalability.
 * @author whackaweasel
 */
public class EntityFactory {
    
    public static Entity createNewEntity(String entityType, GameData gameData) throws Exception {
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

    public static Entity createOldEntity(String entityType, Map loadData, GameData gameData) throws Exception {
        switch(entityType) {
            case "old player":
                Player player = new Player(loadData, gameData);
                return player;
            default:
                throw new Exception("Invalid entity type.");
        }
    }
}
