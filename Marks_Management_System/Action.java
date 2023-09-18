import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Action implements ActionListener{
    JFrame frame = new JFrame();
    JButton ok = new JButton("OK");
    public Action(){
        frame.setBounds(0, 0, 350, 200);
        JLabel roll = new JLabel("Action performed!!!");
        frame.setResizable(false);
        

        roll.setBounds(75, 20, 200, 40);
        ok.setBounds(135, 70, 80, 40);
        ok.addActionListener(this);

        frame.add(roll);
        frame.add(ok);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==ok){
            frame.setVisible(false);
        }
        
    }

}
