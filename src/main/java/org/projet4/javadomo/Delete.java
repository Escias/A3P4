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
        brequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int request = scroll.getSelectedIndex();
                switch(request){
                    case 0:
                        ampConnect.Insertion(window, co);
                        break;
                    case 1:
                        camInstall.Insertion(window, co);
                        break;
                    case 2:
                        datAmp.Insertion(window, co);
                        break;
                    case 3:
                        dataTemp.Insertion(window, co);
                        break;
                    case 4:
                        try {
                            food.Deleted(window, co, id);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 5:
                        photo.Insertion(window, co);
                        break;
                    case 6:
                        room.Insertion(window, co, id);
                        break;
                    case 7:
                        sensor.Insertion(window, co);
                        break;
                    case 8:
                        thermoIntel.Insertion(window, co);
                        break;
                    case 9:
                        personalUser.Insertion(window, co, role);
                        break;
                }
            }
        });
        window.setVisible(true);
    }
}
