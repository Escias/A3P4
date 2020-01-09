package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class Room {
    private JLabel info = new JLabel();
    JPanel pscroll = new JPanel();

    public void Room(JFrame window, int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/projet4", "root", "root");
        String request = "SELECT room_name, room_description FROM room WHERE room_user_id = "+id+" ORDER BY room_name ASC;";
        Statement stm = connection.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()){
            pscroll.add(new JLabel("room name : "+rslt.getString(1)+" / room description :"+rslt.getString(2)));
            pscroll.add(new JLabel("\n"));
            window.getContentPane().add(pscroll);
            window.setVisible(true);
        }
    }
}
