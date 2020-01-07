package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class ConnectSQL extends JFrame {

    public void ConnectSQL(JFrame window, String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/projet4", "root", "");
        String request = "SELECT user_id, user_lastname, user_firstname FROM personal_user WHERE "+username+" = personal_user.user_mail AND "+password+" = personal_user.user_password;";
        Statement stm = connection.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        String result = rslt.toString();
        window.getContentPane().add(new JLabel(result));
    }
}
