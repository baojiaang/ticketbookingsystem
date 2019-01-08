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
import java.util.logging.Level;
import ticketbooking.entity.UserInfo;

/**
 *
 * @author baojiaang
 */
public class UserInfoDao {
    public void register(String username,String password,String name) throws SQLException{
        Connection conn=Dbutil.getConnection();
        String sql="insert into user_info(username,password,name)  values(?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.setString(3, name);
        pstmt.execute();
        System.out.println("register success");
        pstmt.close();
    }
    
    public UserInfo checkLogin(String username,String password) {
        String id=null;
        String name="";
        UserInfo user=null;
        String sql="select * from user_info where username=? and password=?";
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
                user=new UserInfo(Integer.valueOf(id),username,password,name);
            }
        } catch (SQLException ex) {
           
        }
        return user;
    }
        public UserInfo getByID(int id) {
        String name="";
        UserInfo user=null;
        String sql="select * from user_info where id=? ";
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        String username ="";
        String password ="";
       try {
            Connection connection=new Dbutil().getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);  
            rs=preparedStatement.executeQuery();
            while(rs.next()){
  
                username=rs.getString("username");
                password=rs.getString("password");
                name=rs.getString("name");
            }    
                user=new UserInfo(Integer.valueOf(id),username,password,name);
            
        } catch (SQLException ex) {
           
        }
        return user;
    }
      public int changePassword(UserInfo user) throws SQLException{
        Connection conn=Dbutil.getConnection();
        String sql="update user_info set password =? where id =?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, user.getPassword());
        pstmt.setInt(2, user.getId());
        int result= pstmt.executeUpdate();
        return result;
      }
      public boolean checkUsername(String userName) throws SQLException{
        boolean exist=false;
        Connection conn=Dbutil.getConnection();
        String sql="select id from user_info where username=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, userName);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            exist=true;
        }
            return exist;
      }
}
