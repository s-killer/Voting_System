import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class t8 {

	public JFrame pollFrame;
	public JTextField textField_2;
	public JTextField textField_3;
	public JPanel panel;

	public JButton dynamicButton[] = new JButton[16];

	public String CNames[]=new String[16];
	int i,n,counter;
	public int cnt;


	int xOffset=100, yOffset=40, height=30, width=120;
	public  JButton BackButton;

	static Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;
	Statement st;

	public int aFlag;



	public t8() throws ClassNotFoundException, SQLException {
		//    	Class.forName("org.postgresql.Driver");
		//    	con = DriverManager.getConnection("jdbc:postgresql://localhost//voting_system", "postgres", "password");
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system", "root", "");

		initialize();
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					t8 window = new t8();
					window.pollFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public void initialize() {
		pollFrame = new JFrame();
		pollFrame.setBounds(250, 150, 794, 400);
		pollFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pollFrame.getContentPane().setLayout(null);
		pollFrame.setVisible(true);


		panel = new JPanel();
		panel.setBounds(10, 73, 758, 278);
		pollFrame.getContentPane().add(panel);
		panel.setLayout(null);


		JButton createButton = new JButton("Create");
		createButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				CreateCandidate();
			}

		});
		createButton.setBounds(475, 25, 89, 23);
		pollFrame.getContentPane().add(createButton);

		BackButton = new JButton("Back");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Admin();
				pollFrame.dispose();
			}
		});
		BackButton.setBounds(574, 25, 89, 23);
		pollFrame.getContentPane().add(BackButton);



		for(int i=0;i<cnt;i++)
		{    
			//        	dynamicButton[i].setText(candidateName[i]);
			CNames[i]=dynamicButton[i].getText();
			System.out.print("candidate names:"+CNames[i]+"\n");
		}
	};
	
	int getCounter()     {
		counter = Integer.parseInt(textField_2.getText());
		return counter;

	}

	public void CreateCandidate() {

		cnt=getCounter();

		System.out.print("cnt="+cnt+"\n");										/*-----counter--*/

		String candidateName[] =  new String[12];
		for(int i=0;i<cnt;i++)
			candidateName[i]=JOptionPane.showInputDialog("Enter Candidate Names");	 // button name stored in array

		JButton dynamicButton[] = new JButton[cnt];

		int col = (int) Math.ceil(cnt / 4.0); //for column 

		for (int i = 0; i < col; i++) {

			for (int j = 0; j < 4 && cnt > 0; cnt--, j++) {
				dynamicButton[i] = new JButton();

				for(n=0;n<cnt;n++)
					dynamicButton[i].setText(candidateName[n]);
				dynamicButton[i].setVisible(true);
				panel.add(dynamicButton[i]);
				dynamicButton[i].setBounds(xOffset + ((width + 100) * i), yOffset + ((height + 30) * j), width, height);
				CNames[i]=dynamicButton[i].getText();
				System.out.print("candidate names in create candidatae:"+CNames[i]+"\n");

			}

		}
	}


}
