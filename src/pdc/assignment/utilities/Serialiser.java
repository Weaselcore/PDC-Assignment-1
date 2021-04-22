/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import pdc.assignment.entities.Entity;
import pdc.assignment.entities.Player;
import pdc.assignment.main.Level;

/**
 *
 * @author whackaweasel
 */
public class Serialiser {
    
    public static void saveToFile(Level level) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap();
        
        Entity playerEntity =  level.getPlayer();
        Player player = (Player) playerEntity;
        
        // Player name
        String name = playerEntity.getName();
        map.put("name", name);
        // Health
        double health = player.getCurrentHealth();
        map.put("health", health);
        // Level
        int currentLevel = level.getCurrentLevel();
        map.put("level", currentLevel);
        // Weapon
        if (player.getCurrentWeapon() != null) {         
            String weapon = player.getCurrentWeapon().getName();
            map.put("weapon", weapon);
        } else {
            map.put("weapon", null);
        }
        // Armour
        if (player.getCurrentArmour() != null) {         
            String armour = player.getCurrentArmour().getName();
            map.put("armour", armour);
        } else {
            map.put("armour", null);
        }
        // Potions
        if (!player.getInventory().isEmpty()) {
            LinkedList<String> potionList = new LinkedList();

            player.getInventory().forEach(potion -> {
                potionList.add(potion.getName());
            });
        
            map.put("potions", potionList);
        } else {
            map.put("potions", null);
        }

       
        // Save to file
        mapper.writeValue(Paths.get(name + ".json").toFile(), map);
    }  
}
