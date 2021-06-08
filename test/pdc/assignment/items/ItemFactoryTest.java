/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.items;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import pdc.assignment.databaseclasses.GameData;

/**
 *
 * @author wease
 */
public class ItemFactoryTest {
    
    private GameData gameData;
    
    public ItemFactoryTest() throws SQLException {
        this.gameData = new GameData();
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
     * Test of createItem method, of class ItemFactory.
     */
    @Test
    public void testCreateWeapon() throws Exception {
        System.out.println("createWeapon");
        Item result = ItemFactory.createItem("weapon", "Diamond Mace", this.gameData);
        Weapon weapon = (Weapon) result;
        assertEquals("Diamond Mace", weapon.getName());
    }
    
    @Test
    public void testCreateArmour() throws Exception {
        System.out.println("createArmour");
        Item result = ItemFactory.createItem("armour", "Diamond Armour", this.gameData);
        Armour armour = (Armour) result;
        assertEquals("Diamond Armour", armour.getName());
    }
    
    @Test
    public void testCreatePotion() throws Exception {
        System.out.println("createPotion");
        Item result = ItemFactory.createItem("potion", "Small Health Potion", this.gameData);
        Potion potion = (Potion) result;
        assertEquals("Small Health Potion", potion.getName());
    }
    
    @Test
    public void testCreateFail() throws Exception {
        System.out.println("createFailure");
        
        try {
            Item item = ItemFactory.createItem("dog", "Fido", this.gameData);
        }
        catch (final Exception e) {
            final String msg = "Invalid item type.";
            assertEquals(msg, e.getMessage());
        }
    }
    
}
