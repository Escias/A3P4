package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Food {
    JPanel pscroll = new JPanel();
    JTextField t1 = new JTextField(15);
    JTextField t2 = new JTextField(15);
    JTextField t3 = new JTextField(15);
    JTextField t4 = new JTextField(15);
    JTextField t5 = new JTextField(15);
    JTextField t6 = new JTextField(15);
    JButton binsert = new JButton("Insert");
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box l3 = Box.createHorizontalBox();
    Box l4 = Box.createHorizontalBox();
    Box l5 = Box.createHorizontalBox();
    Box l6 = Box.createHorizontalBox();
    Box l7 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();

    public void Food(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT food_id, R.room_name, food_name, food_perempt, food_quantity " +
                "FROM food AS F " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = F.food_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY food_id ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()){
            l1.add(new JLabel("Name : " + rslt.getString(3)));
            l2.add(new JLabel("Salle : " + rslt.getString(2)));
            l3.add(new JLabel("Date de péremption : " + rslt.getString(4)));
            l4.add(new JLabel("Quantité : " + rslt.getInt(5)));
            c1.add(l1);
            c1.add(l2);
            c1.add(l3);
            c1.add(l4);
            pscroll.add(c1);
            window.getContentPane().add(pscroll);
            window.setVisible(true);
        }
    }

    public void Insertion(JFrame window, Connection co){
        l1.add(new JLabel("salle (int)"));
        l1.add(t1);
        l2.add(new JLabel("nom"));
        l2.add(t2);
        l3.add(new JLabel("date péremption"));
        l3.add(t3);
        l4.add(new JLabel("quantité"));
        l4.add(t4);
        l5.add(new JLabel("péremption ap. ouverture"));
        l5.add(t5);
        l6.add(new JLabel("date d'ouverture"));
        l6.add(t6);
        l7.add(binsert);
        c1.add(l1);
        c1.add(l2);
        c1.add(l3);
        c1.add(l4);
        c1.add(l5);
        c1.add(l6);
        c1.add(l7);
        pscroll.add(c1);
        window.getContentPane().add(pscroll);
        binsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String request = "INSERT INTO food (food_room_id, food_name, food_perempt, food_quantity, food_perempt_open, food_open)" +
                            "VALUES ('"+t1.getText()+"', '"+t2.getText()+"', '"+t3.getText()+"', '"+t4.getText()+"', '"+t5.getText()+"', '"+t6.getText()+"')";
                    Statement stm = co.createStatement();
                    stm.executeUpdate(request);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
