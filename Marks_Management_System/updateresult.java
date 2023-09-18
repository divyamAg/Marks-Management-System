import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class updateresult extends JFrame implements ActionListener {
    private JTextField rollNumberField;
    private JLabel nameField;

    JLabel cmpsub1Label = new JLabel("COMPULSORY SUBJECT:");
    JTextField cmpsub1Labela = new JTextField();
    JLabel cmpsub2Label = new JLabel("COMPULSORY SUBJECT:");
    JTextField cmpsub2Labela = new JTextField();
    JLabel cmpsub3Label = new JLabel("COMPULSORY SUBJECT:");
    JTextField cmpsub3Labela = new JTextField();
    JLabel cmpsub4Label = new JLabel("COMPULSORY SUBJECT:");
    JTextField cmpsub4Labela = new JTextField();
    JLabel optsub1Label = new JLabel("OPTIONAL SUBJECT:");
    JTextField optsub1Labela = new JTextField();
    // JRadioButton pcm = new JRadioButton("PCM");
    // JRadioButton pcb = new JRadioButton("PCB");
    // JRadioButton comm = new JRadioButton("COMMERCE");
    // JRadioButton arts = new JRadioButton("ARTS");
    JLabel st = new JLabel("-");

    JLabel sub1Label1 = new JLabel("-");
    JLabel sub2Label1 = new JLabel("-");
    JLabel sub3Label1 = new JLabel("-");
    JLabel sub4Label1 = new JLabel("-");
    JLabel sub5Label1 = new JLabel("-");

    String DB_URL = "jdbc:mysql://localhost:3306/marksheet";
    String DB_USER = "root";
    String DB_PASSWORD = "divyam1234";
    JLabel success = new JLabel();

    static String stream = "-";
    JFrame frame = new JFrame();
    public updateresult() {
        
        frame.setTitle("Update Result");
        frame.setSize(600, 550);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 128, 128));
        frame.setResizable(false);

        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberField = new JTextField();

        JLabel nameLabel = new JLabel("Student Name:");
        nameField = new JLabel();
        nameField.setText("-");

        JButton addButton = new JButton("Update");
        addButton.addActionListener(this);

        JButton showButton = new JButton("Show");
        showButton.addActionListener(this);

        JLabel streamLabel = new JLabel("STREAM:");

        success.setText("result added in database!!");
        success.setBounds(20, 460, 450, 25);
        success.setVisible(false);

        Container inputPanel = frame.getContentPane();
        inputPanel.setLayout(null);

        rollNumberLabel.setBounds(20, 20, 200, 40);
        rollNumberField.setBounds(180, 20, 200, 40);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);

        showButton.setBounds(400, 20, 150, 40);
        inputPanel.add(showButton);

        nameLabel.setBounds(20, 70, 200, 40);
        nameField.setBounds(180, 70, 200, 40);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        streamLabel.setBounds(20, 120, 100, 40);
        st.setBounds(170, 120, 100, 40);
        inputPanel.add(streamLabel);
        inputPanel.add(st);

        cmpsub1Label.setBounds(20, 170, 150, 40);
        sub1Label1.setBounds(190, 170, 150, 40);
        cmpsub1Labela.setBounds(350, 170, 150, 40);
        inputPanel.add(cmpsub1Label);
        inputPanel.add(sub1Label1);
        inputPanel.add(cmpsub1Labela);

        cmpsub2Label.setBounds(20, 220, 150, 40);
        sub2Label1.setBounds(190, 220, 150, 40);
        cmpsub2Labela.setBounds(350, 220, 150, 40);
        inputPanel.add(cmpsub2Label);
        inputPanel.add(sub2Label1);
        inputPanel.add(cmpsub2Labela);

        cmpsub3Label.setBounds(20, 270, 150, 40);
        sub3Label1.setBounds(190, 270, 150, 40);
        cmpsub3Labela.setBounds(350, 270, 150, 40);
        inputPanel.add(cmpsub3Label);
        inputPanel.add(sub3Label1);
        inputPanel.add(cmpsub3Labela);

        cmpsub4Label.setBounds(20, 320, 150, 40);
        sub4Label1.setBounds(190, 320, 150, 40);
        cmpsub4Labela.setBounds(350, 320, 150, 40);
        inputPanel.add(cmpsub4Label);
        inputPanel.add(sub4Label1);
        inputPanel.add(cmpsub4Labela);

        optsub1Label.setBounds(20, 370, 150, 40);
        sub5Label1.setBounds(190, 370, 150, 40);
        optsub1Labela.setBounds(350, 370, 150, 40);
        inputPanel.add(optsub1Label);
        inputPanel.add(sub5Label1);
        inputPanel.add(optsub1Labela);

        addButton.setBounds(200, 420, 200, 40);
        inputPanel.add(addButton);
        inputPanel.add(success);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String rollNumber = rollNumberField.getText();
        if (e.getActionCommand().equals("Update")) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                Statement statement1 = connection.createStatement();

                String sub1 = cmpsub1Labela.getText();
                String sub2 = cmpsub2Labela.getText();
                String sub3 = cmpsub3Labela.getText();
                String sub4 = cmpsub4Labela.getText();
                String sub5 = optsub1Labela.getText();

                float p = (Integer.parseInt(sub1) + Integer.parseInt(sub2) + Integer.parseInt(sub3)
                        + Integer.parseInt(sub4) + Integer.parseInt(sub5)) / 5;
                String grade;
                if (p >= 80) {
                    grade = "Honours";
                } else if (p >= 60) {
                    grade = "1st Div";
                } else if (p >= 50) {
                    grade = "2nd Div";
                } else if (p >= 40) {
                    grade = "3rd Div";
                } else {
                    grade = "Fail";
                }

                ResultSet rs1 = statement1.executeQuery("select * from result where Roll_No='" + rollNumber + "'");
                Boolean ans = true;
                if (rs1.next()) {
                    ans = false;
                }
                if (ans) {
                    success.setText("Result does not exist...");
                    success.setVisible(true);
                } else {

                    statement.executeUpdate("update result set sub1=" + sub1 + ",sub2=" + sub2 + ",sub3=" + sub3
                            + ",sub4=" + sub4 + ",sub5=" + sub5 + ",percentage=" + p + ",grade='" + grade
                            + "' where roll_no='" + rollNumber + "'");
                    success.setText("Result has been updated..");
                    success.setVisible(true);
                }
            } catch (SQLException a) {
                a.printStackTrace();
            }

            frame.setVisible(false);
            Action a = new Action();

        } else if (e.getActionCommand().equals("Show")) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                Statement statement1 = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from result where roll_no='" + rollNumber + "'");
                ResultSet rs1;
                if (rs.next()) {
                    nameField.setText(rs.getString(2).toUpperCase());
                    st.setText(rs.getString(3));
                    sub1Label1.setText(rs.getString(4));
                    sub2Label1.setText(rs.getString(5));
                    sub3Label1.setText(rs.getString(6));
                    sub4Label1.setText(rs.getString(7));
                    sub5Label1.setText(rs.getString(8));

                } else {
                    nameField.setText("-");
                    st.setText("-");
                    sub1Label1.setText("-");
                    sub2Label1.setText("-");
                    sub3Label1.setText("-");
                    sub4Label1.setText("-");
                    sub5Label1.setText("-");

                    success.setText("Result does not exist");
                    success.setVisible(true);
                }
                char roll = rollNumber.charAt(0);
                String roll2 = rollNumber.substring(1);
                if (roll == 'M') {
                    rs1 = statement1.executeQuery("select * from mathstudents where roll_no='" + roll2 + "'");
                    rs1.next();
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("Physics:");
                    cmpsub3Label.setText("Chemistry:");
                    cmpsub4Label.setText("Maths:");
                    optsub1Label.setText(rs1.getString(6) + ":");
                } else if (roll == 'B') {
                    rs1 = statement1.executeQuery("select * from biostudents where roll_no='" + roll2 + "'");
                    rs1.next();
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("Physics:");
                    cmpsub3Label.setText("Chemistry:");
                    cmpsub4Label.setText("Biology:");
                    optsub1Label.setText(rs1.getString(6) + ":");
                } else if (roll == 'C') {
                    rs1 = statement1.executeQuery("select * from comstudents where roll_no='" + roll2 + "'");
                    rs1.next();
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("Accountancy:");
                    cmpsub3Label.setText("Economics:");
                    cmpsub4Label.setText("Buissness Studies:");
                    optsub1Label.setText(rs1.getString(6) + ":");
                } else if (roll == 'A') {
                    rs1 = statement1.executeQuery("select * from hmnstudents where roll_no='" + roll2 + "'");
                    rs1.next();
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("History:");
                    cmpsub3Label.setText("Geography:");
                    cmpsub4Label.setText("Political Science:");
                    optsub1Label.setText(rs1.getString(6) + ":");
                }
                statement.close();
                connection.close();

            } catch (Exception a) {
                a.printStackTrace();
            }
        }
    }
}
