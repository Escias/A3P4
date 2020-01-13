package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class AmpConnect {
    JPanel pscroll = new JPanel();
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box l3 = Box.createHorizontalBox();
    Box l4 = Box.createHorizontalBox();
    Box l5 = Box.createHorizontalBox();
    Box l6 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();

    public void AmpConnect(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT amp_name, R.room_name, amp_status, amp_color, amp_time_on, amp_time_off " +
                "FROM ampconnect AS A " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY amp_name ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()) {
            l1.add(new JLabel("name : " + rslt.getString(1)));
            l2.add(new JLabel("room : " + rslt.getString(2)));
            l3.add(new JLabel("Etat : " + rslt.getString(3)));
            l4.add(new JLabel("Couleur : " + rslt.getString(4)));
            l5.add(new JLabel("Début : " + rslt.getString(5)));
            l6.add(new JLabel("Fin : " + rslt.getString(6)));
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
