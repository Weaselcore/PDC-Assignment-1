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
import java.util.LinkedHashMap;
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
    
    public static void close() throws SQLException {
        Wrapper.dbConnection.close();
    }
    
    // Grabs data from the local database and returns them as a HashMap.
    // The game takes the HashMap to store it into memory and to 
    public static HashMap load(String type) throws SQLException {
        
        String statement;
        ArrayList<String> columnNames;
        
        switch (type) {
            case "weapon":
                // This hashmap will contain nested hashmaps.
                HashMap weaponHashMap = new HashMap();
                
                // Statement needed to look up data from correct table.
                statement = "SELECT * FROM WEAPON";
                
                // Custom column name headings for HashMap creation.
                columnNames = new ArrayList(Arrays.asList("name", "description", "value", "chance"));
                
                // Retrieves each set from the query statement.
                weaponHashMap = fetchQuerySet(columnNames, weaponHashMap, statement);
                return weaponHashMap;
                
            case "armour":
                // This hashmap will contain nested hashmaps.
                HashMap armourHashMap = new HashMap();
                
                // Statement needed to look up data from correct table.
                statement = "SELECT * FROM ARMOUR";
                
                // Custom column name headings for HashMap creation.
                columnNames = new ArrayList(Arrays.asList("name", "description", "value", "chance"));
                
                // Retrieves each set from the query statement.
                armourHashMap = fetchQuerySet(columnNames, armourHashMap, statement);
                return armourHashMap;

            case "potion":
                // This hashmap will contain nested hashmaps.
                HashMap potionHashMap = new HashMap();
                
                // Statement needed to look up data from correct table.
                statement = "SELECT * FROM POTION";
                
                // Custom column name headings for HashMap creation.
                columnNames = new ArrayList(Arrays.asList("name", "description", "value", "type", "chance"));
                
                // Retrieves each set from the query statement.
                potionHashMap = fetchQuerySet(columnNames, potionHashMap, statement);
                return potionHashMap;
                
            case "enemy":
                // This hashmap will contain nested hashmaps.
                HashMap enemyHashMap = new HashMap();
                
                // Statement needed to look up data from correct table.
                statement = "SELECT * FROM ENEMY";
                
                // Custom column name headings for HashMap creation.
                columnNames = new ArrayList(Arrays.asList("name", "description", "health", "armour", "attack"));
                
                // Retrieves each set from the query statement.
                enemyHashMap = fetchQuerySet(columnNames, enemyHashMap, statement);
                return enemyHashMap;
            case "player":
                break;
            default:
                break;
        }
        return null;
    }
    
    private static HashMap fetchQuerySet(ArrayList<String> columnNames,HashMap hashMap, String statement) throws SQLException {
        Statement stmt = Wrapper.dbConnection.createStatement();
        ResultSet rs = stmt.executeQuery(statement);
        while (rs.next()) {                                    
            hashMap = addSetToHashMap(columnNames, rs, hashMap);
        }
        return hashMap;
    }
    
    private static HashMap addSetToHashMap(ArrayList<String> columnNameArray, ResultSet rs, HashMap hashMap) throws SQLException {
        
        // For each element in the result set, add it to a hashmap with the
        // columnName as the key.
        LinkedHashMap innerLinkedHashMap = new LinkedHashMap();

        int sizeOfColumnArray = columnNameArray.size();
        
        // Constructs the inner linked hashmap to be added under item name.
        for (int i=1; i<sizeOfColumnArray; i++) {
            String columnName = columnNameArray.get(i);
            innerLinkedHashMap.put(columnName, rs.getObject(columnName));
        }
        
        hashMap.put(rs.getString(columnNameArray.get(0)), innerLinkedHashMap);

        return hashMap;
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
