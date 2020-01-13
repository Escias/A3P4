package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class DatAmp {
    JPanel pscroll = new JPanel();
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box l3 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();

    public void DatAmp(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT datamp_id, A.amp_name, datamp_action, datamp_datetime " +
                "FROM datamp AS D " +
                "LEFT JOIN ampconnect AS A " +
                "ON A.amp_id = D.datamp_amp_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                "WHERE room_user_id = " + id +
                " ORDER BY datamp_id ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()) {
            l1.add(new JLabel("nom ampoule : " + rslt.getString(2)));
            l2.add(new JLabel("Action : " + rslt.getString(3)));
            l3.add(new JLabel("Horaire : " + rslt.getString(4)));
            c1.add(l1);
            c1.add(l2);
            c1.add(l3);
            pscroll.add(c1);
            window.getContentPane().add(pscroll);
            window.setVisible(true);
        }
    }
}
