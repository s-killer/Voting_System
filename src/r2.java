import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

public class r2   {

	public JFrame ResultFrame,BarFrame;

	static Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet rs,rs2,rs3;
	Statement st;
	public JTable table;
	int MaxVote=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					r2 window = new r2();
					window.ResultFrame.setVisible(true);
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
	public r2()  {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting_system", "root", "");
			//       Class.forName("org.postgresql.Driver");
			//    	 con = DriverManager.getConnection("jdbc:postgresql://localhost//voting_system", "postgres", "password");

			PreparedStatement bar = con.prepareStatement("select cname,votes from poll ORDER BY " + "   (votes) DESC;");
			PreparedStatement barData = con.prepareStatement("select cname,votes from poll ORDER BY " + "   (votes) DESC;");
			PreparedStatement MaxVotes = con.prepareStatement("select Max(votes) from poll;");
			//			st = con.createStatement();
			rs = bar.executeQuery();
			rs2 = barData.executeQuery();
			rs3=MaxVotes.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		initialize();
		
	}

	



	private void initialize() {
		ResultFrame = new JFrame();
		ResultFrame.setBounds(360, 30, 752, 638);
		ResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ResultFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 673, 201);
		ResultFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 653, 179);
		scrollPane.setBounds(10, 11, 653, 179);
		panel.add(scrollPane);

		JPanel BarchartPanel = new JPanel();
		BarchartPanel.setBounds(10, 280, 715, 308);
		ResultFrame.getContentPane().add(BarchartPanel);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rs));

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 // create a dataset...
		         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		         
		      
					dataset.addValue(0, "", "");
		        try {
					while(rs2.next())
					{
						String cname=rs2.getString(1);
						int votes=rs2.getInt(2);
						dataset.addValue(rs2.getInt(2),"votes",rs2.getString(1));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//		        dataset.addValue(20, "First", "Factor 1");
//		        dataset.addValue(30, "First", "Factor 2");
//		        dataset.addValue(40, "First", "Factor 3");
		        
		        // create the chart...
		         JFreeChart jchart = ChartFactory.createBarChart(
		            "Voting Result",  // chart title
		            "Candidate Name",             // domain axis label
		            "Votes",            // range axis label
		            dataset,                // data
		            PlotOrientation.HORIZONTAL,
		            true,                  // include legend
		            true,
		            false
		        );

		         try {
		        	 rs3.next();
					int MaxVote=rs3.getInt(1);
					System.out.println("Max Votes->"+MaxVote);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         
		         
		        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//		        chart.setBackgroundPaint(Color.yellow);  // not seen
		         CategoryPlot plot = jchart.getCategoryPlot();
//		        plot.setInsets(new Insets(0, 0, 0, 0));
		        plot.setRangeGridlinePaint(Color.blue);
        
//		        ChartFrame chartfrm=new ChartFrame("Voting Result",jchart,true);
//		        chartfrm.setVisible(true);
//		        chartfrm.setSize(300, 300);
		        
		        // add the chart to a panel...
		        ChartPanel chartPanel = new ChartPanel(jchart);
		        BarchartPanel.removeAll();
//		        BarchartPanel.setSize(700, 300);
		        chartPanel.setPreferredSize(new java.awt.Dimension(700, 300));
		        BarchartPanel.add(chartPanel);
		        chartPanel.updateUI();
		       
		         CategoryAxis domainAxis = plot.getDomainAxis();
		        domainAxis.setLowerMargin(0.20);
		        domainAxis.setUpperMargin(0.20);	    
		        domainAxis.setVisible(true);
		        
		         NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		         int range=MaxVote+20;	// 				range 
		        rangeAxis.setRange(0.0,range );
		        rangeAxis.setVisible(true);
		        // OPTIONAL CUSTOMISATION COMPLETED.
			}
		});
		btnNewButton.setBounds(653, 233, 30, 23);
		ResultFrame.getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 267, 705, 2);
		ResultFrame.getContentPane().add(separator);
		
	
		table = new JTable();
		table.setModel(DbUtils.resultSetToTableModel(rs));


	}
}
