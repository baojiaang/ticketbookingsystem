/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import javax.persistence.criteria.Order;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import ticketbooking.db.OrderInfoDao;
import ticketbooking.db.SeatDao;
import ticketbooking.db.TicketDao;
import ticketbooking.db.UserInfoDao;
import ticketbooking.entity.Admin;
import ticketbooking.entity.OrderInfo;
import ticketbooking.entity.Seat;
import ticketbooking.entity.Ticket;
import ticketbooking.entity.UserInfo;

/**
 *
 * @author baojiaang
 */
public class AllOrderList {
    private JFrame jf;
    private JPanel nav_Panel;
    private JPanel main_Panel;
    private JButton back_Button;
    private Admin admin;
    public AllOrderList(Admin admin) throws SQLException{
        this.admin=admin;
        jf=new JFrame();
        nav_Panel=new JPanel();
        main_Panel=new JPanel();
        back_Button=new JButton("back");
        init();
        jf.setVisible(true);
        jf.pack();
        jf.setPreferredSize(new Dimension(800,600));
   
        
    }
    public void init() throws SQLException{
        
        back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              jf.dispose();
              new AdminIndex(admin);
            }
        });
        jf.setLayout(new BorderLayout());
        jf.add(nav_Panel, BorderLayout.NORTH);
        jf.add(main_Panel,BorderLayout.CENTER);
       
        JScrollPane jsp=new JScrollPane();
        nav_Panel.add(back_Button);
        main_Panel.add(jsp);
        Object[] columnNames={"order number","order date"," username","price","seat class","row","col","status"};

        ArrayList<OrderInfo> orders=new OrderInfoDao().getOrders();
       Object[][] rowData =new Object[orders.size()][8];
        for(int i=0;i<orders.size();i++){
            OrderInfo order=orders.get(i);
            int ticketID=order.getTicketId();
            Ticket t=new TicketDao().getTicketByID(ticketID);
 
            int seatID=order.getSeatId();
            Seat s=new SeatDao().getSeatByid(seatID);
            int userID=order.getUserId();
            UserInfo u=new UserInfoDao().getByID(userID);
            String username=u.getUsername();
            String orderNumber =order.getOrderNumber();
            Date date =order.getOrderDate();
            double price=t.getPrice();
            String seatclass =t.getDescription();
            int row=s.getSeatRowNumber();
            int col=s.getSeatColumnNumber();
            short status=order.getStatus();
            String print_status;
            if(status==0){
                print_status="booking success";
            }
            else{
                print_status="cancel";
            }
            rowData[i][0]=orderNumber;
            rowData[i][1]=date;
            rowData[i][2]=username;
            rowData[i][3]=price;
            rowData[i][4]=seatclass;
            rowData[i][5]=row;
            rowData[i][6]=col;
            rowData[i][7]=print_status;
        }
        
        JTable jt=new JTable(rowData, columnNames);
        jsp.setViewportView(jt);
        jsp.setPreferredSize(new Dimension(800, 550));
        FitTableColumns(jt);
    }
    public void FitTableColumns(JTable myTable){
  JTableHeader header = myTable.getTableHeader();
     int rowCount = myTable.getRowCount();

     Enumeration columns = myTable.getColumnModel().getColumns();
     while(columns.hasMoreElements()){
         TableColumn column = (TableColumn)columns.nextElement();
         int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
         int width = (int)myTable.getTableHeader().getDefaultRenderer()
                 .getTableCellRendererComponent(myTable, column.getIdentifier()
                         , false, false, -1, col).getPreferredSize().getWidth();
         for(int row = 0; row<rowCount; row++){
             int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
               myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
             width = Math.max(width, preferedWidth);
         }
         header.setResizingColumn(column); // 此行很重要
         column.setWidth(width+myTable.getIntercellSpacing().width);
     }
}
}
