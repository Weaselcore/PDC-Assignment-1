/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.database;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Deserialisation is done using the Jackson library.
 * This class uses static methods to read data from save files,
 * and to read game data from JSON file for enemy and item files.
 * @author whackaweasel
 */
public class Deserialiser {
    
    public static Map readSave(File file) throws JsonGenerationException, JsonMappingException, IOException{
        HashMap<String, Object> map = new ObjectMapper().readValue(file, HashMap.class);
        return map;
    }    
}
