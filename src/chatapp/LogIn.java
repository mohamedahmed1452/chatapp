package chatapp;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class LogIn extends JFrame {

    public String phone;
    public String password;
    public static String FName;
    public static String LName;
    public static int id;
    action a = new action();
    JLabel l1, l2;
    JTextField t1, t2;
    JButton b2 = new JButton("Login");
    JButton b3 = new JButton("Sign up");
    Panel p1;
    JLabel img, img1;
    ImageIcon image, image1;

    public LogIn() {
        image = new ImageIcon(getClass().getResource("ph-02-03.jpeg"));
        img = new JLabel(image);
        img.setBounds(0, 0, 800, 800);
        this.setVisible(true);
        this.setTitle("LogIn");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 100);
        this.setResizable(false);
        this.setLayout(null);

        try {
            File myfile = new File("app.gif");
            Image img = ImageIO.read(myfile);
            this.setIconImage(img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        p1 = new Panel();
        this.add(p1);
        p1.setBounds(0, 0, 800, 800);
        p1.setBackground(Color.PINK);
        p1.setLayout(null);
        l1 = new JLabel("Phone");
        l2 = new JLabel("Password");
        l1.setBounds(155, 500, 400, 100);
        l1.setFont(new Font("", Font.BOLD, 32));
        l1.setForeground(Color.WHITE);
        l2.setBounds(155, 550, 300, 100);
        l2.setFont(new Font("", Font.BOLD, 32));
        l2.setForeground(Color.WHITE);
        p1.add(l1);
        p1.add(l2);
        t1 = new JTextField();
        t2 = new JPasswordField();
        t1.setBounds(350, 530, 270, 30);
        t2.setBounds(350, 580, 270, 30);
        t1.setFont(new Font("", Font.BOLD, 25));
        t2.setFont(new Font("", Font.BOLD, 25));
        p1.add(t1);
        p1.add(t2);
        b2 = new JButton("Login");
        b3 = new JButton("Sign up");
        b2.setBounds(520, 650, 150, 40);
        b2.setForeground(Color.BLACK);
        b2.setFont(new Font("", Font.BOLD, 24));
        b2.setBackground(Color.cyan);
        b3.setBounds(100, 650, 150, 40);
        b3.setForeground(Color.BLACK);
        b3.setFont(new Font("", Font.BOLD, 24));
        b3.setBackground(Color.cyan);
        p1.add(b2);
        p1.add(b3);
        p1.add(img);
        b3.addActionListener(a);
        b2.addActionListener(a);
    }

    private class action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == b3) {
                New_Reg n1 = new New_Reg();
                setVisible(false);
            }
            if (ae.getSource() == b2) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        phone = t1.getText();
                        password = t2.getText();
                        java.sql.Statement stm = con.createStatement();
                        String sql = "SELECT * FROM chatapp.users where mobile_number=" + '"' + phone + '"' + "and password =" + '"' + password + '"' + ';';
                        ResultSet rset = stm.executeQuery(sql);
                        if (rset.next()) {
                            setVisible(false);
                            FName = rset.getString("First_Name");
                            LName = rset.getString("Last_Name");
                            id = rset.getInt("Id");
                            UserProfile u1 = new UserProfile();
                            
                        } else {
                            InputStream in;
                            in = new FileInputStream(new File("C:\\Users\\www\\Downloads\\Music\\sound2.wav"));
                            AudioStream audioStream = new AudioStream(in);
                            AudioPlayer.player.start(audioStream);
                        }
                      con.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());

                    }
                } catch (HeadlessException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());

                }
            }
        }

    }
}
