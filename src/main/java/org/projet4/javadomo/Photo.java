package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class Photo {
    JPanel pscroll = new JPanel();
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box l3 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();

    public void Photo(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT photo_id, cam_name, photo_image, photo_date " +
                "FROM photo AS P " +
                "LEFT JOIN caminstall AS C " +
                "ON C.cam_id = P.photo_cam_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = C.amp_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY room_name ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()){
            l1.add(new JLabel("name : "+rslt.getString(1)));
            l2.add(new JLabel("description :"+rslt.getString(2)));
            l3.add(new JLabel("                                                      "));
            c1.add(l1);
            c1.add(l2);
            c1.add(l3);
            pscroll.add(c1);
            window.getContentPane().add(pscroll);
            window.setVisible(true);
        }
    }
}
