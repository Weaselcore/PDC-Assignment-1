/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wease
 */
public class Wrapper {
    
    public static Connection dbConnection;
    final static private String connUrl = "jdbc:derby://localhost:1527/gamesave";
    final static private String user = "team";
    final static private String pass = "123";
    

    // Create instance of the connection object to access the database.
    public static void connect() throws SQLException {
        try {
            Wrapper.dbConnection = DriverManager.getConnection(connUrl, user, pass);
            System.out.println("A connection has been established with " + connUrl);
        } catch(SQLException e) {
            System.err.println(e);
        }
    }
    
    // Grabs data from the local database and returns them as a HashMap.
    // The game takes the HashMap to store it into memory and to 
    public static void load(String type) {
        switch (type) {
            case "weapon":
                String statement = "SELECT * FROM WEAPON";
                ArrayList columnNames = new ArrayList(Arrays.asList("name", "value", "description"));
                break;
            case "armour":
                break;
            case "potion":
                break;
            case "player":
                break;
            case "enemy":
                break;
            default:
                break;
        }
    }
    
    // The GameData is converted from HashMap to an entry in the database.
    public static void save(String type) {

    }

//    private HashMap query(String sql, ArrayList columnName) {
//        Connection connection = Wrapper.dbConnection;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        String column;
//        HashMap completeHashMap = new HashMap<>();
//        
//        try {
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sql);
//            int count = -1;
//            while(resultSet.next()) {
//                // This is the name of the object.
//                if (count == 0) {
//                    completeHashMap.put(resultSet.get, column)
//                } else if (count > 0) {
//                    column = (String) columnName.get(count);
//                    hashmap.put(sql, column)
//                    
//                }
//                count++;
//            }
//            
//        } catch (SQLException e){
//            System.err.println(e);
//        }
//        return completeHashMap;
//    }
//    
//    private void addToHashmap(Map hashmap) {
//        
//    }
}
