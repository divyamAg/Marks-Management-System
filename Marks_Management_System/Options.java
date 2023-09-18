import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JFrame implements ActionListener {
    //JButton addStudentButton = new JButton("ADD STUDENT");
    JButton updateResultButton = new JButton("ADD RESULT");
    JButton addResultButton = new JButton("UPDATE RESLUT");
    JButton showResultButton = new JButton("SHOW CLASS LIST");
    JButton deleteResultButton = new JButton("DELETE RECORD");

    public Options() {
        setTitle("Options");
        setSize(400, 230);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 165, 0));

        //addStudentButton.setBounds(20, 20, 170, 40);
        updateResultButton.setBounds(20, 20, 170, 40);
        addResultButton.setBounds(210, 20, 170, 40);
        showResultButton.setBounds(20, 80, 170, 40);
        deleteResultButton.setBounds(210, 80, 170, 40);

        //addStudentButton.addActionListener(this);

        updateResultButton.addActionListener(this);

        addResultButton.addActionListener(this);

        showResultButton.addActionListener(this);

        deleteResultButton.addActionListener(this);

        //panel.add(addStudentButton);
        panel.add(updateResultButton);
        panel.add(addResultButton);
        panel.add(showResultButton);
        panel.add(deleteResultButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == updateResultButton) {
            MarksheetManagementSystem mms = new MarksheetManagementSystem();
        } else if (e.getSource() == addResultButton) {
            updateresult ur = new updateresult();
        }
        else if(e.getSource()==showResultButton){
            showClassList scl = new showClassList();
        }
        else if(e.getSource()==deleteResultButton){
            deleteResult dr = new deleteResult();
        }
    }
}
