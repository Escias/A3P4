package org.projet4.javadomo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class Window {
    ConnectSQL connectSQL = new ConnectSQL();
    JFrame window = new JFrame();
    JPanel plog = new JPanel();
    JButton bconnect = new JButton("Connect");
    JTextField login = new JTextField(10);
    JPasswordField password = new JPasswordField(10);
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();
    JCheckBox check = new JCheckBox();
    Check ch = new Check();

    public void Window(){
        window.setTitle("Javadomo");
        window.setSize(700, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new FlowLayout());
        l1.add(login);
        l1.add(password);
        l1.add(check);
        l1.add(new JLabel("mémoriser identifiant"));
        l2.add(bconnect);
        c1.add(l1);
        c1.add(l2);
        plog.add(c1);
        window.getContentPane().add(plog);
        ch.Check(login, password);
        bconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connectSQL.ConnectSQL(window, login, password, plog, check);
                } catch (SQLException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
