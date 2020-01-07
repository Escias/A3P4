package org.projet4.javadomo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Window {
    Test test = new Test();
    JFrame window = new JFrame();
    JButton bconnect = new JButton("Connect");
    JTextField login = new JTextField("Username",10);
    JTextField password = new JTextField("Password",10);

    public void Window(){
        window.setTitle("Javadomo");
        window.setSize(700, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new FlowLayout());
        window.add(login);
        window.add(password);
        window.add(bconnect);
        bconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    test.Test(window, login, password);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
