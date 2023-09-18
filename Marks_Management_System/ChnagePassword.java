import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
import java.awt.Color;
import java.sql.*;

public class ChnagePassword implements ActionListener {
    JFrame frame = new JFrame();
    JLabel rollField = new JLabel("-");
    JPasswordField oldPasswordField = new JPasswordField();
    JPasswordField newPasswordField = new JPasswordField();
    JPasswordField newPassword1Field = new JPasswordField();
    JButton chnagepassButton = new JButton("Change Password");
    JLabel roll = new JLabel("Roll No:");
    JLabel oldPass = new JLabel("OLD PASSWORD:");
    JLabel passwordtext = new JLabel("NEW PASSWORD:");
    JLabel passwordtext1 = new JLabel("CONFIRM PASSWORD:");
    JLabel success = new JLabel();

    String DB_URL = "jdbc:mysql://localhost:3306/marksheet";
    String DB_USER = "root";
    String DB_PASSWORD = "divyam1234";

    public ChnagePassword() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select* from login");
            while (rs.next()) {
                rollField.setText(rs.getString(1));
            }
            

            statement.close();
            connection.close();

        } catch (Exception a) {
            a.printStackTrace();
        }
        roll.setBounds(10, 20, 80, 25);
        rollField.setBounds(150, 20, 165, 25);

        oldPass.setBounds(10, 50, 120, 25);
        oldPasswordField.setBounds(150, 50, 165, 25);

        passwordtext.setBounds(10, 80, 120, 25);
        newPasswordField.setBounds(150, 80, 165, 25);

        passwordtext1.setBounds(10, 110, 150, 25);
        newPassword1Field.setBounds(150, 110, 165, 25);

        chnagepassButton.setBounds(95, 140, 160, 25);
        chnagepassButton.addActionListener(this);

        success.setText("Password does not match!!!");
        success.setBounds(10, 170, 200, 25);
        success.setVisible(false);

        frame.setSize(350, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(128, 128, 128));
        frame.setResizable(false);
        frame.add(roll);
        frame.add(rollField);
        frame.add(oldPass);
        frame.add(oldPasswordField);
        frame.add(passwordtext);
        frame.add(passwordtext1);
        frame.add(newPassword1Field);
        frame.add(newPasswordField);
        frame.add(chnagepassButton);
        frame.add(success);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == chnagepassButton) {
            String rn = rollField.getText();
            String op = oldPasswordField.getText();
            String np = newPasswordField.getText();
            String cp = newPassword1Field.getText();

            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement
                        .executeQuery("select * from students where (password='" + op + "' and roll_no='" + rn + "')");

                if (rs.next()) {
                    if (!cp.equals(np)) {
                        success.setText("Password does not match!!!");
                        success.setVisible(true);
                    } else {
                        statement.executeUpdate(
                                "update students set password ='" + np + "' where roll_no='" + rn + "'");
                        char r = rn.charAt(0);
                        String b = rn.substring(1);
                        if (r == 'M') {
                            statement.executeUpdate(
                                    "update mathstudents set pass ='" + np + "' where roll_no='" + b + "'");

                        } else if (r == 'B') {
                            statement.executeUpdate(
                                    "update biostudents set pass ='" + np + "' where roll_no='" + b + "'");
                        } else if (r == 'C') {
                            statement.executeUpdate(
                                    "update comstudents set pass ='" + np + "' where roll_no='" + b + "'");

                        } else if (r == 'A') {
                            statement.executeUpdate(
                                    "update hmnstudents set pass ='" + np + "' where roll_no='" + b + "'");

                        }
                        success.setText("Password changed.");
                        success.setVisible(true);
                        frame.setVisible(false);
                        Action a = new Action();

                    }
                } else {
                    success.setText("Old password is wrong.");
                    success.setVisible(true);
                }

                statement.close();
                connection.close();

            } catch (Exception a) {
                a.printStackTrace();
            }
        }

    }

}
