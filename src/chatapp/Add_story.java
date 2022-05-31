package chatapp;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Add_story extends JFrame {

    JPanel p1;
    JTextArea ta;
    JButton b1, b2;
    add_story a1 = new add_story();

    public Add_story() {
        this.setVisible(true);
        this.setTitle("story");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(600, 350);
        this.setResizable(false);
        this.setLayout(null);
        this.revalidate();
        p1 = new JPanel();
        p1.setBounds(0, 0, 400, 400);
        p1.setLayout(null);
        this.add(p1);
        ta = new JTextArea();
        ta.setBounds(0, 0, 400, 280);
        p1.add(ta);
        b1 = new JButton("SAVE");
        b1.setBounds(250, 300, 120, 50);
        p1.add(b1);
        b2 = new JButton("EXIT");
        b2.setBounds(20, 300, 120, 50);
        p1.add(b2);
        b1.addActionListener(a1);
        b2.addActionListener(a1);
    }

    private class add_story implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == b2) {
                setVisible(false);
            }
            if (ae.getSource() == b1) {
                String text = ta.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?useSSL=false", "root", "password123456789")) {
                        Statement st = con.createStatement();
                        String sql = "INSERT INTO `chatapp`.`story` (`text`, `userId`) VALUES ('" + text + "', '" + LogIn.id + "');";
                        st.executeUpdate(sql);
                    }
                    JOptionPane.showMessageDialog(null, "done");
                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());

                }
            }
        }

    }

}
