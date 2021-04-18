/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1.Utilities;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import pdc.assignment.pkg1.Entities.Entity;
import pdc.assignment.pkg1.Items.Item;

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
    
    public static HashMap readItemJSON(String string) throws JsonGenerationException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        return (HashMap) mapper.readValue(new File(string), Item.class);
    }
    
    public static HashMap readEntityJSON(String string) throws JsonGenerationException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        return (HashMap) mapper.readValue(new File(string), Entity.class);
    }
    
    
}
