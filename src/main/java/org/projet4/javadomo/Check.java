package org.projet4.javadomo;

import org.ini4j.Wini;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Check {

    public static void Check(JTextField username, JPasswordField password) {
        File file = new File("log.ini");
        System.out.println("file has been created");
        if (file.exists()) {
            try {
                Wini ini = new Wini(file);
                String stringMail = ini.get("Identifiant", "mail");
                String stringPassword = ini.get("Identifiant", "password");
                username.setText(stringMail);
                password.setText(stringPassword);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
