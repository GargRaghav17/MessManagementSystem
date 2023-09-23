package mess.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;

public class Names extends JFrame{
    
    JTable table;
    JTextField tfsearch = new JTextField();
    
    Names(){
        
        // Writing the search heading for the textfield
        
        JLabel search = new JLabel("Search");
        search.setForeground(Color.orange);
        search.setBounds(650,45,150,30);
        search.setFont(new Font("raleway", Font.BOLD, 15));
        add(search);
        
        // Writing the textfield for search
        
        tfsearch.setBounds(720, 50, 100, 20);
        add(tfsearch);
        
        // Showing the database
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        
        JLabel heading = new JLabel("Students database");
        heading.setForeground(Color.WHITE);
        heading.setBounds(320,25,500,50);
        heading.setFont(new Font("raleway", Font.BOLD, 30));
        add(heading);
        
        table = new JTable();
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("Select * from stunames");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
        
        setSize(900,700);
        setLocation(300,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String args[]){
        new Names();
    }
}
