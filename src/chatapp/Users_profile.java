package chatapp;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Users_profile extends JFrame {
  action3 a1 = new action3();
    JLabel img,img1,img2;
    ImageIcon image,image1,image2;
    U_profile user = new U_profile();
    Panel p1 = new Panel();
    JLabel l1 = new JLabel("name : ");
    JLabel l2 = new JLabel("description : ");
    JButton b3;

    Users_profile(int id) {
        this.setVisible(true);
        this.setTitle("UserProfile");
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 100);
        this.setResizable(false);
        this.setLayout(null);
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
        Sql_Profile(id);
        JLabel l3 = new JLabel(user.getF_name() + " " + user.getL_name());
        JLabel l4 = new JLabel(user.getText_description() + " ");
        image = new ImageIcon(getClass().getResource(user.getPhoto()));
        img = new JLabel(image);
        p1.add(img);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        image2 = new ImageIcon(getClass().getResource("ph-02-02-02.jpeg"));
        img2 = new JLabel(image2);
        b3 = new JButton();
        b3.setBounds(0, 0, 120, 90);
        b3.setLayout(null);
        p1.add(b3);
        img2.setBounds(0, 0, 120, 90);
        b3.add(img2);
        img.setBounds(260, 60, 200, 200);
        l1.setFont(new Font("", Font.BOLD, 22));
        l1.setBounds(120, 300, 100, 30);
        l2.setFont(new Font("", Font.BOLD, 22));
        l2.setBounds(120, 350, 150, 30);
        l3.setFont(new Font("", Font.BOLD, 22));
        l3.setBounds(240, 300, 200, 30);
        l4.setFont(new Font("", Font.BOLD, 22));
        l4.setBounds(290, 350, 150, 30);
        image1 = new ImageIcon(getClass().getResource("user-02-01-01.png"));
        img1 = new JLabel(image1);
        img1.setBounds(0, 0, 700, 700);
        p1.add(img1);
        b3.addActionListener(a1);
    }

    private void Sql_Profile(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                java.sql.Statement stm = con.createStatement();
                String sql = "SELECT * FROM chatapp.user_profile,chatapp.users where id="+id+" and User_id="+id+";";
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
              if (ae.getSource() == b3) {
                Frind_User x = new Frind_User();
                setVisible(false);
            }
        }

    }

}
