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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ticketbooking.entity.Seat;

/**
 *
 * @author baojiaang
 */
public class SeatDao {
    public List<Seat> getSeatsListByShowid(Integer show_id) throws SQLException{
        List<Seat> seats=new ArrayList<Seat>();
        Connection con=Dbutil.getConnection();
        String sql="select * from seat where show_id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, show_id);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            int id=rs.getInt("id");
            int seat_row_number=rs.getInt("seat_row_number");
            int seat_column_number=rs.getInt("seat_column_number");
            Double price=rs.getDouble("price");
            int ticket_id=rs.getInt("ticket_id");
            short status=rs.getShort("status");
            short rank=rs.getShort("rank");
            Seat seat=new Seat(id,show_id,seat_row_number,seat_column_number,price,ticket_id,status,rank);
            seats.add(seat);
        }
        con.close();
        return seats;
    }
        public List<Seat> getOrderedSeatsList(Integer show_id) throws SQLException{
        List<Seat> seats=new ArrayList<Seat>();
        Connection con=Dbutil.getConnection();
        String sql="select * from seat where show_id=? and status =1";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, show_id);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            int id=rs.getInt("id");
            int seat_row_number=rs.getInt("seat_row_number");
            int seat_column_number=rs.getInt("seat_column_number");
            Double price=rs.getDouble("price");
            int ticket_id=rs.getInt("ticket_id");
            short status=rs.getShort("status");
            short rank=rs.getShort("rank");
            Seat seat=new Seat(id,show_id,seat_row_number,seat_column_number,price,ticket_id,status,rank);
            seats.add(seat);
        }
        con.close();
        return seats;
    }
      public Seat getSeatByid(Integer id) throws SQLException{
        Seat seat=null;
        Connection con=Dbutil.getConnection();
        String sql="select * from seat where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
             int show_id=rs.getInt("show_id");
            int seat_row_number=rs.getInt("seat_row_number");
            int seat_column_number=rs.getInt("seat_column_number");
            Double price=rs.getDouble("price");
            int ticket_id=rs.getInt("ticket_id");
            short status=rs.getShort("status");
            short rank=rs.getShort("rank");
             seat=new Seat(id,show_id,seat_row_number,seat_column_number,price,ticket_id,status,rank);
       
        }
        con.close();
        return seat;
    }
    
    public int addSeat(Seat seat) throws SQLException{
        Connection con=Dbutil.getConnection();
        String sql="insert into seat(show_id,seat_row_number,seat_column_number,price,ticket_id,status,rank) values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        preparedStatement.setInt(1, seat.getShowId());
        preparedStatement.setInt(2, seat.getSeatRowNumber());
        preparedStatement.setInt(3, seat.getSeatColumnNumber());
        preparedStatement.setDouble(4, seat.getPrice());
        preparedStatement.setInt(5, seat.getTicketId());
        preparedStatement.setShort(6, seat.getStatus());
        preparedStatement.setShort(7, seat.getRank());
        int result=preparedStatement.executeUpdate();
        con.close();
        return result;
    }
    public int changeStatus(Integer id) throws SQLException{
         Connection con=Dbutil.getConnection();
        String sql="update seat set status=1 where id=?";
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int result=preparedStatement.executeUpdate();
        con.close();
        return result;
    }
    public Seat getSeatByShowIdAndRowAndCol(Integer showId, Integer row, Integer col) throws SQLException{
        Connection con= Dbutil.getConnection();
        Seat seat=null;
        String sql="select * from seat where show_id=? and seat_row_number=? and seat_column_number=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, showId);
        pstmt.setInt(2,row);
        pstmt.setInt(3, col);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            int id=rs.getInt("id");
            int show_id=rs.getInt("show_id");
            double price=rs.getDouble("price");
            int ticket_id=rs.getInt("ticket_id");
            short status=rs.getShort("status");
            short rank=rs.getShort("rank");
            seat=new Seat(id, showId, row, col, price, ticket_id, status, rank);
        }
        return seat;
    }
    
    public static void main(String[] args) throws SQLException {
        SeatDao sd=new SeatDao();
        for(int i=1;i<=2;i++){
            for(int j=1;j<=10;j++){
                Seat seat=new Seat(1,i,j,1888.0,5,(short)0,(short)1);
                sd.addSeat(seat);
               
            }
        }
        for(int i=3;i<=5;i++){
            for(int j=1;j<=10;j++){
                Seat seat=new Seat(1,i,j,1288.0,6,(short)0,(short)2);
                sd.addSeat(seat);
               
            }
        }
          for(int i=6;i<=10;i++){
            for(int j=1;j<=10;j++){
                Seat seat=new Seat(1,i,j,1088.0,7,(short)0,(short)3);
                sd.addSeat(seat);
               
            }
        }
    }
      public int changetoBuyableStatus(Integer id) throws SQLException{
        Connection con=Dbutil.getConnection();
        String sql="update seat set status =0  where id =?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, id);
        int result=pstmt.executeUpdate();
        return result;
    }
}
