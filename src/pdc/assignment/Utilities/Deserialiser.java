/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.Utilities;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Deserialisation should be done using the Jackson library.
 *
 * @author whackaweasel
 */
public class Deserialiser {
    
    public static void readSave(Integer number) throws JsonGenerationException, JsonMappingException, IOException{
        String string = "save.json";
        ObjectMapper mapper = new ObjectMapper();
        string = number.toString() + string;
        System.out.println(mapper.readValue(new File(string), SaveData.class));
    }
    
    public static HashMap jsonDataToHashmap(String string) throws JsonGenerationException, JsonMappingException, IOException{
        HashMap<String, Object> map = new ObjectMapper().readValue(new File(string), HashMap.class);
        System.out.println("Data loaded from " + string);
        return map;
    } 
    
}
