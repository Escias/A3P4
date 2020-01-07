package org.projet4.javadomo;

import javax.swing.*;

public class Test extends JFrame {

    public void Test(JFrame window, JTextField login, JTextField password){
        String log = login.getText();
        String pass = password.getText();
        window.getContentPane().add(new JLabel(log+" "+pass));
        window.setVisible(true);
    }
}
