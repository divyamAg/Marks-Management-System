import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ShowResult extends JFrame implements ActionListener {
    private JLabel rollNumberField;
    private JLabel nameField;

    JLabel cmpsub1Label = new JLabel("COMPULSORY SUBJECT:");
    // JTextField cmpsub1Labela = new JTextField();
    JLabel cmpsub2Label = new JLabel("COMPULSORY SUBJECT:");
    // JTextField cmpsub2Labela = new JTextField();
    JLabel cmpsub3Label = new JLabel("COMPULSORY SUBJECT:");
    // JTextField cmpsub3Labela = new JTextField();
    JLabel cmpsub4Label = new JLabel("COMPULSORY SUBJECT:");
    // JTextField cmpsub4Labela = new JTextField();
    JLabel optsub1Label = new JLabel("OPTIONAL SUBJECT:");
    JLabel perLabel = new JLabel("Percentage:");
    JLabel gradeLabel = new JLabel("Grade:");
    JLabel perLabela = new JLabel("-");
    JLabel gradeLabela = new JLabel("-");
    // JTextField optsub1Labela = new JTextField();
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

    public ShowResult() {

        frame.setTitle("Update Result");
        frame.setSize(650, 650);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 128, 128));
        frame.setResizable(false);

        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberField = new JLabel("-");

        JLabel nameLabel = new JLabel("Student Name:");
        nameField = new JLabel();
        nameField.setText("-");

        JButton addButton = new JButton("OK");
        addButton.addActionListener(this);

        // JButton showButton = new JButton("Show");
        // showButton.addActionListener(this);

        JLabel streamLabel = new JLabel("STREAM:");

        success.setText("result added in database!!");
        success.setBounds(20, 560, 450, 25);
        success.setVisible(false);

        Container inputPanel = frame.getContentPane();
        inputPanel.setLayout(null);

        rollNumberLabel.setBounds(20, 20, 200, 40);
        rollNumberField.setBounds(180, 20, 200, 40);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);

        // showButton.setBounds(400, 20, 150, 40);
        // inputPanel.add(showButton);

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
        // cmpsub1Labela.setBounds(350, 170, 150, 40);
        inputPanel.add(cmpsub1Label);
        inputPanel.add(sub1Label1);
        // inputPanel.add(cmpsub1Labela);

        cmpsub2Label.setBounds(20, 220, 150, 40);
        sub2Label1.setBounds(190, 220, 150, 40);
        // cmpsub2Labela.setBounds(350, 220, 150, 40);
        inputPanel.add(cmpsub2Label);
        inputPanel.add(sub2Label1);
        // inputPanel.add(cmpsub2Labela);

        cmpsub3Label.setBounds(20, 270, 150, 40);
        sub3Label1.setBounds(190, 270, 150, 40);
        // cmpsub3Labela.setBounds(350, 270, 150, 40);
        inputPanel.add(cmpsub3Label);
        inputPanel.add(sub3Label1);
        // inputPanel.add(cmpsub3Labela);

        cmpsub4Label.setBounds(20, 320, 150, 40);
        sub4Label1.setBounds(190, 320, 150, 40);
        // cmpsub4Labela.setBounds(350, 320, 150, 40);
        inputPanel.add(cmpsub4Label);
        inputPanel.add(sub4Label1);
        // inputPanel.add(cmpsub4Labela);

        optsub1Label.setBounds(20, 370, 150, 40);
        sub5Label1.setBounds(190, 370, 150, 40);
        // optsub1Labela.setBounds(350, 370, 150, 40);
        inputPanel.add(optsub1Label);
        inputPanel.add(sub5Label1);
        // inputPanel.add(optsub1Labela);

        perLabel.setBounds(20, 420, 150, 40);
        perLabela.setBounds(190, 420, 150, 40);
        inputPanel.add(perLabel);
        inputPanel.add(perLabela);

        gradeLabel.setBounds(20, 470, 150, 40);
        gradeLabela.setBounds(190, 470, 150, 40);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeLabela);

        addButton.setBounds(200, 520, 200, 40);
        inputPanel.add(addButton);
        inputPanel.add(success);

        frame.setVisible(true);
        String rn;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement1 = connection.createStatement();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select* from login");
            String rn1 = "";
            char r = 'l';
            String b = "";
            while (rs.next()) {
                rn1 = rs.getString(1);
                r = rn1.charAt(0);
                b = rn1.substring(1);
            }
            ResultSet rs1 = statement1.executeQuery("select* from mathstudents where roll_no=" + b);
            if (r == 'M') {
                rs1 = statement1.executeQuery("select* from mathstudents where roll_no=" + b);
                st.setText("PCM");
            } else if (r == 'B') {
                rs1 = statement1.executeQuery("select* from biostudents where roll_no=" + b);
                st.setText("PCB");
            } else if (r == 'C') {
                rs1 = statement1.executeQuery("select* from comstudents where roll_no=" + b);
                st.setText("Commerce");

            } else if (r == 'A') {
                rs1 = statement1.executeQuery("select* from hmnstudents where roll_no=" + b);
                st.setText("Arts");
            }
            rs1.next();
            if (r == 'M') {
                rollNumberField.setText("M" + rs1.getString(1));
            } else if (r == 'A') {
                rollNumberField.setText("A" + rs1.getString(1));
            } else if (r == 'B') {
                rollNumberField.setText("B" + rs1.getString(1));
            } else if (r == 'C') {
                rollNumberField.setText("C" + rs1.getString(1));
            }
            nameField.setText(rs1.getString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String rollNumber = rollNumberField.getText();
            char roll = rollNumber.charAt(0);
            String roll2 = rollNumber.substring(1);
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
                perLabela.setText(rs.getString(9));
                gradeLabela.setText(rs.getString(10));

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

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("OK")) {
            frame.setVisible(false);
        }
    }
}
