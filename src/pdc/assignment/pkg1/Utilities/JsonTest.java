/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1.Utilities;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author bursty
 */
class JsonTest {

    public static void main(String[] args) {
        
        TestClass test = new TestClass();

        JsonTest json = new JsonTest();
        
        try{
//            test.setName("Ieuan");
//            test.setAge(21);
//            json.writeJSON(test);
            json.readJSON();
              
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
   
    private void writeJSON(TestClass testclass) throws JsonGenerationException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("filepoggers.json"), testclass);
    }
    
    private void readJSON() throws JsonGenerationException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.readValue(new File("filepoggers.json"), TestClass.class));
    }
}
