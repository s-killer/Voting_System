

import java.awt.Color;
import java.awt.Insets;

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

/**
 * Another horizontal bar chart bg.  This time all the extras (titles, legend and axes) are
 * removed, to display just a single bar.
 *
 */
public class BarChart extends ApplicationFrame {

    /**
     * Creates a new bg.
     *
     * @param title  the frame title.
     */
    public BarChart( String title) {

        super(title);

        // create a dataset...
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        while(rs.next())
//        {
//        	String cname=rs.getString(1);
//        	String votes=()rs.getString(2);
//        	dataset.setValue(votes,"votes",cname);
//        }
        dataset.addValue(83.0, "First", "Factor 1");
        dataset.addValue(85.0, "First", "Factor 2");
        dataset.addValue(86, "First", "Factor 3");

        // create the chart...
         JFreeChart chart = ChartFactory.createBarChart(
            "Voting Result",  // chart title
            "Candidate Name",             // domain axis label
            "Votes",            // range axis label
            dataset,                // data
            PlotOrientation.HORIZONTAL,
            true,                  // include legend
            true,
            false
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//        chart.setBackgroundPaint(Color.yellow);  // not seen
         CategoryPlot plot = chart.getCategoryPlot();
//        plot.setInsets(new Insets(0, 0, 0, 0));
        plot.setRangeGridlinePaint(Color.blue);
//        ChartFrame chartfrm=new ChartFrame("Voting Result",chart,true);
//        chartfrm.setVisible(true);
//        chartfrm.setSize(500,500);
        
        
         CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.20);
        domainAxis.setUpperMargin(0.20);
        domainAxis.setVisible(true);
         NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, 100.0);
        rangeAxis.setVisible(true);
        // OPTIONAL CUSTOMISATION COMPLETED.

        // add the chart to a panel...
         ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        BarChart bg = new BarChart("Minimal Chart Demo");
        bg.pack();
        RefineryUtilities.centerFrameOnScreen(bg);
        bg.setVisible(true);

    }

}
