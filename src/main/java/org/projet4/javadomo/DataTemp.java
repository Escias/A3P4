package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class DataTemp {
    JPanel pscroll = new JPanel();
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box l3 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();

    public void Datatemp(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT datatemp_id, S.sensor_name, datatemp_temp, datatemp_time " +
                "FROM datatemp AS D " +
                "LEFT JOIN sensor AS S " +
                "ON S.sensor_id = D.datatemp_sensor_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = S.sensor_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY datatemp_id ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()) {
            l1.add(new JLabel("capteur : " + rslt.getString(2)));
            l2.add(new JLabel("température : " + rslt.getString(3)));
            l3.add(new JLabel("Date capture : " + rslt.getString(4)));
            c1.add(l1);
            c1.add(l2);
            c1.add(l3);;
            pscroll.add(c1);
            window.getContentPane().add(pscroll);
            window.setVisible(true);
        }
    }
}
