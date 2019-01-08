/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketbooking.entity.OrderInfo;
import ticketbooking.entity.Seat;
import ticketbooking.entity.UserInfo;

/**
 *
 * @author baojiaang
 */
public class OrderInfoDaoIT {
    
    public OrderInfoDaoIT() {
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
     * Test of createOrder method, of class OrderInfoDao.
     */
    @Test
    public void testCreateOrder() throws Exception {
        System.out.println("createOrder");
        Seat seat = null;
        UserInfo u = null;
        String order_number = "";
        Timestamp timestamp = null;
        OrderInfoDao instance = new OrderInfoDao();
        int expResult = 0;
        int result = instance.createOrder(seat, u, order_number, timestamp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderNumberListByUserID method, of class OrderInfoDao.
     */
    @Test
    public void testGetOrderNumberListByUserID() throws Exception {
        System.out.println("getOrderNumberListByUserID");
        Integer user_id = null;
        OrderInfoDao instance = new OrderInfoDao();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getOrderNumberListByUserID(user_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdersByOrderNumber method, of class OrderInfoDao.
     */
    @Test
    public void testGetOrdersByOrderNumber() throws Exception {
        System.out.println("getOrdersByOrderNumber");
        String order_number = "";
        OrderInfoDao instance = new OrderInfoDao();
        ArrayList<OrderInfo> expResult = null;
        ArrayList<OrderInfo> result = instance.getOrdersByOrderNumber(order_number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeStatus method, of class OrderInfoDao.
     */
    @Test
    public void testChangeStatus() throws Exception {
        System.out.println("changeStatus");
        Integer id = null;
        OrderInfoDao instance = new OrderInfoDao();
        int expResult = 0;
        int result = instance.changeStatus(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
