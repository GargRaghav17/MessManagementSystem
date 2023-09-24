package mess.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Login extends JFrame implements ActionListener{
    
    JTextField tfpw = new JTextField();
    JTextField tfusr = new JTextField();
     
    Login(){
        // Login page
        
        // Login Button
        
        JButton logi = new JButton("LOGIN");
        logi.setBounds(150, 140, 150, 30);// Can later change
        logi.setBackground(new Color(20,10,50)); // Change later
        logi.setForeground(Color.WHITE);
        logi.addActionListener(this);
        add(logi);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg")); // Change later
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);
        

        JLabel lblusrnm = new JLabel("Username");
        lblusrnm.setBounds(40, 20, 100, 30); // Change
        lblusrnm.setForeground(Color.WHITE);
        add(lblusrnm);
        
        JLabel lblpw = new JLabel("Password");
        lblpw.setBounds(40, 70, 100, 30);
        lblpw.setForeground(Color.WHITE);
        add(lblpw);
        
        // Text Field
        
        
        tfusr.setBounds(150, 20, 150, 30);
        add(tfusr);
        
        
        tfpw.setBounds(150, 70, 150, 30);
        add(tfpw);
        
        
        getContentPane().setBackground(new Color(40,40,40));
        setLayout(null);
        setSize(600, 300);
        setLocation(450,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        try{
            String username = tfusr.getText();
            String password = tfpw.getText();
            
            // Executing mySQL queries
            Conn c = new Conn();
            String query = "select * from login where username = '"+username+"' and password = '"+password+"'";
            
            ResultSet rs = c.s.executeQuery(query);
            if(rs.next()){
                setVisible(false);
                // next class
                new Names(0);
            }
            else{
                // Change popup appearance later
                JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
                setVisible(false);
            }
       
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]){
        new Login();
    }
}
