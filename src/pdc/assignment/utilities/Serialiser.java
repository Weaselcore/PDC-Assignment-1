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
import pdc.assignment.items.Potion;
import pdc.assignment.main.GameSession;

/**
 *
 * @author whackaweasel
 */
public class Serialiser {
    
    public static void saveToFile(GameSession gameSession) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap();
        
        Entity playerEntity =  gameSession.getPlayer();
        Player player = (Player) playerEntity;
        
        // Player name
        String name = playerEntity.getName();
        map.put("name", name);
        // Health
        double health = player.getCurrentHealth();
        map.put("health", health);
        // Level
        int level = gameSession.getLevel().getCurrentLevel();
        map.put("level", level);
        // Weapon
        String weapon = player.getCurrentWeapon().getName();
        map.put("weapon", weapon);
        // Armour
        String armour = player.getCurrentArmour().getName();
        map.put("armour", armour);
        // Potions
        LinkedList<String> potionList = new LinkedList();
        
        player.getInventory().forEach(potion -> {
            potionList.add(potion.getName());
        });
        
        map.put("potions", potionList);
        
        // Save to file
        mapper.writeValue(Paths.get(name + ".json").toFile(), map);
    }  
}
