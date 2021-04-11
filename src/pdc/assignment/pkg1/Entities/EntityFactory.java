/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1.Entities;

/**
 *
 * @author whackaweasel
 */
public class EntityFactory {
    
        public static Entity CreateEntity(String entityType) throws Exception {
        switch(entityType) {
            case "player":
                Player player = new Player();
                System.out.println("You have created a new player.");
                return player;
            case "enemy":
                Enemy enemy = new Enemy();
                System.out.println("You have created an enemy.");
                return enemy;
            default:
                throw new Exception("Invalid entity type.");
        }
    }
}
