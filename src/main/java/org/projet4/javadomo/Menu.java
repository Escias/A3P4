package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Menu {
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();
    JPanel bmenu = new JPanel();
    JButton request = new JButton("Information");
    JButton update = new JButton("Update");
    JButton insertion = new JButton("Insert");
    JButton delete = new JButton("Delete");
    Request req = new Request();
    Insert insert = new Insert();

    public void Menu(JFrame window, int id, Connection co, String role){
        l1.add(request);
        l1.add(insertion);
        l2.add(update);
        l2.add(delete);
        request.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                req.Request(window, id, co, bmenu);
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        insertion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insert.Insert(window, co, bmenu, id, role);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        c1.add(l1);
        c1.add(l2);
        bmenu.add(c1);
        window.getContentPane().add(bmenu);
        window.setVisible(true);
    }
}
