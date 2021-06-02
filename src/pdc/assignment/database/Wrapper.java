/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.database;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.sql.rowset.serial.SerialBlob;
import pdc.assignment.gameclasses.Level;

/**
 *
 * @author wease
 */
public class Wrapper {
    
    public static Connection dbConnection;
    final static private String CONNECTION_URL = "jdbc:derby://localhost:1527/gamesave";
    final static private String USERNAME = "team";
    final static private String PASSWORD = "123";
    

    // Create instance of the connection object to access the database.
    public static void connect() throws SQLException {
        try {
            Wrapper.dbConnection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
            System.out.println("A connection has been established with " + CONNECTION_URL);
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
    
    // The GameData is converted from HashMap into an db entry.
    public static void saveGame(HashMap level) throws IOException, SQLException {
        
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objStream = new ObjectOutputStream(byteStream);
        objStream.writeObject(level);
        
        //Prepare variables to be inserted.
        java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        
        String playerName = (String) level.get("name");
        Blob blobOject = new SerialBlob(byteStream.toByteArray());        
                Wrapper.connect();
        String statement = "INSERT INTO PLAYERDATA(TIMESTAMP, NAME, PLAYERDATA) VALUES (?, ?, ?)";
        PreparedStatement prepStatement = Wrapper.dbConnection.prepareStatement(statement, PreparedStatement.RETURN_GENERATED_KEYS);
        //Setting values using the variables prepared.
        prepStatement.setTimestamp(1, now);
        prepStatement.setString(2, playerName);
        prepStatement.setBlob(3, blobOject);
        prepStatement.getGeneratedKeys();
        prepStatement.execute();
        
        Wrapper.close();
    }
    
}
