package org.projet4.javadomo;

import org.ini4j.Wini;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class CreateIni {
    File file = new File("log.ini");

    public void CreateIni(JTextField username, JPasswordField password, JCheckBox check){
        try {
            if(check.isSelected()){
                file.createNewFile();
                Wini ini = new Wini(file);
                ini.put("Identifiant", "mail", username.getText());
                ini.put("Identifiant", "password", password.getText());
                ini.store();
            }
            else{
                file.delete();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
