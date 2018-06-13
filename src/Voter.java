import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import com.mysql.jdbc.ResultSetMetaData;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Voter {

	static Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet rs,rs1;
	Statement st;
	String CNames;
	int rowCount, colcnt;
	int cno,votes;
	
	//	boolean Selected=false;
	String selected;
	public JFrame pollFrame;
	public JPanel panel;
	public JTextField textField_2;
	public JTextField textField_3;
	public JButton createButton;
	public JButton dynamicButton[] = new JButton[16];
	int i,n, cnt;
	JButton button[] = new JButton[12];
	int xOffset=100, yOffset=40, height=30, width=120;  

	public JFrame VoterFrame;
	public  JButton BackButton;
	String candidateName[] ,s;
	// PreparedStatement name = con.prepareStatement("select * from voting where name = ?;");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Voter window = new Voter();
					window.VoterFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Voter() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system", "root", "");
			initialize();
		} 
		catch(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e) {
			
			System.out.println("Not connected to database\n");
			JOptionPane.showMessageDialog(null, "Not Connected to database\n");
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	public void initialize() throws SQLException {
		VoterFrame = new JFrame();
		VoterFrame.setBounds(250, 150, 794, 400);
		VoterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		st = con.createStatement();
		getName(); //get names from database

		ResultSet rs=st.executeQuery("select cname from poll");
		ResultSetMetaData rsmd=(ResultSetMetaData) rs.getMetaData();
		colcnt=rsmd.getColumnCount();


		rs=st.executeQuery("SELECT count(*) FROM poll");
		rs.next();
		rowCount = rs.getInt(1);
		System.out.println("\nrow count="+rowCount);
		System.out.println("col count="+colcnt);

		String CandidateNames[] = new String [12];

		int cnt=  rowCount;
		//		JButton dynamicButton[] = new JButton[cnt];
		//
		//		int col = (int) Math.ceil(cnt / 4.0); //for column 

		//		for (int i = 0; i < col; i++) {
		//
		//			for (int j = 0; j < 4 && cnt > 0; cnt--, j++) {
		//				dynamicButton[i] = new JButton();
		//
		//				// for(n=0;n<cnt;n++)
		//				//  dynamicButton[i].setText(candidateName[n]);
		//				dynamicButton[i].setVisible(true);
		//				//dynamicButton[i].setEnabled(false);
		//				//	              dynamicButton[i].setEnabled(false);
		//
		//
		//				VoterFrame.getContentPane().add(dynamicButton[i]);
		//				dynamicButton[i].setBounds(xOffset + ((width + 100) * i), yOffset + ((height + 30) * j), width, height);
		//
		//
		//			} 
		//		}

		ButtonGroup buttonGroup = new ButtonGroup();
		JPanel buttonPanel = new JPanel();
		ActionListener listener = actionEvent ->
		System.out.println(selected=actionEvent.getActionCommand() );
		//		ActionListener listener2 = actionEvent ->
		//		selected=actionEvent.getActionCommand();
		//		System.out.println("-->"+selected);


		//		for (int i = 0; i < 5; i++) {
		int col = (int) Math.ceil(cnt / 4.0); //for column 
		int xOffset=100, yOffset=40, height=30, width=120;
		for (int i = 0; i < col; i++) {

			for (int j = 0; j < 4 && cnt > 0; cnt--, j++) {
					
				JToggleButton dynamicButton2 = new JToggleButton(Integer.toString(j + 1));
				dynamicButton2.setBounds(xOffset + ((width + 100) * i), yOffset + ((height + 30) * j), width, height);
//				------setting text
//				dynamicButton2.setText(s);
//				System.out.print("setname->>"+s+"\n");
				
				dynamicButton2.addActionListener(listener);
				buttonGroup.add(dynamicButton2);
				buttonPanel.add(dynamicButton2);
				//			VoterFrame.getContentPane().add(buttonPanel);
				//			VoterFrame.setLocationRelativeTo(null);
				VoterFrame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
				VoterFrame.setLocationRelativeTo(null);
				VoterFrame.setVisible(true);
			}
		}


		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 298, 758, 52);
		VoterFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton voteButton = new JButton("Vote");
		voteButton.setBounds(255, 311, 93, 23);
		panel_1.add(voteButton);

		JButton LogoutButton = new JButton("Logout");
		LogoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login();
//				Login.setVisible(false);
				VoterFrame.dispose();
			}
		});
		LogoutButton.setBounds(432, 311, 93, 23);
		panel_1.add(LogoutButton);
		//		VoterFrame.getContentPane().setLayout(null);

		JPanel panel_2 = new JPanel();
		VoterFrame.getContentPane().add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(null);
		voteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//				cno=Integer.parseInt(selected);
				try {
					int temp;
					PreparedStatement vote = con.prepareStatement("update poll set votes = ? where cno = ?;");
					vote.setInt(1,1);
					//					votes++;
					//					vote.setInt(1,votes);	//get votecount and update it 
					vote.setString(2,selected);

					if( (temp = vote.executeUpdate()) > 0)
					{
						voteButton.setEnabled(false);
						System.out.println(selected+" Updated");
						JOptionPane.showMessageDialog(null, "Vote Successful");
					}
					//					getVoteCnt();

//					setName();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//							System.out.println( "selected object-->"+dynamicButton[i].getSelectedObjects());
			}
		});
	}

	public void getName() throws SQLException {
		try {
			rs = st.executeQuery("select cname from poll;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		System.out.println("Name " );
		while(rs.next())
		{

			String s =rs.getString(1); 
			 System.out.print("getname->>"+s+"\n");

		}

	}
//		public void getVoteCnt() throws SQLException {
//			try {
//				PreparedStatement votecnt = con.prepareStatement("select votes from poll where cno=3;");
//			int v = ((ResultSet) votecnt).getInt(1);
////				votecnt.setString(1,"3");
//				int temp;
//				
//				if( (temp = votecnt.executeUpdate()) > 0)
//					System.out.println("vote cnt ="+v);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}			
//		}

	public void setName() throws SQLException{
		while(rs.next())
		{
			dynamicButton[i].setText(rs.getString(1)); 
			System.out.print("setname->>"+dynamicButton[i]+"\n");

		}
		
		for(i=0;i<cnt;i++){
			//			dynamicButton[i].setText(s); 
//			System.out.print("setname->>"+dynamicButton[i]+"\n");
	
		}
	}
}
	

