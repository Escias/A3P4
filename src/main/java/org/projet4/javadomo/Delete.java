package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Delete {
    private String[] insert = {"Ampoule Connectée", "Caméra installée", "Donnée Ampoule", "Donnée Thermos", "Nourriture", "Photo", "Salle", "Capteur", "Thermostats", "Info personel"};
    private JComboBox scroll = new JComboBox(insert);
    private JButton brequest = new JButton("OK");
    private JButton breturn = new JButton("retour");
    public JPanel pscroll = new JPanel();
    public JPanel delscroll = new JPanel();
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

    public void Delete(JFrame window, Connection co, JPanel bmenu, int id, String role) {
        window.getContentPane().remove(bmenu);
        window.revalidate();
        window.repaint();
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
                            delscroll = ampConnect.Deleted(window, co, id);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            delscroll = camInstall.Deleted(window, co, id);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            delscroll = datAmp.Deleted(window, co, id);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                            delscroll = dataTemp.Deleted(window, co, id);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            delscroll = food.Deleted(window, co, id);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 5:
                        try {
                            delscroll = photo.Deleted(window, co, id, role);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 6:
                        try {
                            delscroll = room.Deleted(window, co, id);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            delscroll = sensor.Deleted(window, co, id);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 8:
                        try {
                            delscroll = thermoIntel.Deleted(window, co, id);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 9:
                        try {
                            delscroll = personalUser.Deleted(window, co, id, role);
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
