/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1.Utilities;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

/**
 *
 * @author bursty
 */
class JsonTest {

    public static void main(String[] args) {
        String testJsonSource = "{ \"Enemy Name\" : \"Roger\" }";

        try{
            JsonNode node = Deserialiser.parse(testJsonSource);
            System.out.println(node.get("Enemy Name").asText());

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
   
}
