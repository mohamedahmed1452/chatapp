package chatapp;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class change_profile extends JFrame {

    public String phone;
    public String password;
    public static String FName;
    public static String LName;
    public static int id;
    JLabel img,img1,img2,img3;
    Panel p1;
    ImageIcon image,image1,image2,image3;
    U_profile user = new U_profile();
    JButton b3, b4,b5;
    JTextField l4 = new JTextField();
    public change_profile() {
        action3 aa1 = new action3();
        p1 = new Panel();
        JLabel l1 = new JLabel("name : ");
        JLabel l2 = new JLabel("description : ");
        this.setVisible(true);
        this.setTitle("UPDATE PROFILE");
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 150);
        this.setResizable(false);
        this.setLayout(null);
        this.add(p1);
        p1.setBounds(0, 0, 700, 700);
        p1.setLayout(null);
        image2 = new ImageIcon(getClass().getResource("ph-02-02-02.jpeg"));
        img2=new JLabel(image2);
        image1 = new ImageIcon(getClass().getResource("camera.png"));
        img1=new JLabel(image1);
        try {
            File myfile = new File("app.gif");
            Image img = ImageIO.read(myfile);
            this.setIconImage(img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Sql_Profile();
        JLabel l3 = new JLabel(user.getF_name() + " " + user.getL_name());
        image = new ImageIcon(getClass().getResource(user.getPhoto()));
        img = new JLabel(image);
        p1.add(img);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        b3 = new JButton();
        b3.setLayout(null);
        b3.add(img2);
        b4 = new JButton();
        b4.setLayout(null);
        b4.add(img1);
        b4.setBackground(Color.WHITE);
        b5 = new JButton("save");
        b5.setBounds(480, 400, 80, 30);
        b5.setBackground(Color.CYAN);
        b3.setBounds(0, 0, 120, 90);
        b4.setBounds(330, 260, 50, 40);
        b3.setBackground(Color.BLUE);
        b3.setFont(new Font("", Font.BOLD, 22));
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);
        img2.setBounds(0, 0, 120, 90);
        img1.setBounds(0, 5, 50, 35);
        img.setBounds(260, 60, 200, 200);
        l1.setFont(new Font("", Font.BOLD, 22));
        l1.setBounds(80, 350, 100, 30);
        l2.setFont(new Font("", Font.BOLD, 22));
        l2.setBounds(80, 400, 150, 30);
        l3.setFont(new Font("", Font.BOLD, 22));
        l3.setBounds(250, 350, 200, 30);
        l4.setFont(new Font("", Font.BOLD, 22));
        l4.setBounds(250, 400, 200, 30);
        image3 = new ImageIcon(getClass().getResource("user-02-01-01.png"));
        img3 = new JLabel(image3);
        img3.setBounds(0, 0, 700, 700);
        p1.add(img3);
        b4.addActionListener(aa1);
        b3.addActionListener(aa1);
        b5.addActionListener(aa1);

    }

    private void Sql_Profile() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                java.sql.Statement stm = con.createStatement();
                String sql = "SELECT * FROM chatapp.user_profile,chatapp.users where id=" + LogIn.id + " and User_id=" + LogIn.id + ";";
                ResultSet rset = stm.executeQuery(sql);
                if (rset.next()) {
                    user.setF_name(rset.getString("First_Name"));
                    user.setL_name(rset.getString("Last_Name"));
                    user.setId(rset.getInt("User_id"));
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
    private class action3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String image_ph="Login2.png";
            if (ae.getSource() == b4) {
                JFileChooser filea = new JFileChooser();
                int response = filea.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(filea.getSelectedFile().getAbsolutePath());
                    image_ph=file.getName();
                }
                p1.remove(img);
                image = new ImageIcon(getClass().getResource(image_ph));
                p1.remove(img);
                img = new JLabel(image);
                p1.add(img);
                img.setBounds(200, 20, 200, 200);
                try
               {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        java.sql.Statement stm = con.createStatement();
                        String sql = "UPDATE `chatapp`.`user_profile` SET `photo` = "+"'"+image_ph +"'"+"WHERE (`User_id` = " + user.getId() + ");";
                        stm.executeUpdate(sql);
                    }
                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());

                }
            }
              if (ae.getSource() == b3) {
                UserProfile ln = new UserProfile();
                setVisible(false);
            }
              if (ae.getSource() == b5) {
                String text=l4.getText();
                l4.removeAll();
                   try
               {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        java.sql.Statement stm = con.createStatement();
                        String sql = "UPDATE `chatapp`.`user_profile` SET `text_description` = '"+text+"' WHERE (`User_id` = '"+LogIn.id+"');";
                        stm.executeUpdate(sql);
                    }
                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());

                }
               
            }
        }

    }
}
