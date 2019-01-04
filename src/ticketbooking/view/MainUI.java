/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.view;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author baojiaang
 */
public class MainUI extends JFrame {
    public static JFrame frame=new JFrame();
    public static CardLayout cardBig = new CardLayout();
    public static JPanel cardBigPanel = new JPanel(cardBig);
    public static CardLayout cardSmall = new CardLayout();
    public static JPanel cardSmallPanel = new JPanel(cardSmall);
    
    public MainUI(){
        
    }
    
    public void init(){
        frame=this;
   //     JPanel loginUI=new LoginUI.init();
      //  cardBigPanel.add(loginUI);
        frame.add(cardBigPanel);


        frame.setTitle("张学友演唱会订票系统");
        frame.setSize(1600,900);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    

    
}
