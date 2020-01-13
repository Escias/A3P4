package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class ConnectSQL extends JFrame {
    Request req = new Request();
    int id;

    public void ConnectSQL(JFrame window, String username, String password, JPanel plog) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/projet4", "root", "root");
            String request = "SELECT user_id, user_lastname, user_firstname FROM personal_user WHERE '" + username + "' = personal_user.user_mail AND '" + password + "' = personal_user.user_password;";
            Statement stm = connection.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            if (rslt.next()) {
                window.getContentPane().remove(plog);
                window.revalidate();
                window.repaint();
                window.setVisible(true);
                id = rslt.getInt(1);
                req.Request(window, id, connection);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
