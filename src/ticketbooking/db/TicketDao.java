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
import ticketbooking.entity.Ticket;

/**
 *
 * @author baojiaang
 */
public class TicketDao {

    public Ticket getTicketByID(Integer ticket_id) throws SQLException {
        Ticket t = null;
        Connection con = Dbutil.getConnection();
        String sql = "select * from ticket where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, ticket_id);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int show_id = rs.getInt("show_id");
            String des = rs.getString("description");
            double price = rs.getDouble("price");
            short rank = rs.getShort("rank");
            t = new Ticket(ticket_id, show_id, des, price, rank);
        }
        return t;
    }

    public List<Ticket> getTicketsByShowId(Integer show_id) throws SQLException {
        ArrayList<Ticket> tickets = new ArrayList();
        Connection con = Dbutil.getConnection();
        String sql = "select * from ticket where show_id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, show_id);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int ticket_id = rs.getInt("id");
            String des = rs.getString("description");
            double price = rs.getDouble("price");
            short rank = rs.getShort("rank");
            Ticket t = new Ticket(ticket_id, show_id, des, price, rank);
            tickets.add(t);
        }
        return tickets;
    }

    public int addTicket(Ticket ticket) throws SQLException {
        Connection con = Dbutil.getConnection();
        String sql = "insert into ticket (show_id,description,price,rank) values(?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, ticket.getShowId());
        pstmt.setString(2, ticket.getDescription());
        pstmt.setDouble(3, ticket.getPrice());
        pstmt.setShort(4, ticket.getRank());
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;

    }

}
