package chatapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.*;

public class Story_GUI extends JFrame {

    JPanel p1, p2, p3, P4;
    JScrollPane js;
    JTextArea ta1;
    JButton b1, b3, b4;
    JTextField tf1;
    actionChat a1 = new actionChat();
    Stack<Integer> NoId = new Stack<>();
    ArrayList<story> stories = new ArrayList<>();//story one user
    ArrayList<JLabel> names = new ArrayList<>(); //gui
    ArrayList<JLabel> images = new ArrayList<>(); //gui
    ArrayList<JButton> users = new ArrayList<>(); //gui
    ArrayList<JPanel> inboxs = new ArrayList<>();
    ArrayList<JButton> bts = new ArrayList<>();//gui
    ArrayList<JScrollPane> scrs = new ArrayList<>();//gui
    DefaultListModel l1 = new DefaultListModel<>();
    JList ss = new JList();
    ArrayList<U_profile> UserInfo = new ArrayList<>();
    U_profile f1 = new U_profile();
    JLabel img1,img2,img3;
    ImageIcon image,image1,image2;
    JButton user;
    JLabel imagge;
    JLabel name;
    JMenu[] jMenu = new JMenu[100];
    JMenuBar[] jMenuBars = new JMenuBar[100];

    public Story_GUI() {
        Sql_Friend();
        remove_story();
        this.setVisible(true);
        this.setTitle("story");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 100);
        this.setResizable(false);
        this.setLayout(null);
        this.revalidate();
        p1 = new JPanel();
        try {
            File myfile = new File("app.gif");
            Image img = ImageIO.read(myfile);
            this.setIconImage(img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < NoId.size(); i++) {
            f1 = Sql_infoFriend(NoId.get(i));
            UserInfo.add(f1);
            if (i == 0) {
                name = new JLabel("My Stories");
            } else {
                name = new JLabel(f1.getF_name() + " " + f1.getL_name());
            }
            name.setFont(new Font("", Font.BOLD, 22));
            names.add(name);
            image = new ImageIcon(getClass().getResource(f1.getPhoto()));
            imagge = new JLabel(image);
            images.add(imagge);
            user = new JButton();
            users.add(user);
            users.get(i).setLayout(new GridLayout(1, 2));
            users.get(i).add(names.get(i));
            users.get(i).add(images.get(i));
            p1.add(users.get(i));
            users.get(i).addActionListener(a1);
        }
        image2 = new ImageIcon(getClass().getResource("ph-02-02-02.jpeg"));
        img3 = new JLabel(image2);
        img3.setBounds(0, 0, 120, 90);
        b3 = new JButton();
        b3.setBounds(0, 0, 120, 90);
        b3.setLayout(null);
        b3.add(img3);
        b4 = new JButton("add story");
        p1.setLayout(new GridLayout(users.size() + 2, 1, 0, 0));
        JScrollPane j = new JScrollPane(p1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(j);
        j.setBounds(600, 150, 400, 650);
        p2 = new JPanel();
        p2.setBounds(0, 0, 1000, 150);
        p2.setBackground(Color.GRAY);
        p2.setLayout(null);

        this.add(p2);
        JLabel story = new JLabel("stories");
        story.setFont(new Font("", Font.BOLD, 24));
        story.setForeground(Color.WHITE);
        story.setBounds(730, 100, 120, 30);
        b4.setBounds(780, 20, 200, 50);
        b4.setBackground(Color.CYAN);
        b4.setForeground(Color.black);
        b4.setFont(new Font("", Font.BOLD, 22));
        p2.add(story);
        p2.add(b3);
        p2.add(b4);
        p3 = new JPanel();
        p3.setBounds(0, 150, 600, 650);
        p3.setLayout(null);
        JScrollPane jj = new JScrollPane(ss, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        p3.add(jj);
        jj.setBounds(0, 0, 600, 650);
        image1 = new ImageIcon(getClass().getResource("blacka.png"));
        img2 = new JLabel(image1);
        img2.setBounds(0, 0, 1000, 150);
        p2.add(img2);
        b3.addActionListener(a1);
        b4.addActionListener(a1);
        ss.setBackground(Color.LIGHT_GRAY);
    }
    //select all stories previous for user selected
    private ArrayList<story> storiesList(int id) {
        ArrayList<story> stories1 = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                java.sql.Statement stm = con.createStatement();
                String sql = "SELECT * FROM chatapp.story where userId=" + "'" + id + "';";
                ResultSet rset = stm.executeQuery(sql);
                while (rset.next()) {
                    story storys = new story();
                    storys.setId(rset.getInt("id"));
                    storys.setText(rset.getString("text"));
                    storys.setUserId(rset.getInt("userId"));
                    stories1.add(storys);
                }
                con.close();
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

        return stories1;
    }

    private void Sql_Friend() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                java.sql.Statement stm = con.createStatement();
                String sql = "SELECT Friend_id FROM chatapp.users,chatapp.friend_id where Id=" + LogIn.id + " and Id=User_id order by timestamp DESC;";
                ResultSet rset = stm.executeQuery(sql);
                int count = 0;
                NoId.add(LogIn.id);
                while (rset.next()) {
                    NoId.add(rset.getInt("Friend_id"));
                    count++;
                }
                con.close();

            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private U_profile Sql_infoFriend(int idInfo) {
        U_profile f1 = new U_profile();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                java.sql.Statement stm = con.createStatement();
                String sql = "SELECT * FROM chatapp.users,chatapp.user_profile where id=" + idInfo + " and id=User_id;";
                ResultSet rset = stm.executeQuery(sql);
                if (rset.next()) {
                    f1.setF_name(rset.getString("First_Name"));
                    f1.setL_name(rset.getString("Last_Name"));
                    f1.setPhoto(rset.getString("photo"));
                    f1.setId(rset.getInt("User_id"));
                }
                con.close();
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return f1;
    }
    int index = 0;
    int chat_user_id = 0;

    private class actionChat implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            for (int i = 0; i < users.size(); i++) {
                if (ae.getSource() == users.get(i)) {
                    int ch = 0;
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                            java.sql.Statement stm = con.createStatement();
                            String sql = "SELECT * FROM chatapp.friend_id where User_id='" + UserInfo.get(i).getId() + "';";
                            ResultSet rset = stm.executeQuery(sql);
                            if (rset.next()) {
                                ch = rset.getInt("visiability");
                            }
                            con.close();
                        }
                    } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    if (ch == 1 || UserInfo.get(i).getId() == LogIn.id) {
                        add(p3);
                        l1.clear();
                        index = i;
                        stories = storiesList(UserInfo.get(index).getId());////load stories in indox room_id
                        for (story x : stories) {
                            ss.setFont(new Font("", Font.BOLD, 19));
                            ss.setForeground(Color.black);
                            String[] sentences = x.getText().split("\\n");
                            for (String str : sentences) {
                                l1.addElement(str);
                            }
                            l1.addElement("-----------------------------------------------------------------------------------------");
                            l1.addElement("-----------------------------------------------------------------------------------------");
                            ss.setModel(l1);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No contact");

                    }
                    break;
                }
            }

            if (ae.getSource() == b4) {
                Add_story s = new Add_story();
            }
            if (ae.getSource() == b3) {
                UserProfile profile = new UserProfile();
                setVisible(false);
            }
        }

    }

    private void remove_story() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                Statement st = con.createStatement();
                String sql = "delete from chatapp.story where date_time < now() - interval 1 day;";
                st.executeUpdate(sql);
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

}
