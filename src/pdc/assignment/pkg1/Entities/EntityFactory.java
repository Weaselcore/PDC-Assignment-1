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
    
        public static IEntity CreateEntity(String entityType) throws Exception {
        switch(entityType) {
            case "player":
                System.out.println("You have created a player.");
            case "enemy":
                System.out.println("You have created an enemy.");
            default:
                throw new Exception("Invalid enemy type.");
        }
    }
    
}
