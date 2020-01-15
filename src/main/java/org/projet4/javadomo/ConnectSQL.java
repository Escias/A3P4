package org.projet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class ConnectSQL extends JFrame {
    Menu menu = new Menu();
    int id;
    String role;

    public void ConnectSQL(JFrame window, String username, String password, JPanel plog) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/projet4", "root", "root");
            String request = "SELECT user_id, user_type FROM personal_user WHERE '" + username + "' = personal_user.user_mail AND '" + password + "' = personal_user.user_password;";
            Statement stm = connection.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            if (rslt.next()) {
                window.getContentPane().remove(plog);
                window.revalidate();
                window.repaint();
                window.setVisible(true);
                id = rslt.getInt(1);
                role = rslt.getString(2);
                System.out.println(role);
                menu.Menu(window, id, connection, role);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
