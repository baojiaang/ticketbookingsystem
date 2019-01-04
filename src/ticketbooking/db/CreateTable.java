/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author baojiaang
 */
public class CreateTable {
    public void createuserInfoTable() throws SQLException{
        Connection conn=Dbutil.getConnection();
        Statement stmt=conn.createStatement();
        stmt.executeUpdate("create table user_info (\n" +
" \n" +
"   id               INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
" \n" +
"   username           varchar(1024),\n" +
" \n" +
"  password            varchar(1024),\n" +
" \n" +
"   name          varchar(1024),\n" +
" \n" +
"\n" +
"\n" +
"  primary key (id)\n" +
" \n" +
")");
        stmt.close();
    }
    public void createShowTable() throws SQLException{
        Connection conn=Dbutil.getConnection();
        Statement stmt=conn.createStatement();
        stmt.executeUpdate("create table show (\n" +
" \n" +
"   id               INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
" \n" +
"   name           varchar(1024),\n" +
"   location       varchar(1024),\n" +
"   description   varchar(1024),\n" +
"   pic_path      varchar(1024),\n" +
"   show_time      Date,\n" +
"   detail        varchar(1024),\n" +
" \n" +
"  primary key (id)\n" +
" \n" +
")");
        stmt.close();
    }
    public void createTicketTable() throws SQLException{
         Connection conn=Dbutil.getConnection();
        Statement stmt=conn.createStatement();
        stmt.executeUpdate("create table ticket (\n" +
" \n" +
"   id               INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
" \n" +
"   show_id           integer,\n" +
" \n" +
"  description         varchar(1024),\n" +
" \n" +
"  price              double,\n" +
"  reminder           integer,\n" +
"  amout              integer,\n" +
"\n" +
" \n" +
"  primary key (id)\n" +
" \n" +
")");
        stmt.close();
    }
    public void createSeatTable() throws SQLException{
         Connection conn=Dbutil.getConnection();
        Statement stmt=conn.createStatement();
        stmt.executeUpdate("create table seat (\n" +
" \n" +
"   id               INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
" \n" +
"   show_id           integer,\n" +
" \n" +
"  seat_row_number         integer,\n" +
"  seat_column_number      integer, 	\n" +
"  price              double,\n" +
"  ticket_id              integer,\n" +
"  status            smallint default 0, \n" +
"  rank              smallint ,	\n" +
" \n" +
"  primary key (id)\n" +
" \n" +
")");
        stmt.close();
        
    }
    
    public void createOrderTable() throws SQLException{
        Connection conn=Dbutil.getConnection();
        Statement stmt=conn.createStatement();
        stmt.executeUpdate("create table order_info (\n" +
" \n" +
"   id               INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
" \n" +
"   ticket_id            integer,\n" +
" \n" +
"  user_id            integer,\n" +
" \n" +
"  seat_id            integer,\n" +
" \n" +
"  price              double,\n" +
" \n" +
"  status              smallint default 1,\n" +
"  order_number        varchar(1024),\n" +
" \n" +
"   order_date             TIMESTAMP,\n" +
" \n" +
"  primary key (id)\n" +
" \n" +
")");
        conn.close();
    }
    
    public static void main(String[] args) throws SQLException {
        CreateTable createTable=new CreateTable();
//        createTable.createuserInfoTable();
//        createTable.createSeatTable();
//        createTable.createShowTable();
//        createTable.createTicketTable();
//createTable.createSeatTable();
createTable.createOrderTable();

    }
    
    
}