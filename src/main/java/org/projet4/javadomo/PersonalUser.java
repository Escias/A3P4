package org.projet4.javadomo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    Table table = new Table();
    JTable tab = new JTable();
    JPanel pan = new JPanel();

    public JPanel PersonalUser(JFrame window, int id, Connection co) throws SQLException {
        String request = "SELECT user_lastname, user_firstname, user_mail, user_phone, user_adress, user_ZIP, user_type " +
                "FROM personal_user " +
                "WHERE user_id = "+id+
                " ORDER BY user_lastname ASC;";
        String[] t = {"nom", "prénom", "mail", "téléphone", "adresse", "ZIP", "type"};
        table.Table(window, co, t, request);
        tab = table.Table(window, co, t, request);
        pan.add(tab);
        return pan;
    }

    public JPanel Insertion(JFrame window, Connection co, String role){
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
            pscroll.add(new JLabel("You must be an admin to add user"));
            window.getContentPane().add(pscroll);
        }
        window.setVisible(true);
        return pscroll;
    }

    int i;
    String s;
    List<String> data = new ArrayList<>();
    JComboBox del = new JComboBox();
    JButton bdelete = new JButton("Delete");
    JButton byes = new JButton("YES");
    JButton bno = new JButton("NO");
    JButton bme = new JButton("Delete my profile");
    JButton bother = new JButton("Delete other profile");
    Box d1 = Box.createHorizontalBox();
    Box d2 = Box.createHorizontalBox();
    Box f1 = Box.createVerticalBox();

    public JPanel Deleted(JFrame window, Connection co, int id, String role) throws SQLException {
        if(role == "admin") {
            d1.add(bme);
            d1.add(bother);
            f1.add(d1);
            window.getContentPane().add(f1);
            bme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d1.remove(bme);
                    d1.remove(bother);
                    f1.remove(d1);
                    d1.add(new JLabel("Are you sure"));
                    d2.add(byes);
                    d2.add(bno);
                    f1.add(d1);
                    f1.add(d2);
                    pscroll.add(f1);
                    window.getContentPane().add(pscroll);
                    byes.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String request = "DELETE FROM personal_user WHERE user_id = '" + id + "'";
                            try {
                                Statement stm = co.createStatement();
                                stm.executeUpdate(request);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    bno.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            window.getContentPane().remove(pscroll);
                            window.revalidate();
                            window.repaint();
                            window.setVisible(true);
                        }
                    });
                }
            });
            bother.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        i = 0;
                        String request = "SELECT user_id, user_firstname, user_type " +
                                "FROM personal_user " +
                                "WHERE user_type != 'admin'" +
                                " ORDER BY user_id ASC;";
                        Statement stm = co.createStatement();
                        ResultSet rslt = stm.executeQuery(request);
                        while (rslt.next()) {
                            s = rslt.getString(2);
                            data.add(s);
                            i++;
                        }
                    }catch (SQLException ex){
                        ex.printStackTrace();
                    }
                    d1.remove(bme);
                    d1.remove(bother);
                    f1.remove(d1);
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
                            String request = "DELETE FROM personal_user WHERE user_firstname = '" + d + "'";
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
        }
        else{
            d1.add(new JLabel("Do you want to delete your profile ?"));
            d2.add(byes);
            d2.add(bno);
            f1.add(d1);
            f1.add(d2);
            pscroll.add(f1);
            window.getContentPane().add(pscroll);
            byes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String request = "DELETE FROM personal_user WHERE user_id = '" + id + "'";
                    try {
                        Statement stm = co.createStatement();
                        stm.executeUpdate(request);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            bno.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
        window.setVisible(true);
        return pscroll;
    }

    JTextField u1 = new JTextField(15);
    JTextField u2 = new JTextField(15);
    JTextField u3 = new JTextField(15);
    JTextField u4 = new JTextField(15);
    JTextField u5 = new JTextField(15);
    JTextField u6 = new JTextField(15);
    JTextField u7 = new JTextField(15);
    Box d3 = Box.createHorizontalBox();
    Box d4 = Box.createHorizontalBox();
    Box d5 = Box.createHorizontalBox();
    Box d6 = Box.createHorizontalBox();
    Box d7 = Box.createHorizontalBox();
    Box d8 = Box.createHorizontalBox();
    JComboBox up = new JComboBox();
    JButton bupdate = new JButton("Select");
    JButton bup = new JButton("Update");
    JButton bupme = new JButton("Update my profile");
    JButton bupother = new JButton("Update other profile");

    public JPanel Update(JFrame window, Connection co , int id, String role) throws SQLException{
        if(role == "admin") {
            d1.add(bupme);
            d1.add(bupother);
            f1.add(d1);
            bupme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d1.remove(bupme);
                    d1.remove(bupother);
                    f1.remove(d1);
                    d1.add(new JLabel("lastname"));
                    d1.add(u1);
                    d2.add(new JLabel("firstname"));
                    d2.add(u2);
                    d3.add(new JLabel("mail"));
                    d3.add(u3);
                    d4.add(new JLabel("phone"));
                    d4.add(u4);
                    d5.add(new JLabel("adress"));
                    d5.add(u5);
                    d6.add(new JLabel("ZIP"));
                    d6.add(u6);
                    d7.add(new JLabel("type (admin, normal)"));
                    d7.add(u7);
                    d8.add(bup);
                    f1.add(d1);
                    f1.add(d2);
                    f1.add(d3);
                    f1.add(d4);
                    f1.add(d5);
                    f1.add(d6);
                    f1.add(d7);
                    f1.add(d8);
                    pscroll.add(f1);
                    window.getContentPane().add(pscroll);
                    window.setVisible(true);
                    String request = "UPDATE personal_user " +
                            "SET user_lastname = '" + u1.getText() + "', user_firstname = '" + u2.getText() + "', user_mail = '" + u3.getText() + "', user_phone = '" + u4.getText() + "', user_adress = '" + u5.getText() + "', user_ZIP = '"+u6.getText()+"', user_type = '"+u7.getText()+"'" +
                            "WHERE user_id = '" + id + "'";
                    try {
                        Statement stm = co.createStatement();
                        stm.executeUpdate(request);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            bupother.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d1.remove(bupme);
                    d1.remove(bupother);
                    f1.remove(d1);
                    i = 0;
                    String request = "SELECT user_id, user_firstname, user_type " +
                            "FROM personal_user " +
                            "WHERE user_type != 'admin'" +
                            " ORDER BY user_id ASC;";
                    try {
                        Statement stm = co.createStatement();
                        ResultSet rslt = stm.executeQuery(request);
                        while (rslt.next()) {
                            s = rslt.getString(2);
                            data.add(s);
                            i++;
                        }
                    }catch(SQLException ex){
                        ex.printStackTrace();
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
                            d1.add(new JLabel("lastname"));
                            d1.add(u1);
                            d2.add(new JLabel("firstname"));
                            d2.add(u2);
                            d3.add(new JLabel("mail"));
                            d3.add(u3);
                            d4.add(new JLabel("phone"));
                            d4.add(u4);
                            d5.add(new JLabel("adress"));
                            d5.add(u5);
                            d6.add(new JLabel("ZIP"));
                            d6.add(u6);
                            d7.add(new JLabel("type (admin, normal)"));
                            d7.add(u7);
                            d8.add(bup);
                            f1.add(d1);
                            f1.add(d2);
                            f1.add(d3);
                            f1.add(d4);
                            f1.add(d5);
                            f1.add(d6);
                            f1.add(d7);
                            f1.add(d8);
                            pscroll.add(f1);
                            window.getContentPane().add(pscroll);
                            window.setVisible(true);
                            bup.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String request = "UPDATE personal_user " +
                                            "SET user_lastname = '" + u1.getText() + "', user_firstname = '" + u2.getText() + "', user_mail = '" + u3.getText() + "', user_phone = '" + u4.getText() + "', user_adress = '" + u5.getText() + "', user_ZIP = '"+u6.getText()+"', user_type = '"+u7.getText()+"'" +
                                            "WHERE user_id = '" + d + "'";
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
                }
            });
        }
        else{
            d1.add(new JLabel("lastname"));
            d1.add(u1);
            d2.add(new JLabel("firstname"));
            d2.add(u2);
            d3.add(new JLabel("mail"));
            d3.add(u3);
            d4.add(new JLabel("phone"));
            d4.add(u4);
            d5.add(new JLabel("adress"));
            d5.add(u5);
            d6.add(new JLabel("ZIP"));
            d6.add(u6);
            d8.add(bup);
            f1.add(d1);
            f1.add(d2);
            f1.add(d3);
            f1.add(d4);
            f1.add(d5);
            f1.add(d6);
            f1.add(d8);
            pscroll.add(f1);
            window.getContentPane().add(pscroll);
            window.setVisible(true);
            String request = "UPDATE personal_user " +
                    "SET user_lastname = '" + u1.getText() + "', user_firstname = '" + u2.getText() + "', user_mail = '" + u3.getText() + "', user_phone = '" + u4.getText() + "', user_adress = '" + u5.getText() + "', user_ZIP = '"+u6.getText()+"'" +
                    "WHERE user_id = '" + id + "'";
            try {
                Statement stm = co.createStatement();
                stm.executeUpdate(request);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        window.setVisible(true);
        return pscroll;
    }
}
