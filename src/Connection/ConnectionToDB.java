package Connection;

import java.sql.*;
import javax.swing.JOptionPane;
public class ConnectionToDB {
    static Connection con = null;
    public static Connection ConnectToDB(){
        try{
        	Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system", "root", "");
//          Class.forName("org.postgresql.Driver");
//       	 con = DriverManager.getConnection("jdbc:postgresql://localhost//voting_system", "postgres", "password");
            testConnection();
            return con;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public static  void testConnection() {

        if (con != null)  // Checking for connection
        {
            System.out.println("Connected to Database:");
            try {
                System.out.println(String.format("getCatalog() returns: %s", con.getCatalog()));
            	
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //JOptionPane.showMessageDialog(null, "Connected");
        } else // Checking for connection
        	System.out.println("Not Connected to Database:");

    }
}
 