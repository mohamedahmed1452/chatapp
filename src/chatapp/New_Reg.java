package chatapp;

import java.awt.*;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.*;

public class New_Reg extends JFrame {

    action1 aa1 = new action1();
    User user = new User();
    JButton b1, b2;
    Panel p1;
    JLabel l2, l3, l4, l5, l6, img1, img2;
    ImageIcon image1, image2;
    JPasswordField t2;
    JTextField t3, t4, t5, t6;

    public New_Reg() {
        image1 = new ImageIcon(getClass().getResource("sign up.jpeg"));
        img1 = new JLabel(image1);
        image2 = new ImageIcon(getClass().getResource("ph-02-02-02.jpeg"));
        img2 = new JLabel(image2);
        this.setVisible(true);
        this.setTitle("Create Email");
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 100);
        this.setResizable(false);
        this.setLayout(null);
        p1 = new Panel();
        p1.setBounds(0, 0, 1000, 750);
        p1.setBackground(Color.gray);
        p1.setLayout(null);
        this.add(p1);
        l2 = new JLabel("Phone");
        l3 = new JLabel("Password");
        l4 = new JLabel("User_Id");
        l6 = new JLabel("First_Name");
        l5 = new JLabel("Last_Name");
        l2.setBounds(30, 290, 120, 30);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("", Font.BOLD, 24));
        l3.setBounds(30, 340, 120, 30);
        l3.setForeground(Color.WHITE);
        l3.setFont(new Font("", Font.BOLD, 24));
        l4.setBounds(30, 390, 120, 30);
        l4.setForeground(Color.WHITE);
        l4.setFont(new Font("", Font.BOLD, 24));
        l5.setBounds(30, 240, 150, 30);
        l5.setForeground(Color.WHITE);
        l5.setFont(new Font("", Font.BOLD, 24));
        l6.setBounds(30, 190, 150, 30);
        l6.setForeground(Color.WHITE);
        l6.setFont(new Font("", Font.BOLD, 24));
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        t2 = new JPasswordField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();
        t2.setBounds(200, 340, 350, 30);
        t2.setFont(new Font("", Font.BOLD, 24));
        t3.setBounds(200, 290, 350, 30);
        t3.setFont(new Font("", Font.BOLD, 24));
        t4.setBounds(200, 390, 350, 30);
        t4.setFont(new Font("", Font.BOLD, 24));
        t5.setBounds(200, 240, 350, 30);
        t5.setFont(new Font("", Font.BOLD, 24));
        t6.setBounds(200, 190, 350, 30);
        t6.setFont(new Font("", Font.BOLD, 24));
        p1.add(t2);
        p1.add(t3);
        p1.add(t4);
        p1.add(t5);
        p1.add(t6);
        b1 = new JButton("Save");
        b2 = new JButton();
        b2.setBounds(0, 0, 120, 90);
        b1.setBounds(400, 500, 150, 40);
        b1.setForeground(Color.BLACK);
        b1.setBackground(Color.CYAN);
        b1.setFont(new Font("", Font.BOLD, 24));
        p1.add(b1);
        p1.add(b2);
        p1.add(img1);
        img1.setBounds(0, 0, 1000, 750);
        img2.setBounds(0, 0, 120, 90);
        b2.setLayout(null);
        b2.add(img2);
        try {
            File myfile = new File("app.gif");
            Image img = ImageIO.read(myfile);
            this.setIconImage(img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        b2.addActionListener(aa1);
        b1.addActionListener(aa1);
    }

    private class action1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == b2) {
                LogIn ln = new LogIn();
                setVisible(false);
            }
            if (ae.getSource() == b1) {
                user.setPassword(t2.getText());
                user.setF_name(t6.getText());
                user.setL_name(t5.getText());
                user.setPhone(t3.getText());
                user.setId(Integer.parseInt(t4.getText()));
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        Statement st = con.createStatement();
                        String sql = "INSERT INTO `chatapp`.`users` (`Id`, `mobile_number`, `password`,`First_Name`, `Last_Name`) VALUES "
                                + "(" + "'" + user.getId() + "'," + "'" + user.getPhone() + "'," + "'" + user.getPassword() + "'," + "'" + user.getF_name() + "'," + "'" + user.getL_name() + "'" + ");";
                        if (user.getF_name().length() == 0 || user.getL_name().length() == 0 || user.getPassword().length() == 0 || user.getPhone().length() == 0) {
                            throw new SQLException("Full data please ?");
                        }
                        st.executeUpdate(sql);
                    }
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        Statement st = con.createStatement();
                        String sql = "INSERT INTO `chatapp`.`user_profile` (`text_description`, `User_id`, `visiability`, `photo`)"
                                + " VALUES ('null'," + "'" + user.getId() + "'," + " 'true', 'LogIn2.png');";
                        st.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "save");
                          con.close();
                    }
                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }

    }

}
