import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class deleteResult implements ActionListener{

    JTextField rollNoField = new JTextField();
    JButton deleteButton = new JButton("DELETE RESULT");
    String DB_URL = "jdbc:mysql://localhost:3306/marksheet";
    String DB_USER = "root";
    String DB_PASSWORD = "divyam1234";
    JLabel success = new JLabel();
    JFrame frame = new JFrame();
    public deleteResult(){
       

        frame.setSize(350, 270);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 202, 216));

        JLabel rollLabel = new JLabel();
        rollLabel.setText("Roll No.");
        rollLabel.setBounds(20,20, 80, 25);
         rollNoField.setBounds(100, 20, 165, 25);

        deleteButton.setBounds(75, 60, 200,40);
        success.setBounds(20, 110, 200, 25);
        frame.add(deleteButton);
        frame.add(rollLabel);
        frame.add(rollNoField);

        deleteButton.addActionListener(this);

        success.setText("Result deleted!");
        
        success.setVisible(false);
        frame.add(success);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==deleteButton){
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                Statement statement1 = connection.createStatement();
                Statement statement2 = connection.createStatement();
                String roll = rollNoField.getText();

                ResultSet rs = statement2.executeQuery("select * from result where roll_no='" + roll + "'");
                

                String query = "DELETE from result where roll_no='"+roll+"';";

                if(rs.next()){
                statement.executeUpdate(query);
                success.setText("Result deleted!");
                success.setVisible(true);
                }
                else{
                    success.setText("Result does not exist");
                success.setVisible(true);
                }
            } catch (Exception a) {
                a.printStackTrace();
            }

            frame.setVisible(false);
            Action a = new Action();
        }
        
    }
    
}
