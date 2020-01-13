package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class ThermoIntel {
    JPanel pscroll = new JPanel();
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box l3 = Box.createHorizontalBox();
    Box l4 = Box.createHorizontalBox();
    Box l5 = Box.createHorizontalBox();
    Box l6 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();

    public void ThermoIntel(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT thermo_id, R.room_name, thermo_name, thermo_temp_target, thermo_status, " +
                "(SELECT sensor_name FROM sensor as S WHERE S.sensor_id = T.thermo_id_1) as nom_1, " +
                "(SELECT sensor_name FROM sensor as S WHERE S.sensor_id = T.thermo_id_2) as nom_2 " +
                "FROM thermointel as T " +
                "LEFT JOIN sensor as S " +
                "ON T.thermo_name = S.sensor_name " +
                "LEFT JOIN room as R " +
                "ON R.room_id = T.thermo_room_id " +
                "WHERE R.room_user_id = " + id +
                "ORDER BY thermo_id ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()){
            l1.add(new JLabel("Name : " + rslt.getString(3)));
            l2.add(new JLabel("Salle : " + rslt.getString(2)));
            l3.add(new JLabel("Température cible : " + rslt.getString(4)));
            l4.add(new JLabel("Etat : " + rslt.getInt(5)));
            l5.add(new JLabel("Capteur 1 : " + rslt.getInt(6)));
            l6.add(new JLabel("Capteur 2 : " + rslt.getInt(7)));
            c1.add(l1);
            c1.add(l2);
            c1.add(l3);
            c1.add(l4);
            c1.add(l5);
            c1.add(l6);
            pscroll.add(c1);
            window.getContentPane().add(pscroll);
            window.setVisible(true);
        }
    }
}
