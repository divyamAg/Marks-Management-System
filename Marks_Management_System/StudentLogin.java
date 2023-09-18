import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
import java.sql.*;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.text.ParseException;
import java.util.Date;

public class StudentLogin extends JFrame implements ActionListener {
    JFrame frame = new JFrame();

    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton login = new JButton();
    JLabel user = new JLabel();
    JLabel passwordtext = new JLabel();
    JLabel success = new JLabel();

    String DB_URL = "jdbc:mysql://localhost:3306/marksheet";
    String DB_USER = "root";
    String DB_PASSWORD = "divyam1234";

    public StudentLogin() {
        user.setBounds(10, 20, 80, 25);
        user.setText("Roll No.");
        usernameField.setBounds(100, 20, 165, 25);

        passwordtext.setBounds(10, 50, 80, 25);
        passwordtext.setText("Password");
        passwordField.setBounds(100, 50, 165, 25);

        login.setBounds(10, 80, 80, 25);
        login.setText("Login");
        login.addActionListener(this);

        success.setText("Wrong ID or Password!!!");
        success.setBounds(10, 115, 200, 25);
        success.setVisible(false);

        frame.setSize(350, 200);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(128, 128, 128));
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(login);
        frame.add(user);
        frame.add(passwordtext);
        frame.add(success);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (e.getSource() == login) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement1 = connection.createStatement();

                ResultSet rs = statement1.executeQuery("select * from students where roll_no='" + username+"'");

                if (rs.next()) {

                    if (password.equals(rs.getString(4))) {
                        StudentOptions opt = new StudentOptions();
                        String date = LocalDate.now().toString();
                        LocalDateTime dateTime = LocalDateTime.now();
                        String currentTime = dateTime.toLocalTime().toString();
                        statement1.executeUpdate("insert into login values('"+username+"','"+date+"','"+currentTime+"')");

                        frame.setVisible(false);
                    } else {
                        success.setText("Wrong ID/Password.");
                        success.setVisible(true);
                    }
                } else {
                    success.setText("Student is not Registered");
                    success.setVisible(true);
                }
            } catch (Exception a) {
                a.printStackTrace();
            }
        }
    }
}