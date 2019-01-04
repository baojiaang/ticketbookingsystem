/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TemporalType;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import ticketbooking.db.OrderInfoDao;
import ticketbooking.db.SeatDao;
import ticketbooking.db.TicketDao;
import ticketbooking.entity.OrderInfo;
import ticketbooking.entity.Seat;
import ticketbooking.entity.Ticket;
import ticketbooking.entity.UserInfo;

/**
 *
 * @author baojiaang
 */
public class OrderListUI {
    private JFrame jf;
    private JTable orders;
    private UserInfo u;
    private ArrayList<ArrayList<OrderInfo>> orderlist=new ArrayList<>();  // 所有订单
    private JScrollPane jsp=new JScrollPane();
    private JPanel orderJPanel=new JPanel();
    Object[] columnNames ={"order id","ticket class","ticket price","row number","column number"};
    public OrderListUI(UserInfo u) throws SQLException{
        this.u=u;
        jf=new JFrame();
        init();
        jf.pack();
        jf.setVisible(true);
    }
    
    public void init() throws SQLException{
      
        jf.setLayout(new BorderLayout());
        orderJPanel.setLayout(new BoxLayout(orderJPanel, BoxLayout.Y_AXIS));
      //  jf.add(orderJPanel,BorderLayout.CENTER);
        jsp.setViewportView(orderJPanel);
        jf.add(jsp,BorderLayout.CENTER);
        OrderInfoDao orderInfoDao=new OrderInfoDao();
        ArrayList<String> orderNumbers=orderInfoDao.getOrderNumberListByUserID(u.getId());
        System.out.println(orderNumbers);
        for(int i=0;i<orderNumbers.size();i++){ //先获取所有订单
            JPanel smallPanel=new JPanel();
            JPanel infoPanel=new JPanel();
            smallPanel.setLayout(new BorderLayout());
            smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.Y_AXIS));
          Object[][] rowData=null;
        ArrayList<OrderInfo> orderInfos=orderInfoDao.getOrdersByOrderNumber(orderNumbers.get(i)); //单个订单下 的 所有票
            System.out.println(orderInfos);
        String username=null ;
        String order_num =null;
        Date buy_date=null;
         rowData=new Object[orderInfos.size()][5];
         int orderStatus=0;
        for(int j=0;j<orderInfos.size();j++){
            OrderInfo oi=orderInfos.get(j);
            int ticketID=oi.getTicketId();
            int orderID=oi.getId();
            Ticket ticket=new TicketDao().getTicketByID(ticketID);
            String ticket_class=ticket.getDescription();
            double price=oi.getPrice();
            int seatID= oi.getSeatId();
            Seat seat=new SeatDao().getSeatByid(seatID);
            int row=seat.getSeatRowNumber();
            int col=seat.getSeatColumnNumber();
            rowData[j][0]=orderID;
            rowData[j][1]=ticket_class;
            rowData[j][2]=price;
            rowData[j][3]=row;
            rowData[j][4]=col;
            buy_date=oi.getOrderDate();
            username=u.getName();
            order_num= oi.getOrderNumber();
            orderStatus=oi.getStatus();
        }
          JLabel info=new JLabel("username     "+username+"order nuber     "+order_num+"buy date    "+buy_date);
        JTable orderJTable=new JTable(rowData, columnNames);
            JScrollPane scr=new JScrollPane(orderJTable);
            infoPanel.add(info);
            JButton canel=new JButton("cancle");
            canel.addActionListener(new CanelActionListener(orderInfos));
            if(orderStatus==1)
            infoPanel.add(canel);
            JLabel statusJLabel=new JLabel();
            statusJLabel.setText(setStatusLabel(orderStatus));
            infoPanel.add(statusJLabel);
        smallPanel.add(infoPanel);
        smallPanel.add(scr);
        orderJPanel.add(smallPanel);
        orderlist.add(orderInfos);
        }
    }
    public String setStatusLabel(int status){
        if(status==1)
            return "successful booking";
        else if(status==0)
            return "cancled";
        return "cancled";
    }
}
class CanelActionListener implements ActionListener{
    ArrayList<OrderInfo> orderInfos;
    public CanelActionListener(ArrayList<OrderInfo> orderInfos) {
        this.orderInfos=orderInfos;
    }
      
    
    @Override
    public void actionPerformed(ActionEvent e) {
      for(int i=0;i<orderInfos.size();i++){
          OrderInfo oi=orderInfos.get(i);
          int seatID=oi.getSeatId();
          int orderID=oi.getId();
          try {
              new SeatDao().changetoBuyableStatus(seatID);
              new OrderInfoDao().changeStatus(orderID);
          } catch (SQLException ex) {
              Logger.getLogger(CanelActionListener.class.getName()).log(Level.SEVERE, null, ex);
          }
      } 
    }
    
}
