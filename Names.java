package mess.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;

public class Names extends JFrame implements ActionListener{
    
    JTable table;
    JTextField tfsearch;
    JScrollPane jsp;
    JButton search;
    JButton reset;
    JButton printer;
    static int n = 0;
    
    Names(int n){
        
        // Writing the search button for the textfield
        
        //Later replace with enter 
        
        search = new JButton("Search");
        search.setForeground(Color.black);
        search.setBackground(Color.lightGray);
        search.setBounds(635,27, 100,20);
        search.setFont(new Font("raleway", Font.BOLD, 15));
        search.addActionListener(this);
        add(search);
        
        reset = new JButton("Reset");
        reset.setForeground(Color.black);
        reset.setBackground(Color.RED);
        reset.setBounds(635,63, 100,20);
        reset.setFont(new Font("raleway", Font.BOLD, 15));
        reset.addActionListener(this);
        add(reset);
        
        
        printer = new JButton("Print");
        printer.setForeground(Color.black);
        printer.setBackground(Color.RED);
        printer.setBounds(755,63, 100,20);
        printer.setFont(new Font("raleway", Font.BOLD, 15));
        printer.addActionListener(this);
        add(printer);
        
        // Writing the textfield for search
        
        tfsearch = new JTextField();
        tfsearch.setBounds(750, 27, 100, 20);
        add(tfsearch);
        
        // Showing the database
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        
        JLabel heading = new JLabel("Students database");
        heading.setForeground(Color.WHITE);
        heading.setBounds(320,25,500,50);
        heading.setFont(new Font("raleway", Font.BOLD, 30));
        add(heading);
        
        // Add underline under the heading
        
        // n
        
        JLabel no = new JLabel("No of coupons availed:  " + n);
        no.setForeground(Color.ORANGE);
        no.setBounds(20,25,500,50);
        no.setFont(new Font("raleway", Font.BOLD, 20));
        add(no);
        
        table = new JTable();
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("Select * from stunames");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
        
        setSize(900,700);
        setLocation(300,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == search){
            String enrno = tfsearch.getText();
            try{
                Conn c0 = new Conn();
                ResultSet rs0 = c0.s.executeQuery("Select EnrollmentNo from stunames");
                int flag = 0;

                while(rs0.next()){
                    if(enrno.equals(rs0.getString("EnrollmentNo"))){
                        c0.s.executeUpdate("Update stuNames set Recieved = 'Y' where EnrollmentNo = '"+enrno+"'");
                        JOptionPane.showMessageDialog(null, "Updated successfully!");
                        setVisible(false);
                        n+=1;
                        new Names(n);
                        flag = 1;
                        break;
                    }
                    else{
                        continue;
                    }
                }
                if(flag == 0){
                    JOptionPane.showMessageDialog(null, "No Such Student Found!");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == reset){
            try{
                Conn c1 = new Conn();
                ResultSet rs1 = c1.s.executeQuery("Select EnrollmentNo from stunames");
                while(rs1.next()){
                    c1.s.executeUpdate("Update stuNames set Recieved = 'N'");
                    JOptionPane.showMessageDialog(null, "Reset Succesfully!");
                    setVisible(false);
                    n = 0;
                    new Names(n);
                }
            }
            catch(Exception e){
//                e.printStackTrace();

            }
        }
        else if(ae.getSource() == printer){
            try{
                table.print();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String args[]){
        new Names(n);
    }
}
