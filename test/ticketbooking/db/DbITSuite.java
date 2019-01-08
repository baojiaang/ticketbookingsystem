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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author baojiaang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ticketbooking.db.CreateTableIT.class, ticketbooking.db.DbutilIT.class, ticketbooking.db.AdminDaoIT.class, ticketbooking.db.ShowDaoIT.class, ticketbooking.db.UserInfoDaoIT.class, ticketbooking.db.TicketDaoIT.class, ticketbooking.db.SeatDaoIT.class, ticketbooking.db.OrderInfoDaoIT.class})
public class DbITSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
