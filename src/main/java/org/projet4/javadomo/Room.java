package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Room {
    JPanel pscroll = new JPanel();
    JTextField t1 = new JTextField(15);
    JTextField t3 = new JTextField(15);
    JButton binsert = new JButton("Insert");
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box l3 = Box.createHorizontalBox();
    Box l4 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();

    public void Room(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT room_name, room_description " +
                        "FROM room " +
                        "WHERE room_user_id = "+id+
                        " ORDER BY room_name ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while (rslt.next()){
            l1.add(new JLabel("name : "+rslt.getString(1)));
            l2.add(new JLabel("description :"+rslt.getString(2)));
            l3.add(new JLabel("                                                      "));
            c1.add(l1);
            c1.add(l2);
            c1.add(l3);
            pscroll.add(c1);
            window.getContentPane().add(pscroll);
            window.setVisible(true);
        }
    }

    public void Insertion(JFrame window, Connection co, int id){
        l1.add(new JLabel("nom"));
        l1.add(t1);
        l3.add(new JLabel("date et heure"));
        l3.add(t3);
        l4.add(binsert);
        c1.add(l1);
        c1.add(l3);
        c1.add(l4);
        pscroll.add(c1);
        window.getContentPane().add(pscroll);
        binsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String request = "INSERT INTO room (room_name, room_user_id, room_description)" +
                            "VALUES ('"+t1.getText()+"', '"+id+"', '"+t3.getText()+"')";
                    Statement stm = co.createStatement();
                    stm.executeUpdate(request);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
