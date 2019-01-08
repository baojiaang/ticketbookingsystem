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
import ticketbooking.entity.UserInfo;

/**
 *
 * @author baojiaang
 */
public class UserInfoDaoIT {
    
    public UserInfoDaoIT() {
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
     * Test of register method, of class UserInfoDao.
     */
    @Test
    public void testRegister() throws Exception {
        System.out.println("register");
        String username = "";
        String password = "";
        String name = "";
        UserInfoDao instance = new UserInfoDao();
        instance.register(username, password, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkLogin method, of class UserInfoDao.
     */
    @Test
    public void testCheckLogin() {
        System.out.println("checkLogin");
        String username = "";
        String password = "";
        UserInfoDao instance = new UserInfoDao();
        UserInfo expResult = null;
        UserInfo result = instance.checkLogin(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
