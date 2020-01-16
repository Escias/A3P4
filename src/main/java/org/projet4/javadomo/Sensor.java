package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sensor {
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
    JTable tab = new JTable();
    JPanel pan = new JPanel();

    public JPanel Sensor(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT sensor_id, sensor_name, R.room_name, sensor_status, sensor_interval, sensor_temp_min, sensor_temp_max " +
                "FROM sensor AS S " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = S.sensor_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY sensor_id ASC;";
        String[] t = {"id", "nom", "salle", "status", "interval (s)", "temp. min", "temp. max"};
        table.Table(window, co, t, request);
        tab = table.Table(window, co, t, request);
        pan.add(tab);
        return pan;
    }

    public JPanel Insertion(JFrame window, Connection co){
        l1.add(new JLabel("nom"));
        l1.add(t1);
        l2.add(new JLabel("salle (int)"));
        l2.add(t2);
        l3.add(new JLabel("état (on/off)"));
        l3.add(t3);
        l4.add(new JLabel("interval capture (int)"));
        l4.add(t4);
        l5.add(new JLabel("temp min"));
        l5.add(t5);
        l6.add(new JLabel("temp max"));
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
                    String request = "INSERT INTO sensor (sensor_name, sensor_room_id, sensor_status, sensor_interval, sensor_temp_min, sensor_temp_max)" +
                            "VALUES ('"+t1.getText()+"', '"+t2.getText()+"', '"+t3.getText()+"', '"+t4.getText()+"', '"+t5.getText()+"', '"+t6.getText()+"')";
                    Statement stm = co.createStatement();
                    stm.executeUpdate(request);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.setVisible(true);
        return pscroll;
    }

    int i;
    String s;
    List<String> data = new ArrayList<>();
    JComboBox del = new JComboBox();
    JButton bdelete = new JButton("Delete");
    Box d1 = Box.createHorizontalBox();
    Box d2 = Box.createHorizontalBox();
    Box f1 = Box.createVerticalBox();

    public JPanel Deleted(JFrame window, Connection co, int id) throws SQLException {
        i=0;
        String request = "SELECT sensor_id, sensor_name " +
                "FROM sensor AS S " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = S.sensor_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY sensor_id ASC;";
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
        pscroll.add(f1);
        window.getContentPane().add(pscroll);
        bdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String d = String.valueOf(del.getSelectedItem());
                String request = "DELETE FROM sensor WHERE sensor_name = '"+d+"'";
                try {
                    Statement stm = co.createStatement();
                    stm.executeUpdate(request);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.setVisible(true);
        return pscroll;
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

    public JPanel Update(JFrame window, Connection co , int id) throws SQLException{
        i=0;
        String request = "SELECT sensor_id, sensor_name " +
                "FROM sensor AS S " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = S.sensor_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY sensor_id ASC;";
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
                d3.add(new JLabel("interval"));
                d3.add(u3);
                d4.add(new JLabel("temp. min"));
                d4.add(u4);
                d5.add(new JLabel("temp. max"));
                d5.add(u5);
                d6.add(bup);
                f1.add(d1);
                f1.add(d2);
                f1.add(d3);
                f1.add(d4);
                f1.add(d5);
                f1.add(d6);
                pscroll.add(f1);
                window.getContentPane().add(pscroll);
                window.setVisible(true);
                bup.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String request = "UPDATE sensor " +
                                "SET sensor_room_id = '"+u1.getText()+"', sensor status = '"+u2.getText()+"', sensor_interval = '"+u3.getText()+"', sensor_temp_min = '"+u4.getText()+"', sensor_temp_max = '"+u5.getText()+"' "+
                                "WHERE sensor_name = '"+d+"'";
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
        return pscroll;
    }
}
