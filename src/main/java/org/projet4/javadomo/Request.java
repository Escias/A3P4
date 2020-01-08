package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Request {
    private String[] request = { "Ampoule Connectée", "Caméra installée", "Donnée Ampoule", "Donnée Thermos", "Nourriture", "Photo", "Salle", "Capteur", "Thermostats" };
    private JComboBox scroll = new JComboBox(request);
    private JButton brequest = new JButton("OK");

    public void Request(JFrame window, int id){
        window.add(scroll);
        window.add(brequest);
        window.setVisible(true);
    }

    public JComboBox GetRequest(){
        return scroll;
    }
}
