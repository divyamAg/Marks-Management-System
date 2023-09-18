import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {
    JButton admn = new JButton("ADMIN");
    JButton student = new JButton("STUDENT");

    JButton login = new JButton("LOGIN");
    JButton signUp = new JButton("REGISTER");
    JButton exit = new JButton("EXIT");
    JFrame lgn = new JFrame();
    JPanel panel = new JPanel();
    public Login() {
        
        lgn.setTitle("Welcome to The LNMIIT");
        ImageIcon image = new ImageIcon("logo.png");
        lgn.setIconImage(image.getImage());
        lgn.getContentPane().setBackground(new Color(0, 130, 127));

        // JPanel panel = new JPanel();
        // lgn.getContentPane();
        lgn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lgn.setBounds(0, 0, 500, 500);
        lgn.setResizable(false);
        admn.setBounds(33, 10, 200, 40);
        admn.addActionListener(this);

        student.setBounds(266, 10, 200, 40);
        student.addActionListener(this);

        login.setBounds(150, 80, 200, 40);
        login.addActionListener(this);
        login.setVisible(false);

        signUp.setBounds(150, 125, 200, 40);
        signUp.addActionListener(this);
        signUp.setVisible(false);

        exit.setBounds(280, 400, 200, 40);
        exit.addActionListener(this);

        Container container = lgn.getContentPane();
        container.setLayout(null);

        container.add(admn);
        container.add(student);
        container.add(login);
        container.add(signUp);
        container.add(exit);
        //container.add(panel);
        lgn.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == admn) {

            Admin a = new Admin();
            signUp.setVisible(false);
            login.setVisible(false);
        }

        else if (e.getSource() == student) {
            //Display disp = new Display();
            
            login.setVisible(true);
            signUp.setVisible(true);
        }
        else if(e.getSource()==login){
            StudentLogin sl = new StudentLogin();
        }
        else if(e.getSource()==signUp){
            RegisterStudent rs = new RegisterStudent();
        }
        else if(e.getSource()==exit){
            System.exit(0);
        }
    }

}
