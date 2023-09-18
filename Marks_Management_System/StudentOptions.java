import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentOptions extends JFrame implements ActionListener {
    JButton checkDetailsButton = new JButton("CHECK DETAILS");
    JButton editDetailsButton = new JButton("EDIT DETAILS");
    JButton showResultButton = new JButton("SHOW RESULT");
    JButton changePasswordButton = new JButton("CHANGE PASSWORD");
    //JButton deleteResultButton = new JButton("DELETE RECORD");

    public StudentOptions() {
        setTitle("Options");
        setSize(300, 300);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 165, 0));

        
        checkDetailsButton.setBounds(50, 20, 200, 40);
        editDetailsButton.setBounds(50, 80, 200, 40);
        showResultButton.setBounds(50, 140, 200, 40);
        changePasswordButton.setBounds(50, 200, 200, 40);
        //deleteResultButton.setBounds(115, 140, 170, 40);

        checkDetailsButton.addActionListener(this);

        editDetailsButton.addActionListener(this);

        showResultButton.addActionListener(this);

        changePasswordButton.addActionListener(this);

        //deleteResultButton.addActionListener(this);

        panel.add(changePasswordButton);
        panel.add(checkDetailsButton);
        panel.add(editDetailsButton);
        panel.add(showResultButton);
        //panel.add(deleteResultButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkDetailsButton) {
            CheckDetails cd = new CheckDetails();
        } else if (e.getSource() == editDetailsButton) {
            EditDetails ed = new EditDetails();
        } else if (e.getSource() == changePasswordButton) {
            ChnagePassword cp = new ChnagePassword();
        }
        else if(e.getSource()==showResultButton){
            ShowResult sr = new ShowResult();
        }
    }
}
