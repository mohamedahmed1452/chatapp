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
import java.util.ArrayList;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frind_User extends JFrame {

    JPanel p1, p2, p3, P4;
    JScrollPane js;
    JTextArea ta1;
    JButton b1, b2, b3, b4;
    JTextField tf1;
    actionChat a1 = new actionChat();
    Stack<Integer> NoId = new Stack<>();// select all id friends
    ArrayList<Integer> chat_user = new ArrayList<>();
    ArrayList<Integer> chat_friend = new ArrayList<>();
    ArrayList<Message_Status> chat_msgs = new ArrayList<>(); //select all msg between two user in chatroom
    ArrayList<JLabel> names = new ArrayList<>();
    ArrayList<JLabel> images = new ArrayList<>();
    ArrayList<JButton> users = new ArrayList<>();//contain all user in chat room
    ArrayList<JPanel> inboxs = new ArrayList<>();
    ArrayList<JTextArea> tas = new ArrayList<>();
    ArrayList<JTextField> tfs = new ArrayList<>();
    ArrayList<JButton> bts = new ArrayList<>();
    ArrayList<JScrollPane> scrs = new ArrayList<>();
    DefaultListModel<String> l1 = new DefaultListModel<>();
    DefaultListModel<JButton> l2 = new DefaultListModel<>();
    JList<String> ss = new JList<>(l1);
    JList<JButton> ss2 = new JList<>(l2);
    ArrayList<U_profile> UserInfo = new ArrayList<>();
    U_profile f1 = new U_profile();
    JLabel img1,img2,img3,img4;
    ImageIcon image,image1,image2,image3;
    JButton user;
    JLabel imagge;
    JLabel name;
    JMenu[] jMenu = new JMenu[100];
    JMenuBar[] jMenuBars = new JMenuBar[100];

    public Frind_User() {
        Sql_Friend();
        chat_user = Chat_user(LogIn.id);//for know chat_room_id
        this.setVisible(true);
        this.setTitle("Chating_Rooms");
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
            f1 = Sql_infoFriend(NoId.get(i));//set information name , picture and userId
            UserInfo.add(f1);// arraylist contain all information of users
            name = new JLabel(f1.getF_name() + " " + f1.getL_name());
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
        image3 = new ImageIcon(getClass().getResource("ph-02-02-02.jpeg"));
        img4 = new JLabel(image3);
        img4.setBounds(0, 0, 120, 90);
        b3 = new JButton();
        b3.setBounds(0, 0, 120, 90);
        b3.setLayout(null);
        b3.add(img4);
        b4 = new JButton("show profile");
        p1.setLayout(new GridLayout(users.size() + 2, 1, 0, 0));
        JScrollPane j = new JScrollPane(p1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(j);
        j.setBounds(600, 150, 400, 650);
        p2 = new JPanel();
        p2.setBounds(0, 0, 1000, 150);
        p2.setBackground(Color.GRAY);
        p2.setLayout(null);

        this.add(p2);
        JLabel chating = new JLabel("chating");
        chating.setFont(new Font("", Font.BOLD, 24));
        chating.setBounds(735, 100, 120, 30);
        chating.setForeground(Color.WHITE);
        b4.setBounds(780, 20, 200, 50);
        b4.setBackground(Color.CYAN);
        b4.setFont(new Font("", Font.BOLD, 22));
        p2.add(chating);
        p2.add(b3);
        p2.add(b4);
        p3 = new JPanel();
        p3.setBounds(0, 150, 600, 650);
        p3.setLayout(null);
        image1 = new ImageIcon(getClass().getResource("send.png"));
        img2=new JLabel(image1);
        b1 = new JButton();
        b1.setLayout(null);
        img2.setBounds(0, 0, 100, 50);
        b1.setBounds(500, 550, 100, 50);
        b1.add(img2);
        b1.setBackground(Color.white);
        p3.add(b1);
        tf1 = new JTextField();
        tf1.setBounds(0, 550, 500, 50);
        p3.add(tf1);
        JScrollPane jj = new JScrollPane(ss, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        p3.add(jj);
        b2 = new JButton("Undo msg");
        b2.setBackground(Color.CYAN);
        p2.add(b2);
        b2.setBounds(200, 100, 100, 30);
        jj.setBounds(0, 0, 600, 550);
        image2 = new ImageIcon(getClass().getResource("blacka.png"));
        img3 = new JLabel(image2);
        img3.setBounds(0, 0, 1000, 150);
        p2.add(img3);
        b1.addActionListener(a1);
        b2.addActionListener(a1);
        b3.addActionListener(a1);
        b4.addActionListener(a1);
        ss.setBackground(Color.LIGHT_GRAY);

    }
    //select all id friends

    private void Sql_Friend() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                java.sql.Statement stm = con.createStatement();
                String sql = "SELECT Friend_id FROM chatapp.users,chatapp.friend_id where Id=" + LogIn.id + " and Id=User_id order by timestamp DESC;";
                ResultSet rset = stm.executeQuery(sql);
                int count = 0;
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
    //take id of user and select all chat_rooms_id that is contact it in arraylist for know chat_id

    private ArrayList<Integer> Chat_user(int id) {
        ArrayList<Integer> chat_users = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                java.sql.Statement stm = con.createStatement();
                String sql = "SELECT * FROM chatapp.user_room where User_id=" + "'" + id + "';";
                ResultSet rset = stm.executeQuery(sql);
                while (rset.next()) {
                    chat_users.add(rset.getInt("Chat_room_id"));
                }
                con.close();
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return chat_users;
    }
    //select all msg previous between two users in chat_room that id is parameter

    private ArrayList<Message_Status> Chat_Room_msgs(int id) {
        ArrayList<Message_Status> chat_users = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                java.sql.Statement stm = con.createStatement();
                String sql = "SELECT * FROM chatapp.message where Chat_room_id=" + "'" + id + "';";
                ResultSet rset = stm.executeQuery(sql);
                while (rset.next()) {
                    Message_Status msg = new Message_Status();
                    msg.setChat_id(rset.getInt("Chat_room_id"));
                    msg.setPreviousDate(rset.getDate("date"));
                    msg.setFriedId(rset.getInt("msg_friend_id"));
                    msg.setMsgId(rset.getInt("msg_id"));
                    msg.setSeen(rset.getInt("seen"));
                    msg.setText(rset.getString("text"));
                    msg.setPreviousTime(rset.getTime("time"));
                    msg.setUserId(rset.getInt("msg_user_id"));
                    chat_users.add(msg);
                }
                con.close();
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

        return chat_users;
    }
//take id of user and select all info for him

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
            for (int i = 0; i < users.size(); i++) {//this function for load all msg in each chat room
                if (ae.getSource() == users.get(i)) {
                    add(p3);
                    l1.clear();
                    index = i;
                    
                    chat_friend = Chat_user(UserInfo.get(index).getId());//for know room_id
                    for (Integer x : chat_user) {
                        for (Integer y : chat_friend) {
                            if (x == y) {
                                chat_user_id = x;
                                break;
                            }
                        }
                    }
                    //load msg in indox room_id
                    chat_msgs = Chat_Room_msgs(chat_user_id);
                    String seen = "";
                    String des = "";
                    int count=0;
                
                    for (Message_Status x : chat_msgs) {
   
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                                Statement st = con.createStatement();
                                String sql = "INSERT INTO `chatapp`.`seen` (`msg_id`, `user_id`) VALUES ('" + x.getMsgId() + "', '" + LogIn.id + "');";
                                st.executeUpdate(sql);
                            }
                        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                            //JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        int counter = 0;
                        ss.setFont(new Font("", Font.BOLD, 19));
                        ss.setForeground(Color.BLACK);

                        String tp;
                        if (x.getPreviousTime().getHours() > 12) {
                            tp = "PM";
                        } else {
                            tp = "AM";
                        }
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                                java.sql.Statement stm = con.createStatement();
                                String sql = "SELECT count(*) FROM chatapp.seen where msg_id='" + x.getMsgId() + "';";
                                ResultSet rset = stm.executeQuery(sql);
                                if (rset.next()) {
                                    counter = rset.getInt("count(*)");
                                }
                              
                                con.close();
                            }
                        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());

                        }
                        if(counter==2&&x.getUserId()== LogIn.id){
                             seen = "√√";
                        }
                        else{
                            seen="";
                        }
                        if (x.getUserId() == LogIn.id) {

                            des = "";
                        } else {
                            des = "                                              ";
                        }

                        l1.addElement(des + x.getText() + ">>" + x.getPreviousTime().getHours() % 12 + ":" + x.getPreviousTime().getMinutes() + tp + " " + seen);
                        //l1.addElement(seen);

                    }
                    break;
                }
            }
            if (ae.getSource() == b1 && tf1.getText().length() > 0) {
                Message_Status msg = new Message_Status();
                msg.setText(tf1.getText());
                msg.setUserId(LogIn.id);
                msg.setFriedId(UserInfo.get(index).getId());
                msg.setChat_id(chat_user_id);
                ss.setFont(new Font("", Font.BOLD, 19));
                ss.setForeground(Color.BLACK);
                String tp;
                if (msg.getTime().getHour() > 12) {
                    tp = "PM";
                } else {
                    tp = "AM";
                }
                l1.addElement(msg.getText() + ">>" + msg.getTime().getHour() % 12 + ":" + msg.getTime().getMinute() + tp);
                tf1.setFont(new Font("", Font.BOLD, 22));
                tf1.setText("");
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        Statement st = con.createStatement();
                        String sql = "INSERT INTO `chatapp`.`message` ( `text`, `msg_user_id`, `msg_friend_id`, `time`, `date`, `seen`,`Chat_room_id`) VALUES "
                                + "(" + "'" + msg.getText() + "'," + "'" + msg.getUserId() + "'," + "'" + msg.getFriedId() + "'," + "'" + msg.getTime() + "'" + "," + "'" + msg.getDate() + "'" + "," + "'" + msg.getSeen() + "'" + "," + "'" + msg.getChat_id() + "'" + ");";
                        st.executeUpdate(sql);
                    }
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        java.sql.Statement stm = con.createStatement();
                        String sql = "SELECT * FROM chatapp.message order by msg_id DESC LIMIT 1;";
                        ResultSet rset = stm.executeQuery(sql);
                        if (rset.next()) {
                            msg.setMsgId(rset.getInt("msg_id"));
                        }
                        con.close();
                    }

                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

            if (ae.getSource() == b2) {
                int id = 0;
                try {
                    if (l1.isEmpty()) {
                        throw new SQLException("Dont delete this message ?");
                    }
                    l1.removeElement(l1.lastElement());
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        java.sql.Statement stm = con.createStatement();
                        String sql = "SELECT * FROM chatapp.message  order by msg_id DESC LIMIT 1;";
                        ResultSet rset = stm.executeQuery(sql);
                        if (rset.next()) {
                            id = rset.getInt("msg_id");
                        }
                        con.close();
                    }
                     Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        Statement st = con.createStatement();
                        String sql = "DELETE FROM `chatapp`.`seen` WHERE (`msg_id` = '"+id+"') and (`user_id` = '"+LogIn.id+"');";
                        st.executeUpdate(sql);
                        //JOptionPane.showMessageDialog(null, "save");
                    }
                      Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        Statement st = con.createStatement();
                        String sql = "DELETE FROM `chatapp`.`seen` WHERE (`msg_id` = '"+id+"') and (`user_id` = '"+UserInfo.get(index).getId()+"');";
                        st.executeUpdate(sql);
                       // JOptionPane.showMessageDialog(null, "save");
                    }
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        Statement st = con.createStatement();
                        String sql = "DELETE FROM `chatapp`.`message` WHERE (`msg_id` =" + "'" + id + "'" + ");";
                        st.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "save");
                    }
                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

            if (ae.getSource() == b3) {
                UserProfile x = new UserProfile();
                setVisible(false);
            }
            if (ae.getSource() == b4) { //handle visiability
                int ch = 0;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        java.sql.Statement stm = con.createStatement();
                        String sql = "SELECT * FROM chatapp.friend_id where User_id='" + UserInfo.get(index).getId() + "';";
                        ResultSet rset = stm.executeQuery(sql);
                        if (rset.next()) {
                            ch = rset.getInt("visiability");
                        }
                        con.close();
                    }
                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                if (ch == 1) {
                    Users_profile x = new Users_profile(UserInfo.get(index).getId());
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Block profile");
                }

            }

        }

    }
}
