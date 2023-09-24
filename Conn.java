package mess.management.system;

import java.sql.*;

public class Conn {
    
    Connection c;
    Statement s;
   
    public Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); // Step1 Registering the jdbc driver
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/MessManagementSystem", "root", "#Attherate123"); // Step2 connection String
            s = c.createStatement(); // Creating statement
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


