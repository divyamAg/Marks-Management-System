import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterStudent extends JFrame implements ActionListener {
    private JTextField nameField;
    private JTextField Phone;
    private JTextField email;
    private JPasswordField pass;
    JTextField perField;
    JRadioButton male = new JRadioButton("MALE");
    JRadioButton female = new JRadioButton("FEMALE");
    JRadioButton pcm = new JRadioButton("PCM");
    JRadioButton pcb = new JRadioButton("PCB");
    JRadioButton comm = new JRadioButton("COMMERCE");
    JRadioButton arts = new JRadioButton("ARTS");

    String pcmsub[] = { "Select", "Computer", "PE", "Economics" };
    String pcbsub[] = { "Select", "Psycology", "PE", "Economics" };
    String commsub[] = { "Select", "IP", "Maths" };
    String artssub[] = { "Select", "PE", "Maths" };

    JComboBox optsubm = new JComboBox<>(pcmsub);
    JComboBox optsubb = new JComboBox<>(pcbsub);
    JComboBox optsubc = new JComboBox<>(commsub);
    JComboBox optsuba = new JComboBox<>(artssub);

    JLabel cmpsub1Labela = new JLabel("-");
    JLabel cmpsub2Labela = new JLabel("-");
    JLabel cmpsub3Labela = new JLabel("-");
    JLabel cmpsub4Labela = new JLabel("-");

    String DB_URL = "jdbc:mysql://localhost:3306/Marksheet";
    String DB_USER = "root";
    String DB_PASSWORD = "divyam1234";
    JLabel success = new JLabel();

    JButton addButton = new JButton("Register");
    JButton showButton = new JButton("Show offered streams");
    static String gender = "-";
    static String stream = "-";
    static String osub = "-";

    JFrame frame = new JFrame();

    public RegisterStudent() {

        frame.setTitle("Student Rgisteration Desk");
        frame.setSize(720, 720);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 255, 0));
        frame.setResizable(false);

        JLabel nameLabel = new JLabel("Student Name:");
        nameField = new JTextField();

        JLabel genderLabel = new JLabel("GENDER:");

        JLabel phoneLabel = new JLabel("PHONE:");
        Phone = new JTextField();

        JLabel emailLabel = new JLabel("EMAIL:");
        email = new JTextField();

        JLabel perLabel = new JLabel("10th MARKS:");
        perField = new JTextField();

        JLabel streamLabel = new JLabel("STREAM OFFERED:");

        JLabel cmpsub1Label = new JLabel("COMPULSORY SUBJECT:");

        JLabel cmpsub2Label = new JLabel("COMPULSORY SUBJECT:");

        JLabel cmpsub3Label = new JLabel("COMPULSORY SUBJECT:");

        JLabel cmpsub4Label = new JLabel("COMPULSORY SUBJECT:");

        JLabel optsub1Label = new JLabel("OPTIONAL SUBJECT:");

        JLabel passLabel = new JLabel("PASSWORD:");
        pass = new JPasswordField();

        addButton.addActionListener(this);

        success.setText("Registered successfully..");
        success.setBounds(20, 460, 450, 25);
        success.setVisible(false);

        Container inputPanel = frame.getContentPane();
        inputPanel.setLayout(null);

        ButtonGroup grpgender = new ButtonGroup();
        grpgender.add(male);
        grpgender.add(female);

        ButtonGroup grpstream = new ButtonGroup();
        grpstream.add(pcb);
        grpstream.add(pcm);
        grpstream.add(comm);
        grpstream.add(arts);

        nameLabel.setBounds(20, 20, 150, 40);
        nameField.setBounds(170, 20, 150, 40);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        genderLabel.setBounds(20, 70, 150, 40);
        male.setBounds(170, 70, 150, 40);
        female.setBounds(320, 70, 150, 40);
        male.addActionListener(this);
        female.addActionListener(this);
        inputPanel.add(genderLabel);
        inputPanel.add(male);
        inputPanel.add(female);

        phoneLabel.setBounds(20, 120, 150, 40);
        Phone.setBounds(170, 120, 150, 40);
        inputPanel.add(phoneLabel);
        inputPanel.add(Phone);

        emailLabel.setBounds(20, 170, 150, 40);
        email.setBounds(170, 170, 250, 40);
        inputPanel.add(emailLabel);
        inputPanel.add(email);

        perLabel.setBounds(20, 220, 150, 40);
        perField.setBounds(170, 220, 150, 40);
        showButton.setBounds(320, 220, 200, 40);
        showButton.addActionListener(this);
        inputPanel.add(showButton);
        inputPanel.add(perLabel);
        inputPanel.add(perField);

        streamLabel.setBounds(20, 270, 120, 40);
        pcm.setBounds(170, 270, 100, 40);
        pcb.setBounds(270, 270, 100, 40);
        comm.setBounds(370, 270, 120, 40);
        arts.setBounds(500, 270, 100, 40);
        pcm.addActionListener(this);
        pcb.addActionListener(this);
        comm.addActionListener(this);
        arts.addActionListener(this);

        inputPanel.add(streamLabel);
        inputPanel.add(pcm);
        inputPanel.add(pcb);
        inputPanel.add(comm);
        inputPanel.add(arts);

        cmpsub1Label.setBounds(20, 320, 150, 40);
        cmpsub1Labela.setBounds(190, 320, 150, 40);
        inputPanel.add(cmpsub1Label);
        inputPanel.add(cmpsub1Labela);

        cmpsub2Label.setBounds(20, 370, 150, 40);
        cmpsub2Labela.setBounds(190, 370, 150, 40);
        inputPanel.add(cmpsub2Label);
        inputPanel.add(cmpsub2Labela);

        cmpsub3Label.setBounds(20, 420, 150, 40);
        cmpsub3Labela.setBounds(190, 420, 150, 40);
        inputPanel.add(cmpsub3Label);
        inputPanel.add(cmpsub3Labela);

        cmpsub4Label.setBounds(20, 470, 150, 40);
        cmpsub4Labela.setBounds(190, 470, 150, 40);
        inputPanel.add(cmpsub4Label);
        inputPanel.add(cmpsub4Labela);

        optsub1Label.setBounds(20, 520, 150, 40);
        optsubm.setBounds(190, 520, 150, 40);
        optsubm.addActionListener(this);
        optsubb.setBounds(190, 520, 150, 40);
        optsubb.addActionListener(this);
        optsubc.setBounds(190, 520, 150, 40);
        optsubc.addActionListener(this);
        optsuba.setBounds(190, 520, 150, 40);
        optsuba.addActionListener(this);
        optsuba.setVisible(false);
        optsubb.setVisible(false);
        optsubc.setVisible(false);
        optsubm.setVisible(false);
        inputPanel.add(optsub1Label);
        inputPanel.add(optsubm);
        inputPanel.add(optsubb);
        inputPanel.add(optsubc);
        inputPanel.add(optsuba);

        passLabel.setBounds(20, 570, 150, 40);
        pass.setBounds(170, 570, 150, 40);
        inputPanel.add(passLabel);
        inputPanel.add(pass);

        addButton.setBounds(225, 620, 200, 40);
        inputPanel.add(addButton);

        success.setBounds(20, 650, 300, 40);
        success.setVisible(false);
        inputPanel.add(success);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String name = nameField.getText();
        String phone = Phone.getText();
        String emails = email.getText();
        String password = pass.getText();

        if (e.getSource() == male) {
            gender = "MALE";
        } else if (e.getSource() == female) {
            gender = "FEMALE";
        }

        else if (e.getSource() == showButton) {
            if (perField.getText().isBlank()) {
                success.setText("please enter the 10th marks.");
                success.setVisible(true);
            } else {
                float mark = Float.parseFloat(perField.getText().toString());
                if (mark < 50) {
                    pcm.setVisible(false);
                    pcb.setVisible(false);
                    comm.setVisible(false);
                    arts.setVisible(false);
                    success.setText("You are not eligible for promoting..");
                    success.setVisible(true);
                    addButton.setVisible(false);
                } else if (mark < 60) {
                    pcm.setVisible(false);
                    pcb.setVisible(false);
                    comm.setVisible(false);
                    arts.setVisible(true);
                    addButton.setVisible(true);
                    success.setVisible(false);
                } else if (mark < 70) {
                    pcm.setVisible(false);
                    pcb.setVisible(false);
                    comm.setVisible(true);
                    arts.setVisible(true);
                    addButton.setVisible(true);
                    success.setVisible(false);

                } else if (mark < 80) {
                    pcm.setVisible(false);
                    pcb.setVisible(true);
                    comm.setVisible(true);
                    arts.setVisible(true);
                    addButton.setVisible(true);
                    success.setVisible(false);

                } else {
                    pcm.setVisible(true);
                    pcb.setVisible(true);
                    comm.setVisible(true);
                    arts.setVisible(true);
                    addButton.setVisible(true);
                    success.setVisible(false);

                }
            }
        } else if (e.getSource() == pcm) {
            stream = "PCM";
            cmpsub1Labela.setText("English");
            cmpsub2Labela.setText("Physics");
            cmpsub3Labela.setText("Chemistry");
            cmpsub4Labela.setText("Maths");
            optsuba.setVisible(false);
            optsubb.setVisible(false);
            optsubc.setVisible(false);
            optsubm.setVisible(true);
        } else if (e.getSource() == pcb) {
            stream = "PCB";
            cmpsub1Labela.setText("English");
            cmpsub2Labela.setText("Physics");
            cmpsub3Labela.setText("Chemistry");
            cmpsub4Labela.setText("Biology");
            optsuba.setVisible(false);
            optsubb.setVisible(true);
            optsubc.setVisible(false);
            optsubm.setVisible(false);
        } else if (e.getSource() == comm) {
            stream = "COMM";
            cmpsub1Labela.setText("English");
            cmpsub2Labela.setText("Accountancy");
            cmpsub3Labela.setText("Economics");
            cmpsub4Labela.setText("Buissness Studies");
            optsuba.setVisible(false);
            optsubb.setVisible(false);
            optsubc.setVisible(true);
            optsubm.setVisible(false);
        } else if (e.getSource() == arts) {
            stream = "ARTS";
            cmpsub1Labela.setText("English");
            cmpsub2Labela.setText("History");
            cmpsub3Labela.setText("Geography");
            cmpsub4Labela.setText("Political Science");
            optsuba.setVisible(true);
            optsubb.setVisible(false);
            optsubc.setVisible(false);
            optsubm.setVisible(false);
        } else if (e.getSource() == optsuba) {
            osub = (String) optsuba.getSelectedItem();
        } else if (e.getSource() == optsubb) {
            osub = (String) optsubb.getSelectedItem();
        } else if (e.getSource() == optsubc) {
            osub = (String) optsubc.getSelectedItem();
        } else if (e.getSource() == optsubm) {
            osub = (String) optsubm.getSelectedItem();
        } else if (e.getSource() == addButton) {
            if (name.isBlank() || phone.isBlank() || emails.isBlank() || password.isBlank()) {
                success.setText("Please enter all the data.");
                success.setVisible(true);
            } else if (phone.length() != 10) {
                success.setText("Please enter correct Phone Number.");
                success.setVisible(true);
            } else if (!emails.contains("@gmail.com")) {
                success.setText("Please enter correct Email.");
                success.setVisible(true);
            } else {
                if (stream == "PCM") {
                    try {

                        // if (stream.equalsIgnoreCase("PCM")) {
                        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                        Statement statement1 = connection.createStatement();
                        String insertQuery;
                        insertQuery = "INSERT INTO  Mathstudents (name, gender, phn, email , optsub, pass) "
                                +
                                "VALUES (?, ?, ?, ?, ?, ?)";

                        ResultSet rs1 = statement1.executeQuery("select * from Mathstudents where phn='" + phone +
                                "'");
                        Boolean ans = false;
                        if (rs1.next()) {
                            ans = true;
                        }
                        if (ans) {
                            success.setText("Student already registered...");
                            success.setVisible(true);
                        } else {

                            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                            preparedStatement.setString(1, name);
                            preparedStatement.setString(2, gender);
                            preparedStatement.setString(3, phone);
                            preparedStatement.setString(4, emails);
                            preparedStatement.setString(5, osub);
                            preparedStatement.setString(6, password);

                            preparedStatement.executeUpdate();

                            preparedStatement.close();

                            Statement statement = connection.createStatement();
                            ResultSet rs = statement1.executeQuery("select * from Mathstudents where phn='" + phone +
                                    "'");
                            rs.next();
                            String rn = rs.getString(1);
                            statement.executeUpdate(
                                    "insert into students values('M" + rn + "','" + name + "','" + stream + "','"
                                            + password + "');");

                            statement.close();
                            connection.close();

                            JFrame popup = new JFrame();
                            popup.setTitle("Confirmation Box");
                            popup.setSize(300, 200);
                            popup.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            popup.getContentPane().setBackground(new Color(200, 200, 200));

                            JLabel stm = new JLabel();
                            stm.setText("Student registered successfully....");
                            stm.setBounds(50, 20, 200, 40);
                            JLabel ok = new JLabel("Your Roll Number is : M" + rn);
                            ok.setBounds(75, 60, 150, 40);

                            popup.add(stm);
                            popup.add(ok);

                            frame.setVisible(false);
                            popup.setVisible(true);
                            popup.setLocationRelativeTo(null);

                            System.out.println("Data stored in the database successfully!");
                            success.setText("Student registered successfully!!");
                            success.setVisible(true);
                        }
                        // }

                    } catch (SQLException a) {
                        a.printStackTrace();
                        success.setText("Student");
                        success.setVisible(true);
                    }
                } else if (stream == "PCB") {
                    try {

                        // if (stream.equalsIgnoreCase("PCM")) {
                        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                        Statement statement1 = connection.createStatement();
                        String insertQuery;
                        insertQuery = "INSERT INTO  Biostudents (name, gender, phn, email , optsub, pass) "
                                +
                                "VALUES (?, ?, ?, ?, ?, ?)";

                        ResultSet rs1 = statement1.executeQuery("select * from Biostudents where phn='" + phone +
                                "'");
                        Boolean ans = false;
                        if (rs1.next()) {
                            ans = true;
                        }
                        if (ans) {
                            success.setText("Student already registered...");
                            success.setVisible(true);
                        } else {
                            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                            preparedStatement.setString(1, name);
                            preparedStatement.setString(2, gender);
                            preparedStatement.setString(3, phone);
                            preparedStatement.setString(4, emails);
                            preparedStatement.setString(5, osub);
                            preparedStatement.setString(6, password);

                            preparedStatement.executeUpdate();

                            preparedStatement.close();

                            Statement statement = connection.createStatement();
                            ResultSet rs = statement1.executeQuery("select * from biostudents where phn='" + phone +
                                    "'");
                            rs.next();
                            String rn = rs.getString(1);
                            statement.executeUpdate(
                                    "insert into students values('B" + rn + "','" + name + "','" + stream + "','"
                                            + password + "')");

                            statement.close();
                            connection.close();

                            JFrame popup = new JFrame();
                            popup.setTitle("Confirmation Box");
                            popup.setSize(300, 200);
                            popup.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            popup.getContentPane().setBackground(new Color(200, 200, 200));

                            JLabel stm = new JLabel();
                            stm.setText("Student registered successfully....");
                            stm.setBounds(50, 20, 200, 40);
                            JLabel ok = new JLabel("Your Roll Number is : B" + rn);
                            ok.setBounds(75, 60, 150, 40);

                            popup.add(stm);
                            popup.add(ok);

                            frame.setVisible(false);
                            popup.setVisible(true);
                            popup.setLocationRelativeTo(null);

                            System.out.println("Data stored in the database successfully!");
                            success.setText("Student registered successfully!!");
                            success.setVisible(true);
                        }
                        // }

                    } catch (SQLException a) {
                        a.printStackTrace();
                        success.setText("Student");
                        success.setVisible(true);
                    }
                } else if (stream == "COMM") {
                    try {

                        // if (stream.equalsIgnoreCase("PCM")) {
                        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                        Statement statement1 = connection.createStatement();
                        String insertQuery;
                        insertQuery = "INSERT INTO  comstudents (name, gender, phn, email , optsub, pass) "
                                +
                                "VALUES (?, ?, ?, ?, ?, ?)";

                        ResultSet rs1 = statement1.executeQuery("select * from comstudents where phn='" + phone +
                                "'");
                        Boolean ans = false;
                        if (rs1.next()) {
                            ans = true;
                        }
                        if (ans) {
                            success.setText("Student already registered...");
                            success.setVisible(true);
                        } else {
                            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                            preparedStatement.setString(1, name);
                            preparedStatement.setString(2, gender);
                            preparedStatement.setString(3, phone);
                            preparedStatement.setString(4, emails);
                            preparedStatement.setString(5, osub);
                            preparedStatement.setString(6, password);

                            preparedStatement.executeUpdate();

                            preparedStatement.close();

                            Statement statement = connection.createStatement();
                            ResultSet rs = statement1.executeQuery("select * from comstudents where phn='" + phone +
                                    "'");
                            rs.next();
                            String rn = rs.getString(1);
                            statement.executeUpdate(
                                    "insert into students values('C" + rn + "','" + name + "','" + stream + "','"
                                            + password + "')");

                            statement.close();
                            connection.close();

                            JFrame popup = new JFrame();
                            popup.setTitle("Confirmation Box");
                            popup.setSize(300, 200);
                            popup.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            popup.getContentPane().setBackground(new Color(200, 200, 200));

                            JLabel stm = new JLabel();
                            stm.setText("Student registered successfully....");
                            stm.setBounds(50, 20, 200, 40);
                            JLabel ok = new JLabel("Your Roll Number is : C" + rn);
                            ok.setBounds(75, 60, 150, 40);

                            popup.add(stm);
                            popup.add(ok);

                            frame.setVisible(false);
                            popup.setVisible(true);
                            popup.setLocationRelativeTo(null);

                            System.out.println("Data stored in the database successfully!");
                            success.setText("Student registered successfully!!");
                            success.setVisible(true);
                        }
                        // }

                    } catch (SQLException a) {
                        a.printStackTrace();
                        success.setText("Student");
                        success.setVisible(true);
                    }
                } else if (stream == "ARTS") {
                    try {

                        // if (stream.equalsIgnoreCase("PCM")) {
                        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                        Statement statement1 = connection.createStatement();
                        String insertQuery;
                        insertQuery = "INSERT INTO  hmnstudents (name, gender, phn, email , optsub, pass) "
                                +
                                "VALUES (?, ?, ?, ?, ?, ?)";

                        ResultSet rs1 = statement1.executeQuery("select * from hmnstudents where phn='" + phone +
                                "'");
                        Boolean ans = false;
                        if (rs1.next()) {
                            ans = true;
                        }
                        if (ans) {
                            success.setText("Student already registered...");
                            success.setVisible(true);
                        } else {
                            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                            preparedStatement.setString(1, name);
                            preparedStatement.setString(2, gender);
                            preparedStatement.setString(3, phone);
                            preparedStatement.setString(4, emails);
                            preparedStatement.setString(5, osub);
                            preparedStatement.setString(6, password);

                            preparedStatement.executeUpdate();

                            preparedStatement.close();

                            Statement statement = connection.createStatement();
                            ResultSet rs = statement1.executeQuery("select * from hmnstudents where phn='" + phone +
                                    "'");
                            rs.next();
                            String rn = rs.getString(1);
                            statement.executeUpdate(
                                    "insert into students values('A" + rn + "','" + name + "','" + stream + "','"
                                            + password + "')");

                            statement.close();
                            connection.close();

                            JFrame popup = new JFrame();
                            popup.setTitle("Confirmation Box");
                            popup.setSize(300, 200);
                            popup.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            popup.getContentPane().setBackground(new Color(200, 200, 200));

                            JLabel stm = new JLabel();
                            stm.setText("Student registered successfully....");
                            stm.setBounds(50, 20, 200, 40);
                            JLabel ok = new JLabel("Your Roll Number is : A" + rn);
                            ok.setBounds(75, 60, 150, 40);

                            popup.add(stm);
                            popup.add(ok);

                            frame.setVisible(false);
                            popup.setVisible(true);
                            popup.setLocationRelativeTo(null);

                            System.out.println("Data stored in the database successfully!");
                            success.setText("Student registered successfully!!");
                            success.setVisible(true);
                        }
                        // }

                    } catch (SQLException a) {
                        a.printStackTrace();
                        success.setText("................");
                        success.setVisible(true);
                    }
                }

            }
        }
    }
}