package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Request{
    private String[] request = {"Ampoule Connectée", "Caméra installée", "Donnée Ampoule", "Donnée Thermos", "Nourriture", "Photo", "Salle", "Capteur", "Thermostats", "info personnel"};
    private JComboBox scroll = new JComboBox(request);
    private JButton brequest = new JButton("OK");
    private JButton breturn = new JButton("retour");
    public JPanel pscroll = new JPanel();
    int userid;
    Connection connect;
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();
    AmpConnect ampConnect = new AmpConnect();
    CamInstall camInstall = new CamInstall();
    DatAmp datAmp = new DatAmp();
    Room room = new Room();
    ThermoIntel thermoIntel = new ThermoIntel();
    Sensor sensor = new Sensor();
    DataTemp dataTemp = new DataTemp();
    Food food = new Food();
    Photo photo = new Photo();
    PersonalUser personalUser = new PersonalUser();

    public void Request(JFrame window, int id, Connection co, JPanel bmenu) {
        userid = id;
        connect = co;
        window.getContentPane().remove(bmenu);
        window.revalidate();
        window.repaint();
        window.setVisible(true);
        l1.add(scroll);
        l2.add(breturn);
        l2.add(brequest);
        c1.add(l1);
        c1.add(l2);
        pscroll.add(c1);
        window.getContentPane().add(pscroll);
        Object select = scroll.getSelectedItem();
        scroll.setSelectedItem(select);
        brequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int request = scroll.getSelectedIndex();
                switch(request){
                    case 0:
                        try {
                            ampConnect.AmpConnect(window, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            camInstall.CamInstall(window, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            datAmp.DatAmp(window, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                            dataTemp.Datatemp(window, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            food.Food(window, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 5:
                        try {
                            photo.Photo(window, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 6:
                        try {
                            room.Room(window, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            sensor.Sensor(window, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 8:
                        try {
                            thermoIntel.ThermoIntel(window, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 9:
                        try {
                            personalUser.PersonalUser(window, userid, connect);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                }
            }
        });
        window.setVisible(true);
    }
}
