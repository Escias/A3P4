package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class Photo {
    JPanel pscroll = new JPanel();

    public void Photo(JFrame window, int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/projet4", "root", "root");
        String request = "SELECT room_name, room_description FROM room WHERE room_user_id = "+id+" ORDER BY room_name ASC;";
        Statement stm = connection.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()){
            pscroll.add(new JLabel());
            window.getContentPane().add(pscroll);
            window.setVisible(true);
        }
    }
}
