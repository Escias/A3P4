package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AmpConnect {
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
    Table table = new Table();

    public void AmpConnect(JFrame window ,int id, Connection co) throws SQLException {
        String request = "SELECT amp_name, R.room_name, amp_status, amp_color, amp_time_on, amp_time_off " +
                "FROM ampconnect AS A " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY amp_name ASC;";
        String[] t = {"name", "salle", "status", "couleur", "heure d'activation", "heure d'extinction"};
        table.Table(window, co, t, request);
    }

    public void Insertion(JFrame window, Connection co){
        l1.add(new JLabel("nom"));
        l1.add(t1);
        l2.add(new JLabel("salle (id)"));
        l2.add(t2);
        l3.add(new JLabel("état"));
        l3.add(t3);
        l4.add(new JLabel("couleur"));
        l4.add(t4);
        l5.add(new JLabel("heure d'activation"));
        l5.add(t5);
        l6.add(new JLabel("heure d'extinction"));
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
                    String request = "INSERT INTO ampconnect (amp_name, amp_room_id, amp_status, amp_color, amp_time_on, amp_time_off)" +
                        "VALUES ('"+t1.getText()+"', '"+t2.getText()+"', '"+t3.getText()+"', '"+t4.getText()+"', '"+t5.getText()+"', '"+t6.getText()+"')";
                    Statement stm = co.createStatement();
                    stm.executeUpdate(request);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.setVisible(true);
    }

    int i;
    String s;
    List<String> data = new ArrayList<>();
    JComboBox del = new JComboBox();
    JButton bdelete = new JButton("Delete");
    Box d1 = Box.createHorizontalBox();
    Box d2 = Box.createHorizontalBox();
    Box f1 = Box.createVerticalBox();

    public void Deleted(JFrame window, Connection co, int id) throws SQLException {
        i=0;
        String request = "SELECT amp_id, amp_name " +
                "FROM ampconnect AS A " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY amp_id ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while(rslt.next()){
            s = rslt.getString(2);
            data.add(s);
            i++;
        }
        del.setModel(new DefaultComboBoxModel(data.toArray()));
        d1.add(del);
        d2.add(bdelete);
        f1.add(d1);
        f1.add(d2);
        window.getContentPane().add(f1);
        bdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String d = String.valueOf(del.getSelectedItem());
                String request = "DELETE FROM ampconnect WHERE amp_name = '"+d+"'";
                try {
                    Statement stm = co.createStatement();
                    stm.executeUpdate(request);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.setVisible(true);
    }

    JTextField u1 = new JTextField(15);
    JTextField u2 = new JTextField(15);
    JTextField u3 = new JTextField(15);
    JTextField u4 = new JTextField(15);
    JTextField u5 = new JTextField(15);
    Box d3 = Box.createHorizontalBox();
    Box d4 = Box.createHorizontalBox();
    Box d5 = Box.createHorizontalBox();
    Box d6 = Box.createHorizontalBox();
    JComboBox up = new JComboBox();
    JButton bupdate = new JButton("Select");
    JButton bup = new JButton("Update");

    public void Update(JFrame window, Connection co , int id) throws SQLException{
        i=0;
        String request = "SELECT amp_id, amp_name " +
                "FROM ampconnect AS A " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY amp_id ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while(rslt.next()){
            s = rslt.getString(2);
            data.add(s);
            i++;
        }
        up.setModel(new DefaultComboBoxModel(data.toArray()));
        d1.add(up);
        d2.add(bupdate);
        f1.add(d1);
        f1.add(d2);
        window.getContentPane().add(f1);
        bupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String d = String.valueOf(up.getSelectedItem());
                d1.remove(up);
                d2.remove(bupdate);
                f1.remove(d1);
                f1.remove(d2);
                d1.add(new JLabel("salle (id)"));
                d1.add(u1);
                d2.add(new JLabel("état (on/off)"));
                d2.add(u2);
                d3.add(new JLabel("couleur"));
                d3.add(u3);
                d4.add(new JLabel("heure d'activation"));
                d4.add(u4);
                d5.add(new JLabel("heure d'extinction"));
                d5.add(u5);
                d6.add(bup);
                f1.add(d1);
                f1.add(d2);
                f1.add(d3);
                f1.add(d4);
                f1.add(d5);
                f1.add(d6);
                window.getContentPane().add(f1);
                bup.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String request = "UPDATE ampconnect " +
                                "SET amp_room_id = '"+u1.getText()+"', amp_status = '"+u2.getText()+"', amp_color = '"+u3.getText()+"', amp_time_on = '"+u4.getText()+"', amp_time_off = '"+u5.getText()+"' "+
                                "WHERE amp_name = '"+d+"'";
                        try {
                            Statement stm = co.createStatement();
                            stm.executeUpdate(request);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        window.setVisible(true);
    }
}
