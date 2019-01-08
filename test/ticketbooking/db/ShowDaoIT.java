/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.db;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketbooking.entity.Show;

/**
 *
 * @author baojiaang
 */
public class ShowDaoIT {
    
    public ShowDaoIT() {
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
     * Test of getShowList method, of class ShowDao.
     */
    @Test
    public void testGetShowList() throws Exception {
        System.out.println("getShowList");
        ShowDao instance = new ShowDao();
        ArrayList<Show> expResult = null;
        ArrayList<Show> result = instance.getShowList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShowByID method, of class ShowDao.
     */
    @Test
    public void testGetShowByID() throws Exception {
        System.out.println("getShowByID");
        Integer id = null;
        ShowDao instance = new ShowDao();
        Show expResult = null;
        Show result = instance.getShowByID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addShow method, of class ShowDao.
     */
    @Test
    public void testAddShow() throws Exception {
        System.out.println("addShow");
        Show show = null;
        ShowDao instance = new ShowDao();
        int expResult = 0;
        int result = instance.addShow(show);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
