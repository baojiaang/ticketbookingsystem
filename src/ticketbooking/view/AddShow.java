/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.view;

import com.eltima.components.ui.DatePicker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import ticketbooking.db.SeatDao;
import ticketbooking.db.ShowDao;
import ticketbooking.db.TicketDao;
import ticketbooking.entity.Admin;
import ticketbooking.entity.Seat;
import ticketbooking.entity.Show;
import ticketbooking.entity.Ticket;

/**
 *
 * @author baojiaang
 */
public class AddShow extends javax.swing.JFrame {

    /**
     * Creates new form AddShow
     */
    public AddShow(Admin admin) {
        this.admin = admin;
        initComponents();
        this.setVisible(true);
        pic_upload.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                eventOnImport(new JButton());
            }
        });
        add_showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void getInputData() {
        int vip_start = Integer.valueOf(input_vip_start.getText());
        int first_start = Integer.valueOf(input_first_start.getText());
        int second_start = Integer.valueOf(input_second_start.getText());
        int vip_end = Integer.valueOf(input_vip_end.getText());
        int first_end = Integer.valueOf(input_first_end.getText());
        int second_end = Integer.valueOf(input_second_end.getText());

    }

    public int addVipTicket(int showID) throws SQLException {
        double vip_price = Double.valueOf(input_vip.getText());

        Ticket vip = new Ticket(showID, "vip", vip_price, (short) 1);

        return new TicketDao().addTicket(vip);

    }

    public int addFirstTicket(int showID) throws SQLException {

        double first_price = Double.valueOf(input_first.getText());

        Ticket first = new Ticket(showID, "first seat", first_price, (short) 2);

        return new TicketDao().addTicket(first);

    }

    public int addSecondTicket(int showID) throws SQLException {

        double second_price = Double.valueOf(input_second.getText());

        Ticket second = new Ticket(showID, "second seat", second_price, (short) 3);

        return new TicketDao().addTicket(second);

    }

    public int addShow() throws SQLException {
        String showName = input_showName.getText();
        String location = input_location.getText();
        Date date = (Date) datePicker1.getValue();
        String des = description.getText();
        int row = Integer.valueOf(input_row.getText());
        int col = Integer.valueOf(input_col.getText());
        Show show = new Show(showName, location, des, filePath, date, " ", row, col);
        int showID = new ShowDao().addShow(show);
        return showID;
    }

    public void addVipSeat(int showID, int ticketID) throws SQLException {
        int vip_start = Integer.valueOf(input_vip_start.getText());
        int vip_end = Integer.valueOf(input_vip_end.getText());
        int col = Integer.valueOf(input_col.getText());
        double vip_price = Double.valueOf(input_vip.getText());
        for (int i = vip_start; i <= vip_end; i++) {
            for (int j = 1; j <= col; j++) {
                Seat seat = new Seat(showID, i, j, vip_price, ticketID, (short) 0, (short) 1);
                new SeatDao().addSeat(seat);
            }
        }

    }

    public void addFirstSeat(int showID, int ticketID) throws SQLException {
        int first_start = Integer.valueOf(input_first_start.getText());
        int first_end = Integer.valueOf(input_first_end.getText());
        int col = Integer.valueOf(input_col.getText());
        double first_price = Double.valueOf(input_first.getText());
        for (int i = first_start; i <= first_end; i++) {
            for (int j = 1; j <= col; j++) {
                Seat seat = new Seat(showID, i, j, first_price, ticketID, (short) 0, (short) 2);
                new SeatDao().addSeat(seat);
            }
        }
    }

    public void addSecondSeat(int showID, int ticketID) throws SQLException {
        int second_start = Integer.valueOf(input_second_start.getText());
        int second_end = Integer.valueOf(input_second_end.getText());
        int col = Integer.valueOf(input_col.getText());
        double second_price = Double.valueOf(input_second.getText());
        for (int i = second_start; i <= second_end; i++) {
            for (int j = 1; j <= col; j++) {
                Seat seat = new Seat(showID, i, j, second_price, ticketID, (short) 0, (short) 3);
                new SeatDao().addSeat(seat);
            }
        }

    }
    public boolean checkRowAndCol(){
        try{
        int row = Integer.valueOf(input_row.getText());
        int col = Integer.valueOf(input_col.getText());
        if(row<=0||col<=0){
            JOptionPane.showMessageDialog(null, "input show venue size error", "INFORMATION_MESSAGE",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, "input show venue size error", "INFORMATION_MESSAGE",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
    public boolean checkInput() {
        boolean status=false;   
        try{
        int row = Integer.valueOf(input_row.getText());
        int vip_start = Integer.valueOf(input_vip_start.getText());
        int first_start = Integer.valueOf(input_first_start.getText());
        int second_start = Integer.valueOf(input_second_start.getText());
        int vip_end = Integer.valueOf(input_vip_end.getText());
        int first_end = Integer.valueOf(input_first_end.getText());
        int second_end = Integer.valueOf(input_second_end.getText());
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= row; i++) {
            set.add(i);
        }
        for (int i = vip_start; i <= vip_end; i++) {
            set.remove(i);
        }
        for (int i = first_start; i <= first_end; i++) {
            set.remove(i);
        }
        for (int i = second_start; i <= second_end; i++) {
            set.remove(i);
        }
        if(set.isEmpty())
            status=true;
        }
        catch(Exception e){
              JOptionPane.showMessageDialog(null, "input seat class row error!", "error",JOptionPane.WARNING_MESSAGE);  
              return false;
        }
        return status;
    }

    public void eventOnImport(JButton upload) {
        ByteArrayOutputStream baos = null;
        JFileChooser chooser = new JFileChooser();

        chooser.setMultiSelectionEnabled(true);

        /**
         * 过滤文件类型 *
         */
        FileNameExtensionFilter filter = new FileNameExtensionFilter("please choose picture",
                "jpg", "png", "jpeg", "bmp");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(upload);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            /**
             * 得到选择的文件*
             */
            File[] arrfiles = chooser.getSelectedFiles();
            if (arrfiles == null || arrfiles.length == 0) {
                return;
            }
            FileInputStream input = null;
            FileOutputStream out = null;
            String path = "pic/";
            try {
                for (File f : arrfiles) {
                    File dir = new File(path);
                    dir.listFiles();
                    filePath = path + f.getName();
                    // new URL(filePath);

                    input = new FileInputStream(f);
                    byte[] buffer = new byte[1024];
                    File des = new File(path, f.getName());
                    out = new FileOutputStream(des);
                    int len = 0;
                    baos = new ByteArrayOutputStream();
                    while (-1 != (len = input.read(buffer))) {
                        baos.write(buffer);
                        out.write(buffer, 0, len);
                    }

                    ImageIcon IC = new ImageIcon(baos.toByteArray());
                    IC.setImage(IC.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                    show_pic.setIcon(IC);

                    out.close();
                    input.close();
                }

            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "上传失败！", "提示",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "上传失败！", "提示",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    public DatePicker getDatePicker() {
        final DatePicker datepick;
        // 格式
        String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 14);

        Dimension dimension = new Dimension(177, 24);

        int[] hilightDays = {};

        int[] disabledDays = {};
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datepick = new DatePicker(date, DefaultFormat, font, dimension);

        datepick.setLocation(137, 83);//设置起始位置
        /*
        //也可用setBounds()直接设置大小与位置
        datepick.setBounds(137, 83, 177, 24);
         */
        // 设置一个月份中需要高亮显示的日子
        datepick.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datepick.setDisableddays(disabledDays);
        // 设置国家
        datepick.setLocale(Locale.CANADA);
        // 设置时钟面板可见
        datepick.setTimePanleVisible(true);
        return datepick;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        showNameLabel = new javax.swing.JLabel();
        input_showName = new javax.swing.JTextField();
        locationLabel = new javax.swing.JLabel();
        input_location = new javax.swing.JTextField();
        showtimeLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        show_pic = new javax.swing.JLabel();
        pic_upload = new javax.swing.JButton();
        datePicker1 = getDatePicker();
        vip_label = new javax.swing.JLabel();
        first_label = new javax.swing.JLabel();
        second_label = new javax.swing.JLabel();
        input_vip = new javax.swing.JTextField();
        input_first = new javax.swing.JTextField();
        input_second = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        add_showButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        column_label = new javax.swing.JLabel();
        input_row = new javax.swing.JTextField();
        input_col = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        input_vip_start = new javax.swing.JTextField();
        input_vip_end = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        input_first_start = new javax.swing.JTextField();
        input_first_end = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        input_second_start = new javax.swing.JTextField();
        input_second_end = new javax.swing.JTextField();
        back_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setPreferredSize(new java.awt.Dimension(800, 600));

        showNameLabel.setText("Show Name :");

        input_showName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_showNameActionPerformed(evt);
            }
        });

        locationLabel.setText("Location :");

        showtimeLabel.setText("Show Time :");

        jLabel1.setText("Show picture :");

        pic_upload.setText("picture upload");
        pic_upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pic_uploadActionPerformed(evt);
            }
        });

        vip_label.setText("vip price: ");

        first_label.setText("first seat price:");

        second_label.setText("second seat price:");

        input_vip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_vipActionPerformed(evt);
            }
        });

        description.setColumns(20);
        description.setRows(5);
        jScrollPane1.setViewportView(description);

        jLabel2.setText("description:");

        add_showButton.setText("Add Show");
        add_showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_showButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("show venue size: ");

        jLabel4.setText("row:");

        column_label.setText("column:");

        input_col.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_colActionPerformed(evt);
            }
        });

        jLabel5.setText("vip seat location");

        jLabel6.setText("start:");

        jLabel7.setText("end:");

        jLabel8.setText("first seat location:");

        jLabel9.setText("start");

        jLabel10.setText("end");

        jLabel11.setText("second seat location:");

        jLabel12.setText("start:");

        jLabel13.setText("end:");

        back_button.setText("back");
        back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(add_showButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(locationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(vip_label))
                                    .addComponent(showtimeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(showNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(input_row, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(column_label)
                                                .addGap(18, 18, 18)
                                                .addComponent(input_col, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(input_showName, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(input_location, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(157, 157, 157)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(pic_upload)
                                                    .addComponent(jLabel1))))
                                        .addGap(49, 49, 49)
                                        .addComponent(show_pic, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(input_vip, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(first_label)
                                                .addGap(18, 18, 18)
                                                .addComponent(input_first, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(42, 42, 42)
                                                        .addComponent(input_vip_end))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel6))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(input_vip_start, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(64, 64, 64)
                                                .addComponent(second_label)
                                                .addGap(30, 30, 30)
                                                .addComponent(input_second, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(input_first_end, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                                    .addComponent(input_first_start))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel13))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(input_second_start, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(input_second_end, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(42, 42, 42))))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(jLabel8)
                                .addGap(135, 135, 135)
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back_button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back_button)
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(show_pic, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(input_showName, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(showNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(locationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(input_location, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(showtimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(pic_upload)))
                                .addGap(9, 9, 9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(column_label)
                            .addComponent(input_row, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(input_col, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(input_vip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(first_label)
                    .addComponent(input_first, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(second_label)
                    .addComponent(input_second, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vip_label))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(input_vip_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(input_first_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(input_second_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(input_vip_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(input_first_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(input_second_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(add_showButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void input_showNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_showNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_showNameActionPerformed

    private void pic_uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pic_uploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pic_uploadActionPerformed

    private void input_vipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_vipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_vipActionPerformed

    private void input_colActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_colActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_colActionPerformed

    private void add_showButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_showButtonActionPerformed
        // TODO add your handling code here:
        int showID;
        try {
            if(!checkRowAndCol())
                return;
            if( !checkInput()){
                return;
            }
            showID = addShow();
            int vipID = addVipTicket(showID);
            int firstID = addFirstTicket(showID);
            int secondID = addSecondTicket(showID);
            addVipSeat(showID, vipID);
            addFirstSeat(showID, firstID);
            addSecondSeat(showID, secondID);
            System.out.println("add show success");
            Object[] options = {"back to index", "add another show"};  //自定义按钮上的文字
            int m = JOptionPane.showOptionDialog(null, "add show success", "Thanks", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (m == 0) {
                this.dispose();
                new AdminIndex(admin);
            } else {
                new AddShow(admin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddShow.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_add_showButtonActionPerformed

    private void back_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttonActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new AdminIndex(admin);
    }//GEN-LAST:event_back_buttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(AddShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }
    private Admin admin;
    private String filePath;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_showButton;
    private javax.swing.JButton back_button;
    private javax.swing.JLabel column_label;
    private com.eltima.components.ui.DatePicker datePicker1;
    private javax.swing.JTextArea description;
    private javax.swing.JLabel first_label;
    private javax.swing.JTextField input_col;
    private javax.swing.JTextField input_first;
    private javax.swing.JTextField input_first_end;
    private javax.swing.JTextField input_first_start;
    private javax.swing.JTextField input_location;
    private javax.swing.JTextField input_row;
    private javax.swing.JTextField input_second;
    private javax.swing.JTextField input_second_end;
    private javax.swing.JTextField input_second_start;
    private javax.swing.JTextField input_showName;
    private javax.swing.JTextField input_vip;
    private javax.swing.JTextField input_vip_end;
    private javax.swing.JTextField input_vip_start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JButton pic_upload;
    private javax.swing.JLabel second_label;
    private javax.swing.JLabel showNameLabel;
    private javax.swing.JLabel show_pic;
    private javax.swing.JLabel showtimeLabel;
    private javax.swing.JLabel vip_label;
    // End of variables declaration//GEN-END:variables
}
