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
    
    public static void load(String type) {
        
        if 
        
    }
    
    public static void savePlayerData() {
        
    }
    
    public static void loadPlayerData() {
        
    }
    
    private ResultSet query(String sql) {
        Connection connection = Wrapper.dbConnection;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e){
            System.err.println(e);
        }
        return resultSet;
    }
}
