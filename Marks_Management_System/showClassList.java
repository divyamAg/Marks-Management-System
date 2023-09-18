import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class showClassList implements ActionListener {
    JButton addButton = new JButton("SHOW RESULT");
    String stm[] = { "Select", "All", "PCM", "PCB", "COMM", "ARTS" };
    String g[] = { "Select", "All", "Honours", "1st Div", "2nd Div", "3rd Div", "Fail" };

    JComboBox stream = new JComboBox<>(stm);
    JComboBox grade = new JComboBox<>(g);

    JTable table1 = new JTable();
    String DB_URL = "jdbc:mysql://localhost:3306/marksheet";
    String DB_USER = "root";
    String DB_PASSWORD = "divyam1234";

    static String st = "-";
    static String gr = "-";

    public showClassList() {
        JFrame frame = new JFrame();
        frame.setTitle("Class Result");
        frame.setSize(800, 430);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(64, 224, 208));

        addButton.addActionListener(this);

        Container inputPanel = frame.getContentPane();
        inputPanel.setLayout(null);
        addButton.setBounds(300, 20, 200, 40);

        JLabel a = new JLabel("STREAM:");
        a.setBounds(90, 70, 150, 40);
        stream.setBounds(240, 70, 150, 40);
        inputPanel.add(a);
        inputPanel.add(stream);

        JLabel b = new JLabel("GRADE:");
        b.setBounds(410, 70, 150, 40);
        grade.setBounds(560, 70, 150, 40);
        inputPanel.add(b);
        inputPanel.add(grade);

        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setPreferredSize(new Dimension(600, 190));

        JPanel mainPanel = new JPanel();
        mainPanel.add(scrollPane);
        mainPanel.setBounds(100, 150, 610, 200);

        inputPanel.add(addButton);
        inputPanel.add(mainPanel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== addButton){
            try {
                st = (String) stream.getSelectedItem();
                gr = (String) grade.getSelectedItem();

                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs;
                if(st=="All"&&gr=="All"){
                     rs = statement.executeQuery("select * from result;");
                }
                else if(st=="All"){
                    rs = statement.executeQuery("select * from result where (grade='"+gr+"')");
                }
                else if(gr=="All"){
                    rs = statement.executeQuery("select * from result where (stream='"+st+"')");
                }
                else{
                     rs = statement.executeQuery("select * from result where (stream='"+st+"' and grade='"+gr+"')");
                }
                

                ResultSetMetaData rsmd = rs.getMetaData();
                DefaultTableModel model = (DefaultTableModel) table1.getModel();

                int cols = rsmd.getColumnCount();
                String[] colName = new String[cols];
                for (int i = 0; i < cols; i++) {
                    colName[i] = rsmd.getColumnName(i + 1);
                }

                model.setColumnIdentifiers(colName);
                int p = model.getRowCount();
                for(int i=p-1;i>=0;i--){
                    model.removeRow(i);
                }
                String a, b, c, d, f, g, h, i, j, k;

                while(rs.next()){
                    a = rs.getString(1);
                        b = rs.getString(2);
                        c = rs.getString(3);
                        d = rs.getString(4);
                        f = rs.getString(5);
                        g = rs.getString(6);
                        h = rs.getString(7);
                        i = rs.getString(8);
                        j = rs.getString(9);
                        k = rs.getString(10);

                        String[] row = { a, b, c, d, f, g, h, i, j, k };

                        model.addRow(row);
                }

                statement.close();
                connection.close();

            } catch (Exception a) {
                //TODO: handle exception
                a.printStackTrace();
            }
        }
    }
}
