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
import java.util.Date;
import ticketbooking.entity.Show;

/**
 *
 * @author baojiaang
 */
public class ShowDao {
    public ArrayList<Show> getShowList() throws SQLException{
        ArrayList<Show> shows=new ArrayList<>();
        Connection con=Dbutil.getConnection();
        String sql="select * from show";
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        ResultSet rs=preparedStatement.executeQuery();
        while(rs.next()){
            int id=rs.getInt("id");
            String name=rs.getString("name");
            String location=rs.getString("location");
            String description =rs.getString("description");
            String pic_path =rs.getString("pic_path");
            Date showTime =rs.getDate("show_time");
            String detail=rs.getString("detail");
            int row=rs.getInt("row");
            int col=rs.getInt("col");
            Show show=new Show(id,name,location,description,pic_path,showTime,detail,row,col);
            shows.add(show);
        }
        return shows;
    }
    
    public Show getShowByID(Integer id) throws SQLException{
        Connection con=Dbutil.getConnection();
        Show show=null;
        String sql="select * from show where id=?";
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs=preparedStatement.executeQuery();
        while(rs.next()){
            String name=rs.getString("name");
            String location=rs.getString("location");
            String des=rs.getString("description");
            String pic_path=rs.getString("pic_path");
             Date showTime =rs.getDate("show_time");
            String detail=rs.getString("detail");
              int row=rs.getInt("row");
            int col=rs.getInt("col");
              show=new Show(id,name,location,des,pic_path,showTime,detail,row,col);
        }
        return show;
    }
    
    public int addShow(Show show) throws SQLException{
        Connection con=Dbutil.getConnection();
        String sql ="insert into show(name,location,description,pic_path,show_time,row,col) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, show.getName());
        preparedStatement.setString(2, show.getLocation());
        preparedStatement.setString(3, show.getDescription());
        preparedStatement.setString(4, show.getPicPath());
        preparedStatement.setDate(5, new java.sql.Date(show.getShowTime().getTime()));
        preparedStatement.setInt(6, show.getRow());
        preparedStatement.setInt(7, show.getCol());
        preparedStatement.executeUpdate();
        ResultSet rs=preparedStatement.getGeneratedKeys();
        if(rs.next())
            return rs.getInt(1);
        return -1;
    }
}
