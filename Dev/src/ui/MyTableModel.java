/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Ricardo
 */
public class MyTableModel extends AbstractTableModel implements TableModel {

    private final String[] columnas = {"CANTIDAD", "DESCRIPCION", "P. UNITARIO", "TOTAL"};
    private final Object[][] data;

    public MyTableModel(Object[][] data) {
        this.data = data;
        for (int i = 0; i < data.length; i++) {
            if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62)) {
                data[i][0] = "0";
                data[i][3] = "0.00";
            }
        }
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnas[col];
    }

    @Override
    public Object getValueAt(int fila, int col) {
        return data[fila][col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int fila, int col) {
        return (col <= 1) && fila != 0 && fila != 10 && fila != 12 && fila != 20 && fila != 28 && fila != 33 && fila != 37 && fila != 41 && fila != 43 && fila != 46 && fila != 52 && fila != 58 && fila != 62;
    }

    @Override
    public void setValueAt(Object valor, int fila, int col) {
        if (col == 3) {
            data[fila][col] = String.format("%.2f", valor);
            fireTableCellUpdated(fila, col);
        } else {
            data[fila][col] = valor;
            fireTableCellUpdated(fila, col);
        }
    }
}
