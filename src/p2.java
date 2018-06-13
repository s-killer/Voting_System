import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class p2 {

    public JFrame pollFrame;
    public JTextField textField_2;
    public JTextField textField_3;
    public JPanel panel;
    public JButton createButton;

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
    
	public static String candidateName[];
    public static String candidateName2[];
    
    

    public p2() throws ClassNotFoundException, SQLException {
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
                    p2 window = new p2();
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



        textField_2 = new JTextField();
        textField_2.setBounds(362, 26, 86, 20);
        pollFrame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel = new JLabel("No of Candidate");
        lblNewLabel.setBounds(234, 25, 118, 23);
        pollFrame.getContentPane().add(lblNewLabel);

        textField_3 = new JTextField();
        textField_3.setBounds(105, 26, 119, 20);
        pollFrame.getContentPane().add(textField_3);
        textField_3.setColumns(10);
        

        JLabel lblNewLabel_1 = new JLabel("Poll Name");
        lblNewLabel_1.setBounds(24, 26, 71, 20);
        pollFrame.getContentPane().add(lblNewLabel_1);
        
             
        

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
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
             
                    saveCandidate();
               
                System.out.print("save button\n");
        		 
        	}

	
			

			
        });
        saveButton.setBounds(679, 25, 89, 23);
        pollFrame.getContentPane().add(saveButton);
        
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
        
/*-----counter--*/       System.out.print("cnt="+cnt+"\n");
            
            String candidateName[] =  new String[12];
            for(int i=0;i<cnt;i++)
            candidateName[i]=JOptionPane.showInputDialog("Enter Candidate Names");

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
            
	public void saveCandidate()  {
        try {
		PreparedStatement add = con.prepareStatement("insert into poll values(?,?,?);");
        cnt=getCounter();
		for (int i = 0; i < cnt;i++)
	{

//        	CNames[i]=dynamicButton[i].getText(); 
        	cnt=getCounter();
            System.out.println("for i="+i+" cnt="+cnt+"");
            int votes=0;

            add.setInt(1,i);
            add.setString(2,CNames[i]);
            System.out.print("candidatae:"+CNames[i]);
            add.setInt(3,votes);
			
			if( (add.executeUpdate()) > 0)
				System.out.println("Inserted");
			else 
				System.out.println("not Inserted");

        }

	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}
}
