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
    JPanel delscroll = new JPanel();
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
//    Menu menu = new Menu();

    public void Request(JFrame window, int id, Connection co, JPanel bmenu) {
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
//        breturn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                window.getContentPane().removeAll();
//                window.revalidate();
//                window.repaint();
//                window.setVisible(true);
//                menu.Menu(window, id, co, role);
//            }
//        });
        brequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int request = scroll.getSelectedIndex();
                window.getContentPane().remove(delscroll);
                window.revalidate();
                window.repaint();
                window.setVisible(true);
                switch(request){
                    case 0:
                        try {
                            delscroll = ampConnect.AmpConnect(window, id, co);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            delscroll = camInstall.CamInstall(window, id, co);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            delscroll = datAmp.DatAmp(window, id, co);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                            delscroll = dataTemp.Datatemp(window, id, co);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            delscroll = food.Food(window, id, co);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 5:
                        try {
                            delscroll = photo.Photo(window, id, co);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 6:
                        try {
                            delscroll = room.Room(window, id, co);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            delscroll = sensor.Sensor(window, id, co);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 8:
                        try {
                            delscroll = thermoIntel.ThermoIntel(window, id, co);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 9:
                        try {
                            delscroll = personalUser.PersonalUser(window, id, co);
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
