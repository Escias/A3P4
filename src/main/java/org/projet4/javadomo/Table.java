package org.projet4.javadomo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Table {

    public JTable Table(JFrame window, Connection co, String[] t, String req) {
        JTable table = new JTable();
        DefaultTableModel aModel = (DefaultTableModel) table.getModel();
        aModel.setColumnIdentifiers(t);
        JScrollPane jsp = new JScrollPane(table);
        Dimension dimension = new Dimension(500, 300);
        jsp.setPreferredSize(dimension);
        window.add(jsp);
        String request = req;
        try {
            Statement statement = co.createStatement();
            ResultSet results = statement.executeQuery(request);
            ResultSetMetaData meta = results.getMetaData();
            int colCount = meta.getColumnCount();
            while (results.next()) {
                Object[] objects = new Object[colCount];
                for(int i = 0; i < colCount; i++) {
                    objects[i] = results.getObject(i+1);
                }
                aModel.addRow(objects);
            }
            table.setModel(aModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        window.setVisible(true);
        return table;
    }
}
