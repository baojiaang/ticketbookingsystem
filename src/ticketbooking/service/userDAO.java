///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ticketbooking.service;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import ticketbooking.entity.User;
//import util.Dbutil;
//
//
///**
// *
// * @author baojiaang
// */
//public class userDAO {
//    public String checkLogin(String username,String password) {
//        String id=null;
//        String sql="select * from user_info where username=? and password=?";
//        PreparedStatement preparedStatement=null;
//        ResultSet rs=null;
//        try {
//            Connection connection=new Dbutil().getConnection();
//            preparedStatement=connection.prepareStatement(sql);
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//            
//            rs=preparedStatement.executeQuery();
//            while(rs.next()){
//                id=rs.getString("id");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return id;
//    }
//    public static void main(String[] args) {
//      
//        
//    }
//}
