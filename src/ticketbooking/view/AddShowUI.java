/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.view;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author baojiaang
 */
public class AddShowUI {
    private JFrame jf;
    private JPanel nav_JPanel;
    private JPanel main_JPanel;
    
    public AddShowUI(){
        
    }
    
    public void init(){
        jf=new JFrame();
        Container container=jf.getContentPane();
        jf.setLayout(new BorderLayout());
        jf.add(nav_JPanel);
        jf.add(main_JPanel);    
    }
    
    public void initMainPanel(){
         
    }
    
    
    
}
