import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;


public class t6 {


	static Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;
	Statement st;

	public JPanel panel_delete;
	public JPanel panel_candidate;
	public JFrame UserFrame;

	public JTextField textField;
	public JTextField delete_textfield;
	public JButton button_delete;
	public JPasswordField passwordField;

	public JTable table;
	int aFlag;
	char[] password1;
	String passString;


	public t6()throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system", "root", "");
//		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
		//       Class.forName("org.postgresql.Driver");
		//    	 con = DriverManager.getConnection("jdbc:postgresql://localhost//voting_system", "postgres", "password");
		st = con.createStatement();
		initialize();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					t6 window = new t6();
					window.UserFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void setVisible(boolean b) {
		// TODO Auto-generated method stub

	}

	public void initialize() {


		UserFrame = new JFrame();

		UserFrame.setBounds(100, 100, 716, 400);
		UserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UserFrame.getContentPane().setLayout(null);
		UserFrame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(24, 49, 209, 260);
		UserFrame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(true);

		JPanel AddUserPanel = new JPanel();
		AddUserPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		AddUserPanel.setBounds(283, 49, 387, 257);
		UserFrame.getContentPane().add(AddUserPanel);
		AddUserPanel.setVisible(false);

		JButton deleteCandidateButton = new JButton("Delete Candidate");
		
//---------------		Delete Candidate
		deleteCandidateButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				AddUserPanel.setVisible(false);
				panel_candidate.setVisible(false);
				UserFrame.setVisible(true);
				panel_delete.setVisible(true);

			}
		});
		deleteCandidateButton.setBounds(26, 111, 142, 42);
		panel.add(deleteCandidateButton);


//----------------------View Candidate

		JButton viewCandidateButton = new JButton("View Candidate");
		viewCandidateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame.setVisible(true);
				AddUserPanel.setVisible(false);
				panel_delete.setVisible(false);
				panel_candidate.setVisible(true);

				try {
					rs = st.executeQuery("select * from user;");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});

		viewCandidateButton.setBounds(26, 183, 142, 42);
		panel.add(viewCandidateButton);

		JButton addCandidateButton = new JButton("Add Candidate");
		addCandidateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserFrame.setVisible(true);
				AddUserPanel.setVisible(true);
				panel_delete.setVisible(false);
				panel_candidate.setVisible(false);

			}
		});
		addCandidateButton.setBounds(26, 37, 142, 42);
		panel.add(addCandidateButton);
		AddUserPanel.setLayout(null);

//		---------------------Table--------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(283, 50, 387, 257);
		UserFrame.getContentPane().add(scrollPane);


		table = new JTable();
		scrollPane.setViewportView(table);


		JLabel lblUId = new JLabel("UID");
		lblUId.setBounds(27, 25, 105, 23);
		AddUserPanel.add(lblUId);

		JButton btnOk = new JButton("Add");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addUser();
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			public void addUser() throws IOException, ClassNotFoundException, SQLException {


				//                Class.forName("com.mysql.jdbc.Driver");
				//                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system", "root", "");
				PreparedStatement add = con.prepareStatement("INSERT INTO user VALUES(?,?);");
				String u = textField.getText();
				password1 = passwordField.getPassword();
				passString = new String(password1);
				add.setString(1, u);
				add.setString(2, passString);


				if ((aFlag = add.executeUpdate()) > 0)
					JOptionPane.showMessageDialog(null, "Created");


			}
		});
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//addUser();


			}
		});
		btnOk.setBounds(27, 206, 95, 28);
		AddUserPanel.add(btnOk);

		textField = new JTextField();
		textField.setBounds(27, 67, 121, 23);
		AddUserPanel.add(textField);
		textField.setColumns(5);
		//lblUId.setVisible(true);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(27, 117, 105, 28);
		AddUserPanel.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(27, 156, 121, 23);
		AddUserPanel.add(passwordField);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(138, 206, 95, 28);
		AddUserPanel.add(btnCancel);

		//Candidate Panel
		panel_candidate = new JPanel();
		panel_candidate.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_candidate.setBounds(283, 49, 387, 257);
		panel_candidate.setLayout(null);
		UserFrame.getContentPane().add(panel_candidate);
		panel_candidate.setVisible(false);



		//Delete Panel       
		panel_delete = new JPanel();
		panel_delete.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_delete.setBounds(283, 49, 387, 257);
		panel_delete.setLayout(null);
		UserFrame.getContentPane().add(panel_delete);
		panel_delete.setVisible(false);

		button_delete = new JButton("Delete");
		button_delete.setBounds(30, 206, 95, 28);

		delete_textfield = new JTextField();
		delete_textfield.setVisible(true);
		panel_delete.add(delete_textfield);
		delete_textfield.setBounds(30, 67, 121, 23);
		delete_textfield.setColumns(5);

		JLabel deleteUsername = new JLabel("UserName");
		deleteUsername.setBounds(30, 25, 105, 23);
		deleteUsername.setVisible(true);
		panel_delete.add(deleteUsername);
		
		panel_delete.add(button_delete);
		panel_delete.add(delete_textfield);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Admin();
				UserFrame.dispose();
			}
		});
		backButton.setBounds(579, 317, 89, 23);
		UserFrame.getContentPane().add(backButton);



		button_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {

					PreparedStatement Delete = con.prepareStatement("DELETE FROM user WHERE username = ?;");
					String u = delete_textfield.getText();
					Delete.setString(1, u);


					if ((aFlag = Delete.executeUpdate()) > 0) {
						JOptionPane.showMessageDialog(null, "Deleted");
					} else {
						JOptionPane.showMessageDialog(null, "User not found");

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}


		});

		JPanel seprertor = new JPanel();
		seprertor.setBorder(new LineBorder(new Color(0, 0, 0)));
		seprertor.setBounds(263, 11, 1, 338);

	}
}
