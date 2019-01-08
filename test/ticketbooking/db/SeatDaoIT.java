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
import ticketbooking.entity.Seat;

/**
 *
 * @author baojiaang
 */
public class SeatDaoIT {
    
    public SeatDaoIT() {
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
     * Test of getSeatsListByShowid method, of class SeatDao.
     */
    @Test
    public void testGetSeatsListByShowid() throws Exception {
        System.out.println("getSeatsListByShowid");
        Integer show_id = null;
        SeatDao instance = new SeatDao();
        List<Seat> expResult = null;
        List<Seat> result = instance.getSeatsListByShowid(show_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSeatByid method, of class SeatDao.
     */
    @Test
    public void testGetSeatByid() throws Exception {
        System.out.println("getSeatByid");
        Integer id = null;
        SeatDao instance = new SeatDao();
        Seat expResult = null;
        Seat result = instance.getSeatByid(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSeat method, of class SeatDao.
     */
    @Test
    public void testAddSeat() throws Exception {
        System.out.println("addSeat");
        Seat seat = null;
        SeatDao instance = new SeatDao();
        int expResult = 0;
        int result = instance.addSeat(seat);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeStatus method, of class SeatDao.
     */
    @Test
    public void testChangeStatus() throws Exception {
        System.out.println("changeStatus");
        Integer id = null;
        SeatDao instance = new SeatDao();
        int expResult = 0;
        int result = instance.changeStatus(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSeatByShowIdAndRowAndCol method, of class SeatDao.
     */
    @Test
    public void testGetSeatByShowIdAndRowAndCol() throws Exception {
        System.out.println("getSeatByShowIdAndRowAndCol");
        Integer showId = null;
        Integer row = null;
        Integer col = null;
        SeatDao instance = new SeatDao();
        Seat expResult = null;
        Seat result = instance.getSeatByShowIdAndRowAndCol(showId, row, col);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class SeatDao.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        SeatDao.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changetoBuyableStatus method, of class SeatDao.
     */
    @Test
    public void testChangetoBuyableStatus() throws Exception {
        System.out.println("changetoBuyableStatus");
        Integer id = null;
        SeatDao instance = new SeatDao();
        int expResult = 0;
        int result = instance.changetoBuyableStatus(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
