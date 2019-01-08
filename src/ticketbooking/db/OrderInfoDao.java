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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import ticketbooking.entity.OrderInfo;
import ticketbooking.entity.Seat;
import ticketbooking.entity.UserInfo;

/**
 *
 * @author baojiaang
 */
public class OrderInfoDao {
    public int createOrder(Seat seat,UserInfo u,String order_number,Timestamp timestamp) throws SQLException{
        Connection con=Dbutil.getConnection();
        String sql="insert into order_info(ticket_id,user_id,seat_id,price,order_date,status,order_number)  values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, seat.getTicketId());
        pstmt.setInt(2, u.getId());
        pstmt.setInt(3, seat.getId());
        pstmt.setDouble(4, seat.getPrice());
        pstmt.setTimestamp(5, timestamp);
        pstmt.setShort(6,(short)1);
        pstmt.setString(7, order_number);
        int result=pstmt.executeUpdate();
        con.close();
        return result;
    }
    
    public ArrayList<String> getOrderNumberListByUserID(Integer user_id) throws SQLException{
        ArrayList<String> orderNumbers =new ArrayList<>();
        Connection con=Dbutil.getConnection();
        String sql="select distinct order_number from order_info where user_id=? ";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            String order_numer=rs.getString("order_number");
            orderNumbers.add(order_numer);
        }
        return orderNumbers;
    }
    public ArrayList<OrderInfo> getOrdersByOrderNumber(String order_number) throws SQLException{
        ArrayList<OrderInfo> orders=new ArrayList<>();
        Connection con=Dbutil.getConnection();
        String sql="select * from order_info where order_number=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, order_number);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            int id=rs.getInt("id");
            int ticket_id=rs.getInt("ticket_id");
            int user_id=rs.getInt("user_id");
            int seat_id=rs.getInt("seat_id");
            double price=rs.getDouble("price");
            short status=rs.getShort("status");
            Date order_date=rs.getDate("order_date");
            OrderInfo oi=new OrderInfo(id, ticket_id, user_id, seat_id, price, order_date, status, order_number);
            orders.add(oi);
        }
        return orders;
    }
     public ArrayList<OrderInfo> getOrders() throws SQLException{
        ArrayList<OrderInfo> orders=new ArrayList<>();
        Connection con=Dbutil.getConnection();
        String sql="select * from order_info ";
        PreparedStatement pstmt=con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            int id=rs.getInt("id");
            int ticket_id=rs.getInt("ticket_id");
            int user_id=rs.getInt("user_id");
            int seat_id=rs.getInt("seat_id");
            double price=rs.getDouble("price");
            short status=rs.getShort("status");
            Date order_date=rs.getDate("order_date");
            String order_number =rs.getString("order_number");
            OrderInfo oi=new OrderInfo(id, ticket_id, user_id, seat_id, price, order_date, status, order_number);
            orders.add(oi);
        }
        return orders;
    }
    public int changeStatus(Integer id) throws SQLException{
        Connection con=Dbutil.getConnection();
        String sql="update order_info set status =0  where id =?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, id);
        int result=pstmt.executeUpdate();
        return result;
    }
    
}
