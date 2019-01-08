/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import ticketbooking.db.OrderInfoDao;
import ticketbooking.db.SeatDao;
import ticketbooking.db.ShowDao;
import ticketbooking.entity.Admin;
import ticketbooking.entity.Seat;
import ticketbooking.entity.Show;
import ticketbooking.entity.UserInfo;
import util.IDUtils;

/**
 *
 * @author baojiaang
 */
public class SelectSeat {
    private JFrame mainJFrame;
    private JPanel jf;
    private JPanel seat_panel;
    private JPanel stageJPanel;
    private JLabel stageJLabel;
    private JPanel selectPanel;
    private JPanel optionJPanel;
    private JPanel infoJPanel;
    private JLabel slectWord=new JLabel("you choosed:");
     ImageIcon introduction=new ImageIcon("pic/introduction.jpg");
    private JLabel infoJLabel=new JLabel();
    ImageIcon stgaeIcon=new ImageIcon("pic/stage.png");
    private JButton buyButton=new JButton("buy now");
    private ArrayList<Seat> selectedSeats=new ArrayList<>();
    private int showId;
    private UserInfo u;
    private Admin admin;
    private JButton backButton=new JButton("back");
    private JPanel navJPanel=new JPanel();
    private Show show;
    public SelectSeat(Integer showId,UserInfo u) throws SQLException{
        this.showId=showId;
        this.u=u;
        mainJFrame=new JFrame();
        Container container=mainJFrame.getContentPane();
        mainJFrame.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        init();
        initUI();    
    }
        public SelectSeat(Integer showId,Admin admin) throws SQLException{
        this.showId=showId;
        this.admin=admin;
        mainJFrame=new JFrame();
        Container container=mainJFrame.getContentPane();
        mainJFrame.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        init();
        initUI(); 
        optionJPanel.remove(buyButton);
        this.slectWord.setText("booked seat");
        initBookedSeat();
    }
    
    
    public void initUI(){
         navJPanel.add(backButton);
        mainJFrame.add(navJPanel);
        mainJFrame.add(jf);
        mainJFrame.setVisible(true);
        mainJFrame.pack();
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void init() throws SQLException{
        this.show=new ShowDao().getShowByID(showId);
        this.jf=new JPanel();
        this.jf.setLayout(new BorderLayout());
  
        this.seat_panel=new JPanel();
        this.stageJPanel=new JPanel();
        this.stageJLabel=new JLabel();
        this.stageJLabel.setIcon(stgaeIcon);
        this.stageJPanel.add(this.stageJLabel,JLabel.CENTER);
        this.stageJLabel.setOpaque(true);
        this.optionJPanel=new JPanel();
        this.selectPanel=new JPanel();
        this.infoJPanel=new JPanel();
        infoJPanel.add(infoJLabel);
        this.selectPanel.add(slectWord);
        this.introduction.setImage(this.introduction.getImage().getScaledInstance(100, 400, Image.SCALE_DEFAULT));
        this.stgaeIcon.setImage(this.stgaeIcon.getImage().getScaledInstance(600, 100, Image.SCALE_DEFAULT));
        this.infoJLabel.setIcon(introduction);
        optionJPanel.add(buyButton);
        //this.table_panel.setLayout(new BorderLayout());
        this.seat_panel.setLayout(new GridLayout(show.getRow()+1,show.getCol()+1,5,5));
        JScrollPane jsp=new JScrollPane();
        jsp.setViewportView(this.seat_panel);
        initSeat(show.getRow(),show.getCol());
        this.jf.add(jsp,BorderLayout.CENTER);
        this.jf.add(stageJPanel,BorderLayout.NORTH);
        this.jf.add(optionJPanel,BorderLayout.SOUTH);
        this.jf.add(selectPanel,BorderLayout.EAST);
        this.jf.add(infoJPanel,BorderLayout.WEST);
        BoxLayout layout=new BoxLayout(selectPanel, BoxLayout.Y_AXIS);
        selectPanel.setLayout(layout);
            
        addBackButtonListener();
        addBuyButtonListener();
    
     
    }
    
    public void initSeat(int m,int n) throws SQLException{

        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0){
                    if(j==0)
                        seat_panel.add(new JLabel(" "));
                    else
                        seat_panel.add(new JLabel(j+" column ",SwingConstants.CENTER));
                }else if(j==0){
                    if(i>0)
                        seat_panel.add(new JLabel(i+" row ",SwingConstants.CENTER));
                }
                else{
                    Seat seat=new SeatDao().getSeatByShowIdAndRowAndCol(showId, i, j);
                    boolean can_select=false;
                    if(admin==null)
                        can_select=true;
                    JButton seatButton=new oneSeat(seat).init(seat,selectPanel,selectedSeats,can_select);         
                    seat_panel.add(seatButton);
//                     if(admin!=null) // admin cannot choose seat
//                         seatButton.setEnabled(false);
                }
                             
            }
        }
    }
    public void initBookedSeat() throws SQLException{
        List<Seat> seats=new SeatDao().getOrderedSeatsList(showId);
        for(int i=0;i<seats.size();i++){
            Seat seat=seats.get(i);
            int rank=seat.getRank();
            String seatClass =null;
            if(rank==1)
                seatClass=" vip ";
            else if(rank==2)
                seatClass=" first seat ";
            else
                seatClass=" second seat ";
            JLabel booked=new JLabel(" seat class  "+seatClass+" price: "+ seat.getPrice()+" row: "+seat.getSeatRowNumber()+" col: "+seat.getSeatColumnNumber());
            selectPanel.add(booked);
        }
    }
    public void addBuyButtonListener(){
        buyButton.addActionListener(new ActionListener() {
            String ordernumber =IDUtils.createID();
            @Override
            public void actionPerformed(ActionEvent e) {
               for(int i=0;i<selectedSeats.size();i++){
                   Seat seat=selectedSeats.get(i);
                   OrderInfoDao od=new OrderInfoDao();
                   SeatDao sd=new SeatDao();
                   try {
                       Timestamp now=new Timestamp(new Date().getTime());
                       int result=od.createOrder(seat, u,ordernumber,now);
                       int result2=sd.changeStatus(seat.getId());
                       if(result==1&&result2==1){
                           System.out.println(seat+"booking success");
                       }
                   } catch (SQLException ex) {
                       Logger.getLogger(SelectSeat.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               JOptionPane.showMessageDialog(null, "booking tickets success", "INFORMATION_MESSAGE",JOptionPane.INFORMATION_MESSAGE);
               mainJFrame.dispose();
                try {
                    new OrderListUI(u);
                } catch (SQLException ex) {
                    Logger.getLogger(SelectSeat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public void addBackButtonListener(){
            backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               mainJFrame.dispose();
                try {
                   try {
                       new ShowList(u);
                   } catch (FileNotFoundException ex) {
                       Logger.getLogger(SelectSeat.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(SelectSeat.class.getName()).log(Level.SEVERE, null, ex);
                   }
                } catch (IOException ex) {
                    Logger.getLogger(SelectSeat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public static void main(String[] args) throws SQLException {
        
    }
}

