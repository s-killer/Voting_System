import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Choice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Objects;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Login {
	public static boolean connected = false;
	private JFrame LoginFrame;
	private JPasswordField pass;
	private JTextField Uname;
	char[] password;// = pass.getPassword();
	String passString;// = new String(password);

	static Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.LoginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system", "root", "");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		initialize();
	}


	private void initialize() {
		LoginFrame = new JFrame();
		LoginFrame.getContentPane().setLayout(null);

		Choice choice = new Choice();
		choice.add("Admin");
		choice.add("Student");
		choice.setBounds(124, 40, 147, 26);
		LoginFrame.getContentPane().add(choice);

		pass = new JPasswordField();
		pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {		//Error -going in else part of VlaidateAdmin()
				if (ke.getKeyCode() == KeyEvent.VK_ENTER)
				{
					// System.out.println("ENTER key pressed");
					if (choice.getSelectedIndex() == 0) {

						ValidateAdmin();

					}else if (choice.getSelectedIndex() == 1) {
						ValidateUser();
					}
				}
			}
		});

		pass.setBounds(124, 116, 147, 20);
		LoginFrame.getContentPane().add(pass);

		JButton LButton = new JButton("Login");
		LButton.addMouseListener(new MouseAdapter() {


			@Override
			public void mouseClicked(MouseEvent arg0) {
				testConnection();
				password = pass.getPassword();
				passString = new String(password);

				if (choice.getSelectedIndex() == 0) {

					ValidateAdmin();

				}else if (choice.getSelectedIndex() == 1) {
					ValidateUser();
				}


			}
		});
		LButton.setBounds(100, 181, 89, 23);
		LoginFrame.getContentPane().add(LButton);

		JButton BButton = new JButton("Back");
		BButton.setBounds(199, 181, 89, 23);
		LoginFrame.getContentPane().add(BButton);

		Uname = new JTextField();
		Uname.addMouseListener(new MouseAdapter() { //on click clears text field
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (Objects.equals(Uname.getText(), "UserName"))
					Uname.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (Objects.equals(Uname.getText(), ""))
					Uname.setText("UserName");
			}
		});

		Uname.setText("UserName");
		Uname.setBounds(124, 77, 147, 20);
		LoginFrame.getContentPane().add(Uname);
		Uname.setColumns(10);



		LoginFrame.setTitle("LoginFrame");
		LoginFrame.setBounds(100, 100, 450, 300);
		LoginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




	}
	public void testConnection() {

		if(con != null)  // Checking for connection
		{
			System.out.println("Connected to Database:");
			try {
				System.out.println(String.format("getCatalog() returns: %s",con.getCatalog()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Connected");
		}
		else // Checking for connection
			JOptionPane.showMessageDialog(null, "!Connected");

	}


	public boolean ValidateAdmin() {
		try {
			String sql = "SELECT username,password FROM admin WHERE username = '" + Uname.getText() + "' AND password = '" + passString + "'";
			pst = con.prepareStatement(sql);

			rs = pst.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Welcone " + Uname.getText());

				new Admin();
				//  LoginFrame.dispose();
				LoginFrame.setVisible(false);

			} else {
				JOptionPane.showMessageDialog(null, "Please check you username and password");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		return false;
	}



	public  boolean ValidateUser() {
		try {
			String sql = "SELECT username,password FROM user WHERE username = '" + Uname.getText() + "' AND password = '" + passString + "'";
//			String sql = "SELECT UID,password FROM voter WHERE UID = '" + Uname.getText() + "' AND password = '" + passString + "'";
			pst = con.prepareStatement(sql);

			rs = pst.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Welcone " + Uname.getText());
				new Voter();
				LoginFrame.setVisible(false);




			} else {
				JOptionPane.showMessageDialog(null, "Please check you username and password");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		return false;
	}

}

