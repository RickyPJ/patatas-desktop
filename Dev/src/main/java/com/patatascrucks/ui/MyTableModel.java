/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.ui;

import com.patatascrucks.db.JpaController;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Ricardo
 */
public class MyTableModel<K, T> extends AbstractTableModel implements TableModel {

    private Class<T> classReference;
    private String[] columnas;
    private List<T> data;

    public MyTableModel() {
        // Fill data from database
        data = new ArrayList<>();
        try (JpaController<K, T> controller = new JpaController<>()) {
            data = controller.findAll();
        } catch (Exception ex) {
            Logger.getLogger(MyTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Fill columns from class
        columnas = new String[classReference.getFields().length];
        int i = 0;
        for (Field field : classReference.getFields()) {
            columnas[i] = field.getName();
            i++;
        }
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnas[col];
    }

    @Override
    public Object getValueAt(int fila, int col) {
        T item = data.get(fila);
        try {
            return item.getClass().getField(columnas[col]).get(item);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int fila, int col) {
        return true;
    }

    @Override
    public void setValueAt(Object valor, int fila, int col) {
        try {
            T item = data.get(fila);
            Field field = item.getClass().getField(columnas[col]);
            field.setAccessible(true);
            field.set(item, valor);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
