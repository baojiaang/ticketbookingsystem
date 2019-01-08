/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.db;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author baojiaang
 */
public class CreateTableIT {
    
    public CreateTableIT() {
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
     * Test of createuserInfoTable method, of class CreateTable.
     */
    @Test
    public void testCreateuserInfoTable() throws Exception {
        System.out.println("createuserInfoTable");
        CreateTable instance = new CreateTable();
        instance.createuserInfoTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createShowTable method, of class CreateTable.
     */
    @Test
    public void testCreateShowTable() throws Exception {
        System.out.println("createShowTable");
        CreateTable instance = new CreateTable();
        instance.createShowTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTicketTable method, of class CreateTable.
     */
    @Test
    public void testCreateTicketTable() throws Exception {
        System.out.println("createTicketTable");
        CreateTable instance = new CreateTable();
        instance.createTicketTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSeatTable method, of class CreateTable.
     */
    @Test
    public void testCreateSeatTable() throws Exception {
        System.out.println("createSeatTable");
        CreateTable instance = new CreateTable();
        instance.createSeatTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createOrderTable method, of class CreateTable.
     */
    @Test
    public void testCreateOrderTable() throws Exception {
        System.out.println("createOrderTable");
        CreateTable instance = new CreateTable();
        instance.createOrderTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAdminTable method, of class CreateTable.
     */
    @Test
    public void testCreateAdminTable() throws Exception {
        System.out.println("createAdminTable");
        CreateTable instance = new CreateTable();
        instance.createAdminTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class CreateTable.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        CreateTable.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
