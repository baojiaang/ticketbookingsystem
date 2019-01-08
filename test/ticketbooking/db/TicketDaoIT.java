/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.db;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketbooking.entity.Ticket;

/**
 *
 * @author baojiaang
 */
public class TicketDaoIT {
    
    public TicketDaoIT() {
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
     * Test of getTicketByID method, of class TicketDao.
     */
    @Test
    public void testGetTicketByID() throws Exception {
        System.out.println("getTicketByID");
        Integer ticket_id = null;
        TicketDao instance = new TicketDao();
        Ticket expResult = null;
        Ticket result = instance.getTicketByID(ticket_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTicketsByShowId method, of class TicketDao.
     */
    @Test
    public void testGetTicketsByShowId() throws Exception {
        System.out.println("getTicketsByShowId");
        Integer show_id = null;
        TicketDao instance = new TicketDao();
        List<Ticket> expResult = null;
        List<Ticket> result = instance.getTicketsByShowId(show_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTicket method, of class TicketDao.
     */
    @Test
    public void testAddTicket() throws Exception {
        System.out.println("addTicket");
        Ticket ticket = null;
        TicketDao instance = new TicketDao();
        int expResult = 0;
        int result = instance.addTicket(ticket);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
