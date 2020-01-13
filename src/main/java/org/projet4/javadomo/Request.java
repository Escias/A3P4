package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Request implements ActionListener{
    private String[] request = {"Ampoule Connectée", "Caméra installée", "Donnée Ampoule", "Donnée Thermos", "Nourriture", "Photo", "Salle", "Capteur", "Thermostats"};
    private JComboBox scroll = new JComboBox(request);
    private JButton brequest = new JButton("OK");
    public JPanel pscroll = new JPanel();
    int userid;
    public JFrame windows;
    Connection connect;
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();
    AmpConnect ampConnect = new AmpConnect();
    CamInstall camInstall = new CamInstall();
    DatAmp datAmp = new DatAmp();
    Room room = new Room();

    public void Request(JFrame window, int id, Connection co) {
        userid = id;
        connect = co;
        l1.add(scroll);
        l2.add(brequest);
        c1.add(l1);
        c1.add(l2);
        pscroll.add(c1);
        window.getContentPane().add(pscroll);
        Object select = scroll.getSelectedItem();
        scroll.setSelectedItem(select);
        scroll.addActionListener(this);
        windows =  window;
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        brequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int request = scroll.getSelectedIndex();
                switch(request){
                    case 0:
                        try {
                            ampConnect.AmpConnect(windows, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            camInstall.CamInstall(windows, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            datAmp.DatAmp(windows, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 3:
                        System.out.println("World");
                        break;
                    case 4:
                        System.out.println("World");
                        break;
                    case 5:
                        System.out.println("World");
                        break;
                    case 6:
                        try {
                            room.Room(windows, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 7:
                        System.out.println("World");
                        break;
                    case 8:
                        System.out.println("World");
                        break;
                }
            }
        });
    }
}
