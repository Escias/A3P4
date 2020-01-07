package org.projet4.javadomo;

import javax.swing.*;
import java.sql.SQLException;

public class Test extends JFrame {
    ConnectSQL connectSQL = new ConnectSQL();

    public void Test(JFrame window, JTextField login, JTextField password) throws SQLException {
        String log = login.getText();
        String pass = password.getText();
//        window.getContentPane().add(new JLabel(log+" "+pass));
        window.setVisible(true);
        connectSQL.ConnectSQL(window, log, pass);
    }
}
