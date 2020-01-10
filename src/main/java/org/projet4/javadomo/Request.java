package org.projet4.javadomo;

import javax.swing.*  ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Request implements ActionListener{
    private String[] request = {"Ampoule Connectée", "Caméra installée", "Donnée Ampoule", "Donnée Thermos", "Nourriture", "Photo", "Salle", "Capteur", "Thermostats"};
    private JComboBox scroll = new JComboBox(request);
    private JButton brequest = new JButton("OK");
    public JPanel pscroll = new JPanel();
    int userid;
    public JFrame windows;
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();
    AmpConnect ampConnect = new AmpConnect();
    CamInstall camInstall = new CamInstall();
    DatAmp datAmp = new DatAmp();
    DataTemp dataTemp = new DataTemp();
    Food food = new Food();
    Photo photo = new Photo();
    Room room = new Room();
    Sensor sensor = new Sensor();
    ThermoIntel thermoIntel = new ThermoIntel();

    public void Request(JFrame window, int id) {
        userid = id;
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
                            ampConnect.AmpConnect(windows, userid);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            camInstall.CamInstall(windows, userid);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            datAmp.DatAmp(windows, userid);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                            dataTemp.DataTemp(windows, userid);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            food.Food(windows, userid);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 5:
                        try {
                            photo.Photo(windows, userid);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 6:
                        try {
                            room.Room(windows, userid);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            sensor.Sensor(windows, userid);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 8:
                        try {
                            thermoIntel.ThermoIntel(windows, userid);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                }
            }
        });
    }
}
