package chatapp;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserProfile extends JFrame {

    action2 a8 = new action2();
    U_profile user = new U_profile();
    ImageIcon image, image1, image2,image3,image4;
    JPanel p1,p2;
    JLabel l1, l2, l3, l4, img, img1,img2,img3;
    JButton l5, bb, b2, bb1,bb2;
    JTextField ph;

    public UserProfile() {
        this.setVisible(true);
        this.setTitle("UserProfile");
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 100);
        this.setResizable(false);
        this.setLayout(null);
        p1 = new JPanel();
        this.add(p1);
        p1.setBounds(0, 0, 700, 700);
        p1.setLayout(null);
        try {
            File myfile = new File("app.gif");
            Image img = ImageIO.read(myfile);
            this.setIconImage(img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        image3 = new ImageIcon(getClass().getResource("user-02-01-01.png"));
        img2 = new JLabel(image3);
        img2.setBounds(0, 0, 700, 700);
        image4 = new ImageIcon(getClass().getResource("LOG OUT-09-09-09-09.png"));
        img1 = new JLabel(image4);
        Sql_Profile();
        l3 = new JLabel(user.getF_name() + " " + user.getL_name());
        l4 = new JLabel(user.getText_description() + " ");
        image = new ImageIcon(getClass().getResource(user.getPhoto()));
        img = new JLabel(image);
       
        img.setBounds(200, 20, 200, 200);
        
        p1.add(img);
       
        l1 = new JLabel("name : ");
        l1.setFont(new Font("", Font.BOLD, 22));
        l1.setBounds(80, 330, 100, 30);
        l2 = new JLabel("description : ");
        l2.setFont(new Font("", Font.BOLD, 22));
        l2.setBounds(80, 380, 150, 30);
        l3.setFont(new Font("", Font.BOLD, 22));
        l3.setBounds(200, 330, 150, 30);
        l4.setFont(new Font("", Font.BOLD, 22));
        l4.setBounds(230, 380, 250, 30);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        l5 = new JButton("change_profile");
        l5.setBounds(235, 230, 120, 30);
        l5.setBackground(Color.CYAN);
        p1.add(l5);
        bb1 = new JButton("Chating_Rooms");
        bb1.setBounds(510, 290, 130, 40);
        bb1.setBackground(Color.CYAN);
        p1.add(bb1);
        bb2 = new JButton("show story");
        bb2.setBounds(510, 380, 130, 40);
        bb2.setBackground(Color.CYAN);
        p1.add(bb2);
        ph = new JTextField();
        ph.setBounds(500, 100, 150, 30);
        p1.add(ph);
        bb = new JButton("add contact");
        bb.setBounds(515, 150, 120, 30);
        bb.setBackground(Color.CYAN);
        p1.add(bb);
        b2 = new JButton();
        b2.setBounds(10, 10, 50, 40);
        b2.setBackground(Color.WHITE);
        p1.add(b2);
        b2.setLayout(null);
        p1.add(img2);
        img1.setBounds(5, 5, 40, 30);
        img1.setFont(new Font("", Font.BOLD, 22));
        img1.setBackground(Color.yellow);
        b2.add(img1);
        l5.addActionListener(a8);
        bb1.addActionListener(a8);
        bb2.addActionListener(a8);
        bb.addActionListener(a8);
        b2.addActionListener(a8);
    }

    private void Sql_Profile() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                java.sql.Statement stm = con.createStatement();
                String sql = "SELECT * FROM chatapp.user_profile where User_id=" + LogIn.id + ";";
                ResultSet rset = stm.executeQuery(sql);
                if (rset.next()) {
                    user.setF_name(LogIn.FName);
                    user.setL_name(LogIn.LName);
                    user.setId(LogIn.id);
                    user.setPhoto(rset.getString("photo"));
                    user.setText_description(rset.getString("text_description"));
                    user.setVisiability(rset.getString("visiability"));
                } else {
                    JOptionPane.showMessageDialog(null, "wrong????");
                }
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }

    private class action2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == l5) {
                change_profile f = new change_profile();
                setVisible(false);
            }
            if (ae.getSource() == bb1) {
                Frind_User f = new Frind_User();
                setVisible(false);
            }
            if (ae.getSource() == bb2) {
                Story_GUI f = new Story_GUI();
                setVisible(false);
            }
            if (ae.getSource() == b2) {
                LogIn ln = new LogIn();
                setVisible(false);
            }

            if (ae.getSource() == bb && ph.getText().length() == 11) {
                String phone = ph.getText();
                String name = null;
                int connect_id = 0;
                int chat_r_id = 0;
                ph.setText("");
                boolean isExist = true;
                boolean isExist2 = false;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        java.sql.Statement stm = con.createStatement();
                        String sql = "SELECT * FROM chatapp.users where mobile_number=" + '"' + phone + '"' + ';';
                        ResultSet rset = stm.executeQuery(sql);
                        if (rset.next()) {
                            connect_id = rset.getInt("Id");
                            name = rset.getString("First_Name");
                        }
                        else{
                                isExist = false;
                            JOptionPane.showMessageDialog(null, "this contact not have an account");
                        }
                    }
                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                }
                if (isExist) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                            Statement st = con.createStatement();
                            String sql = "INSERT INTO `chatapp`.`friend_id` (`User_id`, `Friend_id`, `visiability`) VALUES (" + "'" + LogIn.id + "'," + "'" + connect_id + "'" + ",'" + 1 + "');";
                            st.executeUpdate(sql);
                            con.close();
                        }
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                            Statement st = con.createStatement();
                            String sql = "INSERT INTO `chatapp`.`friend_id` (`User_id`, `Friend_id`, `visiability`) VALUES (" + "'" + connect_id + "'," + "'" + LogIn.id + "','" + 0 + "');";
                            st.executeUpdate(sql);
                            con.close();
                        }

                        Class.forName("com.mysql.cj.jdbc.Driver");
                        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                            Statement st = con.createStatement();
                            String sql = "INSERT INTO `chatapp`.`chat_room` (`NAME`) VALUES (" + "'" + name + "');";
                            st.executeUpdate(sql);
                            con.close();
                        }
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                            java.sql.Statement stm = con.createStatement();
                            String sql = "SELECT * FROM chatapp.chat_room order by CR_ID DESC LIMIT 1;";
                            ResultSet rset = stm.executeQuery(sql);
                            if (rset.next()) {
                                chat_r_id = rset.getInt("CR_ID");
                            }
                            con.close();
                        }
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                            Statement st = con.createStatement();
                            String sql = "INSERT INTO `chatapp`.`user_room` (`User_id`, `Chat_room_id`) VALUES (" + "'" + LogIn.id + "'," + "'" + chat_r_id + "');";
                            st.executeUpdate(sql);
                            con.close();
                        }
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                            Statement st = con.createStatement();
                            String sql = "INSERT INTO `chatapp`.`user_room` (`User_id`, `Chat_room_id`) VALUES (" + "'" + connect_id + "'," + "'" + chat_r_id + "');";
                            st.executeUpdate(sql);
                            JOptionPane.showMessageDialog(null, "save");
                            con.close();
                        }

                    } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                        //System.out.println(e.getMessage());
                        isExist2 = true;
                    }
                    if (isExist2) {
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                                java.sql.Statement stm = con.createStatement();
                                String sql = "UPDATE `chatapp`.`friend_id` SET `visiability` = '1' WHERE (`Friend_id` ="+"'"+connect_id+"') and (`User_id` ="+"'"+LogIn.id+"');";
                                stm.executeUpdate(sql);
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
    }

}
