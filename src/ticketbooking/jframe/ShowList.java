/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.jframe;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import ticketbooking.entity.Show;
import ticketbooking.service.ShowJpaController;

/**
 *
 * @author baojiaang
 */
public class ShowList extends javax.swing.JFrame {
     private List<Show> shows;
     private EntityManagerFactory factory = Persistence.createEntityManagerFactory("TicketBookingSystemPU");  
     private ShowJpaController sjc=new ShowJpaController(factory);
     private JPanel[] show_panel;
     private JLabel[] show_name;
     private JLabel[] show_pic;
     private JLabel[] show_time;
     private JLabel[] show_location;
     private JButton[] show_detail;
     private ImageIcon show_icon;
     private JFrame jf=new JFrame();
     Box box=Box.createVerticalBox();
     
    /**
     * Creates new form ShowList
     */
    public ShowList() throws IOException {
        initComponents();
        initialze();
        jf.setContentPane(box);
         jf.pack();
        jf.setVisible(true);
 
    }
    public void initialze() throws FileNotFoundException, IOException {
        shows=sjc.findShowEntities();
        show_panel=new JPanel[shows.size()];
        show_name=new JLabel[shows.size()];
        show_pic=new JLabel[shows.size()];
        show_time=new JLabel[shows.size()];
        show_location=new JLabel[shows.size()];
        show_detail=new JButton[shows.size()];
        for(int i=0;i<shows.size();i++){
            Show show=shows.get(i);
            show_name[i]=new JLabel();
             show_time[i]=new JLabel();
              show_location[i]=new JLabel();
               show_panel[i]=new JPanel();
               show_detail[i]=new JButton();
            show_name[i].setText(show.getName());
            show_time[i].setText(show.getShowTime().toString());
            show_location[i].setText(show.getLocation());
            show_icon=new ImageIcon(show.getPicPath());
            show_detail[i].setText("detail");
            show_detail[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                      jf.dispose();
                    Detail detail = new Detail(show);
                }
            });
            show_pic[i]=new JLabel();
            show_icon.setImage(show_icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
               show_pic[i].setIcon(show_icon);
               show_pic[i].setSize(200,100);
             show_panel[i].add(show_pic[i]);
            show_panel[i].add(show_name[i]);
            show_panel[i].add(show_location[i]);
            show_panel[i].add(show_time[i]);
            show_panel[i].add(show_detail[i]);
            
            box.add(show_panel[i]);
            
   
        }
        
        
        
    }
    
    class MyButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         
           
        }
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );

        setBounds(0, 0, 1618, 947);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShowList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ShowList().setVisible(true);
//            }
//        });
     try
    {
        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        UIManager.put("RootPane.setupButtonVisible", false);
    }
    catch(Exception e)
    {
        //TODO exception
    }
       
       ShowList showList=new ShowList();
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}


