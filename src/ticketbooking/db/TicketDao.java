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
import ticketbooking.entity.Ticket;

/**
 *
 * @author baojiaang
 */
public class TicketDao {
    public Ticket getTicketByID(Integer ticket_id) throws SQLException{
         Ticket t=null;
        Connection con=Dbutil.getConnection();
        String sql="select * from ticket where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, ticket_id);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            int show_id=rs.getInt("show_id");
            String des =rs.getString("description");
            double price=rs.getDouble("price");
            short rank=rs.getShort("rank");
             t=new Ticket(ticket_id, ticket_id, des, price,rank);
        }
        return t;
    }
}
