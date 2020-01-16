package org.projet4.javadomo;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class ConnectSQL extends JFrame {
    Menu menu = new Menu();
    int id;
    String role;
    JPanel tryhard = new JPanel();
    CreateIni createIni = new CreateIni();

    public void ConnectSQL(JFrame window, JTextField username, JPasswordField password, JPanel plog, JCheckBox check) throws SQLException, IOException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/projet4", "root", "root");
            String request = "SELECT user_id, user_type FROM personal_user WHERE '" + username.getText() + "' = personal_user.user_mail AND '" + password.getText() + "' = personal_user.user_password;";
            Statement stm = connection.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            if (rslt.next()) {
                window.getContentPane().remove(plog);
                window.revalidate();
                window.repaint();
                id = rslt.getInt(1);
                role = rslt.getString(2);
                System.out.println(role);
                menu.Menu(window, id, connection, role);
                createIni.CreateIni(username, password, check);
            }
            else{
                tryhard.add(new JLabel("Login / Mot de passe incorrect"));
                window.getContentPane().add(tryhard);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        window.setVisible(true);
    }
}
