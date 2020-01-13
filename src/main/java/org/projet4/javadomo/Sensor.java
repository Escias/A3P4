package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class Sensor {
    JPanel pscroll = new JPanel();
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box l3 = Box.createHorizontalBox();
    Box l4 = Box.createHorizontalBox();
    Box l5 = Box.createHorizontalBox();
    Box l6 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();

    public void Sensor(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT sensor_id, sensor_name, R.room_name, sensor_status, sensor_interval, sensor_temp_min, sensor_temp_max " +
                "FROM sensor AS S " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = S.sensor_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY sensor_id ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()){
            l1.add(new JLabel("Name : " + rslt.getString(2)));
            l2.add(new JLabel("Salle : " + rslt.getString(3)));
            l3.add(new JLabel("Etat : " + rslt.getString(4)));
            l4.add(new JLabel("Interval : " + rslt.getInt(5)));
            l5.add(new JLabel("Température min : " + rslt.getInt(6)));
            l6.add(new JLabel("Température max : " + rslt.getInt(7)));
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
