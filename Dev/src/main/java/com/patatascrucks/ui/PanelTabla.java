/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.ui;

/**
 *
 * @author Ricardo
 */
public class PanelTabla extends javax.swing.JPanel {

    /**
     * Creates new form PanelTabla
     */
    public PanelTabla() {
        initComponents();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPaneTabla = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        tableProductos.setModel(new MyTableModel());
        tableProductos.setFillsViewportHeight(true);
        tableProductos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPaneTabla.setViewportView(tableProductos);

        add(scrollPaneTabla, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPaneTabla;
    private javax.swing.JTable tableProductos;
    // End of variables declaration//GEN-END:variables
}
