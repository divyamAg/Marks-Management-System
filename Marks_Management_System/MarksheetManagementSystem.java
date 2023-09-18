import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MarksheetManagementSystem extends JFrame implements ActionListener {
    private JTextField rollNumberField;
    private JTextField nameField;

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
    JRadioButton pcm = new JRadioButton("PCM");
    JRadioButton pcb = new JRadioButton("PCB");
    JRadioButton comm = new JRadioButton("COMMERCE");
    JRadioButton arts = new JRadioButton("ARTS");

    String DB_URL = "jdbc:mysql://localhost:3306/marksheet";
    String DB_USER = "root";
    String DB_PASSWORD = "divyam1234";
    JLabel success = new JLabel();

    static String stream = "-";
    JFrame frame = new JFrame();
    public MarksheetManagementSystem() {
        
        frame.setTitle("Marksheet Management System");
        frame.setSize(600, 550);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 255, 0));
        frame.setResizable(false);

        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberField = new JTextField();

        JLabel nameLabel = new JLabel("Student Name:");
        nameField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);

        JLabel streamLabel = new JLabel("STREAM:");

        ButtonGroup grpstream = new ButtonGroup();
        grpstream.add(pcb);
        grpstream.add(pcm);
        grpstream.add(comm);
        grpstream.add(arts);

        success.setText("result added in database!!");
        success.setBounds(20, 460, 450, 25);
        success.setVisible(false);

        Container inputPanel = frame.getContentPane();
        inputPanel.setLayout(null);

        rollNumberLabel.setBounds(20, 20, 200, 40);
        rollNumberField.setBounds(180, 20, 200, 40);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);

        nameLabel.setBounds(20, 70, 200, 40);
        nameField.setBounds(180, 70, 200, 40);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        streamLabel.setBounds(20, 120, 100, 40);
        pcm.setBounds(170, 120, 100, 40);
        pcb.setBounds(270, 120, 100, 40);
        comm.setBounds(370, 120, 120, 40);
        arts.setBounds(500, 120, 100, 40);
        pcm.addActionListener(this);
        pcb.addActionListener(this);
        comm.addActionListener(this);
        arts.addActionListener(this);
        inputPanel.add(streamLabel);
        inputPanel.add(pcm);
        inputPanel.add(pcb);
        inputPanel.add(comm);
        inputPanel.add(arts);

        cmpsub1Label.setBounds(20, 170, 150, 40);
        cmpsub1Labela.setBounds(190, 170, 150, 40);
        inputPanel.add(cmpsub1Label);
        inputPanel.add(cmpsub1Labela);

        cmpsub2Label.setBounds(20, 220, 150, 40);
        cmpsub2Labela.setBounds(190, 220, 150, 40);
        inputPanel.add(cmpsub2Label);
        inputPanel.add(cmpsub2Labela);

        cmpsub3Label.setBounds(20, 270, 150, 40);
        cmpsub3Labela.setBounds(190, 270, 150, 40);
        inputPanel.add(cmpsub3Label);
        inputPanel.add(cmpsub3Labela);

        cmpsub4Label.setBounds(20, 320, 150, 40);
        cmpsub4Labela.setBounds(190, 320, 150, 40);
        inputPanel.add(cmpsub4Label);
        inputPanel.add(cmpsub4Labela);

        optsub1Label.setBounds(20, 370, 150, 40);
        optsub1Labela.setBounds(190, 370, 150, 40);
        inputPanel.add(optsub1Label);
        inputPanel.add(optsub1Labela);

        addButton.setBounds(200, 420, 200, 40);
        ;
        inputPanel.add(addButton);
        inputPanel.add(success);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String rollNumber = rollNumberField.getText();
        if (e.getActionCommand().equals("Add")) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                Statement statement1 = connection.createStatement();

                String insertQuery = "INSERT INTO result (roll_no, name, stream, sub1, sub2 ,sub3, sub4, sub5,percentage, grade) "
                        +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                String name = nameField.getText();
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
                Boolean ans = false;
                if (rs1.next()) {
                    ans = true;
                }
                if (ans) {
                    success.setText("result already exists...");
                    success.setVisible(true);
                } else {

                    ResultSet rs = statement.executeQuery("select * from students where roll_no='" + rollNumber + "'");

                    if (rs.next()) {
                        String temp = rs.getString(2);
                        if (name.equals(temp)) {

                            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                            preparedStatement.setString(1, rollNumber);
                            preparedStatement.setString(2, name);
                            preparedStatement.setString(3, stream);
                            preparedStatement.setInt(4, Integer.parseInt(sub1));
                            preparedStatement.setInt(5, Integer.parseInt(sub2));
                            preparedStatement.setInt(6, Integer.parseInt(sub3));
                            preparedStatement.setInt(7, Integer.parseInt(sub4));
                            preparedStatement.setInt(8, Integer.parseInt(sub5));
                            preparedStatement.setFloat(9, p);
                            preparedStatement.setString(10, grade);

                            preparedStatement.executeUpdate();

                            preparedStatement.close();
                            connection.close();

                            System.out.println("Data stored in the database successfully!");
                            success.setText("result added in database!!");
                            success.setVisible(true);
                        } else {
                            success.setText("Name and roll number does not match.");
                            success.setVisible(true);
                        }

                    } else {

                        success.setText("Please add the student first.");
                        success.setVisible(true);
                    }
                }
            } catch (SQLException a) {
                a.printStackTrace();
            }

            frame.setVisible(false);
            Action a = new Action();

        } else if (e.getSource() == pcm) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String roll = rollNumber.substring(1);
                ResultSet rs = statement.executeQuery("select * from mathstudents where roll_no=" + roll + "");
                if (rs.next()) {
                    stream = "PCM";
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("Physics:");
                    cmpsub3Label.setText("Chemistry:");
                    cmpsub4Label.setText("Maths:");
                    optsub1Label.setText(rs.getString(6) + ":");
                } else {
                    stream = "PCM";
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("Physics:");
                    cmpsub3Label.setText("Chemistry:");
                    cmpsub4Label.setText("Maths:");
                    optsub1Label.setText("Optional:");
                }
            } catch (Exception a) {
                a.printStackTrace();
            }
        } else if (e.getSource() == pcb) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String roll = rollNumber.substring(1);
                ResultSet rs = statement.executeQuery("select * from biostudents where roll_no=" + roll + "");
                if (rs.next()) {
                    stream = "PCB";
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("Physics:");
                    cmpsub3Label.setText("Chemistry:");
                    cmpsub4Label.setText("Biology:");
                    optsub1Label.setText(rs.getString(6) + ":");
                } else {
                    stream = "PCB";
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("Physics:");
                    cmpsub3Label.setText("Chemistry:");
                    cmpsub4Label.setText("Biology:");
                    optsub1Label.setText("Optional:");
                }
            } catch (Exception a) {
                a.printStackTrace();
            }

        } else if (e.getSource() == comm) {

            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String roll = rollNumber.substring(1);
                ResultSet rs = statement.executeQuery("select * from comstudents where roll_no=" + roll + "");
                if (rs.next()) {
                    stream = "COMM";
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("Accountancy");
                    cmpsub3Label.setText("Economics");
                    cmpsub4Label.setText("Buissness Studies");
                    optsub1Label.setText(rs.getString(6) + ":");
                } else {
                    stream = "COMM";
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("Accountancy");
                    cmpsub3Label.setText("Economics");
                    cmpsub4Label.setText("Buissness Studies");
                    optsub1Label.setText("Optional:");
                }
            } catch (Exception a) {
                a.printStackTrace();
            }
        } else if (e.getSource() == arts) {

            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String roll = rollNumber.substring(1);
                ResultSet rs = statement.executeQuery("select * from hmnstudents where roll_no=" + roll + "");
                if (rs.next()) {
                    stream = "ARTS";
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("History");
                    cmpsub3Label.setText("Geography");
                    cmpsub4Label.setText("Political Science");
                    optsub1Label.setText(rs.getString(6) + ":");
                } else {
                    stream = "ARTS";
                    cmpsub1Label.setText("English:");
                    cmpsub2Label.setText("History");
                    cmpsub3Label.setText("Geography");
                    cmpsub4Label.setText("Political Science");
                    optsub1Label.setText("Optional:");
                }
            } catch (Exception a) {
                a.printStackTrace();
            }
        }
    }
}
