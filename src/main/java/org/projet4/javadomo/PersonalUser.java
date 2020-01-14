package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonalUser {
    JPanel pscroll = new JPanel();
    JTextField t1 = new JTextField(15);
    JTextField t2 = new JTextField(15);
    JTextField t3 = new JTextField(15);
    JTextField t4 = new JTextField(15);
    JTextField t5 = new JTextField(15);
    JTextField t6 = new JTextField(15);
    JTextField t7 = new JTextField(15);
    JTextField t8 = new JPasswordField(15);
    JButton binsert = new JButton("Insert");
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box l3 = Box.createHorizontalBox();
    Box l4 = Box.createHorizontalBox();
    Box l5 = Box.createHorizontalBox();
    Box l6 = Box.createHorizontalBox();
    Box l7 = Box.createHorizontalBox();
    Box l8 = Box.createHorizontalBox();
    Box l9 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();

    public void PersonalUser(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT user_lastname, user_firstname, user_mail, user_phone, user_adress, user_ZIP, user_type " +
                "FROM personal_user " +
                "WHERE user_id = "+id+
                " ORDER BY user_lastname ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()){
            l1.add(new JLabel("lastname : "+rslt.getString(1)));
            l2.add(new JLabel("firstname : "+rslt.getString(2)));
            l3.add(new JLabel("mail : "+rslt.getString(3)));
            l4.add(new JLabel("phone : "+rslt.getInt(4)));
            l5.add(new JLabel("adress : "+rslt.getString(5)));
            l6.add(new JLabel("ZIP : "+rslt.getInt(6)));
            l7.add(new JLabel("type : "+rslt.getString(7)));
            c1.add(l1);
            c1.add(l2);
            c1.add(l3);
            c1.add(l4);
            c1.add(l5);
            c1.add(l6);
            c1.add(l7);
            pscroll.add(c1);
            window.getContentPane().add(pscroll);
            window.setVisible(true);
        }
    }

    public void Insertion(JFrame window, Connection co, String role){
        if (role == "admin") {
            l1.add(new JLabel("nom"));
            l1.add(t1);
            l2.add(new JLabel("prénom"));
            l2.add(t2);
            l3.add(new JLabel("mail"));
            l3.add(t3);
            l4.add(new JLabel("téléphone"));
            l4.add(t4);
            l5.add(new JLabel("adresse"));
            l5.add(t5);
            l6.add(new JLabel("code postal"));
            l6.add(t6);
            l7.add(new JLabel("rôle (admin/normal)"));
            l7.add(t6);
            l8.add(new JLabel("mot de passe"));
            l8.add(t6);
            l9.add(binsert);
            c1.add(l1);
            c1.add(l2);
            c1.add(l3);
            c1.add(l4);
            c1.add(l5);
            c1.add(l6);
            c1.add(l7);
            c1.add(l8);
            c1.add(l9);
            pscroll.add(c1);
            window.getContentPane().add(pscroll);
            binsert.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String request = "INSERT INTO personal_user (user_lastname, user_firstname, user_mail, user_phone, user_adress, user_ZIP, user_type, user_password)" +
                                "VALUES ('" + t1.getText() + "', '" + t2.getText() + "', '" + t3.getText() + "', '" + t4.getText() + "', '" + t5.getText() + "', '" + t6.getText() + "', '" + t7.getText() + "', '" + t8.getText() + "')";
                        Statement stm = co.createStatement();
                        stm.executeUpdate(request);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        else{
            window.getContentPane().add(new JLabel("You must be an admin to add user"));
        }
    }
}
