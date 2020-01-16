package org.projet4.javadomo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
    Table table = new Table();
    JTable tab = new JTable();
    JPanel pan = new JPanel();

    public JPanel Food(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT food_id, R.room_name, food_name, food_perempt, food_quantity " +
                "FROM food AS F " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = F.food_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY food_id ASC;";
        String[] t = {"id", "salle", "nom", "péremption", "quantité"};
        table.Table(window, co, t, request);
        tab = table.Table(window, co, t, request);
        pan.add(tab);
        return pan;
    }

    public JPanel Insertion(JFrame window, Connection co){
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
        String request = "SELECT food_id , food_name " +
                "FROM food AS F " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = F.food_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY food_id ASC;";
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
                String request = "DELETE FROM food WHERE food_name = '"+d+"'";
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
        String request = "SELECT food_id , food_name " +
                "FROM food AS F " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = F.food_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY food_id ASC;";
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
                d2.add(new JLabel("date péremption"));
                d2.add(u2);
                d3.add(new JLabel("quantité"));
                d3.add(u3);
                d4.add(new JLabel("péremption ap. ouverture"));
                d4.add(u4);
                d5.add(new JLabel("date ouverture"));
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
                        String request = "UPDATE food " +
                                "SET food_room_id = '"+u1.getText()+"', food_perempt = '"+u2.getText()+"', food_quantity = '"+u3.getText()+"', food_perempt_open = '"+u4.getText()+"', food_open = '"+u5.getText()+"' "+
                                "WHERE food_name = '"+d+"'";
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