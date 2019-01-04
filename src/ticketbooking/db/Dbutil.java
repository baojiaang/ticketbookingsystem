/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author baojiaang
 */
public class Dbutil {
    private static String driver = "org.apache.derby.jdbc.ClientDriver";
  //  private static String url = "jdbc:derby:ticketbookingdb;create=true";
    private static String url="jdbc:derby://localhost:1527/ticket;create=true";
    private static String username="baojiaang";
    private static String password="123456";
    public static Connection getConnection() throws SQLException
    {
        try{
            Class.forName(driver);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return DriverManager.getConnection(url, username, password);
    }
    
    public static void main(String[] args) throws SQLException {
   
    }
  
}
