import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ToggleButtonsGroup {
	public static void main(String[] args) {
		UIManager.put("ToggleButton.select", new Color(190, 186, 164));
		JFrame frame = createFrame();
		ButtonGroup buttonGroup = new ButtonGroup();
		JPanel buttonPanel = new JPanel();
		ActionListener listener = actionEvent ->
		System.out.println(actionEvent.getActionCommand() + " Selected");
		for (int i = 0; i < 5; i++) {
			JToggleButton b = new JToggleButton(Integer.toString(i + 1));
			b.addActionListener(listener);
			buttonGroup.add(b);
			buttonPanel.add(b);
		}
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static JFrame createFrame() {
		JFrame frame = new JFrame("JToggleButton");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(600, 300));
		return frame;
	}
}