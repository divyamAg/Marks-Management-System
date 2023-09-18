import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditDetails implements ActionListener {
    JRadioButton male = new JRadioButton("MALE");
    JRadioButton female = new JRadioButton("FEMALE");

    JLabel nameField = new JLabel();
    JLabel Phone = new JLabel();
    JLabel email = new JLabel();
    JLabel pass = new JLabel();
    JLabel cmpsub1Labela = new JLabel("-");//
    JLabel cmpsub2Labela = new JLabel("-");//
    JLabel cmpsub3Labela = new JLabel("-");//
    JLabel cmpsub4Labela = new JLabel("-");//
    JLabel optsub1Labela = new JLabel("-");
    JLabel gender = new JLabel();
    JLabel stream = new JLabel();//

    JTextField nField = new JTextField();
    JTextField pField = new JTextField();
    // JTextField gField = new JTextField();
    JTextField eField = new JTextField();

    String DB_URL = "jdbc:mysql://localhost:3306/Marksheet";
    String DB_USER = "root";
    String DB_PASSWORD = "divyam1234";

    JButton addButton = new JButton("EDIT");

    JFrame frame = new JFrame();

    JLabel success = new JLabel();
    static String g = "";

    public EditDetails() {
        frame.setTitle("Edit Details");
        frame.setSize(670, 670);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(172, 112, 109));
        frame.setResizable(false);

        JLabel nameLabel = new JLabel("Student Name:");
        JLabel genderLabel = new JLabel("GENDER:");
        JLabel phoneLabel = new JLabel("PHONE:");
        JLabel emailLabel = new JLabel("EMAIL:");
        JLabel streamLabel = new JLabel("STREAM:");
        JLabel cmpsub1Label = new JLabel("COMPULSORY SUBJECT:");
        JLabel cmpsub2Label = new JLabel("COMPULSORY SUBJECT:");
        JLabel cmpsub3Label = new JLabel("COMPULSORY SUBJECT:");
        JLabel cmpsub4Label = new JLabel("COMPULSORY SUBJECT:");
        JLabel optsub1Label = new JLabel("OPTIONAL SUBJECT:");
        JLabel passLabel = new JLabel("Roll No:");

        addButton.addActionListener(this);
        Container inputPanel = frame.getContentPane();
        inputPanel.setLayout(null);
        ButtonGroup grpgender = new ButtonGroup();
        grpgender.add(male);
        grpgender.add(female);

        nameLabel.setBounds(20, 20, 150, 40);
        nameField.setBounds(200, 20, 150, 40);
        nField.setBounds(350, 20, 150, 40);
        inputPanel.add(nField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        genderLabel.setBounds(20, 70, 150, 40);
        gender.setBounds(200, 70, 150, 40);
        male.setBounds(350, 70, 100, 40);
        female.setBounds(450, 70, 100, 40);
        male.addActionListener(this);
        female.addActionListener(this);
        // gField.setBounds(350, 70, 150, 40);
        // inputPanel.add(gField);
        inputPanel.add(male);
        inputPanel.add(female);
        inputPanel.add(genderLabel);
        inputPanel.add(gender);

        phoneLabel.setBounds(20, 120, 150, 40);
        Phone.setBounds(200, 120, 150, 40);
        pField.setBounds(350, 120, 150, 40);
        inputPanel.add(pField);
        inputPanel.add(phoneLabel);
        inputPanel.add(Phone);

        emailLabel.setBounds(20, 170, 150, 40);
        email.setBounds(200, 170, 250, 40);
        eField.setBounds(350, 170, 270, 40);
        inputPanel.add(eField);
        inputPanel.add(emailLabel);
        inputPanel.add(email);

        streamLabel.setBounds(20, 220, 100, 40);
        stream.setBounds(200, 220, 100, 40);
        inputPanel.add(streamLabel);
        inputPanel.add(stream);

        cmpsub1Label.setBounds(20, 270, 150, 40);
        cmpsub1Labela.setBounds(200, 270, 150, 40);
        inputPanel.add(cmpsub1Label);
        inputPanel.add(cmpsub1Labela);

        cmpsub2Label.setBounds(20, 320, 150, 40);
        cmpsub2Labela.setBounds(200, 320, 150, 40);
        inputPanel.add(cmpsub2Label);
        inputPanel.add(cmpsub2Labela);

        cmpsub3Label.setBounds(20, 370, 150, 40);
        cmpsub3Labela.setBounds(200, 370, 150, 40);
        inputPanel.add(cmpsub3Label);
        inputPanel.add(cmpsub3Labela);

        cmpsub4Label.setBounds(20, 420, 150, 40);
        cmpsub4Labela.setBounds(200, 420, 150, 40);
        inputPanel.add(cmpsub4Label);
        inputPanel.add(cmpsub4Labela);

        optsub1Label.setBounds(20, 470, 150, 40);
        optsub1Labela.setBounds(200, 470, 150, 40);
        inputPanel.add(optsub1Label);
        inputPanel.add(optsub1Labela);

        passLabel.setBounds(20, 520, 150, 40);
        pass.setBounds(200, 520, 150, 40);
        inputPanel.add(passLabel);
        inputPanel.add(pass);

        addButton.setBounds(225, 570, 200, 40);
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        success.setBounds(20, 610, 300, 40);
        success.setVisible(false);
        inputPanel.add(success);

        frame.setVisible(true);

        String rn;
        char r = 'l';
        String b = "";
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement1 = connection.createStatement();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select* from login");
            while (rs.next()) {
                rn = rs.getString(1);
                r = rn.charAt(0);
                b = rn.substring(1);
            }

            ResultSet rs1 = statement1.executeQuery("select* from mathstudents where roll_no=" + b);
            if (r == 'M') {
                rs1 = statement1.executeQuery("select* from mathstudents where roll_no=" + b);
                stream.setText("PCM");
                cmpsub1Labela.setText("English");
                cmpsub2Labela.setText("Physics");
                cmpsub3Labela.setText("Chemistry");
                cmpsub4Labela.setText("Maths");
            } else if (r == 'B') {
                rs1 = statement1.executeQuery("select* from biostudents where roll_no=" + b);
                stream.setText("PCB");
                cmpsub1Labela.setText("English");
                cmpsub2Labela.setText("Physics");
                cmpsub3Labela.setText("Chemistry");
                cmpsub4Labela.setText("Biology");
            } else if (r == 'C') {
                rs1 = statement1.executeQuery("select* from comstudents where roll_no=" + b);
                stream.setText("Commerce");
                ;
                cmpsub1Labela.setText("English");
                cmpsub2Labela.setText("Accountancy");
                cmpsub3Labela.setText("Economics");
                cmpsub4Labela.setText("Buissness Studies");

            } else if (r == 'A') {
                rs1 = statement1.executeQuery("select* from hmnstudents where roll_no=" + b);
                stream.setText("Arts");
                cmpsub1Labela.setText("English");
                cmpsub2Labela.setText("History");
                cmpsub3Labela.setText("Geography");
                cmpsub4Labela.setText("Political Science");
            }
            rs1.next();
            if (r == 'M') {
                pass.setText("M" + rs1.getString(1));
            } else if (r == 'A') {
                pass.setText("A" + rs1.getString(1));
            } else if (r == 'B') {
                pass.setText("B" + rs1.getString(1));
            } else if (r == 'C') {
                pass.setText("C" + rs1.getString(1));
            }
            nameField.setText(rs1.getString(2).toUpperCase());
            gender.setText(rs1.getString(3));
            Phone.setText(rs1.getNString(4));
            email.setText(rs1.getString(5));
            optsub1Labela.setText(rs1.getString(6));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        String n = nField.getText();
        String p = pField.getText();
        String e = eField.getText();
        if (a.getSource() == male) {
            g = "MALE";
        } else if (a.getSource() == male) {
            g = "FEMALE";
        } else if (a.getSource() == addButton) {

            if (n.isBlank() || p.isBlank() || e.isBlank() || g.isBlank()) {
                success.setText("Please fill all the details.");
                success.setVisible(true);
            } else if (p.length() != 10) {
                success.setText("Please enter correct Phone Number.");
                success.setVisible(true);
            } else if (!e.contains("@gmail.com")) {
                success.setText("Please enter correct Email.");
                success.setVisible(true);
            } else {
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

                    if (r == 'M') {
                        statement1.executeUpdate("update mathstudents set name='" + n + "',gender='" + g + "',phn='" + p
                                + "',email='" + e + "' where roll_no=" + b);
                        statement1.executeUpdate("update students set name ='" + n + "' where roll_no='" + rn1 + "'");
                        statement1.executeUpdate("update result set name ='" + n + "' where roll_no='" + rn1 + "'");
                    } else if (r == 'B') {
                        statement1.executeUpdate("update biostudents set name='" + n + "',gender='" + g + "',phn='" + p
                                + "',email='" + e + "' where roll_no=" + b);
                        statement1.executeUpdate("update students set name ='" + n + "' where roll_no='" + rn1 + "'");
                        statement1.executeUpdate("update result set name ='" + n + "' where roll_no='" + rn1 + "'");
                    } else if (r == 'C') {
                        statement1.executeUpdate("update comstudents set name='" + n + "',gender='" + g + "',phn='" + p
                                + "',email='" + e + "' where roll_no=" + b);
                        statement1.executeUpdate("update students set name ='" + n + "' where roll_no='" + rn1 + "'");
                        statement1.executeUpdate("update result set name ='" + n + "' where roll_no='" + rn1 + "'");
                    } else if (r == 'A') {
                        statement1.executeUpdate("update hmnstudents set name='" + n + "',gender='" + g + "',phn='" + p
                                + "',email='" + e + "' where roll_no=" + b);
                        statement1.executeUpdate("update students set name ='" + n + "' where roll_no='" + rn1 + "'");
                        statement1.executeUpdate("update result set name ='" + n + "' where roll_no='" + rn1 + "'");
                    }

                    frame.setVisible(false);
                    Action t = new Action();
                } catch (Exception l) {
                    l.printStackTrace();
                }
            }

        }

    }
}
