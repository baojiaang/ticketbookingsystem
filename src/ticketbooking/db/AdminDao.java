/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ticketbooking.entity.Admin;


/**
 *
 * @author baojiaang
 */
public class AdminDao {
     public Admin checkLogin(String username,String password) {
        String id=null;
        String name="";
        Admin admin=null;
        String sql="select * from admin where username=? and password=?";
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
       try {
            Connection connection=new Dbutil().getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);     
            rs=preparedStatement.executeQuery();
            while(rs.next()){
  
                id=rs.getString("id");
                name=rs.getString("name");
            }
             
            if(id!=null){
                admin=new Admin(Integer.valueOf(id),username,password,name);
            }
        } catch (SQLException ex) {
           
        }
        return admin;
    }
    public int changePassword(Admin admin) throws SQLException{
        Connection conn=Dbutil.getConnection();
        String sql="update admin set password =? where id =?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, admin.getPassword());
        pstmt.setInt(2, admin.getId());
        int result= pstmt.executeUpdate();
        return result;
      }
}
