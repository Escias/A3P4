package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class Food {
    JPanel pscroll = new JPanel();
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box l3 = Box.createHorizontalBox();
    Box l4 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();

    public void Food(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT food_id, R.room_name, food_name, food_perempt, food_quantity " +
                "FROM food AS F " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = F.food_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY food_id ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()){
            l1.add(new JLabel("Name : " + rslt.getString(3)));
            l2.add(new JLabel("Salle : " + rslt.getString(2)));
            l3.add(new JLabel("Date de péremption : " + rslt.getString(4)));
            l4.add(new JLabel("Quantité : " + rslt.getInt(5)));
            c1.add(l1);
            c1.add(l2);
            c1.add(l3);
            c1.add(l4);
            pscroll.add(c1);
            window.getContentPane().add(pscroll);
            window.setVisible(true);
        }
    }
}
