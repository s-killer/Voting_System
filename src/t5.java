import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class t5 {
	public JPanel panel;
	public JToggleButton dynamicButton[] = new JToggleButton[16];
	int i,n,counter;
	public static int cnt;
	int xOffset=100, yOffset=40, height=30, width=120;
	
	public static void main(String[] args) {
		
		UIManager.put("ToggleButton.select", new Color(190, 186, 164));
		JFrame frame = createFrame();
		ButtonGroup buttonGroup = new ButtonGroup();
		JPanel buttonPanel = new JPanel();
		ActionListener listener = actionEvent ->
		System.out.println(actionEvent.getActionCommand() + " Selected");
		cnt=8;
//		for (int i = 0; i < 5; i++) {
		int col = (int) Math.ceil(cnt / 4.0); //for column 

		for (int i = 0; i < col; i++) {

			for (int j = 0; j < 4 && cnt > 0; cnt--, j++) {
			JToggleButton dynamicButton = new JToggleButton(Integer.toString(j + 1));
			dynamicButton.addActionListener(listener);
			buttonGroup.add(dynamicButton);
			buttonPanel.add(dynamicButton);
		}
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		}
	}
		

	private static JFrame createFrame() {
		JFrame frame = new JFrame("JToggleButton");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(600, 300));
		return frame;
	}
}