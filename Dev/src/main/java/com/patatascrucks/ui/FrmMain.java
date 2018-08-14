/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.ui;

import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
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

        barraMenu = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuItemAcerca = new javax.swing.JMenuItem();
        menuItemSalir = new javax.swing.JMenuItem();
        menuInventario = new javax.swing.JMenu();
        menuItemExistencia = new javax.swing.JMenuItem();
        menuItemIngreso = new javax.swing.JMenuItem();
        menuItemEgreso = new javax.swing.JMenuItem();
        menuPerdida = new javax.swing.JMenu();
        menuItemCambio = new javax.swing.JMenuItem();
        menuItemObsequio = new javax.swing.JMenuItem();
        menuVenta = new javax.swing.JMenu();
        menuItemDevolucionAnterior = new javax.swing.JMenuItem();
        menuItemVenta = new javax.swing.JMenuItem();
        menuItemCobro = new javax.swing.JMenuItem();
        menuItemDevolucion = new javax.swing.JMenuItem();
        menuAnulacion = new javax.swing.JMenu();
        menuAdmin = new javax.swing.JMenu();
        menuItemUsuarios = new javax.swing.JMenuItem();
        menuItemSocios = new javax.swing.JMenuItem();
        menuVendedores = new javax.swing.JMenuItem();
        menuItemClientes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Patatas Cruck's");
        setExtendedState(6);
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 750));
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 750));

        barraMenu.setName(""); // NOI18N

        menuArchivo.setText("Archivo");
        menuArchivo.setName(""); // NOI18N

        menuItemAcerca.setText("Acerca de...");
        menuItemAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAcercaActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemAcerca);

        menuItemSalir.setText("Salir");
        menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemSalir);

        barraMenu.add(menuArchivo);

        menuInventario.setText("Inventario");

        menuItemExistencia.setText("Existencia");
        menuInventario.add(menuItemExistencia);

        menuItemIngreso.setText("Ingresos");
        menuInventario.add(menuItemIngreso);

        menuItemEgreso.setText("Egresos");
        menuInventario.add(menuItemEgreso);

        menuPerdida.setText("Perdidas");

        menuItemCambio.setText("Cambios");
        menuPerdida.add(menuItemCambio);

        menuItemObsequio.setText("Obsequios");
        menuPerdida.add(menuItemObsequio);

        menuInventario.add(menuPerdida);

        barraMenu.add(menuInventario);

        menuVenta.setText("Ventas");

        menuItemDevolucionAnterior.setText("Devolucion Anterior");
        menuVenta.add(menuItemDevolucionAnterior);

        menuItemVenta.setText("Nueva Venta");
        menuVenta.add(menuItemVenta);

        menuItemCobro.setText("Cobro");
        menuVenta.add(menuItemCobro);

        menuItemDevolucion.setText("Devolucion");
        menuVenta.add(menuItemDevolucion);

        barraMenu.add(menuVenta);

        menuAnulacion.setText("Anulaciones");
        menuAnulacion.setEnabled(false);
        barraMenu.add(menuAnulacion);

        menuAdmin.setText("Administrar");

        menuItemUsuarios.setText("Usuarios");
        menuItemUsuarios.setEnabled(false);
        menuAdmin.add(menuItemUsuarios);

        menuItemSocios.setText("Socios");
        menuAdmin.add(menuItemSocios);

        menuVendedores.setText("Vendedores");
        menuAdmin.add(menuVendedores);

        menuItemClientes.setText("Clientes");
        menuAdmin.add(menuItemClientes);

        barraMenu.add(menuAdmin);

        setJMenuBar(barraMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAcercaActionPerformed
        JOptionPane.showMessageDialog(this, "PATATAS CRUCK'S\n\nSoftware\n\nDesarrolado por:\nIng. Ricardo Pati\u00f1o\n\nTodos los derechos reservados",
                                "Patatas Cruck's", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_menuItemAcercaActionPerformed

    private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemSalirActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenu menuAdmin;
    private javax.swing.JMenu menuAnulacion;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuInventario;
    private javax.swing.JMenuItem menuItemAcerca;
    private javax.swing.JMenuItem menuItemCambio;
    private javax.swing.JMenuItem menuItemClientes;
    private javax.swing.JMenuItem menuItemCobro;
    private javax.swing.JMenuItem menuItemDevolucion;
    private javax.swing.JMenuItem menuItemDevolucionAnterior;
    private javax.swing.JMenuItem menuItemEgreso;
    private javax.swing.JMenuItem menuItemExistencia;
    private javax.swing.JMenuItem menuItemIngreso;
    private javax.swing.JMenuItem menuItemObsequio;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JMenuItem menuItemSocios;
    private javax.swing.JMenuItem menuItemUsuarios;
    private javax.swing.JMenuItem menuItemVenta;
    private javax.swing.JMenu menuPerdida;
    private javax.swing.JMenuItem menuVendedores;
    private javax.swing.JMenu menuVenta;
    // End of variables declaration//GEN-END:variables
}