/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.loot;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdc.assignment.databaseclasses.GameData;
import pdc.assignment.items.Item;

/**
 *
 * @author wease
 */
public class LootTableGeneratorTest {
    
    private GameData gameData;
    private LootTableGenerator lootGen;
    
    public LootTableGeneratorTest() throws SQLException {
        this.gameData = new GameData();
        this.lootGen = new LootTableGenerator(this.gameData);
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
     * Test of generateItem method, of class LootTableGenerator.
     */
    @Test
    public void testGenerateItem() throws Exception {
        System.out.println("generateItem");
        ArrayList<Item> result = this.lootGen.generateItem();
        assertTrue(result.size() == 3);
    }
    
}
