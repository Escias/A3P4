package org.projet4.javadomo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Window {
    Test test = new Test();
    JFrame window = new JFrame();
    JPanel plog = new JPanel();
    JButton bconnect = new JButton("Connect");
    JTextField login = new JTextField("jimmyl@projet.com",10);
    JPasswordField password = new JPasswordField("ascrgn91",10);
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();

    public void Window(){
        window.setTitle("Javadomo");
        window.setSize(700, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new FlowLayout());
        l1.add(login);
        l1.add(password);
        l2.add(bconnect);
        c1.add(l1);
        c1.add(l2);
        plog.add(c1);
        window.getContentPane().add(plog);
        bconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    test.Test(window, login, password, plog);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
