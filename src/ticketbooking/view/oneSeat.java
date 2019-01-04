/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ticketbooking.entity.Seat;

/**
 *
 * @author baojiaang
 */
public class oneSeat {
    ImageIcon seatWhite = new ImageIcon("pic/white.png");
    ImageIcon seatGreen = new ImageIcon("pic/green.png");
    ImageIcon vipseat = new ImageIcon("pic/vip.jpg");
    ImageIcon firstseat=new ImageIcon("pic/first.jpg");
    ImageIcon secondseat=new ImageIcon("pic/second.jpg");
    ImageIcon choosed=new ImageIcon("pic/choosed.jpg");
    private int row;
    private int col;
    private int status;
    private int num;
    private int seat_id;
    private JLabel infoJLabel;
    public ImageIcon getSeatWhite() {
        return seatWhite;
    }

    public void setSeatWhite(ImageIcon seatWhite) {
        this.seatWhite = seatWhite;
    }

    public ImageIcon getSeatGreen() {
        return seatGreen;
    }

    public void setSeatGreen(ImageIcon seatGreen) {
        this.seatGreen = seatGreen;
    }

    public ImageIcon getVipseat() {
        return vipseat;
    }

    public void setVipseat(ImageIcon vipseat) {
        this.vipseat = vipseat;
    }

    public ImageIcon getFirstseat() {
        return firstseat;
    }

    public void setFirstseat(ImageIcon firstseat) {
        this.firstseat = firstseat;
    }

    public ImageIcon getSecondseat() {
        return secondseat;
    }

    public void setSecondseat(ImageIcon secondseat) {
        this.secondseat = secondseat;
    }

    public ImageIcon getChoosed() {
        return choosed;
    }

    public void setChoosed(ImageIcon choosed) {
        this.choosed = choosed;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public JButton getSeatButton() {
        return seatButton;
    }

    public void setSeatButton(JButton seatButton) {
        this.seatButton = seatButton;
    }

    
    
    JButton seatButton=new JButton();
    
    public oneSeat(){}
    
    public oneSeat(Seat seat){

        this.row=seat.getSeatRowNumber();
        this.col=seat.getSeatColumnNumber();
        this.status=seat.getStatus();
        this.seat_id=seat.getId();
        infoJLabel=new JLabel("row\n "+seat.getSeatRowNumber()+"column\n "+seat.getSeatColumnNumber()+"price\n "+seat.getPrice());
  
    }
    public JButton init(Seat seat,JPanel selectedJPanel,ArrayList<Seat> seats){
        if(seat.getStatus()==0){ //正常
            if(seat.getRank()==1){
            this.seatButton.setIcon(vipseat);
            }
            else if(seat.getRank()==2)
            this.seatButton.setIcon(firstseat);
            else
            this.seatButton.setIcon(secondseat);
        }
        else if(seat.getStatus()==1){
            this.seatButton.setIcon(choosed); // 被买了
        }
        
        seatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(getStatus()==0){// 可选
                  seatButton.setIcon(seatGreen);
                   setStatus(2);
                  selectedJPanel.add(infoJLabel);
                  seats.add(seat);
                   
               }else if(getStatus()==2){
                  if(seat.getRank()==1)
            seatButton.setIcon(vipseat);
            else if(seat.getRank()==2)
            seatButton.setIcon(firstseat);
            else
            seatButton.setIcon(secondseat);
                  selectedJPanel.remove(infoJLabel);
                   setStatus(0);
                   seats.remove(seat);
               }
                         
            }
        });
        return seatButton;
    }
    
}
