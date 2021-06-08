/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.databaseclasses;

import java.util.HashMap;
import java.util.LinkedHashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wease
 */
public class WrapperTest {
    
    public WrapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of connect method, of class Wrapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("connect");
        Wrapper.connect();
        assertFalse(Wrapper.dbConnection.isClosed());
        Wrapper.close();
    }

    /**
     * Test of close method, of class Wrapper.
     */
    @Test
    public void testClose() throws Exception {
        Wrapper.connect();
        Wrapper.close();
        assertTrue(Wrapper.dbConnection.isClosed());
    }

    /**
     * Test of loadGameData method, of class Wrapper.
     */
    @Test
    public void testGameDataLoad() throws Exception {
        Wrapper.connect();
        String type = "weapon";
        HashMap result = Wrapper.loadGameData(type);
        assertNotNull(result);
        Wrapper.close();
    }

    /**
     * Test of loadGame method, of class Wrapper.
     */
    @Test
    public void testLoadGame() throws Exception {
        Wrapper.connect();
        System.out.println("loadGame");
        int id = 4;
        HashMap result = Wrapper.loadGame(id);
        assertNotNull(result);
        Wrapper.close();
    }

    /**
     * Test of retrieveSaveList method, of class Wrapper.
     */
    @Test
    public void testRetrieveSaveList() throws Exception {        
        Wrapper.connect();
        LinkedHashMap result = Wrapper.retrieveSaveList();
        assertNotNull(result);
        Wrapper.close();
    }
    
}
