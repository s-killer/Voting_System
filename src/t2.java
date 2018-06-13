
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class t2 extends JFrame {
	 public static Boolean Bselected=false;
  final JButton b = new JButton("Add");
//  b.setBounds(679, 25, 89, 23);
  int size = 10;

  public t2() {
    setSize(347, 202);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    getContentPane().setLayout(null);
    b.setBounds(138, 43, 51, 23);
    getContentPane().add(b);
    b.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent ev) {
        b.setFont(new Font("Dialog", Font.PLAIN, ++size));
        Bselected=true;
        b.revalidate();
        System.out.println(Bselected);
      }
    });
    setVisible(true);
  }

  public static void main(String[] args) {
    new t2();
    System.out.println(Bselected+"main");
  }
}
