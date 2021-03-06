package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatAmp {
    JPanel pscroll = new JPanel();
    JTextField t1 = new JTextField(15);
    JTextField t2 = new JTextField(15);
    JTextField t3 = new JTextField(15);
    JButton binsert = new JButton("Insert");
    Box l1 = Box.createHorizontalBox();
    Box l2 = Box.createHorizontalBox();
    Box l3 = Box.createHorizontalBox();
    Box l4 = Box.createHorizontalBox();
    Box c1 = Box.createVerticalBox();
    Table table = new Table();
    JTable tab = new JTable();
    JPanel pan = new JPanel();

    public JPanel DatAmp(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT datamp_id, A.amp_name, datamp_action, datamp_datetime " +
                "FROM datamp AS D " +
                "LEFT JOIN ampconnect AS A " +
                "ON A.amp_id = D.datamp_amp_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                "WHERE room_user_id = " + id +
                " ORDER BY datamp_id ASC;";
        String[] t = {"name", "description"};
        table.Table(window, co, t, request);
        tab = table.Table(window, co, t, request);
        pan.add(tab);
        return pan;
    }

    public JPanel Insertion(JFrame window, Connection co){
        l1.add(new JLabel("ampoule (int)"));
        l1.add(t1);
        l2.add(new JLabel("action (allumer/éteindre)"));
        l2.add(t2);
        l3.add(new JLabel("date capture"));
        l3.add(t3);
        l4.add(binsert);
        c1.add(l1);
        c1.add(l2);
        c1.add(l3);
        c1.add(l4);
        pscroll.add(c1);
        window.getContentPane().add(pscroll);
        binsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String request = "INSERT INTO datamp (datamp_amp_id, datamp_action, datamp_datetime)" +
                            "VALUES ('"+t1.getText()+"', '"+t2.getText()+"', '"+t3.getText()+"')";
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
        String request = "SELECT datamp_id " +
                "FROM datamp AS D " +
                "LEFT JOIN ampconnect AS A " +
                "ON A.amp_id = D.datamp_amp_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                "WHERE room_user_id = " + id +
                " ORDER BY datamp_id ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while(rslt.next()){
            s = rslt.getString(1);
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
                String request = "DELETE FROM datamp  WHERE datamp_id = '"+d+"'";
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
    Box d3 = Box.createHorizontalBox();
    Box d6 = Box.createHorizontalBox();
    JComboBox up = new JComboBox();
    JButton bupdate = new JButton("Select");
    JButton bup = new JButton("Update");

    public JPanel Update(JFrame window, Connection co , int id) throws SQLException{
        i=0;
        String request = "SELECT datamp_id " +
                "FROM datamp AS D " +
                "LEFT JOIN ampconnect AS A " +
                "ON A.amp_id = D.datamp_amp_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                "WHERE room_user_id = " + id +
                " ORDER BY datamp_id ASC;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        while(rslt.next()){
            s = rslt.getString(1);
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
                d1.add(new JLabel("ampoule (id)"));
                d1.add(u1);
                d2.add(new JLabel("état (allumer/éteindre)"));
                d2.add(u2);
                d3.add(new JLabel("date capture"));
                d3.add(u3);
                d6.add(bup);
                f1.add(d1);
                f1.add(d2);
                f1.add(d3);
                f1.add(d6);
                pscroll.add(f1);
                window.getContentPane().add(pscroll);
                bup.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String request = "UPDATE datamp " +
                                "SET datamp_amp_id = '"+u1.getText()+"', datamp_action = '"+u2.getText()+"', datamp_datetime = '"+u3.getText()+"' "+
                                "WHERE datamp_id = '"+d+"'";
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
