/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import ticketbooking.db.ShowDao;
import ticketbooking.entity.Admin;
import ticketbooking.entity.Show;
import ticketbooking.entity.UserInfo;

/**
 *
 * @author baojiaang
 */
public class ShowList extends javax.swing.JFrame {

    private List<Show> shows;

    private JPanel[] show_panel;
    private JLabel[] show_name;
    private JLabel[] show_pic;
    private JLabel[] show_time;
    private JLabel[] show_location;
    private JButton[] show_detail;
    private ImageIcon show_icon;
    private JFrame jf = new JFrame();
    private JButton backButton = new JButton("back");
    private JLabel infoJLabel = new JLabel("show list");
    private JPanel navJPanel = new JPanel();
    JPanel jpanel = new JPanel();
    //  Box box=Box.createVerticalBox();
    JScrollPane jsp = new JScrollPane();

    private UserInfo u;
    private Admin admin;

    /**
     * Creates new form ShowList
     */
    public ShowList(UserInfo u) throws IOException, FileNotFoundException, SQLException {
        this.u = u;
        initComponents();
        initialze();
        initUI();

    }
      public ShowList(Admin admin) throws IOException, FileNotFoundException, SQLException {
        this.admin=admin;
        initComponents();
        initialze();
        initUI();

    }
    public void initUI(){
         BoxLayout layout = new BoxLayout(jpanel, BoxLayout.Y_AXIS);
        jpanel.setLayout(layout);
        jsp.setViewportView(jpanel);

        Container container = jf.getContentPane();
        jf.setLayout(new BorderLayout());
        jf.add(jsp, BorderLayout.CENTER);
        jf.add(navJPanel, BorderLayout.NORTH);
        navJPanel.add(backButton);
        navJPanel.add(infoJLabel);
        addBackActionListener();
        jf.setPreferredSize(new Dimension(800, 600));
        jf.pack();
        jf.setVisible(true);
    }

    public void initialze() throws FileNotFoundException, IOException, SQLException {
        shows = new ShowDao().getShowList();
        show_panel = new JPanel[shows.size()];
        show_name = new JLabel[shows.size()];
        show_pic = new JLabel[shows.size()];
        show_time = new JLabel[shows.size()];
        show_location = new JLabel[shows.size()];
        show_detail = new JButton[shows.size()];
        for (int i = 0; i < shows.size(); i++) {
            Show show = shows.get(i);
            show_name[i] = new JLabel();
            show_time[i] = new JLabel();
            show_location[i] = new JLabel();
            show_panel[i] = new JPanel();
            show_detail[i] = new JButton();
            show_name[i].setText(show.getName());
            show_time[i].setText(show.getShowTime().toString());
            show_location[i].setText(show.getLocation());
            show_icon = new ImageIcon(show.getPicPath());
            show_detail[i].setText("detail");
            show_detail[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jf.dispose();
                    try {
                        if(u!=null){
                        new Detail(show, u);
                        }
                        else if(admin!=null){
                            new Detail(show, admin);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ShowList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            show_pic[i] = new JLabel();
            show_icon.setImage(show_icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
            show_pic[i].setIcon(show_icon);
            show_pic[i].setSize(200, 100);
            show_panel[i].add(show_pic[i]);
            show_panel[i].add(show_name[i]);
            show_panel[i].add(show_location[i]);
            show_panel[i].add(show_time[i]);
            show_panel[i].add(show_detail[i]);
            show_panel[i].setLayout(new FlowLayout(FlowLayout.LEFT));

            jpanel.add(show_panel[i]);

        }
    }

    public void addBackActionListener() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                if(u!=null)
                    new UserIndex(u);
                else
                    new AdminIndex(admin);
            }
        });
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
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

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
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            //TODO exception
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
