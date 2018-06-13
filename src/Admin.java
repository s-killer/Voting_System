import javax.swing.*;

import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


public class Admin {

    public JFrame AdminFrame;


    public Admin() {
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Admin window = new Admin();
                    window.AdminFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }

    public void initialize() {
        AdminFrame = new JFrame();
        AdminFrame.setBounds(350, 200, 450, 300);
        AdminFrame.setVisible(true);
        AdminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AdminFrame.getContentPane().setLayout(null);


        JButton btnNewButton = new JButton("USER");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    new User();
                    new Result();
                } catch (ClassNotFoundException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //AdminFrame.setVisible(false);
                AdminFrame.dispose();

            }
        });

        btnNewButton.setBounds(81, 26, 115, 76);
        AdminFrame.getContentPane().add(btnNewButton);

        JButton resultBtn = new JButton("View Result");
        resultBtn.setBounds(242, 26, 115, 76);
        AdminFrame.getContentPane().add(resultBtn);
        resultBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new Result();
                
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                AdminFrame.setVisible(false);
//                AdminFrame.dispose();
            }
        });
        
        
        JButton btnNewButton_2 = new JButton("Create Poll");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new Poll();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                AdminFrame.dispose();
            }
        });
        btnNewButton_2.setBounds(81, 137, 115, 76);
        AdminFrame.getContentPane().add(btnNewButton_2);

        JButton logOutButton = new JButton("LogOut");
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login2();
                AdminFrame.dispose();
            }
        });
        logOutButton.setBounds(242, 137, 115, 76);
        AdminFrame.getContentPane().add(logOutButton);
    }


}
