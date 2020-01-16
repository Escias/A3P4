package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Photo {
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

    public void Photo(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT photo_id, cam_name, photo_image, photo_date " +
                "FROM photo AS P " +
                "LEFT JOIN caminstall AS C " +
                "ON C.cam_id = P.photo_cam_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = C.cam_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY room_name ASC;";
        String[] t = {"id", "nom", "chemin", "date"};
        table.Table(window, co, t, request);
    }

    public void Insertion(JFrame window, Connection co){
        l1.add(new JLabel("caméra (int)"));
        l1.add(t1);
        l2.add(new JLabel("chemin"));
        l2.add(t2);
        l3.add(new JLabel("date"));
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
                    String request = "INSERT INTO photo (photo_cam_id, photo_image, photo_date)" +
                            "VALUES ('"+t1.getText()+"', '"+t2.getText()+"', '"+t3.getText()+"')";
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

    public void Deleted(JFrame window, Connection co, int id, String role) throws SQLException {
        if(role == "admin") {
            i = 0;
            String request = "SELECT photo_id " +
                    "FROM photo AS P " +
                    "LEFT JOIN caminstall AS C " +
                    "ON C.cam_id = P.photo_cam_id " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = C.cam_room_id " +
                    "WHERE R.room_user_id = " + id +
                    " ORDER BY photo_id ASC;";
            Statement stm = co.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            while (rslt.next()) {
                s = rslt.getString(1);
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
                    String request = "DELETE FROM photo WHERE photo_id = '" + d + "'";
                    try {
                        Statement stm = co.createStatement();
                        stm.executeUpdate(request);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        else{
            window.getContentPane().add(new JLabel("You must be an admin to delete an image"));
        }
        window.setVisible(true);
    }

    JTextField u1 = new JTextField(15);
    JTextField u2 = new JTextField(15);
    JTextField u3 = new JTextField(15);
    Box d3 = Box.createHorizontalBox();
    Box d6 = Box.createHorizontalBox();
    JComboBox up = new JComboBox();
    JButton bupdate = new JButton("Select");
    JButton bup = new JButton("Update");

    public void Update(JFrame window, Connection co , int id) throws SQLException{
        i=0;
        String request = "SELECT photo_id " +
                "FROM photo AS P " +
                "LEFT JOIN caminstall AS C " +
                "ON C.cam_id = P.photo_cam_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = C.cam_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY photo_id ASC;";
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
                window.getContentPane().removeAll();
                window.revalidate();
                window.repaint();
                d1.add(new JLabel("caméra (id)"));
                d1.add(u1);
                d2.add(new JLabel("chemin"));
                d2.add(u2);
                d3.add(new JLabel("date capture"));
                d3.add(u3);
                d6.add(bup);
                f1.add(d1);
                f1.add(d2);
                f1.add(d3);
                f1.add(d6);
                window.getContentPane().add(f1);
                window.setVisible(true);
                bup.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String request = "UPDATE photo " +
                                "SET photo_cam_id = '"+u1.getText()+"', photo_image = '"+u2.getText()+"', phot_date = '"+u3.getText()+"' "+
                                "WHERE photo_id = '"+d+"'";
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
