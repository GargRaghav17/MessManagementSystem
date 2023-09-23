package mess.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame implements ActionListener{
    
    Splash(){
        
        // Landing screen
        
        getContentPane().setBackground(new Color(50,50,50));
        setLayout(null);
        
        JLabel heading = new JLabel("MESS MANAGEMENT SYSTEM");
        heading.setBounds(150, 30, 1200,60);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("roboto", Font.BOLD, 60));
        add(heading);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg")); // Change later
        Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 100, 1050, 500);
        add(image);
        
        
        JButton cont = new JButton("CONTINUE");
        cont.setBounds(400, 300, 300, 70);// Can later change
        cont.setBackground(new Color(60,50,50)); // Change later
        cont.setForeground(Color.WHITE);
        cont.addActionListener(this);
        image.add(cont);
        
        setSize(1170, 650);
        setLocation(70,50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
        // Can later use for transitions
        while(true){
            heading.setVisible(false);
            
            try{
                Thread.sleep(500);
            }
            catch(Exception e){
                
            }
            
            heading.setVisible(true);
            break;
        } 
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Login();
    }
    
    public static void main(String args[]){
        new Splash();
        
        // button click event --> login frame
    }
    
}
