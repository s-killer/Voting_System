import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import javax.swing.JButton;

public class Result	 {

	private JFrame resultFrame;
	public JTable table;

	static Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;
	Statement st;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Result window = new Result();
					window.resultFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Result()throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system", "root", "");
		//       Class.forName("org.postgresql.Driver");
		//    	 con = DriverManager.getConnection("jdbc:postgresql://localhost//voting_system", "postgres", "password");
		
		
		st = con.createStatement();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		resultFrame = new JFrame();
		resultFrame.setBounds(100, 100, 450, 300);
		resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 179);
		resultFrame.getContentPane().add(panel);
		panel.setLayout(null);
//		PreparedStatement result = con.prepareStatement("select cname,votes from poll ORDER BY \" + \"   (votes) DESC;");

		try {
			rs = st.executeQuery("select cname,votes from poll ORDER BY " + "   (votes) DESC;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(10, 11, 394, 161);
		 panel.add(scrollPane);
		 panel.setVisible(true);
	        
	        table = new JTable();
	        scrollPane.setViewportView(table);
	        table.setModel(DbUtils.resultSetToTableModel(rs));
	        
	        JButton btnNewButton = new JButton("New button");
	        btnNewButton.setBounds(387, 201, 37, 23);
	        resultFrame.getContentPane().add(btnNewButton);
	}
}

