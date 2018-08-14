//
//
//
package com.patatascrucks.ui;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import javax.swing.table.*;
import javax.swing.Timer;
import javax.swing.*;
import java.util.*;
import java.util.regex.*;

import com.patatascrucks.util.ResultSetTableModel;

import com.patatascrucks.print.PatatasPrint;

public class PatatasUI extends JFrame {

    private static final String CLAVE = "cruckssystem";

    static final String CONTROLADOR_JDBC = "com.mysql.jdbc.Driver";
    static final String URL_BASEDATOS = "jdbc:mysql://192.168.1.254/patatas";
    static final String USUARIO = "root";
    static final String PASSWORD = "ytsejam";
    static final String CONSULTA_IMPLICITA = "SELECT cantidad, producto, precioUnitario, "
            + "total FROM Productos ORDER BY cod";
    private Connection conexion;
    private Statement sentencia;
    private ResultSetTableModel modelo, modeloTabla, modeloTabla1;
    private JDesktopPane Escritorio;
    private JButton guardar, cancelar, existenciaInicial, botonCuadro;
    private JButton botonAgregar, botonFiltro;
    private JTextField formaPago, textoFiltro;
    private final JTextField version, verfecha, creator, materia;
    private JTextField codSocio, nombreSocio;
    private JTextField codVendedor, nombreVendedor, apellidoVendedor;
    private JTextField anio, mes, dia, anio2, mes2, dia2;
    private JTextField codCliente, nombreCliente, apellidoCliente, idCliente, comCliente, dirCliente, telCliente;
    private JTextField campTotal, campTOT, codFact, codFact2;
    private JTextField pesoPapas, pesoPapas2, pesoPlatano, pesoPlatano2, pesoYuca, pesoYuca2, pesoMani, pesoMani2, pesoSisco, pesoSisco2;
    private JTextField textDesp, textDev, textDesc, textRetCont, textRetCob, textCob, textCont, textCred, textDeuda;
    private JTextField totalDinero, totalVendido, totalEntrega, totalEspera, totalResto;
    private JTable tabla, tablaEncabezado, tablaConsulta;
    private JRadioButton botonCobro, botonCredito;
    private ButtonGroup grupoDeuda;
    private final Calendar fecha;
    private Timer temporizador;
    private final JLabel barraestado, estadoSQL, mensaje;
    private JLabel labelAutorizacion, labelProd1, labelProd2, labelProd3;
    private int codigo = 0, nota = 0, flag = 0;
    private double tDespachos = 0.00, tDevolucion = 0.00, tVentas = 0.00, tVentaContado = 0.00, tVentaCredito = 0.00, tot = 0.00;
    private boolean consulta;
    private String textoImprimir;
    private String total, cSocio, socio;
    private String cVendedor, nVendedor, aVendedor;
    private String cCliente, nCliente, aCliente;
    private String fPago, nombreFecha, nombreFecha2, cFact, id, telefono, comercial, direccion;
    private final double IVA = 0.14;
    private Object[][] data = {{"", "PATATAS", "NATURAL", ""},
    {"0", "0.20", "0.1071", "0.00"},
    {"0", "0.24", "0.1786", "0.00"},
    {"0", "0.28", "0.2143", "0.00"},
    {"0", "0.32", "0.2500", "0.00"},
    {"0", "0.48", "0.3571", "0.00"},
    {"0", "0.80", "0.5357", "0.00"},
    {"0", "1.00", "0.7143", "0.00"},
    {"0", "1.28", "1.0000", "0.00"},
    {"0", "2.00", "1.5625", "0.00"},
    {"", "PATATAS", "MAYONESA", ""},
    {"0", "0.28", "0.2143", "0.00"},
    {"", "PATATAS", "PICANTES", ""},
    {"0", "0.28", "0.2143", "0.00"},
    {"0", "0.32", "0.2500", "0.00"},
    {"0", "0.48", "0.3571", "0.00"},
    {"0", "0.80", "0.5357", "0.00"},
    {"0", "1.00", "0.7143", "0.00"},
    {"0", "1.28", "1.0000", "0.00"},
    {"0", "2.00", "1.5625", "0.00"},
    {"", "PATATAS", "CEBOLLAS", ""},
    {"0", "0.28", "0.2143", "0.00"},
    {"0", "0.32", "0.2500", "0.00"},
    {"0", "0.48", "0.3571", "0.00"},
    {"0", "0.80", "0.5357", "0.00"},
    {"0", "1.00", "0.7143", "0.00"},
    {"0", "1.28", "1.0000", "0.00"},
    {"0", "2.00", "1.5625", "0.00"},
    {"", "PATATAS", "PICADAS", ""},
    {"0", "0.20", "0.1786", "0.00"},
    {"0", "0.40", "0.3571", "0.00"},
    {"0", "0.84", "0.7500", "0.00"},
    {"0", "1.60", "1.4286", "0.00"},
    {"", "PATACON", "SAL", ""},
    {"0", "0.32", "0.2500", "0.00"},
    {"0", "0.60", "0.5357", "0.00"},
    {"0", "1.00", "0.8929", "0.00"},
    {"", "PATACON", "DULCE", ""},
    {"0", "0.32", "0.2500", "0.00"},
    {"0", "0.60", "0.5357", "0.00"},
    {"0", "1.00", "0.8929", "0.00"},
    {"", "PATACON", "PICANTE", ""},
    {"0", "0.32", "0.2500", "0.00"},
    {"", "TUQUITAS", "", ""},
    {"0", "0.32", "0.2500", "0.00"},
    {"0", "1.00", "0.8929", "0.00"},
    {"", "MANI SAL", "", ""},
    {"0", "0.12", "0.1071", "0.00"},
    {"0", "0.25", "0.2232", "0.00"},
    {"0", "0.50", "0.4464", "0.00"},
    {"0", "0.95", "0.8482", "0.00"},
    {"0", "1.90", "1.6964", "0.00"},
    {"", "MANI DULCE", "", ""},
    {"0", "0.12", "0.1071", "0.00"},
    {"0", "0.25", "0.2232", "0.00"},
    {"0", "0.50", "0.4464", "0.00"},
    {"0", "0.95", "0.8482", "0.00"},
    {"0", "1.90", "1.6964", "0.00"},
    {"", "TOSTADO", "", ""},
    {"0", "0.24", "0.2143", "0.00"},
    {"0", "0.50", "0.4464", "0.00"},
    {"0", "0.90", "0.8036", "0.00"},
    {"", "SISCO", "", ""},
    {"0", "2.00", "1.3393", "0.00"}
    };
    private int fact;
    private double[] prod = new double[data.length];
    private String consultarInv = "", consultarVend = "", consultarClient = "";
    private final JProgressBar progresoSQL;

    public PatatasUI() {
        super("Patatas DataBase");

        try {
            modelo = new ResultSetTableModel(CONTROLADOR_JDBC, URL_BASEDATOS, USUARIO, PASSWORD,
                    CONSULTA_IMPLICITA);
            modeloTabla = new ResultSetTableModel(CONTROLADOR_JDBC, URL_BASEDATOS, USUARIO, PASSWORD,
                    CONSULTA_IMPLICITA);
            modeloTabla1 = new ResultSetTableModel(CONTROLADOR_JDBC, URL_BASEDATOS, USUARIO, PASSWORD,
                    CONSULTA_IMPLICITA);
            conexion = modeloTabla.getConnection();

            modeloTabla.establecerConsulta("SELECT producto, precioUnitario FROM Productos ORDER BY cod");
            
            int row = 0;
            for (int i = 0; i < data.length; i++) {
                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62)) {
                    data[i][1] = String.format(Locale.UK, "%.2f", modeloTabla.getValueAt(row, 1));
                    data[i][2] = String.format(Locale.UK, "%.4f", Double.parseDouble((String) data[i][1]) / (1 + IVA));
                    data[i][3] = "0.00";
                    ++row;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC");
            JOptionPane.showMessageDialog(PatatasUI.this, "Error al cargar el controlador JDBC");
            System.exit(1);
        } catch (SQLException e) {
            System.err.println("Incapaz de conectarse");
            JOptionPane.showMessageDialog(PatatasUI.this, "Incapaz de conectarse");
            System.exit(1);
        }

        fecha = Calendar.getInstance();
        setLayout(new BorderLayout());

        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setMnemonic('A');

        JMenuItem opcionAcerca = new JMenuItem("Acerca de...");
        opcionAcerca.setMnemonic('c');
        opcionAcerca.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        JOptionPane.showMessageDialog(PatatasUI.this, "PATATAS DATABASE\n\nIngenieria Electronica\n\nDesarrolado por:\nRicardo Pati\u00f1o\n\nTodos los derechos reservados",
                                "Acerca de", JOptionPane.PLAIN_MESSAGE);
                    }
                }
        );
        menuArchivo.add(opcionAcerca);

        JMenuItem opcionSalir = new JMenuItem("Salir");
        opcionSalir.setMnemonic('S');
        opcionSalir.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        System.exit(0);
                    }
                }
        );
        menuArchivo.add(opcionSalir);

        JMenuBar barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
        barraMenu.add(menuArchivo);

        JMenu menuAgregarInventario = new JMenu("Inventario");
        JMenuItem existencia = new JMenuItem("Existencia Inicial");
        JMenuItem nuevoIngreso = new JMenuItem("Ingresos");
        JMenuItem nuevoEgreso = new JMenuItem("Egresos");
        JMenu perd = new JMenu("Perdidas");
        JMenuItem nuevoObsequio = new JMenuItem("Obsequios");
        JMenuItem nuevoCambio = new JMenuItem("Cambios");

        menuAgregarInventario.add(existencia);
        menuAgregarInventario.add(nuevoIngreso);
        menuAgregarInventario.add(nuevoEgreso);
        perd.add(nuevoObsequio);
        perd.add(nuevoCambio);
        menuAgregarInventario.add(perd);
        barraMenu.add(menuAgregarInventario);

        JMenu menuAgregarVentas = new JMenu("Ventas");
        JMenuItem nuevoInicial = new JMenuItem("Devolucion Anterior");
        JMenuItem nuevoContado = new JMenuItem("Ventas");
        JMenuItem nuevaDevolucion = new JMenuItem("Devolucion");
        JMenuItem nuevoCredito = new JMenuItem("Cobros");
        JMenu saldos = new JMenu("Saldos");
        JMenuItem contados = new JMenuItem("Total Ventas Contado");
        JMenuItem creditos = new JMenuItem("Total Ventas Credito");
        JMenuItem cobros = new JMenuItem("Total Cobros");
        JMenuItem cuadro = new JMenuItem("Cuadro Creditos");

        menuAgregarVentas.add(nuevoInicial);
        menuAgregarVentas.add(nuevoContado);
        menuAgregarVentas.add(nuevaDevolucion);
        menuAgregarVentas.add(nuevoCredito);
        saldos.add(contados);
        saldos.add(creditos);
        saldos.add(cobros);
        saldos.add(cuadro);
        menuAgregarVentas.add(saldos);
        barraMenu.add(menuAgregarVentas);

        JMenu nuevoAnulaciones = new JMenu("Anulaciones");
        JMenuItem anularProduccion = new JMenuItem("Produccion");
        JMenuItem anularDespacho = new JMenuItem("Despachos");
        JMenuItem anularDevolucion = new JMenuItem("Devoluciones");
        JMenuItem anularVenta = new JMenuItem("Ventas");
        JMenuItem anularObsequio = new JMenuItem("Obsequios");
        JMenuItem anularCambio = new JMenuItem("Cambios");
        JMenu anularSaldos = new JMenu("Saldos");
        JMenuItem anularContados = new JMenuItem("Total Contado");
        JMenuItem anularCreditos = new JMenuItem("Total Credito");
        JMenuItem anularCobros = new JMenuItem("Cobros");

        nuevoAnulaciones.add(anularProduccion);
        nuevoAnulaciones.add(anularDespacho);
        nuevoAnulaciones.add(anularDevolucion);
        nuevoAnulaciones.add(anularVenta);
        nuevoAnulaciones.add(anularObsequio);
        nuevoAnulaciones.add(anularCambio);
        anularSaldos.add(anularContados);
        anularSaldos.add(anularCreditos);
        anularSaldos.add(anularCobros);
        nuevoAnulaciones.add(anularSaldos);
        barraMenu.add(nuevoAnulaciones);

        JMenu menuAgregarNuevo = new JMenu("Agregar/Editar/Eliminar");
        JMenu agV = new JMenu("Vendedor");
        JMenuItem agregarVendedor = new JMenuItem("Nuevo");
        JMenuItem editarVendedor = new JMenuItem("Editar");
        JMenuItem eliminarVendedor = new JMenuItem("Eliminar");
        JMenu agC = new JMenu("Cliente");
        JMenuItem agregarCliente = new JMenuItem("Nuevo");
        JMenuItem editarCliente = new JMenuItem("Editar");
        JMenuItem eliminarCliente = new JMenuItem("Eliminar");

        menuAgregarNuevo.add(agV);
        agV.add(agregarVendedor);
        agV.add(editarVendedor);
        agV.add(eliminarVendedor);
        menuAgregarNuevo.add(agC);
        agC.add(agregarCliente);
        agC.add(editarCliente);
        agC.add(eliminarCliente);
        barraMenu.add(menuAgregarNuevo);

        JMenu menuAgregarConsultas = new JMenu("Consultas");
        JMenu nuevoInventario = new JMenu("Inventario");
        JMenuItem menuInventario = new JMenuItem("Existencia");
        JMenu producciones = new JMenu("Produccion");
        JMenuItem menuProduccion = new JMenuItem("Produccion");
        JMenuItem totalProduccion = new JMenuItem("Produccion Socios");
        JMenuItem TProduccion = new JMenuItem("Produccion Total");
        JMenuItem frame = new JMenuItem("Despachos");
        JMenu nuevoPerdidas = new JMenu("Perdidas");
        JMenu cambios = new JMenu("Cambios");
        JMenuItem menuCambios = new JMenuItem("Diario");
        JMenuItem totalCambios = new JMenuItem("Total");
        JMenu obsequios = new JMenu("Obsequios");
        JMenuItem menuObsequios = new JMenuItem("Diario");
        JMenuItem totalObsequios = new JMenuItem("Total");
        JMenu nuevoVentas = new JMenu("Ventas");
        JMenuItem menuContado = new JMenuItem("Contado");
        JMenu Credito = new JMenu("Creditos");
        JMenuItem menuCredito = new JMenuItem("Total");
        JMenuItem menuCredVend = new JMenuItem("Vendedores");
        JMenuItem menuCuadro = new JMenuItem("Diario");
        JMenu totVentas = new JMenu("Total Ventas");
        JMenuItem menuVentas = new JMenuItem("Por Vendedor");
        JMenuItem menuTotalVentas = new JMenuItem("Todos");
        JMenuItem nuevoClientes = new JMenuItem("Clientes");
        JMenu evaluacion = new JMenu("Evaluaciones");
        JMenuItem evaluarProd = new JMenuItem("Produccion");
        JMenuItem evaluarSocios = new JMenuItem("Socios");
        JMenuItem evaluarVendedores = new JMenuItem("Vendedores");
        JMenuItem evaluarClientes = new JMenuItem("Clientes");
        /*JMenu menuAnulaciones = new JMenu("Anulaciones");
         JMenuItem prodAnulada = new JMenuItem("Produccion");
         JMenuItem despAnulado = new JMenuItem("Despachos");
         JMenuItem ventaAnulada = new JMenuItem("Ventas");
         JMenuItem clienteAnulado = new JMenuItem("Clientes");*/

        nuevoInventario.add(menuInventario);
        producciones.add(menuProduccion);
        producciones.add(totalProduccion);
        producciones.add(TProduccion);
        nuevoInventario.add(producciones);
        nuevoInventario.add(frame);
        menuAgregarConsultas.add(nuevoInventario);
        cambios.add(menuCambios);
        cambios.add(totalCambios);
        nuevoPerdidas.add(cambios);
        obsequios.add(menuObsequios);
        obsequios.add(totalObsequios);
        nuevoPerdidas.add(obsequios);
        menuAgregarConsultas.add(nuevoPerdidas);
        nuevoVentas.add(menuContado);
        Credito.add(menuCredito);
        Credito.add(menuCredVend);
        nuevoVentas.add(Credito);
        nuevoVentas.add(menuCuadro);
        totVentas.add(menuVentas);
        totVentas.add(menuTotalVentas);
        nuevoVentas.add(totVentas);
        menuAgregarConsultas.add(nuevoVentas);
        menuAgregarConsultas.add(nuevoClientes);
        /*menuAnulaciones.add(prodAnulada);
         menuAnulaciones.add(despAnulado);
         menuAnulaciones.add(ventaAnulada);
         menuAnulaciones.add(clienteAnulado);
         menuAgregarConsultas.add(menuAnulaciones);*/
        barraMenu.add(menuAgregarConsultas);
        evaluacion.add(evaluarProd);
        evaluacion.add(evaluarSocios);
        evaluacion.add(evaluarVendedores);
        evaluacion.add(evaluarClientes);
        barraMenu.add(evaluacion);

        Escritorio = new JDesktopPane();
        add(Escritorio, BorderLayout.CENTER);
///*********************************************************************
      /*existencia.addActionListener(
         new ActionListener()
         {
         public void actionPerformed(ActionEvent evento)
         {
         consulta = false;
         flag = 1;
         JPanel panelClave = new JPanel(new GridLayout(2, 1));
         panelClave.add(new JLabel("Ingrese la contrase�a:"));
         JPasswordField passw = new JPasswordField();
         panelClave.add(passw);
			
         JOptionPane.showMessageDialog(null, panelClave, "Administrador", JOptionPane.QUESTION_MESSAGE);
         if (String.valueOf(passw.getPassword()).equals(CLAVE)){
         final JInternalFrame frame = new JInternalFrame("Existencia Inicial", false, false, false, false);
               
         MiJPanel panel = new MiJPanel();     			
         frame.add(panel, BorderLayout.CENTER); Escritorio.add(frame, BorderLayout.CENTER); frame.setVisible(true);
         frame.setMaximum(true);           	
               
                        	
                        
               
         codSocio.setEditable(false);
         guardar.setEnabled(true);
               
         guardar.addActionListener(
         new ActionListener()
         {
         public void actionPerformed(ActionEvent evento)
         {
         guardar.setEnabled(false);
         nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
         int cod = 0;
         int cant;
         int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
         if (q == JOptionPane.YES_OPTION)
         {
         //mensaje.setText("Espere mientras se guardan los datos");
         try
         {
         sentencia = conexion.createStatement();
         for (int i = 0; i < data.length; i++)
         {
         if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64))
         {
         ++cod;
         cant = Integer.parseInt((String)tabla.getValueAt(i, 0));
         sentencia.executeUpdate("INSERT INTO Existencia VALUES ('" + nombreFecha + "', '"
         + cod + "', '" + cant + "', '" 
         + prod[i] + "')");
         //progresoSQL.setValue(cod * 100 / 52);
         //estadoSQL.setText(String.format("Completado el %d%%.\n", (cod * 100 / 52)));
         }
         }
         JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
         frame.dispose();
         }
         catch (SQLException ee)
         {	
         JOptionPane.showMessageDialog(null, ee.getMessage());
         frame.dispose();
         }
         }
         //mensaje.setText("");
         //progresoSQL.setValue(0);
         //estadoSQL.setText("");
         }
         }
         );
					
         cancelar.addActionListener(
         new ActionListener()
         {
         public void actionPerformed(ActionEvent evento)
         {
         int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?","Salir", JOptionPane.YES_NO_OPTION);
         if (q == JOptionPane.YES_OPTION)
         frame.dispose();
         }
         }
         );
         }else JOptionPane.showMessageDialog(null, "Contrase�a incorrecta!", "ERROR", JOptionPane.ERROR_MESSAGE);
					
         guardar.requestFocus();
				
         } 
         }
         );*/

        nuevoIngreso.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 1;
                        final JInternalFrame frame = new JInternalFrame("Produccion", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        tot = Double.parseDouble(campTOT.getText());
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            //mensaje.setText("Espere mientras se guardan los datos");
                                            try {
                                                sentencia = conexion.createStatement();
                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = Integer.parseInt((String) tabla.getValueAt(i, 0));
                                                        sentencia.executeUpdate("INSERT INTO ProdFecha VALUES ('" + nombreFecha + "', '"
                                                                + cod + "', '" + codSocio.getText() + "', '" + cant + "', '"
                                                                + prod[i] + "', '" + pesoPapas.getText() + "', '"
                                                                + pesoPlatano.getText() + "', '" + pesoYuca.getText() + "')");

                                                        //progresoSQL.setValue(cod * 100 / 52);
                                                        //estadoSQL.setText(String.format("Completado el %d%%.\n", (cod * 100 / 52)));
                                                    }
                                                }

                                                modeloTabla.establecerConsulta("SELECT produccion FROM Socios WHERE codSocio = '" + codSocio.getText() + "'");
                                                tot += (Double) modeloTabla.getValueAt(0, 0);
                                                sentencia = conexion.createStatement();
                                                sentencia.executeUpdate("UPDATE Socios SET produccion = '" + tot + "' "
                                                        + "WHERE codSocio = '" + codSocio.getText() + "'");
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                        //mensaje.setText("");
                                        //progresoSQL.setValue(0);
                                        //estadoSQL.setText("");
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();

                    }
                }
        );

        nuevoEgreso.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Despacho", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        codigo = 0;
                        try {
                            modeloTabla.establecerConsulta("SELECT cDespacho FROM Codigos");
                            codigo = (Integer) modeloTabla.getValueAt(0, 0);
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        cFact = String.valueOf(codigo);
                        codFact.setText("d");
                        codFact.setEditable(true);
                        codFact2.setText(cFact);
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();
                        tabla.setEnabled(false);

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        aVendedor = apellidoVendedor.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        textoImprimir = "";
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                ++codigo;
                                                if (cVendedor.equals("000")) {
                                                    nVendedor = String.valueOf(JOptionPane.showInputDialog(frame, "Cliente:",
                                                                    "Venta de Oficina", JOptionPane.INFORMATION_MESSAGE));
                                                }
                                                sentencia = conexion.createStatement();
                                                sentencia.executeUpdate("UPDATE Codigos SET cDespacho = '" + codigo
                                                        + "' WHERE cDespacho = '" + (codigo - 1) + "'");

                                                textoImprimir += String.format("Vendedor: %s %-12s\nFecha:    %s\nDespacho: %s\n\n", cVendedor, nVendedor, nombreFecha, codFact2.getText());

                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = Integer.parseInt((String) tabla.getValueAt(i, 0));
                                                        sentencia.executeUpdate("INSERT INTO DespFecha VALUES ('" + nombreFecha + "', 'd-"
                                                                + codFact2.getText() + "', '" + cVendedor + "', '"
                                                                + cod + "', '" + cant + "', '" + prod[i] + "')");
                                                        if (cant != 0) {
                                                            textoImprimir += String.format("%5s %10s %10s\n", (String) tabla.getValueAt(i, 0), (String) tabla.getValueAt(i, 1),
                                                                    (String) tabla.getValueAt(i, 3));
                                                        }
                                                    } else {
                                                        textoImprimir += String.format("%10s %s\n", (String) tabla.getValueAt(i, 1), (String) tabla.getValueAt(i, 2));
                                                    }
                                                }
                                                sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tDespachos) "
                                                        + "VALUES ('" + nombreFecha + "', 'd-" + codFact2.getText() + "', '" + cVendedor
                                                        + "', '" + tDespachos + "')");

                                                textoImprimir += String.format("%16s %10s\n", "TOTAL:", campTOT.getText());
                                                int p = JOptionPane.showConfirmDialog(frame, "Desea imprimir?", "Imprimir", JOptionPane.YES_NO_OPTION);
                                                if (p == JOptionPane.YES_OPTION) {
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                PatatasPrint imprimir;
                                                                //do
                                                                //{
                                                                imprimir = new PatatasPrint(textoImprimir);
                                                                //} while(!imprimir.wasPrinted());
													/*tabla.print(JTable.PrintMode.FIT_WIDTH,
                                                                 new MessageFormat(MessageFormat.format("Vendedor: {0} {1}\nFecha: {2}\nDespacho: {3}", cVendedor, nVendedor, nombreFecha, codFact2.getText())),
                                                                 new MessageFormat(MessageFormat.format("Total: {0, number, double}", tDespachos)));*/
                                                            } catch (Exception pe) {
                                                                JOptionPane.showMessageDialog(null, "Error al imprimir");
                                                            }
                                                        }
                                                    }).start();
                                                }
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (HeadlessException | NumberFormatException | SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        nuevoObsequio.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Obsequios", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        labelAutorizacion = new JLabel("Autorizado por: ", SwingConstants.CENTER);
                        frame.add(labelAutorizacion, BorderLayout.SOUTH);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        codigo = 0;
                        try {
                            modeloTabla.establecerConsulta("SELECT cObsequio FROM Codigos");
                            codigo = (Integer) modeloTabla.getValueAt(0, 0);
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        cFact = String.valueOf(codigo);
                        codFact.setText("ob");
                        codFact2.setText(cFact);
                        codFact2.setEditable(true);

                        codVendedor.requestFocus();

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        textoImprimir = "";
                                        int cod = 0;
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        int cant;
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        double obsequio = Double.parseDouble(campTOT.getText());
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                final String autoriza = String.valueOf(JOptionPane.showInputDialog(frame, "Autorizado por:",
                                                                "Autorizacion", JOptionPane.INFORMATION_MESSAGE));
                                                textoImprimir += String.format("Vendedor: %s %-12s\nFecha:    %s\nObsequio: %s   Autoriza: %s\n\n", cVendedor, nVendedor, nombreFecha, codFact2.getText(), autoriza);
                                                sentencia = conexion.createStatement();
                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = Integer.parseInt((String) tabla.getValueAt(i, 0));
                                                        sentencia.executeUpdate("INSERT INTO ObsequiosFecha VALUES ('" + nombreFecha + "', '" + cod + "', '"
                                                                + cVendedor + "', '" + cant
                                                                + "', '" + prod[i] + "', '" + autoriza + "', 'ob-"
                                                                + codFact2.getText() + "')");
                                                        if (cant != 0) {
                                                            textoImprimir += String.format("%5s %10s %10s\n", (String) tabla.getValueAt(i, 0), (String) tabla.getValueAt(i, 1),
                                                                    (String) tabla.getValueAt(i, 3));
                                                        }
                                                    } else {
                                                        textoImprimir += String.format("%10s %s\n", (String) tabla.getValueAt(i, 1), (String) tabla.getValueAt(i, 2));
                                                    }
                                                }
                                                textoImprimir += String.format("%16s %10s\n", "TOTAL:", campTOT.getText());
                                                int p = JOptionPane.showConfirmDialog(frame, "Desea imprimir?", "Imprimir", JOptionPane.YES_NO_OPTION);
                                                if (p == JOptionPane.YES_OPTION) {
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                PatatasPrint imprimir;
                                                                //do
                                                                //{
                                                                imprimir = new PatatasPrint(textoImprimir);
                                                                //} while(!imprimir.wasPrinted());
													/*tabla.print(JTable.PrintMode.FIT_WIDTH,
                                                                 new MessageFormat(MessageFormat.format("Vendedor: {0} {1}\nFecha: {2}\nDespacho: {3}", cVendedor, nVendedor, nombreFecha, codFact2.getText())),
                                                                 new MessageFormat(MessageFormat.format("Total: {0, number, double}", tDespachos)));*/
                                                            } catch (Exception pe) {
                                                                JOptionPane.showMessageDialog(null, "Error al imprimir");
                                                            }
                                                        }
                                                    }).start();
                                                }
                                                sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tObsequio) "
                                                        + "VALUES ('" + nombreFecha + "', 'ob-" + codFact2.getText() + "', '" + cVendedor
                                                        + "', '" + obsequio + "')");
                                                sentencia.executeUpdate("UPDATE Codigos SET cObsequio = '" + Integer.parseInt(codFact2.getText()) + "'");
                                                labelAutorizacion.setText("Autorizado por: " + autoriza);
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                                frame.dispose();
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                        guardar.requestFocus();
                    }
                }
        );

        nuevoCambio.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Cambios", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        labelAutorizacion = new JLabel("Autorizado por: ", SwingConstants.CENTER);
                        frame.add(labelAutorizacion, BorderLayout.SOUTH);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        codigo = 0;
                        try {
                            modeloTabla.establecerConsulta("SELECT cCambio FROM Codigos");
                            codigo = (Integer) modeloTabla.getValueAt(0, 0);
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        cFact = String.valueOf(codigo);
                        codFact.setText("camb");
                        codFact2.setText(cFact);
                        codFact2.setEditable(true);

                        codVendedor.requestFocus();

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        textoImprimir = "";
                                        int cod = 0;
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        int cant;
                                        double cambio = Double.parseDouble(campTOT.getText());
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                final String autoriza = String.valueOf(JOptionPane.showInputDialog(frame, "Autorizado por:",
                                                                "Autorizacion", JOptionPane.INFORMATION_MESSAGE));
                                                textoImprimir += String.format("Vendedor: %s %-12s\nFecha:    %s\nCambio: %s   Autoriza: %s\n\n", cVendedor, nVendedor, nombreFecha, codFact2.getText(), autoriza);
                                                sentencia = conexion.createStatement();
                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = Integer.parseInt((String) tabla.getValueAt(i, 0));
                                                        sentencia.executeUpdate("INSERT INTO CambiosFecha VALUES ('" + nombreFecha + "', '" + cod + "', '"
                                                                + cVendedor + "', '" + cant
                                                                + "', '" + prod[i] + "', '" + autoriza + "', 'camb-"
                                                                + codFact2.getText() + "')");
                                                        if (cant != 0) {
                                                            textoImprimir += String.format("%5s %10s %10s\n", (String) tabla.getValueAt(i, 0), (String) tabla.getValueAt(i, 1),
                                                                    (String) tabla.getValueAt(i, 3));
                                                        }
                                                    } else {
                                                        textoImprimir += String.format("%10s %s\n", (String) tabla.getValueAt(i, 1), (String) tabla.getValueAt(i, 2));
                                                    }
                                                }
                                                textoImprimir += String.format("%16s %10s\n", "TOTAL:", campTOT.getText());
                                                int p = JOptionPane.showConfirmDialog(frame, "Desea imprimir?", "Imprimir", JOptionPane.YES_NO_OPTION);
                                                if (p == JOptionPane.YES_OPTION) {
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                PatatasPrint imprimir;
                                                                //do
                                                                //{
                                                                imprimir = new PatatasPrint(textoImprimir);
                                                                //} while(!imprimir.wasPrinted());
													/*tabla.print(JTable.PrintMode.FIT_WIDTH,
                                                                 new MessageFormat(MessageFormat.format("Vendedor: {0} {1}\nFecha: {2}\nDespacho: {3}", cVendedor, nVendedor, nombreFecha, codFact2.getText())),
                                                                 new MessageFormat(MessageFormat.format("Total: {0, number, double}", tDespachos)));*/
                                                            } catch (Exception pe) {
                                                                JOptionPane.showMessageDialog(null, "Error al imprimir");
                                                            }
                                                        }
                                                    }).start();
                                                }
                                                sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tCambio) "
                                                        + "VALUES ('" + nombreFecha + "', 'camb-" + codFact2.getText() + "', '" + cVendedor
                                                        + "', '" + cambio + "')");
                                                sentencia.executeUpdate("UPDATE Codigos SET cCambio = '" + Integer.parseInt(codFact2.getText()) + "'");
                                                labelAutorizacion.setText("Autorizado por: " + autoriza);
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                                frame.dispose();
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                        guardar.requestFocus();
                    }
                }
        );

///*********************************************************************      
        nuevoInicial.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Devolucion Anterior", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        codigo = 0;
                        try {
                            modeloTabla.establecerConsulta("SELECT cDespacho FROM Codigos");
                            codigo = (Integer) modeloTabla.getValueAt(0, 0);
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        cFact = String.valueOf(codigo);
                        codFact.setText("devA");
                        codFact2.setText(cFact);
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        int cod = 0;
                                        tot = 0.00;

                                        try {
                                            modeloTabla.establecerConsulta("SELECT cantidad, total FROM DevFecha WHERE "
                                                    + "codigoVendedor = '" + cVendedor + "' AND "
                                                    + "codDevolucion = 'dev-" + cFact + "'");
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    tabla.setValueAt((Integer) modeloTabla.getValueAt(cod - 1, 0), i, 0);
                                                }
                                            }
                                            cod = 0;
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    prod[i] = (Double) modeloTabla.getValueAt(cod - 1, 1);
                                                    tot += prod[i];
                                                    tabla.setValueAt(prod[i], i, 3);
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                            guardar.setEnabled(true);
                                        } catch (IllegalStateException | SQLException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                ++codigo;
                                                sentencia = conexion.createStatement();
                                                sentencia.executeUpdate("UPDATE Codigos SET cDespacho = '" + codigo
                                                        + "' WHERE cDespacho = '" + (codigo - 1) + "'");
                                                sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tDespachos) "
                                                        + "VALUES ('" + nombreFecha + "', 'devA-" + cFact + "', '" + cVendedor
                                                        + "', '" + tDespachos + "')");
                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = (Integer) tabla.getValueAt(i, 0);
                                                        sentencia.executeUpdate("INSERT INTO DevAFecha VALUES ('" + nombreFecha + "', 'devA-"
                                                                + cFact + "', '" + cVendedor + "', '"
                                                                + cod + "', '" + cant + "', '" + prod[i] + "')");
                                                    }
                                                }
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (HeadlessException | SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        nuevoContado.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 3;
                        final JInternalFrame frame = new JInternalFrame("Ventas", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        codigo = 0;
                        try {
                            modeloTabla.establecerConsulta("SELECT cVenta FROM Codigos");
                            codigo = (Integer) modeloTabla.getValueAt(0, 0);
                            modeloTabla.establecerConsulta("SELECT cNota FROM Codigos");
                            nota = (Integer) modeloTabla.getValueAt(0, 0);
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        final String[] trans = new String[] {"Nota de Venta", "Factura"};
                        int f = JOptionPane.showOptionDialog(frame, "Seleccione el tipo de registro para la transaccion.", "Transaccion",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, trans, null);
                        if (f == JOptionPane.YES_OPTION) {
                            cFact = String.valueOf(nota);
                            codFact.setText("nota");
                            codFact2.setText(cFact);
                        } else {
                            cFact = String.valueOf(codigo);
                            codFact.setText("fact");
                            codFact2.setText(cFact);
                        }
                        tabla.setEnabled(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();
                        codFact2.setEditable(true);

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        int cod = 0;
                                        int cant;
                                        int desc;
                                        double TOTAL = Double.parseDouble(campTOT.getText());
                                        int op = JOptionPane.showConfirmDialog(frame, "Aplica Descuento?", "Confirmar Descuento", JOptionPane.YES_NO_OPTION);
                                        if (op == JOptionPane.YES_OPTION) {
                                            desc = Integer.parseInt(JOptionPane.showInputDialog(frame, "Descuento del:",
                                                            "Descuento",
                                                            JOptionPane.INFORMATION_MESSAGE));
                                        } else {
                                            desc = 0;
                                        }
                                        campTOT.setText(String.format(Locale.UK, "%.2f", (TOTAL - TOTAL * desc / 100)));
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                sentencia = conexion.createStatement();
                                                if (desc != 0) {
                                                    sentencia.executeUpdate("INSERT INTO TVentasFecha (fecha, cod, codigoVendedor, tDescuentos)"
                                                            + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-" + codFact2.getText()
                                                            + "', '" + cVendedor + "', '" + (TOTAL * desc / 100) + "')");
                                                }
                                                if (codFact.getText().equals("fact")) {
                                                    ++codigo;
                                                    sentencia.executeUpdate("UPDATE Codigos SET cVenta = '" + codigo + "'");
                                                } else {
                                                    ++nota;
                                                    sentencia.executeUpdate("UPDATE Codigos SET cNota = '" + nota + "'");
                                                }

                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = Integer.parseInt((String) tabla.getValueAt(i, 0));
                                                        sentencia.executeUpdate("INSERT INTO VentaFecha VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-"
                                                                + codFact2.getText() + "', '" + cVendedor + "', '" + cCliente + "', '"
                                                                + formaPago.getText() + "', '" + cod + "', '" + cant + "', '"
                                                                + prod[i] + "', '0')");
                                                    }
                                                }
                                                switch (formaPago.getText()) {
                                                    case "Credito":
                                                        tVentaCredito = Double.parseDouble(campTOT.getText());
                                                        modeloTabla.establecerConsulta("SELECT facturas, deudaCliente FROM CLIENTES "
                                                                + "WHERE codCliente = '" + cCliente + "'");
                                                        fact = (Integer) modeloTabla.getValueAt(0, 0);
                                                        ++fact;
                                                        sentencia = conexion.createStatement();
                                                        sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tVentasCredito) "
                                                                + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-" + codFact2.getText()
                                                                + "', '" + cVendedor + "', '" + tVentaCredito + "')");
                                                        sentencia.executeUpdate("UPDATE Clientes SET facturas = '" + fact + "' "
                                                                + "WHERE codCliente = '" + cCliente + "'");
                                                        tVentaCredito += (Double) modeloTabla.getValueAt(0, 1);
                                                        sentencia.executeUpdate("UPDATE Clientes SET deudaCliente = '" + tVentaCredito + "' "
                                                                + "WHERE codCliente = '" + cCliente + "'");
                                                        break;
                                                    case "Contado":
                                                        double ret = 0.00;
                                                        if (codFact.getText().equals("fact")) {
                                                            int opr = JOptionPane.showConfirmDialog(frame, "Aplica Retencion?", "Retencion", JOptionPane.YES_NO_OPTION);
                                                            if (opr == JOptionPane.YES_OPTION) {
                                                                ret = Double.parseDouble(JOptionPane.showInputDialog(frame, "Retencion de:",
                                                                        "Retencion",
                                                                        JOptionPane.INFORMATION_MESSAGE));
                                                            } else {
                                                                ret = 0;
                                                            }
                                                            if (ret != 0) {
                                                                sentencia.executeUpdate("INSERT INTO TVentasFecha (fecha, cod, codigoVendedor, tRetContado)"
                                                                        + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-" + cFact
                                                                        + "', '" + cVendedor + "', '" + ret + "')");
                                                            }
                                                        }   total = campTOT.getText();
                                                    tVentaContado = Double.parseDouble(total);
                                                        campTOT.setText(String.format(Locale.UK, "%.2f", tVentaContado - ret));
                                                        sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tVentasContado) "
                                                                + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-" + codFact2.getText()
                                                                + "', '" + cVendedor + "', '" + tVentaContado + "')");
                                                        modeloTabla.establecerConsulta("SELECT compras FROM Clientes "
                                                                + "WHERE codigoVendedor = '" + cVendedor + "' "
                                                                + "AND codCliente = '" + cCliente + "'");
                                                        tVentaContado += (Double) modeloTabla.getValueAt(0, 0);
                                                        sentencia.executeUpdate("UPDATE Clientes SET compras = '" + tVentaContado + "' "
                                                                + "WHERE codigoVendedor = '" + cVendedor + "' "
                                                                + "AND codCliente = '" + cCliente + "'");
                                                        break;
                                                }
                                                tVentas = Double.parseDouble(campTOT.getText());
                                                modeloTabla.establecerConsulta("SELECT ventas FROM Vendedores WHERE "
                                                        + "codigoVendedor = '" + cVendedor + "'");
                                                tVentas += (Double) modeloTabla.getValueAt(0, 0);
                                                sentencia.executeUpdate("UPDATE Vendedores SET ventas = '" + tVentas + "' "
                                                        + "WHERE codigoVendedor = '" + cVendedor + "'");
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (SQLException | NumberFormatException | IllegalStateException | HeadlessException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        contados.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Ventas al Contado", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        codigo = 0;
                        try {
                            modeloTabla.establecerConsulta("SELECT cNota FROM Codigos");
                            codigo = (Integer) modeloTabla.getValueAt(0, 0);
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        cFact = String.valueOf(codigo);
                        codFact.setText("nota");
                        codFact2.setText(cFact);
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();
                        tabla.setEnabled(false);
                        campTOT.setEditable(true);

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        aVendedor = apellidoVendedor.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                ++codigo;
                                                sentencia = conexion.createStatement();
                                                sentencia.executeUpdate("UPDATE Codigos SET cNota = '" + codigo
                                                        + "' WHERE cNota = '" + (codigo - 1) + "'");
                                                sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tVentasContado) "
                                                        + "VALUES ('" + nombreFecha + "', 'nota-" + codFact2.getText() + "', '" + cVendedor
                                                        + "', '" + tDespachos + "')");
                                                double desc;
                                                int opd = JOptionPane.showConfirmDialog(frame, "Aplica Descuento?", "Descuentos", JOptionPane.YES_NO_OPTION);
                                                if (opd == JOptionPane.YES_OPTION) {
                                                    desc = Double.parseDouble(JOptionPane.showInputDialog(frame, "Descuento de:",
                                                                    "Total Descuentos",
                                                                    JOptionPane.INFORMATION_MESSAGE));
                                                    sentencia.executeUpdate("INSERT INTO TVentasFecha (fecha, cod, codigoVendedor, tDescuentos)"
                                                            + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-" + cFact
                                                            + "', '" + cVendedor + "', '" + desc + "')");
                                                }
                                                double ret;
                                                int opr = JOptionPane.showConfirmDialog(frame, "Aplica Retencion?", "Retencion", JOptionPane.YES_NO_OPTION);
                                                if (opr == JOptionPane.YES_OPTION) {
                                                    ret = Double.parseDouble(JOptionPane.showInputDialog(frame, "Retencion de:",
                                                                    "Retencion",
                                                                    JOptionPane.INFORMATION_MESSAGE));
                                                    sentencia.executeUpdate("INSERT INTO TVentasFecha (fecha, cod, codigoVendedor, tRetContado)"
                                                            + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-" + cFact
                                                            + "', '" + cVendedor + "', '" + ret + "')");
                                                } else {
                                                    ret = 0;
                                                }
                                                campTOT.setText(String.format(Locale.UK, "%.2f", tDespachos - ret));
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        creditos.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 3;
                        final JInternalFrame frame = new JInternalFrame("Ventas al Credito", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        codigo = 0;
                        try {
                            modeloTabla.establecerConsulta("SELECT cVenta FROM Codigos");
                            codigo = (Integer) modeloTabla.getValueAt(0, 0);
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        cFact = String.valueOf(codigo);
                        codFact.setText("fact");
                        codFact2.setText(cFact);
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();
                        tabla.setEnabled(false);
                        campTOT.setEditable(true);

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                ++codigo;
                                                sentencia = conexion.createStatement();
                                                int op = JOptionPane.showConfirmDialog(frame, "Aplica Descuento?", "Confirmar Descuento", JOptionPane.YES_NO_OPTION);
                                                double desc;
                                                if (op == JOptionPane.YES_OPTION) {
                                                    desc = Double.parseDouble(JOptionPane.showInputDialog(frame, "Descuento del:",
                                                                    "Descuento",
                                                                    JOptionPane.INFORMATION_MESSAGE));
                                                    desc = 1 - (desc / 100);
                                                    sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tDescuentos) "
                                                            + "VALUES ('" + nombreFecha + "', 'fact-" + codFact2.getText() + "', '" + cVendedor
                                                            + "', '" + (tDespachos * (1 / desc - 1)) + "')");
                                                }
                                                sentencia.executeUpdate("UPDATE Codigos SET cNota = '" + codigo
                                                        + "' WHERE cNota = '" + (codigo - 1) + "'");
                                                sentencia.executeUpdate("INSERT INTO VentaFecha VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-"
                                                        + codFact2.getText() + "', '" + cVendedor + "', '" + cCliente
                                                        + "', 'Credito', '1', '0', '" + tDespachos + "', '0')");
                                                sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tVentasCredito) "
                                                        + "VALUES ('" + nombreFecha + "', 'fact-" + codFact2.getText() + "', '" + cVendedor
                                                        + "', '" + tDespachos + "')");
                                                modeloTabla.establecerConsulta("SELECT facturas, deudaCliente FROM Clientes "
                                                        + "WHERE codCliente = '" + cCliente + "'");
                                                fact = (Integer) modeloTabla.getValueAt(0, 0);
                                                ++fact;
                                                sentencia.executeUpdate("UPDATE Clientes SET deudaCliente = '" + ((Double) modeloTabla.getValueAt(0, 1) + tDespachos) + "', facturas = '" + fact + "' "
                                                        + "WHERE codCliente = '" + cCliente + "'");
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        cobros.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 3;
                        final JInternalFrame frame = new JInternalFrame("Cobro Total", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codFact.setEditable(false);
                        codFact.setText("fact");
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        cVendedor = codVendedor.getText();
                                        int cod = 0;
                                        tot = 0.00;

                                        try {
                                            modeloTabla.establecerConsulta("SELECT codCliente FROM VentaFecha WHERE "
                                                    + "codigoVendedor = '" + cVendedor + "' AND "
                                                    + "codVenta = '" + codFact.getText() + "-" + cFact + "'");
                                            cCliente = (String) modeloTabla.getValueAt(0, 0);
                                            modeloTabla.establecerConsulta("SELECT nombreCliente, apellidoCliente FROM Clientes WHERE "
                                                    + "codCliente = '" + cCliente + "'");
                                            codCliente.setText(cCliente);
                                            nombreCliente.setText((String) modeloTabla.getValueAt(0, 0));
                                            apellidoCliente.setText((String) modeloTabla.getValueAt(0, 1));
                                            modeloTabla.establecerConsulta("SELECT cobrado, total FROM VentaFecha WHERE "
                                                    + "codigoVendedor = '" + cVendedor + "' AND "
                                                    + "codCliente = '" + cCliente + "' AND "
                                                    + "codVenta = '" + codFact.getText() + "-" + cFact + "'");
                                            tot = (Double) modeloTabla.getValueAt(0, 1);
                                            if ((Integer) modeloTabla.getValueAt(0, 0) <= 1) {
                                                campTOT.setText(String.format(Locale.UK, "%.2f", tot));

                                                if ((Integer) modeloTabla.getValueAt(0, 0) == 1) {
                                                    JOptionPane.showMessageDialog(frame, "Esta factura tiene un cobro parcial anterior.", "Cobro Parcial", JOptionPane.INFORMATION_MESSAGE);
                                                    /*modeloTabla.establecerConsulta("SELECT tCobros FROM TVentasFecha "
                                                     + "WHERE cod = 'fact-" + cFact + "' "
                                                     + "AND codigoVendedor = '" + cVendedor + "' "
                                                     + "AND tCobros <> 0");
                                                     double c = 0.00;
                                                     for (int i = 0; i < modeloTabla.getRowCount(); i ++)
                                                     c += (Double)modeloTabla.getValueAt(i, 0);*/
                                                    campTOT.setText(String.format(Locale.UK, "%.2f", (tot)));
                                                    campTOT.setFont(new Font("Dialog", Font.BOLD, 13));
                                                }
                                                guardar.setEnabled(true);
                                                final String[] cobros = {"Total", "Parcial"};
                                                int cob = JOptionPane.showOptionDialog(frame, "Seleccione la forma de cobro:", "Cobro",
                                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                        null, cobros, null);
                                                if (cob == JOptionPane.YES_OPTION) {
                                                    JOptionPane.showMessageDialog(frame, "Usted esta por realizar un cobro TOTAL", "Cobro TOTAL", JOptionPane.INFORMATION_MESSAGE);
                                                    campTOT.setFont(new Font("Dialog", Font.BOLD, 13));
                                                } else {
                                                    double cPar = Double.parseDouble(JOptionPane.showInputDialog(frame, "Usted esta por realizar un cobro PARCIAL de:",
                                                                    "Cobro PARCIAL", JOptionPane.INFORMATION_MESSAGE));
                                                    campTOT.setText(String.format(Locale.UK, "%.2f", cPar));
                                                    campTOT.setFont(new Font("Dialog", Font.BOLD, 13));
                                                    campTOT.setForeground(Color.RED);
                                                }
                                            } else if ((Integer) modeloTabla.getValueAt(0, 0) == 2) {
                                                JOptionPane.showMessageDialog(frame, "La factura que ha ingresado ya ha sido cobrada.", "Cobrado", JOptionPane.INFORMATION_MESSAGE);
                                                codFact2.setEditable(true);
                                            }
                                            codCliente.setEditable(false);
                                            nombreCliente.setEditable(false);
                                            apellidoCliente.setEditable(false);
                                        } catch (HeadlessException | IllegalStateException | NumberFormatException | SQLException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        aVendedor = apellidoVendedor.getText();
                                        cCliente = codCliente.getText();
                                        nCliente = nombreCliente.getText();
                                        aCliente = apellidoCliente.getText();
                                        fPago = formaPago.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tVentaCredito = Double.parseDouble(total);
                                        int cod = 0;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                if (campTOT.getForeground() != Color.RED) {
                                                    modeloTabla.establecerConsulta("SELECT facturas FROM Clientes "
                                                            + "WHERE codCliente = '" + cCliente + "'");
                                                    fact = (Integer) modeloTabla.getValueAt(0, 0);
                                                    --fact;
                                                    sentencia.executeUpdate("UPDATE Clientes SET facturas = '" + fact + "' "
                                                            + "WHERE codCliente = '" + cCliente + "'");
                                                    sentencia.executeUpdate("UPDATE VentaFecha SET cobrado = '2' "
                                                            + "WHERE codVenta = '" + codFact.getText() + "-" + cFact + "' "
                                                            + "AND codCliente = '" + cCliente + "' AND codigoVendedor = '" + cVendedor + "'");
                                                } else {
                                                    sentencia.executeUpdate("UPDATE VentaFecha SET cobrado = '1' "
                                                            + "WHERE codVenta = '" + codFact.getText() + "-" + cFact + "' "
                                                            + "AND codCliente = '" + cCliente + "' AND codigoVendedor = '" + cVendedor + "'");
                                                }
                                                double ret;
                                                int opr = JOptionPane.showConfirmDialog(frame, "Aplica Retencion?", "Retencion", JOptionPane.YES_NO_OPTION);
                                                if (opr == JOptionPane.YES_OPTION) {
                                                    ret = Double.parseDouble(JOptionPane.showInputDialog(frame, "Retencion de:",
                                                                    "Retencion",
                                                                    JOptionPane.INFORMATION_MESSAGE));
                                                    sentencia.executeUpdate("INSERT INTO TVentasFecha (fecha, cod, codigoVendedor, tRetCobros)"
                                                            + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-" + cFact
                                                            + "', '" + cVendedor + "', '" + ret + "')");
                                                    modeloTabla.establecerConsulta("SELECT total FROM VentaFecha WHERE codVenta = '" + codFact.getText() + "-" + cFact + "' "
                                                            + "AND codCliente = '" + cCliente + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    sentencia.executeUpdate("UPDATE VentaFecha SET total = '" + ((Double) modeloTabla.getValueAt(0, 0) - ret) + "' WHERE codVenta = '" + codFact.getText() + "-" + cFact + "' "
                                                            + "AND codCliente = '" + cCliente + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    //sentencia.executeUpdate("UPDATE TVentasFecha SET tVentasCredito = '" + ((Double)modeloTabla.getValueAt(0, 0) - ret) + "' WHERE cod = '" + codFact.getText() + "-" + cFact + "' "
                                                    //									+ "AND codigoVendedor = '" + cVendedor + "' AND tVentasCredito <> 0");
                                                } else {
                                                    ret = 0;
                                                }
                                                campTOT.setText(String.format(Locale.UK, "%.2f", tVentaCredito - ret));
                                                if (!campTOT.getText().equals("0.00")) {
                                                    sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tCobros) "
                                                            + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-" + cFact + "', '" + cVendedor
                                                            + "', '" + Double.parseDouble(campTOT.getText()) + "')");
                                                    modeloTabla.establecerConsulta("SELECT total FROM VentaFecha WHERE codVenta = '" + codFact.getText() + "-" + cFact + "' "
                                                            + "AND codCliente = '" + cCliente + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    sentencia.executeUpdate("UPDATE VentaFecha SET total = '" + ((Double) modeloTabla.getValueAt(0, 0) - Double.parseDouble(campTOT.getText())) + "' WHERE codVenta = '" + codFact.getText() + "-" + cFact + "' "
                                                            + "AND codCliente = '" + cCliente + "' AND codigoVendedor = '" + cVendedor + "'");
                                                }
                                                modeloTabla.establecerConsulta("SELECT compras, deudaCliente FROM Clientes "
                                                        + "WHERE codCliente = '" + cCliente + "'");
                                                sentencia.executeUpdate("UPDATE Clientes SET compras = '" + (tVentaCredito + (Double) modeloTabla.getValueAt(0, 0)) + "' "
                                                        + "WHERE codCliente = '" + cCliente + "'");
                                                sentencia.executeUpdate("UPDATE Clientes SET deudaCliente = '" + ((Double) modeloTabla.getValueAt(0, 1) - tVentaCredito) + "' "
                                                        + "WHERE codCliente = '" + cCliente + "'");
                                            } catch (SQLException | IllegalStateException | HeadlessException | NumberFormatException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                            JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        cuadro.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 9;
                        final JInternalFrame frame = new JInternalFrame("Cuadro Creditos", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        codFact.setEditable(false);
                        codFact.setText("fact");
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();
                        try {
                            modeloTabla.establecerConsulta("SELECT * FROM Productos WHERE cod = '0'");
                        } catch (IllegalStateException | SQLException e) {
                            JOptionPane.showMessageDialog(null, e.toString());
                        }

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        cFact = codFact.getText() + "-" + codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/";
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        int cod = 0;
                                        tot = 0.00;

                                        try {
                                            modelo.establecerConsulta("SELECT codCliente FROM VentaFecha WHERE "
                                                    + "codigoVendedor = '" + cVendedor + "' AND "
                                                    + "codVenta = '" + cFact + "'");
                                            cCliente = (String) modelo.getValueAt(0, 0);
                                            modelo.establecerConsulta("SELECT nombreCliente, apellidoCliente FROM Clientes WHERE "
                                                    + "codCliente = '" + cCliente + "'");
                                            codCliente.setText(cCliente);
                                            nombreCliente.setText((String) modelo.getValueAt(0, 0));
                                            apellidoCliente.setText((String) modelo.getValueAt(0, 1));
                                            modeloTabla.establecerConsulta("SELECT VentaFecha.fecha Fecha, codVenta Factura, IF(cobrado = 0, '-', TVentasFecha.fecha) Fecha_Cobro, total CREDITO, "
                                                    + "IF(cobrado = 0, 0, tCobros) COBROS, IF(cobrado = 0, 0, tRetCobros) RETENCION, IF(cobrado = 0, 'Pendiente', 'Cobrado') Estado "
                                                    + "FROM VentaFecha, TVentasFecha WHERE TVentasFecha.codigoVendedor = '" + cVendedor + "' AND VentaFecha.codigoVendedor = '" + cVendedor + "' "
                                                    + "AND VentaFecha.fecha >= '" + nombreFecha + dia.getText() + "' "
                                                    + "AND TVentasFecha.cod = codVenta AND CASE WHEN cobrado = 0 THEN tVentasCredito <> 0 ELSE tVentasCredito = 0 END "
                                                    + "AND codCliente = '" + cCliente + "' AND tDescuentos = 0 ORDER BY fecha, codVenta");
                                            for (int i = 0; i < tablaConsulta.getRowCount(); i++) {
                                                tot = tot + (Double) tablaConsulta.getValueAt(i, 3) - (Double) tablaConsulta.getValueAt(i, 4) - (Double) tablaConsulta.getValueAt(i, 5);
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                            codCliente.setEditable(false);
                                            nombreCliente.setEditable(false);
                                            apellidoCliente.setEditable(false);

                                        } catch (SQLException | IllegalStateException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        aVendedor = apellidoVendedor.getText();
                                        cCliente = codCliente.getText();
                                        nCliente = nombreCliente.getText();
                                        aCliente = apellidoCliente.getText();
                                        fPago = formaPago.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        double tVentas = Double.parseDouble(textDeuda.getText());
                                        int cod = 0;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                if (botonCredito.isSelected()) {
                                                    int op = JOptionPane.showConfirmDialog(frame, "Aplica Descuento?", "Confirmar Descuento", JOptionPane.YES_NO_OPTION);
                                                    double desc;
                                                    if (op == JOptionPane.YES_OPTION) {
                                                        desc = Double.parseDouble(JOptionPane.showInputDialog(frame, "Descuento del:",
                                                                        "Descuento",
                                                                        JOptionPane.INFORMATION_MESSAGE));
                                                        desc = 1 - (desc / 100);
                                                        sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tDescuentos) "
                                                                + "VALUES ('" + nombreFecha + "', 'fact-" + codFact2.getText() + "', '" + cVendedor
                                                                + "', '" + (tVentas * (1 / desc - 1)) + "')");
                                                    }
                                                    sentencia.executeUpdate("INSERT INTO VentaFecha VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-"
                                                            + codFact2.getText() + "', '" + cVendedor + "', '" + cCliente
                                                            + "', 'Credito', '1', '0', '" + tVentas + "', '0')");
                                                    sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tVentasCredito) "
                                                            + "VALUES ('" + nombreFecha + "', 'fact-" + codFact2.getText() + "', '" + cVendedor
                                                            + "', '" + tVentas + "')");
                                                    modelo.establecerConsulta("SELECT facturas, deudaCliente FROM Clientes "
                                                            + "WHERE codCliente = '" + cCliente + "'");
                                                    fact = (Integer) modelo.getValueAt(0, 0);
                                                    ++fact;
                                                    sentencia.executeUpdate("UPDATE Clientes SET deudaCliente = '" + ((Double) modelo.getValueAt(0, 1) + tDespachos) + "', facturas = '" + fact + "' "
                                                            + "WHERE codCliente = '" + cCliente + "'");
                                                } else {
                                                    modelo.establecerConsulta("SELECT cobrado, total FROM VentaFecha WHERE "
                                                            + "codigoVendedor = '" + cVendedor + "' AND "
                                                            + "codCliente = '" + cCliente + "' AND "
                                                            + "codVenta = '" + cFact + "'");
                                                    tot = (Double) modelo.getValueAt(0, 1);
                                                    if ((Integer) modelo.getValueAt(0, 0) <= 1) {
                                                        textDeuda.setText(String.format(Locale.UK, "%.2f", tot));

                                                        if ((Integer) modelo.getValueAt(0, 0) == 1) {
                                                            JOptionPane.showMessageDialog(frame, "Esta factura tiene un cobro parcial anterior.", "Cobro Parcial", JOptionPane.INFORMATION_MESSAGE);
                                                            modelo.establecerConsulta("SELECT tCobros FROM TVentasFecha "
                                                                    + "WHERE cod = '" + cFact + "'");
                                                            double c = 0.00;
                                                            for (int i = 0; i < modelo.getRowCount(); i++) {
                                                                c += (Double) modelo.getValueAt(i, 0);
                                                            }
                                                            textDeuda.setText(String.format(Locale.UK, "%.2f", (tot - c)));
                                                            textDeuda.setFont(new Font("Dialog", Font.BOLD, 13));
                                                        }
                                                        guardar.setEnabled(true);
                                                        String[] cobros = {"Total", "Parcial"};
                                                        int cob = JOptionPane.showOptionDialog(frame, "Seleccione la forma de cobro:", "Cobro",
                                                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                                null, cobros, null);
                                                        if (cob == JOptionPane.YES_OPTION) {
                                                            JOptionPane.showMessageDialog(frame, "Usted esta por realizar un cobro TOTAL", "Cobro TOTAL", JOptionPane.INFORMATION_MESSAGE);
                                                            textDeuda.setFont(new Font("Dialog", Font.BOLD, 13));
                                                        } else {
                                                            double cPar = Double.parseDouble(JOptionPane.showInputDialog(frame, "Usted esta por realizar un cobro PARCIAL de:",
                                                                            "Cobro PARCIAL", JOptionPane.INFORMATION_MESSAGE));
                                                            textDeuda.setText(String.format(Locale.UK, "%.2f", cPar));
                                                            textDeuda.setFont(new Font("Dialog", Font.BOLD, 13));
                                                            textDeuda.setForeground(Color.RED);
                                                        }
                                                    } else if ((Integer) modelo.getValueAt(0, 0) == 2) {
                                                        JOptionPane.showMessageDialog(frame, "La factura que ha ingresado ya ha sido cobrada.", "Cobrado", JOptionPane.INFORMATION_MESSAGE);
                                                        codFact2.setEditable(true);
                                                    }

                                                    if (textDeuda.getForeground() != Color.RED) {
                                                        modelo.establecerConsulta("SELECT facturas FROM Clientes "
                                                                + "WHERE codCliente = '" + cCliente + "'");
                                                        fact = (Integer) modelo.getValueAt(0, 0);
                                                        --fact;
                                                        sentencia.executeUpdate("UPDATE Clientes SET facturas = '" + fact + "' "
                                                                + "WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE VentaFecha SET cobrado = '2' "
                                                                + "WHERE codVenta = '" + codFact.getText() + "-" + cFact + "' "
                                                                + "AND codCliente = '" + cCliente + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    } else {
                                                        sentencia.executeUpdate("UPDATE VentaFecha SET cobrado = '1' "
                                                                + "WHERE codVenta = '" + codFact.getText() + "-" + cFact + "' "
                                                                + "AND codCliente = '" + cCliente + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    }

                                                    double ret;
                                                    int opr = JOptionPane.showConfirmDialog(frame, "Aplica Retencion?", "Retencion", JOptionPane.YES_NO_OPTION);
                                                    if (opr == JOptionPane.YES_OPTION) {
                                                        ret = Double.parseDouble(JOptionPane.showInputDialog(frame, "Retencion de:",
                                                                        "Retencion",
                                                                        JOptionPane.INFORMATION_MESSAGE));
                                                        sentencia.executeUpdate("INSERT INTO TVentasFecha (fecha, cod, codigoVendedor, tRetCobros)"
                                                                + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-" + cFact
                                                                + "', '" + cVendedor + "', '" + ret + "')");
                                                        modelo.establecerConsulta("SELECT total FROM VentaFecha WHERE codVenta = '" + codFact.getText() + "-" + cFact + "' "
                                                                + "AND codCliente = '" + cCliente + "' AND codigoVendedor = '" + cVendedor + "'");
                                                        sentencia.executeUpdate("UPDATE VentaFecha SET total = '" + ((Double) modelo.getValueAt(0, 0) - ret) + "' WHERE codVenta = '" + cFact + "' "
                                                                + "AND codCliente = '" + cCliente + "' AND codigoVendedor = '" + cVendedor + "'");
                                                        sentencia.executeUpdate("UPDATE TVentasFecha SET tVentasCredito = '" + ((Double) modelo.getValueAt(0, 0) - ret) + "' WHERE cod = '" + cFact + "' "
                                                                + "AND codigoVendedor = '" + cVendedor + "' AND tVentasCredito <> 0");
                                                    } else {
                                                        ret = 0;
                                                    }
                                                    textDeuda.setText(String.format(Locale.UK, "%.2f", tVentas - ret));
                                                    if ((tVentas - ret) != 0) {
                                                        sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tCobros) "
                                                                + "VALUES ('" + nombreFecha + "', '" + cFact + "', '" + cVendedor
                                                                + "', '" + (tVentas - ret) + "')");
                                                    }
                                                    modelo.establecerConsulta("SELECT compras, deudaCliente FROM Clientes "
                                                            + "WHERE codCliente = '" + cCliente + "'");
                                                    sentencia.executeUpdate("UPDATE Clientes SET compras = '" + (tVentas + (Double) modelo.getValueAt(0, 0)) + "' "
                                                            + "WHERE codCliente = '" + cCliente + "'");
                                                    sentencia.executeUpdate("UPDATE Clientes SET deudaCliente = '" + ((Double) modelo.getValueAt(0, 1) - tVentas) + "' "
                                                            + "WHERE codCliente = '" + cCliente + "'");
                                                }
                                                modeloTabla.establecerConsulta("SELECT VentaFecha.fecha Fecha, codVenta Factura, IF(cobrado = 0, '-', TVentasFecha.fecha) Fecha_Cobro, total CREDITO, "
                                                        + "IF(cobrado = 0, 0, tCobros) COBROS, IF(cobrado = 0, 0, tRetCobros) RETENCION, IF(cobrado = 0, 'Pendiente', 'Cobrado') Estado "
                                                        + "FROM VentaFecha, TVentasFecha WHERE TVentasFecha.codigoVendedor = '" + cVendedor + "' AND VentaFecha.codigoVendedor = '" + cVendedor + "' "
                                                        + "AND VentaFecha.fecha >= '" + nombreFecha + dia.getText() + "' "
                                                        + "AND TVentasFecha.cod = codVenta AND CASE WHEN cobrado = 0 THEN tVentasCredito <> 0 ELSE tVentasCredito = 0 END "
                                                        + "AND codCliente = '" + cCliente + "' AND tDescuentos = 0 ORDER BY fecha, cod");
                                                for (int i = 0; i < tablaConsulta.getRowCount(); i++) {
                                                    tot = tot + (Double) tablaConsulta.getValueAt(i, 3) - (Double) tablaConsulta.getValueAt(i, 4) - (Double) tablaConsulta.getValueAt(i, 5);
                                                }
                                                campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                            } catch (HeadlessException | NumberFormatException | SQLException | IllegalStateException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                            JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        nuevaDevolucion.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Devolucion de Ventas", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();
                        codFact.setText("dev");
                        codFact2.setEditable(true);

                        codigo = 0;
                        try {
                            modeloTabla.establecerConsulta("SELECT cPedido FROM Codigos");
                            codigo = (Integer) modeloTabla.getValueAt(0, 0);
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        codFact2.setText(String.valueOf(codigo));

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        textoImprimir = "";
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDevolucion = Double.parseDouble(total);
                                        int cod = 0, cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                ++codigo;
                                                sentencia = conexion.createStatement();
                                                textoImprimir += String.format("Vendedor: %s %-12s\nFecha:    %s\nDevolucion: %s\n\n", cVendedor, nVendedor, nombreFecha, codFact2.getText());
                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = Integer.parseInt((String) tabla.getValueAt(i, 0));
                                                        sentencia.executeUpdate("INSERT INTO DevFecha VALUES ('" + nombreFecha + "', 'dev-"
                                                                + codFact2.getText() + "', '" + cVendedor + "', '"
                                                                + cod + "', '" + cant + "', '" + prod[i] + "')");
                                                        if (cant != 0) {
                                                            textoImprimir += String.format("%5s %10s %10s\n", (String) tabla.getValueAt(i, 0), (String) tabla.getValueAt(i, 1),
                                                                    (String) tabla.getValueAt(i, 3));
                                                        }
                                                    } else {
                                                        textoImprimir += String.format("%10s %s\n", (String) tabla.getValueAt(i, 1), (String) tabla.getValueAt(i, 2));
                                                    }
                                                }
                                                sentencia.executeUpdate("UPDATE Codigos SET cPedido = '" + codigo
                                                        + "' WHERE cPedido = '" + (codigo - 1) + "'");
                                                sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tDevolucion) "
                                                        + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-"
                                                        + codFact2.getText() + "', '" + codVendedor.getText()
                                                        + "', '" + tDevolucion + "')");

                                                textoImprimir += String.format("%16s %10s\n", "TOTAL:", campTOT.getText());
                                                int p = JOptionPane.showConfirmDialog(frame, "Desea imprimir?", "Imprimir", JOptionPane.YES_NO_OPTION);
                                                if (p == JOptionPane.YES_OPTION) {
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                PatatasPrint imprimir;
                                                                //do
                                                                //{
                                                                imprimir = new PatatasPrint(textoImprimir);
                                                                //} while(!imprimir.wasPrinted());
													/*tabla.print(JTable.PrintMode.FIT_WIDTH,
                                                                 new MessageFormat(MessageFormat.format("Vendedor: {0} {1}\nFecha: {2}\nDespacho: {3}", cVendedor, nVendedor, nombreFecha, codFact2.getText())),
                                                                 new MessageFormat(MessageFormat.format("Total: {0, number, double}", tDespachos)));*/
                                                            } catch (Exception pe) {
                                                                JOptionPane.showMessageDialog(null, "Error al imprimir");
                                                            }
                                                        }
                                                    }).start();
                                                }
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (SQLException | NumberFormatException | HeadlessException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        nuevoCredito.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 3;
                        final JInternalFrame frame = new JInternalFrame("Cobro Total", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codFact.setEditable(true);
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        if (formaPago.getText().equals("Contado")) {
                                            JOptionPane.showMessageDialog(frame, "Debe ingresar datos de un Cliente a Credito", "Error", JOptionPane.ERROR_MESSAGE);
                                            guardar.setEnabled(false);
                                        } else {
                                            codFact2.setEditable(false);
                                            cFact = codFact2.getText();
                                            nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                            cVendedor = codVendedor.getText();
                                            cCliente = codCliente.getText();
                                            int cod = 0;
                                            tot = 0.00;

                                            try {
                                                modeloTabla.establecerConsulta("SELECT cantidad, cobrado FROM VentaFecha WHERE "
                                                        + "codigoVendedor = '" + cVendedor + "' AND "
                                                        + "codCliente = '" + cCliente + "' AND "
                                                        + "codVenta = '" + codFact.getText() + "-" + cFact + "'");
                                                if ((Integer) modeloTabla.getValueAt(0, 1) <= 1) {
                                                    for (int i = 0; i < data.length; i++) {
                                                        if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                            ++cod;
                                                            tabla.setValueAt((Integer) modeloTabla.getValueAt(cod - 1, 0), i, 0);
                                                        }
                                                    }
                                                    for (int i = 0; i < data.length; i++) {
                                                        if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                            prod[i] = (Integer) tabla.getValueAt(i, 0) * Double.parseDouble((String) tabla.getValueAt(i, 1));
                                                            tot += prod[i];
                                                            tabla.setValueAt(prod[i], i, 3);
                                                            campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                                        }
                                                    }
                                                    if ((Integer) modeloTabla.getValueAt(0, 1) == 1) {
                                                        JOptionPane.showMessageDialog(frame, "Esta factura tiene un cobro parcial anterior.", "Cobro Parcial", JOptionPane.INFORMATION_MESSAGE);
                                                        modeloTabla.establecerConsulta("SELECT tCobros FROM TVentasFecha "
                                                                + "WHERE cod = 'fact-" + cFact + "'");
                                                        campTOT.setText(String.format(Locale.UK, "%.2f", (tot - (Double) modeloTabla.getValueAt(0, 0))));
                                                        campTOT.setFont(new Font("Dialog", Font.BOLD, 13));
                                                    }
                                                    guardar.setEnabled(true);
                                                    String[] cobros = {"Total", "Parcial"};
                                                    int cob = JOptionPane.showOptionDialog(frame, "Seleccione la forma de cobro:", "Cobro",
                                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                            null, cobros, null);
                                                    if (cob == JOptionPane.YES_OPTION) {
                                                        JOptionPane.showMessageDialog(frame, "Usted esta por realizar un cobro TOTAL", "Cobro TOTAL", JOptionPane.INFORMATION_MESSAGE);
                                                        campTOT.setFont(new Font("Dialog", Font.BOLD, 13));
                                                    } else {
                                                        double cPar = Double.parseDouble(JOptionPane.showInputDialog(frame, "Usted esta por realizar un cobro PARCIAL de:",
                                                                        "Cobro PARCIAL", JOptionPane.INFORMATION_MESSAGE));
                                                        campTOT.setText(String.format(Locale.UK, "%.2f", cPar));
                                                        campTOT.setFont(new Font("Dialog", Font.BOLD, 13));
                                                        campTOT.setForeground(Color.RED);
                                                    }
                                                } else if ((Integer) modeloTabla.getValueAt(0, 1) == 2) {
                                                    JOptionPane.showMessageDialog(frame, "La factura que ha ingresado ya ha sido cobrada.", "Cobrado", JOptionPane.INFORMATION_MESSAGE);
                                                    codFact2.setEditable(true);
                                                }
                                            } catch (SQLException | IllegalStateException | NumberFormatException | HeadlessException ee) {
                                                JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                                codFact2.setEditable(true);
                                            }
                                        }
                                    }
                                }
                        );

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        aVendedor = apellidoVendedor.getText();
                                        cCliente = codCliente.getText();
                                        nCliente = nombreCliente.getText();
                                        aCliente = apellidoCliente.getText();
                                        fPago = formaPago.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tVentaCredito = Double.parseDouble(total);
                                        int cod = 0;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                if (campTOT.getForeground() != Color.RED) {
                                                    modeloTabla.establecerConsulta("SELECT facturas FROM Clientes "
                                                            + "WHERE codCliente = '" + cCliente + "'");
                                                    fact = (Integer) modeloTabla.getValueAt(0, 0);
                                                    --fact;
                                                    sentencia.executeUpdate("UPDATE Clientes SET facturas = '" + fact + "' "
                                                            + "WHERE codCliente = '" + cCliente + "'");
                                                    sentencia.executeUpdate("UPDATE VentaFecha SET cobrado = '2' "
                                                            + "WHERE codVenta = '" + codFact.getText() + "-" + cFact + "'");
                                                } else {
                                                    sentencia.executeUpdate("UPDATE VentaFecha SET cobrado = '1' "
                                                            + "WHERE codVenta = '" + codFact.getText() + "-" + cFact + "'");
                                                }
                                                double ret;
                                                int opr = JOptionPane.showConfirmDialog(frame, "Aplica Retencion?", "Retencion", JOptionPane.YES_NO_OPTION);
                                                if (opr == JOptionPane.YES_OPTION) {
                                                    ret = Double.parseDouble(JOptionPane.showInputDialog(frame, "Retencion de:",
                                                                    "Retencion",
                                                                    JOptionPane.INFORMATION_MESSAGE));
                                                    sentencia.executeUpdate("INSERT INTO TVentasFecha (fecha, cod, codigoVendedor, tRetCobros)"
                                                            + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-" + cFact
                                                            + "', '" + cVendedor + "', '" + ret + "')");
                                                } else {
                                                    ret = 0;
                                                }
                                                campTOT.setText(String.format(Locale.UK, "%.2f", tVentaCredito - ret));
                                                sentencia.executeUpdate("INSERT INTO TVentasFecha(fecha, cod, codigoVendedor, tCobros) "
                                                        + "VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-" + cFact + "', '" + cVendedor
                                                        + "', '" + (tVentaCredito - ret) + "')");
                                                modeloTabla.establecerConsulta("SELECT compras, deudaCliente FROM Clientes "
                                                        + "WHERE codCliente = '" + cCliente + "'");
                                                sentencia.executeUpdate("UPDATE Clientes SET compras = '" + (tVentaCredito + (Double) modeloTabla.getValueAt(0, 0)) + "' "
                                                        + "WHERE codCliente = '" + cCliente + "'");
                                                sentencia.executeUpdate("UPDATE Clientes SET deudaCliente = '" + ((Double) modeloTabla.getValueAt(0, 1) - tVentaCredito) + "' "
                                                        + "WHERE codCliente = '" + cCliente + "'");
                                            } catch (SQLException | IllegalStateException | HeadlessException | NumberFormatException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                            JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

///*********************************************************************      
        anularProduccion.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 0;
                        final JInternalFrame frame = new JInternalFrame("Anular Produccion", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codFact.setColumns(20);
                        codFact.setText("Al ingresar los datos");
                        codFact2.setColumns(20);
                        codFact2.setText("Presione ENTER aqui");
                        codFact2.setEditable(true);
                        codSocio.setEditable(true);
                        codSocio.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        cSocio = codSocio.getText();
                                        int cod = 0;
                                        tot = 0.00;
                                        try {
                                            modeloTabla.establecerConsulta("SELECT cantidad FROM ProdFecha WHERE "
                                                    + "codSocio = '" + cSocio + "' AND "
                                                    + "fecha = '" + nombreFecha + "'");
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    tabla.setValueAt((Integer) modeloTabla.getValueAt(cod - 1, 0), i, 0);
                                                }
                                            }
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    prod[i] = (Integer) tabla.getValueAt(i, 0) * Double.parseDouble((String) tabla.getValueAt(i, 1));
                                                    tot += prod[i];
                                                    tabla.setValueAt(prod[i], i, 3);
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                            guardar.setEnabled(true);
                                        } catch (IllegalStateException | NumberFormatException | SQLException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.setText("Anular");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cSocio = codSocio.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                //mensaje.setText("Espere mientras se actualizan los datos");
                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = (Integer) tabla.getValueAt(i, 0);
                                                        sentencia = conexion.createStatement();
                                                        sentencia.executeUpdate("DELETE FROM ProdFecha WHERE fecha = '" + nombreFecha + "' "
                                                                + "AND cod = '" + cod + "' "
                                                                + "AND codSocio = '" + codSocio.getText() + "'");
                                                        sentencia.executeUpdate("INSERT INTO AnulacionProd VALUES ('" + nombreFecha + "', '"
                                                                + cod + "', '" + codSocio.getText() + "', '" + cant + "', '"
                                                                + prod[i] + "')");
                                                        //progresoSQL.setValue(cod * 100 / 52);
                                                        //estadoSQL.setText(String.format("Completado el %d%%.\n", (cod * 100 / 52)));
                                                    }
                                                }
                                                modeloTabla.establecerConsulta("SELECT produccion FROM Socios WHERE codSocio = '" + codSocio.getText() + "'");
                                                sentencia.executeUpdate("UPDATE Socios SET produccion = '" + ((Double) modeloTabla.getValueAt(0, 0) - tDespachos) + "' "
                                                        + "WHERE codSocio = '" + codSocio.getText() + "'");
                                            } catch (SQLException | IllegalStateException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                                frame.dispose();
                                            }
                                            JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                            frame.dispose();
                                        }
                                        //mensaje.setText("");
                                        //progresoSQL.setValue(0);
                                        //estadoSQL.setText("");
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        anularDespacho.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Anular Despacho", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        codFact.setText("d");
                        codFact.setEditable(true);
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        int cod = 0;
                                        tot = 0.00;

                                        try {
                                            if (codFact.getText().equals("d")) {
                                                modeloTabla.establecerConsulta("SELECT cantidad FROM DespFecha WHERE "
                                                        + "codigoVendedor = '" + cVendedor + "' AND "
                                                        + "codDespacho = 'd-" + cFact + "'");
                                            } else {
                                                modeloTabla.establecerConsulta("SELECT cantidad FROM DevAFecha WHERE "
                                                        + "codigoVendedor = '" + cVendedor + "' AND "
                                                        + "codDevolA = 'devA-" + cFact + "'");
                                            }
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    tabla.setValueAt((Integer) modeloTabla.getValueAt(cod - 1, 0), i, 0);
                                                }
                                            }
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    prod[i] = (Integer) tabla.getValueAt(i, 0) * Double.parseDouble((String) tabla.getValueAt(i, 1));
                                                    tot += prod[i];
                                                    tabla.setValueAt(prod[i], i, 3);
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                            guardar.setEnabled(true);
                                        } catch (SQLException | IllegalStateException | NumberFormatException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.setText("Anular");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea anular?", "Anular", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                sentencia = conexion.createStatement();
                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = (Integer) tabla.getValueAt(i, 0);

                                                        sentencia.executeUpdate("INSERT INTO AnulacionDesp VALUES ('" + nombreFecha + "', 'd-"
                                                                + codFact2.getText() + "', '" + cVendedor + "', '"
                                                                + cod + "', '" + cant
                                                                + "', '" + prod[i] + "')");
                                                    }
                                                }
                                                if (codFact.getText().equals("d")) {
                                                    sentencia.executeUpdate("DELETE FROM DespFecha WHERE codDespacho = 'd-" + codFact2.getText() + "' "
                                                            + "AND fecha = '" + nombreFecha + "' "
                                                            + "AND codigoVendedor = '" + cVendedor + "'");
                                                    sentencia.executeUpdate("DELETE FROM TVentasFecha WHERE cod = 'd-" + codFact2.getText() + "' "
                                                            + "AND fecha = '" + nombreFecha + "' "
                                                            + "AND codigoVendedor = '" + cVendedor + "'");
                                                } else {
                                                    sentencia.executeUpdate("DELETE FROM DevAFecha WHERE codDevolA = 'devA-" + codFact2.getText() + "' "
                                                            + "AND fecha = '" + nombreFecha + "' "
                                                            + "AND codigoVendedor = '" + cVendedor + "'");
                                                    sentencia.executeUpdate("DELETE FROM TVentasFecha WHERE cod = 'devA-" + codFact2.getText() + "' "
                                                            + "AND fecha = '" + nombreFecha + "' "
                                                            + "AND codigoVendedor = '" + cVendedor + "'");
                                                }
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (HeadlessException | SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        anularDevolucion.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Anular Devolucion", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codFact.setText("dev");
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        int cod = 0;
                                        tot = 0.00;

                                        try {
                                            modeloTabla.establecerConsulta("SELECT cantidad FROM DevFecha WHERE "
                                                    + "codigoVendedor = '" + cVendedor + "' AND "
                                                    + "codDevolucion = 'dev-" + cFact + "'");
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    tabla.setValueAt((Integer) modeloTabla.getValueAt(cod - 1, 0), i, 0);
                                                }
                                            }
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    prod[i] = (Integer) tabla.getValueAt(i, 0) * Double.parseDouble((String) tabla.getValueAt(i, 1));
                                                    tot += prod[i];
                                                    tabla.setValueAt(prod[i], i, 3);
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                            guardar.setEnabled(true);
                                        } catch (SQLException | IllegalStateException | NumberFormatException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.setText("Anular");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea anular?", "Anular", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                sentencia = conexion.createStatement();
                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = (Integer) tabla.getValueAt(i, 0);
                                                        sentencia.executeUpdate("INSERT INTO AnulacionDesp VALUES ('" + nombreFecha + "', 'dev-"
                                                                + codFact2.getText() + "', '" + cVendedor + "', '"
                                                                + cod + "', '" + cant + "', '" + prod[i] + "')");
                                                    }
                                                }
                                                sentencia.executeUpdate("DELETE FROM DevFecha WHERE codDevolucion = 'dev-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "'");
                                                sentencia.executeUpdate("DELETE FROM TVentasFecha WHERE cod = 'dev-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "'");
                                            } catch (SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                            JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        anularVenta.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 3;
                        final JInternalFrame frame = new JInternalFrame("Anular Venta", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        String[] trans = {"Nota de Venta", "Factura"};
                        tabla.setEnabled(false);
                        int f = JOptionPane.showOptionDialog(frame, "Seleccione el tipo de registro de transaccion a anular:", "Transaccion",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, trans, null);
                        if (f == JOptionPane.YES_OPTION) {
                            codFact.setText("nota");
                        } else {
                            codFact.setText("fact");
                        }
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        int cod = 0;
                                        tot = 0.00;

                                        try {
                                            modeloTabla.establecerConsulta("SELECT cantidad FROM VentaFecha WHERE "
                                                    + "codigoVendedor = '" + cVendedor + "' AND "
                                                    + "codCliente = '" + cCliente + "' AND "
                                                    + "codVenta = '" + codFact.getText() + "-" + cFact + "'");
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    tabla.setValueAt((Integer) modeloTabla.getValueAt(cod - 1, 0), i, 0);
                                                }
                                            }
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    prod[i] = (Integer) tabla.getValueAt(i, 0) * Double.parseDouble((String) tabla.getValueAt(i, 1));
                                                    tot += prod[i];
                                                    tabla.setValueAt(prod[i], i, 3);
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                            guardar.setEnabled(true);
                                        } catch (SQLException | IllegalStateException | NumberFormatException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos!", "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.setText("Anular");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        aVendedor = apellidoVendedor.getText();
                                        cCliente = codCliente.getText();
                                        nCliente = nombreCliente.getText();
                                        aCliente = apellidoCliente.getText();
                                        fPago = formaPago.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tVentas = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea anular?", "Anular", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                sentencia = conexion.createStatement();
                                                switch (fPago) {
                                                    case "Contado":
                                                        modeloTabla.establecerConsulta("SELECT compras FROM Clientes "
                                                                + "WHERE codigoVendedor = '" + cVendedor + "' "
                                                                + "AND codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET compras = '" + ((Double) modeloTabla.getValueAt(0, 0) - tVentas) + "' "
                                                                + "WHERE codigoVendedor = '" + cVendedor + "' "
                                                                + "AND codCliente = '" + cCliente + "'");
                                                        break;
                                                    case "Credito":
                                                        modeloTabla.establecerConsulta("SELECT facturas, deudaCliente FROM CLIENTES "
                                                                + "WHERE codCliente = '" + cCliente + "'");
                                                        fact = (Integer) modeloTabla.getValueAt(0, 0);
                                                        --fact;
                                                        sentencia.executeUpdate("UPDATE Clientes SET facturas = '" + fact + "' "
                                                                + "WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET deudaCliente = '" + ((Double) modeloTabla.getValueAt(0, 1) - tVentas) + "' "
                                                                + "WHERE codCliente = '" + cCliente + "'");
                                                        break;
                                                }

                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = (Integer) tabla.getValueAt(i, 0);
                                                        sentencia.executeUpdate("INSERT INTO AnulacionVentas VALUES ('" + nombreFecha + "', '" + codFact.getText() + "-"
                                                                + codFact2.getText() + "', '" + cVendedor + "', '" + cCliente + "', '"
                                                                + formaPago.getText() + "' , '" + cod + "', '" + cant + "', '"
                                                                + prod[i] + "')");
                                                    }
                                                }
                                                sentencia.executeUpdate("DELETE FROM VentaFecha WHERE codVenta = '" + codFact.getText() + "-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codCliente = '" + cCliente + "' "
                                                        + "AND formaVentas = '" + formaPago.getText() + "'");
                                                sentencia.executeUpdate("DELETE FROM TVentasFecha WHERE cod = '" + codFact.getText() + "-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "'");

                                                modeloTabla.establecerConsulta("SELECT ventas FROM Vendedores WHERE "
                                                        + "codigoVendedor = '" + cVendedor + "'");
                                                sentencia.executeUpdate("UPDATE Vendedores SET ventas = '" + ((Double) modeloTabla.getValueAt(0, 0) - tVentas) + "' "
                                                        + "WHERE codigoVendedor = '" + cVendedor + "'");
                                            } catch (SQLException | IllegalStateException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                            JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        anularObsequio.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Anular Obsequio", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codFact.setText("ob");
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        int cod = 0;
                                        tot = 0.00;

                                        try {
                                            modeloTabla.establecerConsulta("SELECT cantidad FROM ObsequiosFecha WHERE "
                                                    + "codigoVendedor = '" + cVendedor + "' AND "
                                                    + "codObsequio = 'ob-" + cFact + "'");
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    tabla.setValueAt((Integer) modeloTabla.getValueAt(cod - 1, 0), i, 0);
                                                }
                                            }
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    prod[i] = (Integer) tabla.getValueAt(i, 0) * Double.parseDouble((String) tabla.getValueAt(i, 1));
                                                    tot += prod[i];
                                                    tabla.setValueAt(prod[i], i, 3);
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                            guardar.setEnabled(true);
                                        } catch (IllegalStateException | NumberFormatException | SQLException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.setText("Anular");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        aVendedor = apellidoVendedor.getText();
                                        cCliente = codCliente.getText();
                                        nCliente = nombreCliente.getText();
                                        aCliente = apellidoCliente.getText();
                                        fPago = formaPago.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea anular?", "Anular", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                sentencia = conexion.createStatement();
                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = (Integer) tabla.getValueAt(i, 0);
                                                        sentencia.executeUpdate("INSERT INTO AnulacionDesp VALUES ('" + nombreFecha + "', 'ob-"
                                                                + codFact2.getText() + "', '" + cVendedor + "', '"
                                                                + cod + "', '" + cant + "', '" + prod[i] + "')");
                                                    }
                                                }
                                                sentencia.executeUpdate("DELETE FROM ObsequiosFecha WHERE codObsequio = 'ob-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "'");
                                                sentencia.executeUpdate("DELETE FROM TVentasFecha WHERE cod = 'ob-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "'");
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (HeadlessException | SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        anularCambio.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Anular Cambio", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codFact.setText("camb");
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        int cod = 0;
                                        tot = 0.00;

                                        try {
                                            modeloTabla.establecerConsulta("SELECT cantidad FROM CambiosFecha WHERE "
                                                    + "codigoVendedor = '" + cVendedor + "' AND "
                                                    + "codCambio = 'camb-" + cFact + "'");
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    tabla.setValueAt((Integer) modeloTabla.getValueAt(cod - 1, 0), i, 0);
                                                }
                                            }
                                            for (int i = 0; i < data.length; i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    prod[i] = (Integer) tabla.getValueAt(i, 0) * Double.parseDouble((String) tabla.getValueAt(i, 1));
                                                    tot += prod[i];
                                                    tabla.setValueAt(prod[i], i, 3);
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                            guardar.setEnabled(true);
                                        } catch (IllegalStateException | NumberFormatException | SQLException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.setText("Anular");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        aVendedor = apellidoVendedor.getText();
                                        cCliente = codCliente.getText();
                                        nCliente = nombreCliente.getText();
                                        aCliente = apellidoCliente.getText();
                                        fPago = formaPago.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea anular?", "Anular", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                sentencia = conexion.createStatement();
                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = (Integer) tabla.getValueAt(i, 0);
                                                        sentencia.executeUpdate("INSERT INTO AnulacionDesp VALUES ('" + nombreFecha + "', 'camb-"
                                                                + codFact2.getText() + "', '" + cVendedor + "', '"
                                                                + cod + "', '" + cant + "', '" + prod[i] + "')");
                                                    }
                                                }
                                                sentencia.executeUpdate("DELETE FROM CambiosFecha WHERE codCambio = 'camb-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "'");
                                                sentencia.executeUpdate("DELETE FROM TVentasFecha WHERE cod = 'camb-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "'");
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (SQLException | HeadlessException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        anularContados.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Anular Total Contado", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codFact.setEditable(true);
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        int cod = 0;
                                        tot = 0.00;

                                        try {
                                            modeloTabla.establecerConsulta("SELECT tVentasContado FROM TVentasFecha WHERE "
                                                    + "codigoVendedor = '" + cVendedor + "' AND "
                                                    + "cod = '" + codFact.getText() + "-" + cFact + "' AND "
                                                    + "fecha = '" + nombreFecha + "'");
                                            campTOT.setText(String.format(Locale.UK, "%.2f", (Double) modeloTabla.getValueAt(0, 0)));
                                            guardar.setEnabled(true);
                                        } catch (IllegalStateException | SQLException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.setText("Anular");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea anular?", "Anular", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                sentencia.executeUpdate("DELETE FROM TVentasFecha WHERE cod = '" + codFact.getText() + "-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "'");
                                            } catch (SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                            JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        anularCreditos.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 3;
                        final JInternalFrame frame = new JInternalFrame("Anular Total Credito", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codFact.setEditable(false);
                        codFact.setText("fact");
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        int cod = 0;
                                        tot = 0.00;

                                        try {
                                            modeloTabla.establecerConsulta("SELECT total FROM VentaFecha WHERE "
                                                    + "codigoVendedor = '" + cVendedor + "' AND "
                                                    + "codCliente = '" + cCliente + "' AND "
                                                    + "codVenta = '" + codFact.getText() + "-" + cFact + "' AND "
                                                    + "fecha = '" + nombreFecha + "'");
                                            campTOT.setText(String.format(Locale.UK, "%.2f", (Double) modeloTabla.getValueAt(0, 0)));
                                            guardar.setEnabled(true);
                                        } catch (SQLException | IllegalStateException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.setText("Anular");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea anular?", "Anular", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                sentencia.executeUpdate("DELETE FROM VentaFecha WHERE codVenta = '" + codFact.getText() + "-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "'");
                                                sentencia.executeUpdate("DELETE FROM TVentasFecha WHERE cod = '" + codFact.getText() + "-" + codFact2.getText() + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "'");
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (SQLException | HeadlessException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );

        anularCobros.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 3;
                        final JInternalFrame frame = new JInternalFrame("Anular Cobros", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codFact.setEditable(false);
                        codFact.setText("fact");
                        codFact2.setEditable(true);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        codFact2.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        codFact2.setEditable(false);
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        int cod = 0;
                                        tot = 0.00;

                                        try {
                                            modeloTabla.establecerConsulta("SELECT tCobros FROM TVentasFecha WHERE "
                                                    + "codigoVendedor = '" + cVendedor + "' AND (tCobros <> 0 OR tRetCobros <> 0) AND "
                                                    + "cod = '" + codFact.getText() + "-" + cFact + "' AND "
                                                    + "fecha = '" + nombreFecha + "'");
                                            campTOT.setText(String.format(Locale.UK, "%.2f", (Double) modeloTabla.getValueAt(0, 0)));
                                            guardar.setEnabled(true);
                                        } catch (IllegalStateException | SQLException ee) {
                                            JOptionPane.showMessageDialog(null, "Datos Invalidos! " + ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            codFact2.setEditable(true);
                                        }
                                    }
                                }
                        );

                        guardar.setText("Anular");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        guardar.setEnabled(false);
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        cFact = codFact2.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        total = campTOT.getText();
                                        tDespachos = Double.parseDouble(total);
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea anular?", "Anular", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                modeloTabla.establecerConsulta("SELECT tVentasCredito FROM TVentasFecha WHERE "
                                                        + "codigoVendedor = '" + cVendedor + "' AND tVentasCredito <> 0 AND "
                                                        + "cod = '" + codFact.getText() + "-" + cFact + "'");
                                                sentencia.executeUpdate("UPDATE VentaFecha SET cobrado = 0, total = " + (Double) modeloTabla.getValueAt(0, 0) + " WHERE codVenta = '" + codFact.getText() + "-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "' "
                                                        + "AND codCliente = '" + cCliente + "'");
                                                sentencia.executeUpdate("DELETE FROM TVentasFecha WHERE cod = '" + codFact.getText() + "-" + codFact2.getText() + "' "
                                                        + "AND fecha = '" + nombreFecha + "' "
                                                        + "AND codigoVendedor = '" + cVendedor + "' AND (tCobros <> 0 OR tRetCobros <> 0)");
                                            } catch (SQLException | IllegalStateException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                            JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        guardar.requestFocus();
                    }
                }
        );
///*********************************************************************
        agregarVendedor.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 12;
                        final JInternalFrame frame = new JInternalFrame("Nuevo Vendedor", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();
                        telCliente.setEditable(true);
                        idCliente.setEditable(true);

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        aVendedor = apellidoVendedor.getText();
                                        if (idCliente.getText().length() == 0 && telCliente.getText().length() == 0) {
                                            JOptionPane.showMessageDialog(frame, "Ingrese por lo menos uno de los datos del Vendedor");
                                            idCliente.requestFocus();
                                        } else {
                                            int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea agregar?", "Agregar Vendedor", JOptionPane.YES_NO_OPTION);
                                            if (q == JOptionPane.YES_OPTION) {
                                                try {
                                                    sentencia = conexion.createStatement();
                                                    if (idCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("INSERT INTO Vendedores VALUES('" + cVendedor + "', '"
                                                                + nVendedor + "', '" + aVendedor + "', 'S/N', '"
                                                                + telCliente.getText() + "', 0.00)");
                                                    } else if (telCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("INSERT INTO Vendedores VALUES('" + cVendedor + "', '"
                                                                + nVendedor + "', '" + aVendedor + "', '" + idCliente.getText()
                                                                + "', 'S/N', 0.00)");
                                                    } else {
                                                        sentencia.executeUpdate("INSERT INTO Vendedores VALUES('" + cVendedor + "', '"
                                                                + nVendedor + "', '" + aVendedor + "', '" + idCliente.getText() + "', '"
                                                                + telCliente.getText() + "', 0.00)");
                                                    }
                                                    JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                    frame.dispose();
                                                } catch (SQLException ee) {
                                                    JOptionPane.showMessageDialog(null, ee.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        editarVendedor.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 22;
                        final JInternalFrame frame = new JInternalFrame("Editar Vendedor", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        tabla.setEnabled(false);
                        codVendedor.setEditable(true);
                        codVendedor.requestFocus();

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        cVendedor = codVendedor.getText();
                                        nVendedor = nombreVendedor.getText();
                                        aVendedor = apellidoVendedor.getText();
                                        if (idCliente.getText().length() == 0 || telCliente.getText().length() == 0) {
                                            JOptionPane.showMessageDialog(frame, "Ingrese por lo menos uno de los datos del Vendedor");
                                            idCliente.requestFocus();
                                        } else {
                                            int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea editar?", "Editar Vendedor", JOptionPane.YES_NO_OPTION);
                                            if (q == JOptionPane.YES_OPTION) {
                                                try {
                                                    sentencia = conexion.createStatement();
                                                    sentencia.executeUpdate("UPDATE Vendedores SET nombreVendedor = '"
                                                            + nVendedor + "' WHERE codigoVendedor = '" + cVendedor + "'");
                                                    sentencia.executeUpdate("UPDATE Vendedores SET apellidoVendedor = '"
                                                            + aVendedor + "' WHERE codigoVendedor = '" + cVendedor + "'");
                                                    if (idCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("UPDATE Vendedores SET idVendedor = 'S/N'"
                                                                + "WHERE codigoVendedor = '" + cVendedor + "'");
                                                        sentencia.executeUpdate("UPDATE Vendedores SET telefono = '"
                                                                + telCliente.getText() + "' WHERE codigoVendedor = '" + cVendedor + "'");
                                                    } else if (telCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("UPDATE Vendedores SET idVendedor = '"
                                                                + idCliente.getText() + "' WHERE codigoVendedor = '" + cVendedor + "'");
                                                        sentencia.executeUpdate("UPDATE Vendedores SET telefono = 'S/N'"
                                                                + "WHERE codigoVendedor = '" + cVendedor + "'");
                                                    } else {
                                                        sentencia.executeUpdate("UPDATE Vendedores SET idVendedor = '"
                                                                + idCliente.getText() + "' WHERE codigoVendedor = '" + cVendedor + "'");
                                                        sentencia.executeUpdate("UPDATE Vendedores SET telefono = '"
                                                                + telCliente.getText() + "' WHERE codigoVendedor = '" + cVendedor + "'");
                                                    }
                                                    JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                    frame.dispose();
                                                } catch (SQLException ee) {
                                                    JOptionPane.showMessageDialog(null, ee.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        eliminarVendedor.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 22;
                        final JInternalFrame frame = new JInternalFrame("Eliminar Vendedor", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        guardar.setText("Eliminar");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        cVendedor = codVendedor.getText();
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea eliminar?", "Eliminar Vendedor", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                sentencia = conexion.createStatement();
                                                sentencia.executeUpdate("DELETE FROM Vendedores WHERE codigoVendedor = '" + cVendedor + "'");
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        agregarCliente.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 33;
                        final JInternalFrame frame = new JInternalFrame("Nuevo Cliente", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        nCliente = nombreCliente.getText();
                                        aCliente = apellidoCliente.getText();
                                        fPago = formaPago.getText();
                                        if (!(!(idCliente.getText().length() == 0) || !(telCliente.getText().length() == 0) || !(dirCliente.getText().length() == 0)) || fPago.length() == 0) {
                                            JOptionPane.showMessageDialog(frame, "Ingrese por lo menos uno de los datos del Cliente.\nCampo de Forma de Pago: Obligatorio");
                                            idCliente.requestFocus();
                                        } else {
                                            int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea agregar?", "Agregar Cliente", JOptionPane.YES_NO_OPTION);
                                            if (q == JOptionPane.YES_OPTION) {
                                                try {
                                                    sentencia = conexion.createStatement();
                                                    if (comCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("INSERT INTO Clientes VALUES('" + cCliente + "', '" + cVendedor + "', '"
                                                                + nCliente + "', '" + aCliente + "', 'S/N', '"
                                                                + idCliente.getText() + "', '" + dirCliente.getText() + "', '"
                                                                + telCliente.getText() + "', '" + fPago + "', 0.00, 0, 0.00)");
                                                    } else if (idCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("INSERT INTO Clientes VALUES('" + cCliente + "', '" + cVendedor + "', '"
                                                                + nCliente + "', '" + aCliente + "', '" + comCliente.getText() + "', 'S/N', '"
                                                                + dirCliente.getText() + "', '"
                                                                + telCliente.getText() + "', '" + fPago + "', 0.00, 0, 0.00)");
                                                    } else if (dirCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("INSERT INTO Clientes VALUES('" + cCliente + "', '" + cVendedor + "', '"
                                                                + nCliente + "', '" + aCliente + "', '" + comCliente.getText() + "', '"
                                                                + idCliente.getText() + "', 'S/N', '"
                                                                + telCliente.getText() + "', '" + fPago + "', 0.00, 0, 0.00)");
                                                    } else if (telCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("INSERT INTO Clientes VALUES('" + cCliente + "', '" + cVendedor + "', '"
                                                                + nCliente + "', '" + aCliente + "', '" + comCliente.getText() + "', '"
                                                                + idCliente.getText() + "', '" + dirCliente.getText() + "', 'S/N', '"
                                                                + fPago + "', 0.00, 0, 0.00)");
                                                    } else {
                                                        sentencia.executeUpdate("INSERT INTO Clientes VALUES('" + cCliente + "', '" + cVendedor + "', '"
                                                                + nCliente + "', '" + aCliente + "', '" + comCliente.getText() + "', '"
                                                                + idCliente.getText() + "', '" + dirCliente.getText() + "', '"
                                                                + telCliente.getText() + "', '" + fPago + "', 0.00, 0, 0.00)");
                                                    }
                                                    String ruta;
                                                    do {
                                                        ruta = String.valueOf(JOptionPane.showInputDialog(frame, "Indique la ruta para el cliente:",
                                                                        "Ruta",
                                                                        JOptionPane.INFORMATION_MESSAGE));
                                                        if (ruta.length() == 0) {
                                                            JOptionPane.showMessageDialog(frame, "Debe Ingresar la ruta!", "Error", JOptionPane.ERROR_MESSAGE);
                                                        } else {
                                                            sentencia.executeUpdate("INSERT INTO Rutas VALUES ('" + ruta + "', '" + cVendedor + "', '" + cCliente + "')");
                                                        }
                                                    } while (ruta.length() <= 0);
                                                    JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                    frame.dispose();
                                                } catch (SQLException ee) {
                                                    JOptionPane.showMessageDialog(null, ee.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        editarCliente.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 43;
                        final JInternalFrame frame = new JInternalFrame("Editar Cliente", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        formaPago.setEditable(true);

                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        nCliente = nombreCliente.getText();
                                        aCliente = apellidoCliente.getText();
                                        fPago = formaPago.getText();
                                        if (!(!(idCliente.getText().length() == 0) || !(telCliente.getText().length() == 0) || !(dirCliente.getText().length() == 0)) || fPago.length() == 0) {
                                            JOptionPane.showMessageDialog(frame, "Ingrese por lo menos uno de los datos del Cliente.\nCampo Forma de Pago: Obligatorio");
                                            idCliente.requestFocus();
                                        } else {
                                            int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea editar?", "Editar Cliente", JOptionPane.YES_NO_OPTION);
                                            if (q == JOptionPane.YES_OPTION) {
                                                try {
                                                    sentencia = conexion.createStatement();
                                                    sentencia.executeUpdate("UPDATE Clientes SET nombreCliente = '"
                                                            + nCliente + "' WHERE codCliente = '" + cCliente + "'");
                                                    sentencia.executeUpdate("UPDATE Clientes SET apellidoCliente = '"
                                                            + aCliente + "' WHERE codigoVendedor = '" + cCliente + "'");
                                                    if (comCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("UPDATE Clientes SET comercial = 'S/N"
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET idCliente = '" + idCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET direccion = '" + dirCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET telefono = '" + telCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                    } else if (idCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("UPDATE Clientes SET comercial = '" + comCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET idCliente = 'S/N"
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET direccion = '" + dirCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET telefono = '" + telCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                    } else if (dirCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("UPDATE Clientes SET comercial = '" + comCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET idCliente = '" + idCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET direccion = 'S/N"
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET telefono = '" + telCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                    } else if (telCliente.getText().length() == 0) {
                                                        sentencia.executeUpdate("UPDATE Clientes SET comercial = '" + comCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET idCliente = '" + idCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET direccion = '" + dirCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET telefono = 'S/N"
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                    } else {
                                                        sentencia.executeUpdate("UPDATE Clientes SET comercial = '" + comCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET idCliente = '" + idCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET direccion = '" + dirCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                        sentencia.executeUpdate("UPDATE Clientes SET telefono = '" + telCliente.getText()
                                                                + "' WHERE codCliente = '" + cCliente + "'");
                                                    }
                                                    sentencia.executeUpdate("UPDATE Clientes SET formaPago = '" + fPago + "' WHERE codCliente = '" + cCliente + "'");
                                                    sentencia.executeUpdate("UPDATE Clientes SET nombreCliente = '" + nCliente + "' WHERE codCliente = '" + cCliente + "'");
                                                    sentencia.executeUpdate("UPDATE Clientes SET apellidoCliente = '" + aCliente + "' WHERE codCliente = '" + cCliente + "'");
                                                    JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                    frame.dispose();
                                                } catch (SQLException ee) {
                                                    JOptionPane.showMessageDialog(null, ee.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        eliminarCliente.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 43;
                        final JInternalFrame frame = new JInternalFrame("Eliminar Cliente", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        guardar.setText("Eliminar");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        cVendedor = codVendedor.getText();
                                        cCliente = codCliente.getText();
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea eliminar?", "Eliminar Cliente", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                sentencia = conexion.createStatement();
                                                sentencia.executeUpdate("DELETE FROM Clientes WHERE codigoVendedor = '" + cVendedor
                                                        + "' AND codCliente = '" + cCliente + "'");
                                                sentencia.executeUpdate("DELETE FROM Rutas WHERE codigoVendedor = '" + cVendedor
                                                        + "' AND codCliente = '" + cCliente + "'");
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                            }
                                        }
                                    }
                                }
                        );

                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir sin guardar?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );
///*********************************************************************

        menuInventario.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = true;
                        flag = 101;
                        final JInternalFrame frame = new JInternalFrame("Existencia", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        guardar.setEnabled(true);

                        try {
                            modeloTabla.establecerConsulta("SELECT cantidad, producto, precioUnitario, total "
                                    + "FROM Productos WHERE cod = 0");
                        } catch (SQLException | IllegalStateException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        guardar.setText("Consultar");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        if (dia.getText().equals("30") || dia.getText().equals("31") || (mes.getText().equals("02") && (dia.getText().equals("28") || dia.getText().equals("29")))) {
                                            existenciaInicial.setEnabled(true);
                                        } else {
                                            existenciaInicial.setEnabled(false);
                                        }
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        int cod = 0;
                                        int init, cant, eg, ob, camb;
                                        double tInit, totalP, totalEg, totalOb, totalCamb, totE;
                                        try {
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM Existencia WHERE fecha = '" + anio.getText() + "/" + mes.getText() + "/01' "
                                                            + "AND cod = '" + cod + "'");
                                                    init = (Integer) modeloTabla.getValueAt(0, 0);

                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM ProdFecha WHERE fecha >= '" + anio.getText() + "/" + mes.getText() + "/01' "
                                                            + "AND fecha <= '" + nombreFecha + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    cant = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        cant += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM DespFecha WHERE fecha >= '" + anio.getText() + "/" + mes.getText() + "/01' "
                                                            + "AND fecha <= '" + nombreFecha + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    eg = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        eg += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM CambiosFecha WHERE fecha >= '" + anio.getText() + "/" + mes.getText() + "/01' "
                                                            + "AND fecha <= '" + nombreFecha + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    camb = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        camb += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM ObsequiosFecha WHERE fecha >= '" + anio.getText() + "/" + mes.getText() + "/01' "
                                                            + "AND fecha <= '" + nombreFecha + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    ob = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        ob += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    tabla.setValueAt(init + cant - eg - camb - ob, i, 0);
                                                }
                                            }
                                            cod = 0;
                                            totE = 0.00;
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM Existencia WHERE fecha = '" + anio.getText() + "/" + mes.getText() + "/01" + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    tInit = (Double) modeloTabla.getValueAt(0, 0);

                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM ProdFecha WHERE fecha >= '" + anio.getText() + "/" + mes.getText() + "/01' "
                                                            + "AND fecha <= '" + nombreFecha + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    totalP = 0.00;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        totalP += (Double) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM DespFecha WHERE fecha >= '" + anio.getText() + "/" + mes.getText() + "/01' "
                                                            + "AND fecha <= '" + nombreFecha + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    totalEg = 0.00;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        totalEg += (Double) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM CambiosFecha WHERE fecha >= '" + anio.getText() + "/" + mes.getText() + "/01' "
                                                            + "AND fecha <= '" + nombreFecha + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    totalCamb = 0.00;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        totalCamb += (Double) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM ObsequiosFecha WHERE fecha >= '" + anio.getText() + "/" + mes.getText() + "/01' "
                                                            + "AND fecha <= '" + nombreFecha + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    totalOb = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        totalOb += (Double) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    prod[i] = tInit + totalP - totalEg - totalCamb - totalOb;
                                                    //prod[i] = (Integer)tabla.getValueAt(i, 0) * Double.parseDouble((String)tabla.getValueAt(i, 1));
                                                    tabla.setValueAt(prod[i], i, 3);
                                                    totE += prod[i];
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", totE));
                                        } catch (IllegalStateException | SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );

                        existenciaInicial.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        existenciaInicial.setEnabled(false);
                                        if (Integer.parseInt(mes.getText()) == 12) {
                                            int ano = Integer.parseInt(anio.getText()) + 1;
                                            anio.setText(String.valueOf(ano));
                                            mes.setText("01");
                                            dia.setText("01");
                                        } else {
                                            int m = Integer.parseInt(mes.getText()) + 1;
                                            mes.setText(String.format("%02d", m));
                                            dia.setText("01");
                                        }
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        int cod = 0;
                                        int cant;
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            //mensaje.setText("Espere mientras se guardan los datos");
                                            try {
                                                sentencia = conexion.createStatement();
                                                for (int i = 0; i < data.length; i++) {
                                                    if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                        ++cod;
                                                        cant = (Integer) tabla.getValueAt(i, 0);
                                                        sentencia.executeUpdate("INSERT INTO Existencia VALUES ('" + nombreFecha + "', '"
                                                                + cod + "', '" + cant + "', '"
                                                                + prod[i] + "')");
                                                        //progresoSQL.setValue(cod * 100 / 52);
                                                        //estadoSQL.setText(String.format("Completado el %d%%.\n", (cod * 100 / 52)));
                                                    }
                                                }
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                frame.dispose();
                                            } catch (HeadlessException | SQLException ee) {
                                                JOptionPane.showMessageDialog(null, ee.getMessage());
                                                frame.dispose();
                                            }
                                        }
                                        //mensaje.setText("");
                                        //progresoSQL.setValue(0);
                                        //estadoSQL.setText("");
                                    }
                                }
                        );

                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        menuVentas.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 222;
                        final JInternalFrame frame = new JInternalFrame("Ventas por Vendedor", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        guardar.setEnabled(true);
                        totalEntrega.setEditable(false);

                        try {
                            modeloTabla.establecerConsulta("SELECT cantidad, producto, precioUnitario, total "
                                    + "FROM Productos WHERE cod = 0");
                        } catch (IllegalStateException | SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        guardar.setText("Consultar");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        cVendedor = codVendedor.getText();
                                        codVendedor.setEditable(true);
                                        int cod = 0, np = 0, npl = 0, ny = 0;
                                        int init, cant, dev;
                                        double tInit, totalP, totalDev, totE;
                                        double p = 0.00, pl = 0.00, y = 0.00;
                                        try {
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM DevAFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    init = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        init += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM DespFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    cant = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        cant += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM DevFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    dev = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        dev += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    tabla.setValueAt(init + cant - dev, i, 0);
                                                    if (cod <= 37 || cod == 51) {
                                                        np += init + cant - dev;
                                                    } else if (cod > 37 && cod <= 47) {
                                                        npl += init + cant - dev;
                                                    } else if (cod > 47 && cod <= 50) {
                                                        ny += init + cant - dev;
                                                    }
                                                }
                                            }
                                            cod = 0;
                                            totE = 0.00;
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM DevAFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    tInit = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        tInit += (Double) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM DespFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    totalP = 0.00;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        totalP += (Double) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM DevFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    totalDev = 0.00;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        totalDev += (Double) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    prod[i] = tInit + totalP - totalDev;
                                                    tabla.setValueAt(tInit + totalP - totalDev, i, 3);
                                                    totE += tInit + totalP - totalDev;
                                                    if (cod <= 37 || cod == 51) {
                                                        p += prod[i];
                                                    } else if (cod > 37 && cod <= 47) {
                                                        pl += prod[i];
                                                    } else if (cod > 47 && cod <= 50) {
                                                        y += prod[i];
                                                    }
                                                }
                                            }
                                            modeloTabla.establecerConsulta("SELECT tDescuentos FROM TVentasFecha WHERE codigoVendedor = '" + cVendedor + "' "
                                                    + "AND fecha >= '" + nombreFecha + "' AND fecha <= '" + nombreFecha2 + "' "
                                                    + "AND tDescuentos <> 0");
                                            if (modeloTabla.getRowCount() == 0) {
                                                totalEspera.setText(String.format(Locale.UK, "%.2f", 0.00));
                                            } else {
                                                double descuent = 0.00;
                                                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                                                    descuent += (Double) modeloTabla.getValueAt(i, 0);
                                                }
                                                totalEspera.setText(String.format(Locale.UK, "%.2f", descuent));
                                            }

                                            modeloTabla.establecerConsulta("SELECT entrega, resto FROM EvalVendedores WHERE codigoVendedor = '" + cVendedor + "' "
                                                    + "AND fecha >= '" + nombreFecha + "' AND fecha <= '" + nombreFecha2 + "'");
                                            if (modeloTabla.getRowCount() == 0) {
                                                totalEntrega.setText(String.format(Locale.UK, "%.2f", 0.00));
                                                totalResto.setText(String.format(Locale.UK, "%.2f", 0.00));
                                            } else {
                                                double entreg = 0.00, res = 0.00;
                                                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                                                    entreg += (Double) modeloTabla.getValueAt(i, 0);
                                                    res += (Double) modeloTabla.getValueAt(i, 1);
                                                }
                                                totalEntrega.setText(String.format(Locale.UK, "%.2f", entreg));
                                                totalResto.setText(String.format(Locale.UK, "%.2f", res));
                                            }

                                            campTOT.setText(String.format(Locale.UK, "%.2f", totE));
                                            pesoPapas.setText(String.format(Locale.UK, "%d", np));
                                            pesoPlatano.setText(String.format(Locale.UK, "%d", npl));
                                            pesoYuca.setText(String.format(Locale.UK, "%d", ny));
                                            pesoPapas2.setText(String.format(Locale.UK, "%.2f", p));
                                            pesoPlatano2.setText(String.format(Locale.UK, "%.2f", pl));
                                            pesoYuca2.setText(String.format(Locale.UK, "%.2f", y));
                                        } catch (SQLException | IllegalStateException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );
                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        menuTotalVentas.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 222;
                        final JInternalFrame frame = new JInternalFrame("Ventas Totales", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        guardar.setEnabled(true);
                        totalEntrega.setEditable(false);

                        try {
                            modeloTabla.establecerConsulta("SELECT cantidad, producto, precioUnitario, total "
                                    + "FROM Productos WHERE cod = 0");
                        } catch (SQLException | IllegalStateException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        guardar.setText("Consultar");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        cVendedor = codVendedor.getText();
                                        codVendedor.setEditable(false);
                                        int cod = 0, np = 0, npl = 0, ny = 0;
                                        int init, cant, dev;
                                        double tInit, totalP, totalDev, totE;
                                        double p = 0.00, pl = 0.00, y = 0.00;
                                        try {
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM DevAFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    init = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        init += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM DespFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    cant = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        cant += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM DevFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    dev = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        dev += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    tabla.setValueAt(init + cant - dev, i, 0);
                                                    if (cod <= 37 || cod == 51) {
                                                        np += init + cant - dev;
                                                    } else if (cod > 37 && cod <= 47) {
                                                        npl += init + cant - dev;
                                                    } else if (cod > 47 && cod <= 50) {
                                                        ny += init + cant - dev;
                                                    }
                                                }
                                            }
                                            cod = 0;
                                            totE = 0.00;
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM DevAFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    tInit = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        tInit += (Double) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM DespFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    totalP = 0.00;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        totalP += (Double) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM DevFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    totalDev = 0.00;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        totalDev += (Double) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    prod[i] = tInit + totalP - totalDev;
                                                    tabla.setValueAt(tInit + totalP - totalDev, i, 3);
                                                    totE += tInit + totalP - totalDev;
                                                    if (cod <= 37 || cod == 51) {
                                                        p += prod[i];
                                                    } else if (cod > 37 && cod <= 47) {
                                                        pl += prod[i];
                                                    } else if (cod > 47 && cod <= 50) {
                                                        y += prod[i];
                                                    }
                                                }
                                            }
                                            modeloTabla.establecerConsulta("SELECT tDescuentos FROM TVentasFecha "
                                                    + "WHERE fecha >= '" + nombreFecha + "' AND fecha <= '" + nombreFecha2 + "' "
                                                    + "AND tDescuentos <> 0");
                                            if (modeloTabla.getRowCount() == 0) {
                                                totalEspera.setText(String.format(Locale.UK, "%.2f", 0.00));
                                            } else {
                                                double descuent = 0.00;
                                                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                                                    descuent += (Double) modeloTabla.getValueAt(i, 0);
                                                }
                                                totalEspera.setText(String.format(Locale.UK, "%.2f", descuent));
                                            }

                                            modeloTabla.establecerConsulta("SELECT entrega, resto FROM EvalVendedores "
                                                    + "WHERE fecha >= '" + nombreFecha + "' AND fecha <= '" + nombreFecha2 + "'");
                                            if (modeloTabla.getRowCount() == 0) {
                                                totalEntrega.setText(String.format(Locale.UK, "%.2f", 0.00));
                                                totalResto.setText(String.format(Locale.UK, "%.2f", 0.00));
                                            } else {
                                                double entreg = 0.00, res = 0.00;
                                                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                                                    entreg += (Double) modeloTabla.getValueAt(i, 0);
                                                    res += (Double) modeloTabla.getValueAt(i, 1);
                                                }
                                                totalEntrega.setText(String.format(Locale.UK, "%.2f", entreg));
                                                totalResto.setText(String.format(Locale.UK, "%.2f", res));
                                            }

                                            campTOT.setText(String.format(Locale.UK, "%.2f", totE));
                                            pesoPapas.setText(String.format(Locale.UK, "%d", np));
                                            pesoPlatano.setText(String.format(Locale.UK, "%d", npl));
                                            pesoYuca.setText(String.format(Locale.UK, "%d", ny));
                                            pesoPapas2.setText(String.format(Locale.UK, "%.2f", p));
                                            pesoPlatano2.setText(String.format(Locale.UK, "%.2f", pl));
                                            pesoYuca2.setText(String.format(Locale.UK, "%.2f", y));
                                        } catch (IllegalStateException | SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );
                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        totalCambios.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 222;
                        final JInternalFrame frame = new JInternalFrame("Total Cambios", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        guardar.setEnabled(true);

                        try {
                            modeloTabla.establecerConsulta("SELECT cantidad, producto, precioUnitario, total "
                                    + "FROM Productos WHERE cod = 0");
                        } catch (IllegalStateException | SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        guardar.setText("Consultar");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        cVendedor = codVendedor.getText();
                                        codVendedor.setEditable(true);
                                        int cod = 0;
                                        int cant;
                                        double totalP, totE;
                                        try {
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM CambiosFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    cant = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        cant += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    tabla.setValueAt(cant, i, 0);
                                                }
                                            }
                                            cod = 0;
                                            totE = 0.00;
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM CambiosFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    totalP = 0.00;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        totalP += (Double) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    prod[i] = totalP;
                                                    tabla.setValueAt(totalP, i, 3);
                                                    totE += totalP;
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", totE));
                                        } catch (SQLException | IllegalStateException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );
                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        totalObsequios.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 222;
                        final JInternalFrame frame = new JInternalFrame("Total Obsequios", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        guardar.setEnabled(true);

                        try {
                            modeloTabla.establecerConsulta("SELECT cantidad, producto, precioUnitario, total "
                                    + "FROM Productos WHERE cod = 0");
                        } catch (IllegalStateException | SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        guardar.setText("Consultar");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        cVendedor = codVendedor.getText();
                                        codVendedor.setEditable(true);
                                        int cod = 0;
                                        int cant;
                                        double totalP, totE;
                                        try {
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM ObsequiosFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    cant = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        cant += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    tabla.setValueAt(cant, i, 0);
                                                }
                                            }
                                            cod = 0;
                                            totE = 0.00;
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM ObsequiosFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codigoVendedor = '" + cVendedor + "'");
                                                    totalP = 0.00;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        totalP += (Double) modeloTabla.getValueAt(j, 0);
                                                    }

                                                    prod[i] = totalP;
                                                    tabla.setValueAt(totalP, i, 3);
                                                    totE += totalP;
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", totE));
                                        } catch (SQLException | IllegalStateException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );
                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        menuProduccion.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = true;
                        flag = 1;
                        final JInternalFrame frame = new JInternalFrame("Produccion", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabla1);
                        tablaEncabezado.setRowSorter(sorter);
                        sorter.setRowFilter(null);

                        try {
                            modeloTabla.establecerConsulta("SELECT fecha, Socios.codSocio, nombre, apellido "
                                    + "FROM Socios, ProdFecha WHERE ProdFecha.codSocio = Socios.codSocio "
                                    + "AND Socios.codSocio = '0'");
                            modeloTabla1.establecerConsulta("SELECT fecha, Socios.codSocio, nombre, apellido "
                                    + "FROM Socios, ProdFecha WHERE ProdFecha.codSocio = Socios.codSocio "
                                    + "AND cod = 1 AND Socios.codSocio = '0'");
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }

                        botonFiltro.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        String texto = textoFiltro.getText();
                                        if (texto.length() == 0) {
                                            sorter.setRowFilter(null);
                                        } else {
                                            try {
                                                sorter.setRowFilter(RowFilter.regexFilter(texto));
                                            } catch (PatternSyntaxException pse) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Patron de filtro incorrecto", "Patron de exp reg incorrecto", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                        );

                        guardar.setText("Consultar");
                        guardar.setEnabled(true);
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        consultarInv = "SELECT DATE_FORMAT(fecha, '%Y-%m-%d') AS fecha, Socios.codSocio, nombre, apellido "
                                        + "FROM Socios, ProdFecha WHERE ProdFecha.codSocio = Socios.codSocio "
                                        + "AND cod = 1 AND fecha >= '" + nombreFecha + "' "
                                        + "AND fecha <= '" + nombreFecha2 + "' ORDER BY fecha";
                                        try {
                                            modeloTabla1.establecerConsulta(consultarInv);
                                        } catch (SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );

                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        totalProduccion.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 111;
                        final JInternalFrame frame = new JInternalFrame("Produccion Acumulada Socios", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        guardar.setEnabled(true);

                        try {
                            modeloTabla.establecerConsulta("SELECT cantidad, producto, precioUnitario, total "
                                    + "FROM Productos WHERE cod = 0");
                        } catch (SQLException | IllegalStateException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        guardar.setText("Consultar");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        cSocio = codSocio.getText();
                                        codSocio.setEditable(true);
                                        int cod = 0;
                                        int pr, np = 0, npl = 0, ny = 0, nm = 0, ns = 0;
                                        double tP, totE;
                                        double t = 0.00, p = 0.00, pl = 0.00, y = 0.00, m = 0.00, s = 0.00;
                                        try {
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM ProdFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codSocio = '" + cSocio + "'");
                                                    pr = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        pr += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    tabla.setValueAt(pr, i, 0);
                                                    if (cod <= 28 || cod == 51) {
                                                        np += pr;
                                                    } else if (cod > 28 && cod <= 35) {
                                                        npl += pr;
                                                    } else if (cod > 35 && cod <= 37) {
                                                        ny += pr;
                                                    } else if (cod > 37 && cod <= 47) {
                                                        nm += pr;
                                                    } else if (cod > 47 && cod <= 50) {
                                                        ns += pr;
                                                    }
                                                }
                                            }
                                            cod = 0;
                                            totE = 0.00;
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM ProdFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "' AND codSocio = '" + cSocio + "'");
                                                    tP = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        tP += (Double) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    prod[i] = tP;
                                                    tabla.setValueAt(tP, i, 3);
                                                    totE += tP;
                                                    if (cod <= 28 || cod == 51) {
                                                        p += prod[i];
                                                    } else if (cod > 28 && cod <= 35) {
                                                        pl += prod[i];
                                                    } else if (cod > 35 && cod <= 37) {
                                                        y += prod[i];
                                                    } else if (cod > 37 && cod <= 47) {
                                                        m += prod[i];
                                                    } else if (cod > 47 && cod <= 50) {
                                                        s += prod[i];
                                                    }
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", totE));
                                            pesoPapas.setText(String.format(Locale.UK, "%d", np));
                                            pesoPlatano.setText(String.format(Locale.UK, "%d", npl));
                                            pesoYuca.setText(String.format(Locale.UK, "%d", ny));
                                            pesoMani.setText(String.format(Locale.UK, "%d", nm));
                                            pesoSisco.setText(String.format(Locale.UK, "%d", ns));
                                            pesoPapas2.setText(String.format(Locale.UK, "%.2f", p));
                                            pesoPlatano2.setText(String.format(Locale.UK, "%.2f", pl));
                                            pesoYuca2.setText(String.format(Locale.UK, "%.2f", y));
                                            pesoMani2.setText(String.format(Locale.UK, "%.2f", m));
                                            pesoSisco2.setText(String.format(Locale.UK, "%.2f", s));
                                        } catch (SQLException | IllegalStateException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );
                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        TProduccion.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = false;
                        flag = 111;
                        final JInternalFrame frame = new JInternalFrame("Produccion Total", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        guardar.setEnabled(true);
                        codSocio.setEditable(false);
                        codSocio.setEnabled(false);

                        try {
                            modeloTabla.establecerConsulta("SELECT cantidad, producto, precioUnitario, total "
                                    + "FROM Productos WHERE cod = 0");
                        } catch (IllegalStateException | SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }
                        guardar.setText("Consultar");
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        int cod = 0;
                                        int pr, np = 0, npl = 0, ny = 0, nm = 0, ns = 0;
                                        double tP, totE;
                                        double t = 0.00, p = 0.00, pl = 0.00, y = 0.00, m = 0.00, s = 0.00;
                                        try {
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT cantidad "
                                                            + "FROM ProdFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    pr = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        pr += (Integer) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    tabla.setValueAt(pr, i, 0);
                                                    if (cod <= 28 || cod == 51) {
                                                        np += pr;
                                                    } else if (cod > 28 && cod <= 35) {
                                                        npl += pr;
                                                    } else if (cod > 35 && cod <= 37) {
                                                        ny += pr;
                                                    } else if (cod > 37 && cod <= 47) {
                                                        nm += pr;
                                                    } else if (cod > 47 && cod <= 50) {
                                                        ns += pr;
                                                    }
                                                }
                                            }
                                            cod = 0;
                                            totE = 0.00;
                                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                                if (!(i == 0 || i == 10 || i == 12 || i == 20 || i == 28 || i == 33 || i == 37 || i == 41 || i == 43 || i == 46 || i == 52 || i == 58 || i == 62 || i == 64)) {
                                                    ++cod;
                                                    modeloTabla.establecerConsulta("SELECT total "
                                                            + "FROM ProdFecha WHERE fecha >= '" + nombreFecha + "' "
                                                            + "AND fecha <= '" + nombreFecha2 + "' "
                                                            + "AND cod = '" + cod + "'");
                                                    tP = 0;
                                                    for (int j = 0; j < modeloTabla.getRowCount(); j++) {
                                                        tP += (Double) modeloTabla.getValueAt(j, 0);
                                                    }
                                                    prod[i] = tP;
                                                    tabla.setValueAt(tP, i, 3);
                                                    totE += tP;
                                                    if (cod <= 28 || cod == 51) {
                                                        p += prod[i];
                                                    } else if (cod > 28 && cod <= 35) {
                                                        pl += prod[i];
                                                    } else if (cod > 35 && cod <= 37) {
                                                        y += prod[i];
                                                    } else if (cod > 37 && cod <= 47) {
                                                        m += prod[i];
                                                    } else if (cod > 47 && cod <= 50) {
                                                        s += prod[i];
                                                    }
                                                }
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", totE));
                                            pesoPapas.setText(String.format(Locale.UK, "%d", np));
                                            pesoPlatano.setText(String.format(Locale.UK, "%d", npl));
                                            pesoYuca.setText(String.format(Locale.UK, "%d", ny));
                                            pesoMani.setText(String.format(Locale.UK, "%d", nm));
                                            pesoSisco.setText(String.format(Locale.UK, "%d", ns));
                                            pesoPapas2.setText(String.format(Locale.UK, "%.2f", p));
                                            pesoPlatano2.setText(String.format(Locale.UK, "%.2f", pl));
                                            pesoYuca2.setText(String.format(Locale.UK, "%.2f", y));
                                            pesoMani2.setText(String.format(Locale.UK, "%.2f", m));
                                            pesoSisco2.setText(String.format(Locale.UK, "%.2f", s));
                                        } catch (IllegalStateException | SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );
                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        frame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = true;
                        flag = 2;
                        final JInternalFrame frame = new JInternalFrame("Despachos", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabla1);
                        tablaEncabezado.setRowSorter(sorter);
                        sorter.setRowFilter(null);

                        try {
                            modeloTabla.establecerConsulta("SELECT DespFecha.cantidad, producto, precioUnitario, DespFecha.total "
                                    + "FROM DespFecha, Productos WHERE DespFecha.cod = Productos.cod "
                                    + "AND codigoVendedor = '0'");
                            modeloTabla1.establecerConsulta("SELECT fecha, codDespacho, Vendedores.codigoVendedor, nombreVendedor, apellidoVendedor "
                                    + "FROM Vendedores, DespFecha WHERE DespFecha.codigoVendedor = Vendedores.codigoVendedor "
                                    + "AND cod = 1 AND Vendedores.codigoVendedor = '0'");
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }

                        botonFiltro.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        String texto = textoFiltro.getText();
                                        if (texto.length() == 0) {
                                            sorter.setRowFilter(null);
                                        } else {
                                            try {
                                                sorter.setRowFilter(RowFilter.regexFilter(texto));
                                            } catch (PatternSyntaxException pse) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Patron de filtro incorrecto", "Patron de exp reg incorrecto", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                        );

                        guardar.setText("Consultar");
                        guardar.setEnabled(true);
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        consultarInv = "SELECT DATE_FORMAT(fecha, '%Y-%m-%d') AS fecha, codDespacho, Vendedores.codigoVendedor, nombreVendedor, apellidoVendedor "
                                        + "FROM Vendedores, DespFecha WHERE DespFecha.codigoVendedor = Vendedores.codigoVendedor "
                                        + "AND cod = 1 AND fecha >= '" + nombreFecha + "' "
                                        + "AND fecha <= '" + nombreFecha2 + "' ORDER BY fecha";
                                        try {
                                            modeloTabla1.establecerConsulta(consultarInv);
                                        } catch (SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );

                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        menuContado.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = true;
                        flag = 3;
                        final JInternalFrame frame = new JInternalFrame("Ventas al Contado", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabla1);
                        tablaEncabezado.setRowSorter(sorter);
                        sorter.setRowFilter(null);

                        try {
                            modeloTabla.establecerConsulta("SELECT VentaFecha.cantidad, producto, precioUnitario, VentaFecha.total "
                                    + "FROM VentaFecha, Productos WHERE VentaFecha.cod = Productos.cod "
                                    + "AND codigoVendedor = '0'");
                            modeloTabla1.establecerConsulta("SELECT fecha, codVenta, Vendedores.codigoVendedor,  Clientes.codCliente, nombreCliente, apellidoCliente "
                                    + "FROM Vendedores, Clientes, VentaFecha WHERE VentaFecha.codigoVendedor = Vendedores.codigoVendedor "
                                    + "AND VentaFecha.codCliente = Clientes.codCliente AND formaPago = '' AND cod = 1 ORDER BY fecha");
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }

                        botonFiltro.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        String texto = textoFiltro.getText();
                                        if (texto.length() == 0) {
                                            sorter.setRowFilter(null);
                                        } else {
                                            try {
                                                sorter.setRowFilter(RowFilter.regexFilter(texto));
                                            } catch (PatternSyntaxException pse) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Patron de filtro incorrecto", "Patron de exp reg incorrecto", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                        );

                        guardar.setText("Consultar");
                        guardar.setEnabled(true);
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        cVendedor = codVendedor.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        consultarVend = "SELECT DATE_FORMAT(fecha, '%Y-%m-%d') AS fecha, codVenta, Vendedores.codigoVendedor, Clientes.codCliente, nombreCliente, apellidoCliente "
                                        + "FROM Vendedores, Clientes, VentaFecha WHERE VentaFecha.codigoVendedor = Vendedores.codigoVendedor "
                                        + "AND VentaFecha.codCliente = Clientes.codCliente AND formaPago = 'Contado' AND cod = 1 "
                                        + "AND fecha >= '" + nombreFecha + "' AND fecha <= '" + nombreFecha2 + "' ORDER BY fecha";
                                        try {
                                            sentencia = conexion.createStatement();
                                            modeloTabla1.establecerConsulta(consultarVend);
                                        } catch (SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );

                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                        guardar.requestFocus();
                    }
                }
        );

        menuCredito.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = true;
                        flag = 4;
                        final JInternalFrame frame = new JInternalFrame("Ventas al Credito", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabla1);
                        tablaEncabezado.setRowSorter(sorter);
                        sorter.setRowFilter(null);

                        try {
                            modeloTabla.establecerConsulta("SELECT VentaFecha.cantidad, producto, precioUnitario, VentaFecha.total "
                                    + "FROM VentaFecha, Productos WHERE VentaFecha.cod = Productos.cod "
                                    + "AND codigoVendedor = '0'");
                            modeloTabla1.establecerConsulta("SELECT fecha, codVenta, Vendedores.codigoVendedor, Clientes.codCliente, nombreCliente, apellidoCliente "
                                    + "FROM Vendedores, Clientes, VentaFecha WHERE VentaFecha.codigoVendedor = Vendedores.codigoVendedor "
                                    + "AND VentaFecha.codCliente = Clientes.codCliente AND formaPago = '' AND cod = 1 ORDER BY codCliente, fecha");
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }

                        botonFiltro.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        String texto = textoFiltro.getText();
                                        if (texto.length() == 0) {
                                            sorter.setRowFilter(null);
                                        } else {
                                            try {
                                                sorter.setRowFilter(RowFilter.regexFilter(texto));
                                            } catch (PatternSyntaxException pse) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Patron de filtro incorrecto", "Patron de exp reg incorrecto", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                        );

                        guardar.setText("Consultar");
                        guardar.setEnabled(true);
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        cVendedor = codVendedor.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        consultarVend = "SELECT DATE_FORMAT(VentaFecha.fecha, '%Y-%m-%d') AS fecha, codVenta, Vendedores.codigoVendedor, Clientes.codCliente, nombreCliente, apellidoCliente, tVentasCredito, IF(tVentasCredito = total, 'NO TIENE', 'TIENE') Retencion "
                                        + "FROM Vendedores, Clientes, VentaFecha, TVentasFecha WHERE VentaFecha.codigoVendedor = Vendedores.codigoVendedor "
                                        + "AND VentaFecha.codCliente = Clientes.codCliente AND formaVentas = 'Credito' AND VentaFecha.cod = 1 AND cobrado < 2 "
                                        + "AND VentaFecha.fecha >= '" + nombreFecha + "' AND VentaFecha.fecha <= '" + nombreFecha2 + "' AND tVentasCredito <> 0 "
                                        + "AND TVentasFecha.fecha = VentaFecha.fecha AND TVentasFecha.cod = codVenta AND TVentasFecha.codigoVendedor = VentaFecha.codigoVendedor "
                                        + "ORDER BY codCliente, VentaFecha.fecha";
                                        try {
                                            sentencia = conexion.createStatement();
                                            modeloTabla1.establecerConsulta(consultarVend);
                                            JOptionPane.showMessageDialog(null, "Tiene un total de: " + String.valueOf(modeloTabla1.getRowCount()) + " Creditos pendientes\n\tpara el periodo seleccionado.");
                                        } catch (SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );

                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                        guardar.requestFocus();
                    }
                }
        );

        menuCredVend.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = true;
                        flag = 3;
                        final JInternalFrame frame = new JInternalFrame("Credito por Vendedores", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabla);
                        tablaConsulta.setRowSorter(sorter);
                        sorter.setRowFilter(null);

                        try {
                            modeloTabla.establecerConsulta("SELECT VentaFecha.cantidad, producto, precioUnitario, VentaFecha.total "
                                    + "FROM VentaFecha, Productos WHERE VentaFecha.cod = Productos.cod "
                                    + "AND codigoVendedor = '0'");
                            modeloTabla1.establecerConsulta("SELECT codigoVendedor, nombreVendedor, apellidoVendedor, idVendedor, ventas "
                                    + "FROM Vendedores ORDER BY codigoVendedor");
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }

                        botonFiltro.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        String texto = textoFiltro.getText();
                                        if (texto.length() == 0) {
                                            sorter.setRowFilter(null);
                                        } else {
                                            try {
                                                sorter.setRowFilter(RowFilter.regexFilter(texto));
                                                double tot = 0.00;
                                                for (int i = 0; i < tablaConsulta.getRowCount(); i++) {
                                                    tot += (Double) tablaConsulta.getValueAt(i, 6);
                                                }
                                                campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                                JOptionPane.showMessageDialog(null, "Tiene un total de: " + String.valueOf(tablaConsulta.getRowCount()) + " Creditos pendientes\n\tpara el periodo seleccionado.");
                                            } catch (HeadlessException e) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Patron de filtro incorrecto", "Patron de exp reg incorrecto", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                        );

                        guardar.setText("Consultar");
                        guardar.setEnabled(true);
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        cVendedor = codVendedor.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        consultarVend = "SELECT DISTINCT DATE_FORMAT(VentaFecha.fecha, '%Y-%m-%d') AS fecha, codVenta, Vendedores.codigoVendedor, Clientes.codCliente, nombreCliente, apellidoCliente, total Resto, IF(cobrado = 1, 'Parcial', 'Sin Cobrar') COBRO, IF(tVentasCredito = total AND cobrado = 0, 'NO TIENE', 'TIENE') Retencion "
                                        + "FROM Vendedores, Clientes, VentaFecha, TVentasFecha WHERE VentaFecha.codigoVendedor = Vendedores.codigoVendedor "
                                        + "AND VentaFecha.codCliente = Clientes.codCliente AND formaVentas = 'Credito' AND VentaFecha.cod = 1 AND cobrado < 2 "
                                        + "AND VentaFecha.fecha >= '" + nombreFecha + "' AND VentaFecha.fecha <= '" + nombreFecha2 + "' AND tVentasCredito <> 0 "
                                        + "AND TVentasFecha.fecha = VentaFecha.fecha AND TVentasFecha.cod = codVenta AND TVentasFecha.codigoVendedor = VentaFecha.codigoVendedor "
                                        + "ORDER BY codCliente, VentaFecha.fecha";
                                        try {
                                            sentencia = conexion.createStatement();
                                            modeloTabla.establecerConsulta(consultarVend);
                                            double tot = 0.00;
                                            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                                                tot += (Double) tablaConsulta.getValueAt(i, 6);
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                            JOptionPane.showMessageDialog(null, "Tiene un total de: " + String.valueOf(modeloTabla.getRowCount()) + " Creditos pendientes\n\tpara el periodo seleccionado.");
                                        } catch (HeadlessException | IllegalStateException | SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );

                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                        guardar.requestFocus();
                    }
                }
        );

        menuCuadro.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = true;
                        flag = 5;
                        final JInternalFrame frame = new JInternalFrame("Diario de Ventas", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabla);
                        tablaConsulta.setRowSorter(sorter);
                        sorter.setRowFilter(null);

                        try {
                            modeloTabla.establecerConsulta("SELECT cod, tDespachos, tDescuentos, tRetContado, tRetCobros, tCobros, tVentasContado, tVentasCredito "
                                    + "FROM TVentasFecha WHERE codigoVendedor = '0'");
                            modeloTabla1.establecerConsulta("SELECT VentaFecha.fecha, Vendedores.codigoVendedor, nombreVendedor, apellidoVendedor, formaVentas "
                                    + "FROM Vendedores, VentaFecha WHERE VentaFecha.codigoVendedor = Vendedores.codigoVendedor "
                                    + "AND Vendedores.codigoVendedor = '0' AND cod = '1' ORDER BY fecha, codigoVendedor");
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }

                        botonFiltro.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        String texto = textoFiltro.getText();
                                        if (texto.length() == 0) {
                                            sorter.setRowFilter(null);
                                        } else {
                                            try {
                                                sorter.setRowFilter(RowFilter.regexFilter(texto));
                                            } catch (PatternSyntaxException pse) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Patron de filtro incorrecto", "Patron de exp reg incorrecto", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                        );

                        totalEntrega.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        totalEntrega.setEditable(false);
                                        double diferencia = Double.parseDouble(totalEspera.getText()) - Double.parseDouble(totalEntrega.getText());
                                        totalResto.setText(String.format(Locale.UK, "%.2f", diferencia));
                                        botonCuadro.setEnabled(true);
                                        /**/
                                        totalResto.requestFocus();
                                    }
                                }
                        );

                        botonCuadro.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea guardar?", "Guardar", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            try {
                                                sentencia = conexion.createStatement();
                                                sentencia.executeUpdate("INSERT INTO EvalVendedores VALUES ('" + nombreFecha2 + "', '"
                                                        + cVendedor + "', '" + Double.parseDouble(totalDinero.getText()) + "', '"
                                                        + Double.parseDouble(totalVendido.getText()) + "', '" + Double.parseDouble(totalEntrega.getText()) + "', '"
                                                        + Double.parseDouble(textDesp.getText()) + "', '" + Double.parseDouble(textDev.getText()) + "', '"
                                                        + Double.parseDouble(textDesc.getText()) + "', '" + Double.parseDouble(textCob.getText()) + "', '"
                                                        + Double.parseDouble(textRetCont.getText()) + "', '" + Double.parseDouble(textRetCob.getText()) + "', '"
                                                        + Double.parseDouble(textCont.getText()) + "', '" + Double.parseDouble(textCred.getText()) + "', '"
                                                        + Double.parseDouble(totalResto.getText()) + "', '" + Double.parseDouble(totalEspera.getText()) + "')");
                                                JOptionPane.showMessageDialog(frame, "Datos guardados con exito.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                                totalEntrega.setEditable(true);
                                                botonCuadro.setEnabled(false);
                                            } catch (HeadlessException | NumberFormatException | SQLException e) {
                                                JOptionPane.showMessageDialog(null, e.toString() + "\n" + cVendedor);
                                                frame.dispose();
                                            }
                                        }
                                    }
                                }
                        );

                        guardar.setText("Consultar");
                        guardar.setEnabled(true);
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        cVendedor = codVendedor.getText();
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        consultarVend = "SELECT DISTINCT DATE_FORMAT(fecha, '%Y-%m-%d') AS fecha, Vendedores.codigoVendedor, nombreVendedor, apellidoVendedor "
                                        + "FROM Vendedores, TVentasFecha WHERE TVentasFecha.codigoVendedor = Vendedores.codigoVendedor "
                                        + "/*AND cod = '1' */AND fecha >= '" + nombreFecha + "' AND fecha <= '" + nombreFecha2 + "' "
                                        + "ORDER BY fecha, codigoVendedor";
                                        try {
                                            sentencia = conexion.createStatement();
                                            modeloTabla1.establecerConsulta(consultarVend);
                                        } catch (SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );

                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                        guardar.requestFocus();
                    }
                }
        );

        nuevoClientes.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = true;
                        flag = 6;
                        final JInternalFrame frame = new JInternalFrame("Clientes", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabla);
                        tablaConsulta.setRowSorter(sorter);
                        sorter.setRowFilter(null);

                        try {
                            modeloTabla.establecerConsulta("SELECT Clientes.codCliente, ruta, nombreCliente, apellidoCliente, compras, facturas "
                                    + "FROM Clientes, Rutas WHERE Clientes.codCliente = '0'");
                            modeloTabla1.establecerConsulta("SELECT Vendedores.codigoVendedor, nombreVendedor, apellidoVendedor, idVendedor "
                                    + "FROM Vendedores WHERE codigoVendedor = '0' "
                                    + "ORDER BY Vendedores.codigoVendedor");
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }

                        botonFiltro.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        String texto = textoFiltro.getText();
                                        if (texto.length() == 0) {
                                            sorter.setRowFilter(null);
                                        } else {
                                            try {
                                                sorter.setRowFilter(RowFilter.regexFilter(texto));
                                            } catch (PatternSyntaxException pse) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Patron de filtro incorrecto", "Patron de exp reg incorrecto", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                        );

                        guardar.setText("Consultar");
                        guardar.setEnabled(true);
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        consultarClient = "SELECT Vendedores.codigoVendedor, nombreVendedor, apellidoVendedor, idVendedor "
                                        + "FROM Vendedores "
                                        + "ORDER BY Vendedores.codigoVendedor";
                                        try {
                                            sentencia = conexion.createStatement();
                                            modeloTabla1.establecerConsulta(consultarClient);
                                            modelo.establecerConsulta("SELECT * FROM Clientes");
                                            JOptionPane.showMessageDialog(null, "Actualmente tiene un total de: " + String.valueOf(modelo.getRowCount()) + " Clientes\nen su base de datos.");
                                        } catch (SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );

                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );

                        /*button.addActionListener(
                         new ActionListener() 
                         {
                         public void actionPerformed(ActionEvent e) 
                         {
                         String text = filterText.getText();
                         try 
                         {
                         sorter.setRowFilter(RowFilter.regexFilter(text));
                         } 
                         catch (PatternSyntaxException pse) 
                         {
                         System.err.println("Bad regex pattern");
                         }
                         }
                         }
                         );*/
                        guardar.requestFocus();
                    }
                }
        );

        menuCambios.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = true;
                        flag = 7;
                        final JInternalFrame frame = new JInternalFrame("Cambios", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabla1);
                        tablaEncabezado.setRowSorter(sorter);
                        sorter.setRowFilter(null);

                        try {
                            modeloTabla.establecerConsulta("SELECT CambiosFecha.cantidad, producto, precioUnitario, CambiosFecha.total "
                                    + "FROM CambiosFecha, Productos WHERE CambiosFecha.cod = Productos.cod "
                                    + "AND codigoVendedor = '0'");
                            modeloTabla1.establecerConsulta("SELECT fecha, codCambio, Vendedores.codigoVendedor, nombreVendedor, apellidoVendedor "
                                    + "FROM Vendedores, CambiosFecha WHERE CambiosFecha.codigoVendedor = Vendedores.codigoVendedor "
                                    + "AND cod = 1 AND Vendedores.codigoVendedor = '0'");
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }

                        botonFiltro.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        String texto = textoFiltro.getText();
                                        if (texto.length() == 0) {
                                            sorter.setRowFilter(null);
                                        } else {
                                            try {
                                                sorter.setRowFilter(RowFilter.regexFilter(texto));
                                            } catch (PatternSyntaxException pse) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Patron de filtro incorrecto", "Patron de exp reg incorrecto", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                        );

                        guardar.setText("Consultar");
                        guardar.setEnabled(true);
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        consultarInv = "SELECT DATE_FORMAT(fecha, '%Y-%m-%d') AS fecha, codCambio, Vendedores.codigoVendedor, nombreVendedor, apellidoVendedor "
                                        + "FROM Vendedores, CambiosFecha WHERE CambiosFecha.codigoVendedor = Vendedores.codigoVendedor "
                                        + "AND cod = 1 AND fecha >= '" + nombreFecha + "' "
                                        + "AND fecha <= '" + nombreFecha2 + "' ORDER BY fecha";
                                        try {
                                            modeloTabla1.establecerConsulta(consultarInv);
                                        } catch (SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );

                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        menuObsequios.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {
                        consulta = true;
                        flag = 8;
                        final JInternalFrame frame = new JInternalFrame("Obsequios", false, false, false, false);

                        MiJPanel panel = new MiJPanel();
                        frame.add(panel, BorderLayout.CENTER);
                        Escritorio.add(frame, BorderLayout.CENTER);
                        frame.setVisible(true);
                        try {
                            frame.setMaximum(true);
                        } catch (PropertyVetoException ex) {
                            System.out.println(ex.getMessage());
                        }

                        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabla1);
                        tablaEncabezado.setRowSorter(sorter);
                        sorter.setRowFilter(null);

                        try {
                            modeloTabla.establecerConsulta("SELECT ObsequiosFecha.cantidad, producto, precioUnitario, ObsequiosFecha.total "
                                    + "FROM ObsequiosFecha, Productos WHERE ObsequiosFecha.cod = Productos.cod "
                                    + "AND codigoVendedor = '0'");
                            modeloTabla1.establecerConsulta("SELECT fecha, codObsequio, Vendedores.codigoVendedor, nombreVendedor, apellidoVendedor "
                                    + "FROM Vendedores, ObsequiosFecha WHERE ObsequiosFecha.codigoVendedor = Vendedores.codigoVendedor "
                                    + "AND cod = 1 AND Vendedores.codigoVendedor = '0'");
                        } catch (SQLException ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }

                        botonFiltro.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        String texto = textoFiltro.getText();
                                        if (texto.length() == 0) {
                                            sorter.setRowFilter(null);
                                        } else {
                                            try {
                                                sorter.setRowFilter(RowFilter.regexFilter(texto));
                                            } catch (PatternSyntaxException pse) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Patron de filtro incorrecto", "Patron de exp reg incorrecto", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                        );

                        guardar.setText("Consultar");
                        guardar.setEnabled(true);
                        guardar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        consultarInv = "SELECT DATE_FORMAT(fecha, '%Y-%m-%d') AS fecha, codObsequio, Vendedores.codigoVendedor, nombreVendedor, apellidoVendedor "
                                        + "FROM Vendedores, ObsequiosFecha WHERE ObsequiosFecha.codigoVendedor = Vendedores.codigoVendedor "
                                        + "AND cod = 1 AND fecha >= '" + nombreFecha + "' "
                                        + "AND fecha <= '" + nombreFecha2 + "' ORDER BY fecha";
                                        try {
                                            modeloTabla1.establecerConsulta(consultarInv);
                                        } catch (SQLException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    }
                                }
                        );

                        cancelar.setText("Salir");
                        cancelar.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evento) {
                                        int q = JOptionPane.showConfirmDialog(frame, "Esta seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                                        if (q == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                        }
                                    }
                                }
                        );
                    }
                }
        );

        ///************************************************************************************************************************************************************************
        evaluarProd.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evento) {

                    }
                }
        );
///************************************************************************************************************************************************************************************

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new GridLayout(2, 1));

        JLabel etiqueta = new JLabel(" PASABOCAS CRUCK'S ", SwingConstants.CENTER);
        etiqueta.setFont(new Font("Monospaced", Font.BOLD, 30));
        encabezado.add(etiqueta);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(2, 2));
        version = new JTextField("Version 1.1");
        creator = new JTextField("Electronica");
        verfecha = new JTextField("");
        verfecha.setText(String.format("Fecha: %tF", fecha));
        materia = new JTextField("Comercializacion");

        creator.setEditable(false);
        version.setEditable(false);
        materia.setEditable(false);
        verfecha.setEditable(false);
        panelInfo.add(creator);
        panelInfo.add(materia);
        panelInfo.add(version);
        panelInfo.add(verfecha);
        encabezado.add(panelInfo);
        add(encabezado, BorderLayout.NORTH);

        /*temporizador = new Timer(1000, new ManejadorTemp());
         temporizador.start();*/
        JPanel panelEstado = new JPanel(new GridLayout(1, 4));
        mensaje = new JLabel("");
        estadoSQL = new JLabel("");
        progresoSQL = new JProgressBar(0, 100);
        barraestado = new JLabel(String.format("%1$tH:%1$tM", fecha), SwingConstants.RIGHT);
        panelEstado.add(mensaje);
        panelEstado.add(progresoSQL);
        panelEstado.add(estadoSQL);
        panelEstado.add(barraestado);
        add(panelEstado, BorderLayout.SOUTH);

        addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent evento) {
                        modeloTabla.desconectarDeBaseDatos();
                        System.exit(0);
                    }
                }
        );
    }

    class MiJPanel extends JPanel {

        public MiJPanel() {
            cFact = "";
            setLayout(new BorderLayout());
            JPanel panelCodSocio = new JPanel(new GridLayout(1, 2));
            panelCodSocio.add(new JLabel("Codigo:", SwingConstants.RIGHT));
            codSocio = new JTextField("");
            codSocio.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evento) throws IllegalStateException {
                            codSocio.setEditable(false);
                            cSocio = codSocio.getText();
                            if (cSocio.length() == 0) {
                                codSocio.requestFocus();
                            } else {
                                try {
                                    sentencia = conexion.createStatement();
                                    modeloTabla.establecerConsulta("SELECT nombre, apellido "
                                            + "FROM Socios "
                                            + "WHERE codSocio = "
                                            + "'" + cSocio + "'");
                                    nombreSocio.setText((String) modeloTabla.getValueAt(0, 0) + " "
                                            + (String) modeloTabla.getValueAt(0, 1));
                                    if (modeloTabla.getRowCount() == 0) {
                                        JOptionPane.showMessageDialog(null, "Codigo Invalido!", "Error", JOptionPane.ERROR_MESSAGE);
                                        codSocio.setEditable(true);
                                        codSocio.requestFocus();
                                    } else {
                                        nombreSocio.setEditable(false);
                                        pesoPapas.setEditable(true);
                                        pesoPlatano.setEditable(true);
                                        pesoYuca.setEditable(true);
                                        tabla.setEnabled(true);
                                        guardar.setEnabled(true);
                                        tabla.requestFocus();
                                    }
                                } catch (HeadlessException | IllegalStateException | SQLException ee) {
                                    JOptionPane.showMessageDialog(null, "Codigo Invalido!", "Error", JOptionPane.ERROR_MESSAGE);
                                    codSocio.setEditable(true);
                                    codSocio.requestFocus();
                                }
                            }
                        }
                    }
            );
            panelCodSocio.add(codSocio);

            JPanel panelSocio = new JPanel(new GridLayout(1, 2));
            panelSocio.add(new JLabel("Socio:", SwingConstants.CENTER));
            nombreSocio = new JTextField("");
            nombreSocio.setEditable(false);
            panelSocio.add(nombreSocio);

            JPanel panelNorte = new JPanel();
            JPanel panelFiltro = new JPanel(new FlowLayout());
            textoFiltro = new JTextField(20);
            botonFiltro = new JButton("Filtrar");
            panelFiltro.add(textoFiltro);
            panelFiltro.add(botonFiltro);
            JPanel panelVendedor = new JPanel(new GridLayout(1, 5));
            codVendedor = new JTextField("");
            panelVendedor.add(new JLabel("Cod Vendedor:"));
            nombreVendedor = new JTextField("");
            nombreVendedor.setEditable(false);
            panelVendedor.add(new JLabel("Vendedor (N/A):"));
            apellidoVendedor = new JTextField("");
            apellidoVendedor.setEditable(false);
            if (flag < 10 || flag > 20) {
                codVendedor.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) throws IllegalStateException {
                                codVendedor.setEditable(false);
                                cVendedor = codVendedor.getText();
                                if (cVendedor.length() == 0) {
                                    nombreVendedor.requestFocus();
                                } else {
                                    try {
                                        sentencia = conexion.createStatement();
                                        modeloTabla.establecerConsulta("SELECT nombreVendedor, idVendedor "
                                                + "FROM Vendedores "
                                                + "WHERE codigoVendedor = "
                                                + "'" + cVendedor + "'");
                                        nombreVendedor.setText((String) modeloTabla.getValueAt(0, 0));
                                        idCliente.setText((String) modeloTabla.getValueAt(0, 1));
                                        modeloTabla.establecerConsulta("SELECT apellidoVendedor, telefono "
                                                + "FROM Vendedores "
                                                + "WHERE codigoVendedor = "
                                                + "'" + cVendedor + "'");
                                        if (nombreVendedor.getText().length() == 0) {
                                            JOptionPane.showMessageDialog(null, "Codigo Invalido!", "Error", JOptionPane.ERROR_MESSAGE);
                                            codVendedor.setEditable(true);
                                            codVendedor.requestFocus();
                                        } else {
                                            apellidoVendedor.setText((String) modeloTabla.getValueAt(0, 0));
                                            telCliente.setText((String) modeloTabla.getValueAt(0, 1));
                                            if (flag == 22) {
                                                nombreVendedor.setEditable(true);
                                                apellidoVendedor.setEditable(true);
                                            } else {
                                                nombreVendedor.setEditable(false);
                                                apellidoVendedor.setEditable(false);
                                            }
                                            idCliente.setEditable(true);
                                            telCliente.setEditable(true);
                                            tabla.setEnabled(true);
                                            codCliente.setEditable(true);
                                            codCliente.requestFocus();
                                            nombreCliente.setEditable(true);
                                            apellidoCliente.setEditable(true);
                                            guardar.setEnabled(true);
                                        }
                                    } catch (HeadlessException | IllegalStateException | SQLException ee) {
                                        JOptionPane.showMessageDialog(null, "Codigo Invalido!", "Error", JOptionPane.ERROR_MESSAGE);
                                        codVendedor.setEditable(true);
                                        codVendedor.requestFocus();
                                    }
                                }
                            }
                        }
                );
                nombreVendedor.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) {
                                nombreVendedor.setEditable(false);
                                apellidoVendedor.setEditable(true);
                                apellidoVendedor.requestFocus();
                            }
                        }
                );
                apellidoVendedor.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) {
                                apellidoVendedor.setEditable(false);
                                nVendedor = nombreVendedor.getText();
                                aVendedor = apellidoVendedor.getText();
                                try {
                                    sentencia = conexion.createStatement();
                                    modeloTabla.establecerConsulta("SELECT codigoVendedor "
                                            + "FROM Vendedores "
                                            + "WHERE nombreVendedor = "
                                            + "'" + nVendedor + "' "
                                            + "AND apellidoVendedor = "
                                            + "'" + aVendedor + "'");
                                    if (modeloTabla.getRowCount() == 0) {
                                        JOptionPane.showMessageDialog(null, "Nombre y/o Apellido Invalidos!", "Error", JOptionPane.ERROR_MESSAGE);
                                        codVendedor.setEditable(true);
                                        nombreVendedor.setEditable(true);
                                        apellidoVendedor.setEditable(true);
                                        codVendedor.requestFocus();
                                    } else {
                                        codVendedor.setText((String) modeloTabla.getValueAt(0, 0));
                                        cVendedor = codVendedor.getText();
                                        codVendedor.setEditable(false);
                                        nombreVendedor.setEditable(false);
                                        tabla.setEnabled(true);
                                        codCliente.setEditable(true);
                                        codCliente.requestFocus();
                                        nombreCliente.setEditable(true);
                                        apellidoCliente.setEditable(true);
                                        guardar.setEnabled(true);
                                    }
                                } catch (SQLException | IllegalStateException | HeadlessException ee) {
                                    JOptionPane.showMessageDialog(null, "Nombre y/o Apellido Invalidos!", "Error", JOptionPane.ERROR_MESSAGE);
                                    codVendedor.setEditable(true);
                                    nombreVendedor.setEditable(true);
                                    apellidoVendedor.setEditable(true);
                                    codVendedor.requestFocus();
                                }
                            }
                        }
                );
            } else {
                codVendedor.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) throws IllegalStateException {
                                codVendedor.setEditable(false);
                                cVendedor = codVendedor.getText();
                                boolean existe = false;
                                if (cVendedor.length() == 0) {
                                    codVendedor.requestFocus();
                                } else {
                                    try {
                                        sentencia = conexion.createStatement();
                                        modeloTabla.establecerConsulta("SELECT codigoVendedor "
                                                + "FROM Vendedores");
                                    } catch (SQLException | IllegalStateException ee) {
                                        JOptionPane.showMessageDialog(null, ee.getMessage());
                                        codVendedor.requestFocus();
                                    }
                                    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                                        if (codVendedor.getText().equals((String) modeloTabla.getValueAt(i, 0))) {
                                            existe = true;
                                            break;
                                        } else {
                                            existe = false;
                                        }
                                    }

                                    if (existe == true) {
                                        JOptionPane.showMessageDialog(null, "El Codigo ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
                                        codVendedor.setEditable(true);
                                        codVendedor.requestFocus();
                                    } else {
                                        codVendedor.setEditable(false);
                                        nombreVendedor.requestFocus();
                                        nombreVendedor.setEditable(true);
                                    }
                                }
                            }
                        }
                );
                nombreVendedor.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) {
                                nombreVendedor.setEditable(false);
                                apellidoVendedor.setEditable(true);
                                apellidoVendedor.requestFocus();
                            }
                        }
                );
                apellidoVendedor.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) {
                                apellidoVendedor.setEditable(false);
                                guardar.setEnabled(true);
                                guardar.requestFocus();
                            }
                        }
                );
            }
            panelVendedor.add(codVendedor);
            panelVendedor.add(nombreVendedor);
            panelVendedor.add(apellidoVendedor);

            JPanel date = new JPanel(new GridLayout(1, 6));
            anio = new JTextField(String.format("%1$tY", fecha), SwingConstants.CENTER);
            mes = new JTextField(String.format("%1$tm", fecha), SwingConstants.CENTER);
            dia = new JTextField(String.format("%1$td", fecha), SwingConstants.CENTER);
            date.add(new JLabel("Fecha: ", SwingConstants.RIGHT));
            date.add(anio);
            date.add(new JLabel("/", SwingConstants.CENTER));
            date.add(mes);
            date.add(new JLabel("/", SwingConstants.CENTER));
            date.add(dia);

            JPanel date2 = new JPanel(new GridLayout(1, 6));
            anio2 = new JTextField(String.format("%1$tY", fecha), SwingConstants.CENTER);
            mes2 = new JTextField(String.format("%1$tm", fecha), SwingConstants.CENTER);
            dia2 = new JTextField(String.format("%1$td", fecha), SwingConstants.CENTER);
            date2.add(new JLabel("Hasta: ", SwingConstants.RIGHT));
            date2.add(anio2);
            date2.add(new JLabel("/", SwingConstants.CENTER));
            date2.add(mes2);
            date2.add(new JLabel("/", SwingConstants.CENTER));
            date2.add(dia2);

            JPanel panelCliente = new JPanel(new GridLayout(1, 5));
            codCliente = new JTextField("");
            codCliente.setEditable(false);
            panelCliente.add(new JLabel("Cod Cliente:"));
            nombreCliente = new JTextField("");
            nombreCliente.setEditable(false);
            panelCliente.add(new JLabel("Cliente (N/A):"));
            apellidoCliente = new JTextField("");
            apellidoCliente.setEditable(false);
            if (flag < 30 || flag > 40) {
                codCliente.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) {
                                codCliente.setEditable(false);
                                cCliente = codCliente.getText();
                                if (cCliente.length() == 0) {
                                    nombreCliente.requestFocus();
                                } else {
                                    try {
                                        sentencia = conexion.createStatement();
                                        modeloTabla.establecerConsulta("SELECT nombreCliente, comercial, idCliente "
                                                + "FROM Clientes "
                                                + "WHERE codCliente = '"
                                                + cCliente + "' "
                                                + "AND codigoVendedor = '"
                                                + cVendedor + "'");
                                        nombreCliente.setText((String) modeloTabla.getValueAt(0, 0));
                                        comCliente.setText((String) modeloTabla.getValueAt(0, 1));
                                        idCliente.setText((String) modeloTabla.getValueAt(0, 2));
                                        modeloTabla.establecerConsulta("SELECT apellidoCliente, direccion, telefono "
                                                + "FROM Clientes "
                                                + "WHERE codCliente = '"
                                                + cCliente + "' "
                                                + "AND codigoVendedor = '"
                                                + cVendedor + "'");
                                        if (nombreCliente.getText().length() == 0) {
                                            JOptionPane.showMessageDialog(null, "Codigo Invalido!", "Error", JOptionPane.ERROR_MESSAGE);
                                            codCliente.setEditable(true);
                                            codCliente.requestFocus();
                                        } else {
                                            apellidoCliente.setText((String) modeloTabla.getValueAt(0, 0));
                                            dirCliente.setText((String) modeloTabla.getValueAt(0, 1));
                                            telCliente.setText((String) modeloTabla.getValueAt(0, 2));
                                            modeloTabla.establecerConsulta("SELECT formaPago FROM Clientes WHERE codCliente = '"
                                                    + cCliente + "' AND codigoVendedor = '"
                                                    + cVendedor + "'");
                                            formaPago.setText((String) modeloTabla.getValueAt(0, 0));
                                            if (flag == 43) {
                                                nombreCliente.setEditable(true);
                                                apellidoCliente.setEditable(true);
                                            } else {
                                                nombreCliente.setEditable(false);
                                                apellidoCliente.setEditable(false);
                                            }
                                            comCliente.setEditable(true);
                                            idCliente.setEditable(true);
                                            dirCliente.setEditable(true);
                                            telCliente.setEditable(true);
                                            tabla.setEnabled(true);
                                            tabla.requestFocus();
                                            guardar.setEnabled(true);
                                        }
                                    } catch (HeadlessException | IllegalStateException | SQLException ee) {
                                        JOptionPane.showMessageDialog(null, "Codigo Invalido!", "Error", JOptionPane.ERROR_MESSAGE);
                                        codCliente.setEditable(true);
                                        codCliente.requestFocus();
                                    }
                                }
                            }
                        }
                );

                nombreCliente.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) {
                                nombreCliente.setEditable(false);
                                apellidoCliente.requestFocus();
                            }
                        }
                );

                apellidoCliente.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) {
                                apellidoCliente.setEditable(false);
                                nCliente = nombreCliente.getText();
                                aCliente = apellidoCliente.getText();
                                try {
                                    sentencia = conexion.createStatement();
                                    modeloTabla.establecerConsulta("SELECT codCliente, formaPago "
                                            + "FROM Clientes "
                                            + "WHERE nombreCliente = "
                                            + "'" + nCliente + "' "
                                            + "AND apellidoCliente = "
                                            + "'" + aCliente + "' "
                                            + "AND codigoVendedor = "
                                            + "'" + cVendedor + "'");
                                    if (modeloTabla.getRowCount() == 0) {
                                        JOptionPane.showMessageDialog(null, "Nombre y/o Apellido Invalidos!", "Error", JOptionPane.ERROR_MESSAGE);
                                        codCliente.setEditable(true);
                                        nombreCliente.setEditable(true);
                                        apellidoCliente.setEditable(true);
                                        codCliente.requestFocus();
                                    } else {
                                        codCliente.setText((String) modeloTabla.getValueAt(0, 0));
                                        codCliente.setEditable(false);
                                        nombreCliente.setEditable(false);
                                        formaPago.setText((String) modeloTabla.getValueAt(0, 1));
                                        tabla.setEnabled(true);
                                        guardar.setEnabled(true);
                                    }
                                } catch (SQLException ee) {
                                    JOptionPane.showMessageDialog(null, "Nombre y/o Apellido Invalidos!", "Error", JOptionPane.ERROR_MESSAGE);
                                    codCliente.setEditable(true);
                                    nombreCliente.setEditable(true);
                                    apellidoCliente.setEditable(true);
                                    codCliente.requestFocus();
                                }
                            }
                        }
                );
            } else {
                codCliente.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) throws IllegalStateException {
                                codCliente.setEditable(false);
                                cCliente = codCliente.getText();
                                boolean existe = false;
                                if (cCliente.length() == 0) {
                                    codCliente.requestFocus();
                                } else {
                                    try {
                                        sentencia = conexion.createStatement();
                                        modeloTabla.establecerConsulta("SELECT codCliente "
                                                + "FROM Clientes");
                                    } catch (SQLException | IllegalStateException ee) {
                                        JOptionPane.showMessageDialog(null, ee.getMessage());
                                        codCliente.requestFocus();
                                    }
                                    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                                        if (codCliente.getText().equals((String) modeloTabla.getValueAt(i, 0))) {
                                            existe = true;
                                            break;
                                        } else {
                                            existe = false;
                                        }
                                    }

                                    if (existe == true) {
                                        JOptionPane.showMessageDialog(null, "El Codigo ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
                                        codCliente.setEditable(true);
                                        codCliente.requestFocus();
                                    } else {
                                        codCliente.setEditable(false);
                                        nombreCliente.requestFocus();
                                        nombreCliente.setEditable(true);
                                        formaPago.setEditable(true);
                                        comCliente.setEditable(true);
                                        dirCliente.setEditable(true);
                                    }
                                }
                            }
                        }
                );
                nombreCliente.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) {
                                nombreCliente.setEditable(false);
                                apellidoCliente.setEditable(true);
                                apellidoCliente.requestFocus();
                            }
                        }
                );
                apellidoCliente.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evento) {
                                apellidoCliente.setEditable(false);
                                guardar.setEnabled(true);
                                guardar.requestFocus();
                            }
                        }
                );
            }
            panelCliente.add(codCliente);
            panelCliente.add(nombreCliente);
            panelCliente.add(apellidoCliente);

            JPanel panelEtiq = new JPanel(new GridLayout(4, 1));
            idCliente = new JTextField("");
            idCliente.setEditable(false);
            comCliente = new JTextField("");
            comCliente.setEditable(false);
            telCliente = new JTextField("");
            telCliente.setEditable(false);
            dirCliente = new JTextField("");
            dirCliente.setEditable(false);
            JPanel panelDat = new JPanel(new GridLayout(4, 1));
            if (flag > 10 && flag < 30) {
                panelEtiq.add(new JLabel("Id:"));
                panelEtiq.add(new JLabel("Telefono:"));
                panelDat.add(idCliente);
                panelDat.add(telCliente);
            } else {
                panelEtiq.add(new JLabel("Id:"));
                panelEtiq.add(new JLabel("Comercial:"));
                panelEtiq.add(new JLabel("Telefono:"));
                panelEtiq.add(new JLabel("Direccion:"));
                panelDat.add(idCliente);
                panelDat.add(comCliente);
                panelDat.add(telCliente);
                panelDat.add(dirCliente);
            }
            JPanel panelDatos = new JPanel(new BorderLayout());
            panelDatos.add(panelEtiq, BorderLayout.WEST);
            panelDatos.add(panelDat, BorderLayout.CENTER);

            JPanel panelPago = new JPanel(new GridLayout(1, 2));
            formaPago = new JTextField("");
            panelPago.add(new JLabel("Forma de Pago: ", SwingConstants.CENTER));
            formaPago.setEditable(false);
            panelPago.add(formaPago);

            JPanel panelDinero = new JPanel(new GridLayout(3, 2));
            panelDinero.add(new JLabel("Total Dinero:"));
            totalDinero = new JTextField("0.00", SwingConstants.RIGHT);
            totalDinero.setEditable(false);
            panelDinero.add(totalDinero);
            panelDinero.add(new JLabel("Total Vendido:"));
            totalVendido = new JTextField("0.00", SwingConstants.RIGHT);
            totalVendido.setFont(new Font("Serif", Font.BOLD, 12));
            totalVendido.setLocale(Locale.UK);
            totalVendido.setEditable(false);
            panelDinero.add(totalVendido);
            panelDinero.add(new JLabel(""));
            panelDinero.add(new JLabel(""));

            JPanel panelEntrega = new JPanel(new GridLayout(3, 2));
            panelEntrega.add(new JLabel(((flag == 222) ? "Descuentos:" : "Por Entregar:")));
            totalEspera = new JTextField("0.00", SwingConstants.RIGHT);
            totalEspera.setEditable(false);
            panelEntrega.add(totalEspera);
            panelEntrega.add(new JLabel(((flag == 222) ? "Dinero Entregado:" : "Entrega:")));
            totalEntrega = new JTextField("0.00", SwingConstants.RIGHT);
            totalEntrega.setFont(new Font("Serif", Font.BOLD, 12));
            totalEntrega.setLocale(Locale.UK);
            panelEntrega.add(totalEntrega);
            panelEntrega.add(new JLabel(((flag == 222) ? "Saldo:" : "Diferencia:")));
            totalResto = new JTextField("0.00", SwingConstants.RIGHT);
            totalResto.setFont(new Font("Serif", Font.BOLD, 12));
            totalResto.setLocale(Locale.UK);
            totalResto.setEditable(false);
            panelEntrega.add(totalResto);
            if (!consulta) {
                panelNorte.setLayout(new GridLayout(2, 2));
                if (flag <= 1) {
                    if (flag != 11) {
                        panelNorte.add(panelCodSocio);
                        panelNorte.add(date);
                        panelNorte.add(panelSocio);
                        panelNorte.add(new JLabel(""));
                    } else {
                        panelNorte.add(new JLabel(""));
                        panelNorte.add(new JLabel(""));
                        panelNorte.add(new JLabel(""));
                        panelNorte.add(date);
                    }
                } else if (flag == 111) {
                    panelNorte.add(panelCodSocio);
                    panelNorte.add(date);
                    panelNorte.add(panelSocio);
                    panelNorte.add(date2);
                } else if (flag == 100) {
                    panelNorte.add(new JLabel(""));
                    panelNorte.add(date);
                } else if (flag == 2 || flag == 12 || flag == 22) {
                    panelNorte.add(panelVendedor);
                    panelNorte.add(date);
                    panelNorte.add(new JLabel(""));
                    panelNorte.add(new JLabel(""));
                } else if (flag == 222) {
                    panelNorte.add(panelVendedor);
                    panelNorte.add(date);
                    panelNorte.add(new JLabel(""));
                    panelNorte.add(date2);
                } else if (flag == 3 || flag == 33 || flag == 43 || flag == 9) {
                    panelNorte.add(panelVendedor);
                    panelNorte.add(date);
                    panelNorte.add(panelCliente);
                    panelNorte.add(panelPago);
                }

                add(panelNorte, BorderLayout.NORTH);
            } else {
                panelNorte.setLayout(new GridLayout(3, 2));
                if (flag == 0) {
                    panelNorte.add(new JLabel("EXISTENCIA", SwingConstants.CENTER));
                    panelNorte.add(new JLabel("BODEGA", SwingConstants.CENTER));

                } else if (flag == 101) {
                    panelNorte.add(new JLabel("EXISTENCIA", SwingConstants.CENTER));
                    panelNorte.add(date);
                    panelNorte.add(new JLabel("INICIAL", SwingConstants.CENTER));
                    panelNorte.add(new JLabel(""));
                } else if (flag == 5) {
                    panelNorte.setLayout(new BorderLayout());
                    JPanel panelNorteNorte = new JPanel(new GridLayout(2, 2));
                    panelNorteNorte.add(panelFiltro);
                    panelNorteNorte.add(date);
                    panelNorteNorte.add(new JLabel(""));
                    panelNorteNorte.add(date2);
                    JPanel panelNorteCentro = new JPanel(new GridLayout(1,2));
                    panelNorteCentro.add(panelDinero);
                    panelNorteCentro.add(panelEntrega);
                    panelNorte.add(panelNorteNorte, BorderLayout.NORTH);
                    panelNorte.add(panelNorteCentro, BorderLayout.CENTER);
                } else {
                    panelNorte.add(panelFiltro);
                    panelNorte.add(date);
                    panelNorte.add(new JLabel(""));
                    panelNorte.add(date2);
                }

                add(panelNorte, BorderLayout.NORTH);
            }

            JPanel panelCentro = new JPanel(new BorderLayout());
            for (int i = 0; i < prod.length; i++) {
                prod[i] = 0.00;
            }

            JPanel panelCodigo = new JPanel();
            panelCodigo.add(new JLabel("Transaccion #:"));
            codFact = new JTextField(3);
            codFact.setEditable(false);
            panelCodigo.add(codFact);
            codFact2 = new JTextField(7);
            codFact2.setEditable(false);
            panelCodigo.add(new JLabel("-"));
            panelCodigo.add(codFact2);
            JPanel panelCuadro = new JPanel(new GridLayout(2, 3));
            textDeuda = new JTextField("");
            textDeuda.setHorizontalAlignment(SwingConstants.RIGHT);
            textDeuda.setFont(new Font("Courier New", Font.BOLD, 15));
            grupoDeuda = new ButtonGroup();
            botonCredito = new JRadioButton("Credito", true);
            botonCobro = new JRadioButton("Cobro");
            panelCuadro.add(new JLabel("Valor:"));
            panelCuadro.add(textDeuda);
            panelCuadro.add(new JLabel(""));
            panelCuadro.add(new JLabel("Seleccionar Opcion:"));
            panelCuadro.add(botonCredito);
            grupoDeuda.add(botonCredito);
            panelCuadro.add(botonCobro);
            grupoDeuda.add(botonCobro);
            tablaEncabezado = new JTable(modeloTabla1);
            tablaEncabezado.setPreferredScrollableViewportSize(new Dimension(100, 350));
            tablaEncabezado.setFillsViewportHeight(true);
            tablaConsulta = new JTable(modeloTabla);
            tablaConsulta.setPreferredScrollableViewportSize(new Dimension(400, 350));
            tablaConsulta.setFillsViewportHeight(true);
            Container panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            if (flag == 4) {
                c.ipady = 200;
            } else {
                c.ipady = 55;
            }
            c.weightx = 0.5;
            c.gridx = 0;
            c.gridy = 0;
            if (flag == 9) {
                panel.add(panelCuadro);
            } else {
                panel.add(new JScrollPane(tablaEncabezado), c);
            }
            c.fill = GridBagConstraints.HORIZONTAL;
            if (flag == 4) {
                c.ipady = 50;
            } else {
                c.ipady = 215;
            }
            c.weightx = 0.5;
            c.gridx = 0;
            c.gridy = 1;
            panel.add(new JScrollPane(tablaConsulta), c);
            tabla = new JTable(new MyTableModel(data));
            tabla.setPreferredScrollableViewportSize(new Dimension(200, 350));
            tabla.setFillsViewportHeight(true);
            tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            tabla.setRowSelectionAllowed(true);
            JPanel panelTablaCentro = new JPanel(new GridLayout(1, 3));
            panelTablaCentro.add(new JLabel(""));
            panelTablaCentro.add(new JScrollPane(tabla));
            panelTablaCentro.add(new JLabel(""));
            tabla.addKeyListener(
                    new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            int row = tabla.getSelectedRow();
                            int col = tabla.getSelectedColumn();
                            int key = e.getKeyCode();

                            try {
                                if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_ENTER) && tabla.isEditing()) {
                                    tabla.getCellEditor().stopCellEditing();
                                    tot = 0.00;
                                    if (tabla.getValueAt(row, col) == "") {
                                        prod[row] = 0.00;
                                    } else {
                                        prod[row] = Integer.parseInt((String) tabla.getValueAt(row, 0)) * Double.parseDouble((String) tabla.getValueAt(row, 1));
                                    }
                                    for (int i = 0; i < prod.length; i++) {
                                        tot += prod[i];
                                    }
                                    tabla.setValueAt(prod[row], row, 3);
                                    campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                } else if (!tabla.isEditing() && col == 0 && !(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE)) {
                                    tabla.setValueAt("", row, col);
                                } else if (col == 0 && (key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE)) {
                                    tabla.getCellEditor().stopCellEditing();
                                    tabla.setValueAt("0", row, col);
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }
            );

            tabla.setDefaultRenderer(Object.class, new MiRender());
            tablaEncabezado.addMouseListener(
                    new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int row = tablaEncabezado.rowAtPoint(e.getPoint());
                            if (row > -1) {
                                try {
                                    if (flag == 1) {
                                        modeloTabla.establecerConsulta("SELECT ProdFecha.cantidad, producto, precioUnitario, ProdFecha.total "
                                                + "FROM ProdFecha, Productos WHERE ProdFecha.cod = Productos.cod "
                                                + "AND codSocio = '" + (String) tablaEncabezado.getValueAt(row, 1) + "' "
                                                + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "'");
                                        double t = 0.00, p = 0.00, pl = 0.00, y = 0.00;
                                        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                                            t += (Double) modeloTabla.getValueAt(i, 3);
                                            if (i <= 27) {
                                                p += (Double) modeloTabla.getValueAt(i, 3);
                                            } else if (i > 27 && i <= 34) {
                                                pl += (Double) modeloTabla.getValueAt(i, 3);
                                            } else if (i > 34 && i <= 36) {
                                                y += (Double) modeloTabla.getValueAt(i, 3);
                                            }
                                        }
                                        campTOT.setText(String.format(Locale.UK, "%.2f", t));
                                        pesoPapas.setText(String.format(Locale.UK, "%.2f", p));
                                        pesoPlatano.setText(String.format(Locale.UK, "%.2f", pl));
                                        pesoYuca.setText(String.format(Locale.UK, "%.2f", y));
                                        modelo.establecerConsulta("SELECT pesoPapas, pesoPlatano, pesoYuca FROM ProdFecha "
                                                + "WHERE codSocio = '" + (String) tablaEncabezado.getValueAt(row, 1) + "' "
                                                + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "'");
                                        pesoPapas2.setText(String.format(Locale.UK, "%.2f", p / (Double) modelo.getValueAt(0, 0)));
                                        pesoPlatano2.setText(String.format(Locale.UK, "%.2f", pl / (Double) modelo.getValueAt(0, 1)));
                                        pesoYuca2.setText(String.format(Locale.UK, "%.2f", y / (Double) modelo.getValueAt(0, 2)));
                                    } else if (flag == 2) {
                                        modeloTabla.establecerConsulta("SELECT DespFecha.cantidad, producto, precioUnitario, DespFecha.total "
                                                + "FROM DespFecha, Productos WHERE DespFecha.cod = Productos.cod "
                                                + "AND codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 2) + "' "
                                                + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "' "
                                                + "AND codDespacho = '" + (String) tablaEncabezado.getValueAt(row, 1) + "'");
                                        modelo.establecerConsulta("SELECT tDespachos FROM TVentasFecha "
                                                + "WHERE cod = '" + (String) tablaEncabezado.getValueAt(row, 1) + "' "
                                                + "AND codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 2) + "' "
                                                + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "' "
                                                + "AND tDespachos <> 0");
                                        campTOT.setText(String.format(Locale.UK, "%.2f", (Double) modelo.getValueAt(0, 0)));
                                    } else if (flag == 3) {
                                        /*modeloTabla.establecerConsulta("SELECT VentaFecha.cantidad, producto, precioUnitario, VentaFecha.total "
                                         + "FROM VentaFecha, Productos WHERE VentaFecha.cod = Productos.cod "
                                         + "AND codigoVendedor = '" + (String)tablaEncabezado.getValueAt(row, 2) + "' "
                                         + "AND codCliente = '" + (String)tablaEncabezado.getValueAt(row, 3) + "' "
                                         + "AND codVenta = '" + (String)tablaEncabezado.getValueAt(row, 1) + "' "
                                         + "AND formaVentas = 'Contado' "
                                         + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "'");
                                         modelo.establecerConsulta("SELECT tVentasContado FROM TVentasFecha "
                                         + "WHERE cod = '" + (String)tablaEncabezado.getValueAt(row, 1) + "' "
                                         + "AND codigoVendedor = '" + (String)tablaEncabezado.getValueAt(row, 2) + "' "
                                         + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "' "
                                         + "AND tVentasContado <> 0");
                                         campTOT.setText(String.format(Locale.UK, "%.2f", (Double)modelo.getValueAt(0, 0)));*/
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        consultarVend = "SELECT DISTINCT DATE_FORMAT(VentaFecha.fecha, '%Y-%m-%d') AS fecha, codVenta, Vendedores.codigoVendedor, Clientes.codCliente, nombreCliente, apellidoCliente, total Resto, IF(cobrado = 1, 'Parcial', 'Sin Cobrar') COBRO, IF(tVentasCredito = total AND cobrado = 0, 'NO TIENE', 'TIENE') Retencion "
                                        + "FROM Vendedores, Clientes, VentaFecha, TVentasFecha WHERE VentaFecha.codigoVendedor = Vendedores.codigoVendedor "
                                        + "AND VentaFecha.codCliente = Clientes.codCliente AND formaVentas = 'Credito' AND VentaFecha.cod = 1 AND cobrado < 2 "
                                        + "AND VentaFecha.fecha >= '" + nombreFecha + "' AND VentaFecha.fecha <= '" + nombreFecha2 + "' AND tVentasCredito <> 0 "
                                        + "AND TVentasFecha.fecha = VentaFecha.fecha AND TVentasFecha.cod = codVenta AND TVentasFecha.codigoVendedor = VentaFecha.codigoVendedor "
                                        + "AND VentaFecha.codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 0) + "' "
                                        + "ORDER BY codCliente, VentaFecha.fecha";
                                        try {
                                            sentencia = conexion.createStatement();
                                            modeloTabla.establecerConsulta(consultarVend);
                                            double tot = 0.00;
                                            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                                                tot += (Double) tablaConsulta.getValueAt(i, 6);
                                            }
                                            campTOT.setText(String.format(Locale.UK, "%.2f", tot));
                                            JOptionPane.showMessageDialog(null, "Tiene un total de: " + String.valueOf(modeloTabla.getRowCount()) + " Creditos pendientes\n\tpara el periodo seleccionado.");
                                        } catch (SQLException | IllegalStateException | HeadlessException ee) {
                                            JOptionPane.showMessageDialog(null, ee.getMessage());
                                        }
                                    } else if (flag == 4) {
                                        modeloTabla.establecerConsulta("SELECT VentaFecha.total CREDITOS, IF(cobrado = 0, '-', TVentasFecha.fecha) Fecha_Cobro, "
                                                + "IF(cobrado = 0, 0, tCobros) COBROS, IF(cobrado = 0, 0, tRetCobros) RETENCION, IF(cobrado < 2, 'Pendiente', 'Cobrado') Estado "
                                                + "FROM VentaFecha, TVentasFecha WHERE TVentasFecha.codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 2) + "' "
                                                + "AND VentaFecha.codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 2) + "' "
                                                + "AND VentaFecha.fecha = '" + tablaEncabezado.getValueAt(row, 0) + "' "
                                                + "AND codVenta = '" + (String) tablaEncabezado.getValueAt(row, 1) + "' "
                                                + "AND TVentasFecha.cod = codVenta AND (tCobros <> 0 OR tRetCobros <> 0) "
                                                + "AND TVentasFecha.codigoVendedor = VentaFecha.codigoVendedor "
                                                + "AND codCliente = '" + (String) tablaEncabezado.getValueAt(row, 3) + "' AND tDescuentos = 0 ORDER BY VentaFecha.fecha, codVenta");
                                        modelo.establecerConsulta("SELECT total FROM VentaFecha "
                                                + "WHERE codVenta = '" + (String) tablaEncabezado.getValueAt(row, 1) + "' "
                                                + "AND codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 2) + "' "
                                                + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "' "
                                                + "AND codCliente = '" + (String) tablaEncabezado.getValueAt(row, 3) + "'");
                                        double tot = (Double) modelo.getValueAt(0, 0);
                                        modelo.establecerConsulta("SELECT tCobros FROM TVentasFecha "
                                                + "WHERE cod = '" + (String) tablaEncabezado.getValueAt(row, 1) + "' "
                                                + "AND codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 2) + "' "
                                                + "AND tCobros <> 0");
                                        double c = 0.00;
                                        for (int i = 0; i < modelo.getRowCount(); i++) {
                                            c += (Double) modelo.getValueAt(i, 0);
                                        }
                                        campTOT.setText(String.format(Locale.UK, "%.2f", (tot - c)));
                                    } else if (flag == 5) {
                                        cVendedor = (String) tablaEncabezado.getValueAt(row, 1);
                                        nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
                                        nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
                                        modeloTabla.establecerConsulta("SELECT cod, tDespachos, tDevolucion, tDescuentos, tRetContado, tRetCobros, tCobros, tVentasContado, tVentasCredito "
                                                + "FROM TVentasFecha "
                                                + "WHERE codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 1) + "' "
                                                + "AND fecha >= '" + nombreFecha + "' "
                                                + "AND fecha <= '" + nombreFecha2 + "' ORDER BY cod");
                                        double desp = 0.00, dev = 0.00, desc = 0.00, retCont = 0.00, retCob = 0.00, cob = 0.00, cont = 0.00, cred = 0.00;
                                        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                                            desp += (Double) modeloTabla.getValueAt(i, 1);
                                            dev += (Double) modeloTabla.getValueAt(i, 2);
                                            desc += (Double) modeloTabla.getValueAt(i, 3);
                                            retCont += (Double) modeloTabla.getValueAt(i, 4);
                                            retCob += (Double) modeloTabla.getValueAt(i, 5);
                                            cob += (Double) modeloTabla.getValueAt(i, 6);
                                            cont += (Double) modeloTabla.getValueAt(i, 7);
                                            cred += (Double) modeloTabla.getValueAt(i, 8);
                                        }
                                        textDesp.setText(String.format(Locale.UK, "%.2f", desp));
                                        textDev.setText(String.format(Locale.UK, "%.2f", dev));
                                        textDesc.setText(String.format(Locale.UK, "%.2f", desc));
                                        textRetCont.setText(String.format(Locale.UK, "%.2f", retCont));
                                        textRetCob.setText(String.format(Locale.UK, "%.2f", retCob));
                                        textCob.setText(String.format(Locale.UK, "%.2f", cob));
                                        textCont.setText(String.format(Locale.UK, "%.2f", desp - dev - cred - desc - retCont));
                                        textCred.setText(String.format(Locale.UK, "%.2f", cred));
                                        totalDinero.setText(String.format(Locale.UK, "%.2f", retCont + retCob + cob + Double.parseDouble(textCont.getText())));
                                        totalVendido.setText(String.format(Locale.UK, "%.2f", desp - dev));
                                        totalEspera.setText(String.format(Locale.UK, "%.2f", desp - dev + cob - cred - desc - retCont));
                                    } else if (flag == 6) {
                                        modeloTabla.establecerConsulta("SELECT Clientes.codCliente, ruta, nombreCliente, apellidoCliente, formaPago, compras, facturas "
                                                + "FROM Clientes, Rutas WHERE Clientes.codCliente = Rutas.codCliente "
                                                + "AND Clientes.codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 0) + "'");
                                    } else if (flag == 7) {
                                        modeloTabla.establecerConsulta("SELECT CambiosFecha.cantidad, producto, precioUnitario, CambiosFecha.total "
                                                + "FROM CambiosFecha, Productos WHERE CambiosFecha.cod = Productos.cod "
                                                + "AND codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 2) + "' "
                                                + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "' "
                                                + "AND codCambio = '" + (String) tablaEncabezado.getValueAt(row, 1) + "'");
                                        modelo.establecerConsulta("SELECT tCambio FROM TVentasFecha "
                                                + "WHERE cod = '" + (String) tablaEncabezado.getValueAt(row, 1) + "' "
                                                + "AND codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 2) + "' "
                                                + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "'");
                                        campTOT.setText(String.format(Locale.UK, "%.2f", (Double) modelo.getValueAt(0, 0)));
                                    } else if (flag == 8) {
                                        modeloTabla.establecerConsulta("SELECT ObsequiosFecha.cantidad, producto, precioUnitario, ObsequiosFecha.total "
                                                + "FROM ObsequiosFecha, Productos WHERE ObsequiosFecha.cod = Productos.cod "
                                                + "AND codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 2) + "' "
                                                + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "' "
                                                + "AND codObsequio = '" + (String) tablaEncabezado.getValueAt(row, 1) + "'");
                                        modelo.establecerConsulta("SELECT tObsequio FROM TVentasFecha "
                                                + "WHERE cod = '" + (String) tablaEncabezado.getValueAt(row, 1) + "' "
                                                + "AND codigoVendedor = '" + (String) tablaEncabezado.getValueAt(row, 2) + "' "
                                                + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "'");
                                        campTOT.setText(String.format(Locale.UK, "%.2f", (Double) modelo.getValueAt(0, 0)));
                                    } else if (flag == 9) {
                                        modeloTabla.establecerConsulta("SELECT PerdidaFecha.cantidad, producto, precioUnitario, PerdidaFecha.total, tPerdida "
                                                + "FROM PerdidaFecha, Productos WHERE PerdidaFecha.cod = Productos.cod "
                                                + "AND fecha = '" + tablaEncabezado.getValueAt(row, 0) + "' "
                                                + "AND codPerdida = '" + (String) tablaEncabezado.getValueAt(row, 1) + "'");
                                        campTOT.setText(String.format(Locale.UK, "%.2f", (Double) modeloTabla.getValueAt(0, 4)));
                                    }
                                } catch (HeadlessException | IllegalStateException | NumberFormatException | SQLException ee) {
                                    JOptionPane.showMessageDialog(null, ee.getMessage());
                                }
                            }
                        }
                    }
            );
            tablaConsulta.addMouseListener(
                    new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int row = tablaConsulta.rowAtPoint(e.getPoint());
                            if (row > -1) {
                                try {
                                    if (flag == 6) {
                                        modelo.establecerConsulta("SELECT idCliente, comercial, telefono, direccion "
                                                + "FROM Clientes WHERE codCliente = '" + (String) tablaConsulta.getValueAt(row, 0) + "'");
                                        idCliente.setText((String) modelo.getValueAt(0, 0));
                                        comCliente.setText((String) modelo.getValueAt(0, 1));
                                        telCliente.setText((String) modelo.getValueAt(0, 2));
                                        dirCliente.setText((String) modelo.getValueAt(0, 3));
                                    }
                                } catch (SQLException ee) {
                                    JOptionPane.showMessageDialog(null, ee.getMessage());
                                }
                            }
                        }
                    }
            );
            if (flag < 3 || flag > 6 && flag != 9) {
                tablaConsulta.setDefaultRenderer(Object.class, new MiRender2());
            }
            JPanel panelTotal = new JPanel(new GridLayout(1, 12));
            campTotal = new JTextField("TOTAL");
            campTotal.setFont(new Font("Serif", Font.BOLD, 12));
            campTotal.setHorizontalAlignment(SwingConstants.LEFT);
            campTotal.setForeground(Color.WHITE);
            campTotal.setBackground(Color.DARK_GRAY);
            campTotal.setEditable(false);
            campTOT = new JTextField("0.00");
            campTOT.setHorizontalAlignment(SwingConstants.RIGHT);
            campTOT.setForeground(Color.WHITE);
            campTOT.setBackground(Color.DARK_GRAY);
            campTOT.setEditable(false);
            if (!consulta || flag == 101) {
                panelTotal.add(new JLabel(""));
                panelTotal.add(new JLabel(""));
                panelTotal.add(new JLabel(""));
                panelTotal.add(new JLabel(""));
                panelTotal.add(new JLabel(""));
                panelTotal.add(new JLabel(""));
                panelTotal.add(campTotal);
                panelTotal.add(campTOT);
                panelTotal.add(new JLabel(""));
                panelTotal.add(new JLabel(""));
                panelTotal.add(new JLabel(""));
                panelTotal.add(new JLabel(""));
            } else {
                panelTotal.add(new JLabel(""));
                panelTotal.add(new JLabel(""));
                panelTotal.add(campTotal);
                panelTotal.add(campTOT);
            }

            JPanel panelPesos = new JPanel(new GridLayout(1, 9));
            panelPesos.add(new JLabel("Patatas:", SwingConstants.CENTER));
            pesoPapas = new JTextField("0");
            pesoPapas2 = new JTextField("");
            if (flag == 1) {
                panelPesos.add(pesoPapas);
            }
            panelPesos.add(new JLabel("sacos"));
            panelPesos.add(new JLabel("Platanos:", SwingConstants.CENTER));
            pesoPlatano = new JTextField("0");
            pesoPlatano2 = new JTextField("");
            if (flag == 1) {
                panelPesos.add(pesoPlatano);
            }
            panelPesos.add(new JLabel("racimos"));
            panelPesos.add(new JLabel("Yucas:", SwingConstants.CENTER));
            pesoYuca = new JTextField("0");
            pesoYuca2 = new JTextField("");
            if (flag == 1) {
                panelPesos.add(pesoYuca);
            }
            panelPesos.add(new JLabel("sacos"));
            pesoPapas.setEditable(false);
            pesoPapas2.setEditable(false);
            pesoPlatano.setEditable(false);
            pesoPlatano2.setEditable(false);
            pesoYuca.setEditable(false);
            pesoYuca2.setEditable(false);
            pesoMani = new JTextField("0");
            pesoMani2 = new JTextField("");
            pesoSisco = new JTextField("0");
            pesoSisco2 = new JTextField("");

            JPanel panelProd = new JPanel(new BorderLayout());
            JPanel panelComp = new JPanel(new GridLayout(5, 4));
            if (flag == 111) {
                panelComp.add(new JLabel("Patatas:"));
                panelComp.add(pesoPapas);
                panelComp.add(new JLabel("Total:", SwingConstants.CENTER));
            } else if (flag == 222) {
                panelComp.add(new JLabel("Patatas/Patacon/Yucas:"));
                panelComp.add(pesoPapas);
                panelComp.add(new JLabel("Total:", SwingConstants.CENTER));
            } else if (flag == 1 && consulta) {
                panelComp.add(new JLabel("Patatas:"));
                panelComp.add(pesoPapas);
                panelComp.add(new JLabel("Estimado /180:", SwingConstants.CENTER));
            }
            panelComp.add(pesoPapas2);
            if (flag == 111) {
                panelComp.add(new JLabel("Platanos:"));
                panelComp.add(pesoPlatano);
                panelComp.add(new JLabel("Total:", SwingConstants.CENTER));
            } else if (flag == 222) {
                panelComp.add(new JLabel("Man�:"));
                panelComp.add(pesoPlatano);
                panelComp.add(new JLabel("Total:", SwingConstants.CENTER));
            } else if (flag == 1 && consulta) {
                panelComp.add(new JLabel("Platanos:"));
                panelComp.add(pesoPlatano);
                panelComp.add(new JLabel("Estimado /70:", SwingConstants.CENTER));
            }
            panelComp.add(pesoPlatano2);
            if (flag == 111) {
                panelComp.add(new JLabel("Yuca:"));
                panelComp.add(pesoYuca);
                panelComp.add(new JLabel("Total:", SwingConstants.CENTER));
            } else if (flag == 222) {
                panelComp.add(new JLabel("Tostado:"));
                panelComp.add(pesoYuca);
                panelComp.add(new JLabel("Total:", SwingConstants.CENTER));
            } else if (flag == 1 && consulta) {
                panelComp.add(new JLabel("Yuca:"));
                panelComp.add(pesoYuca);
                panelComp.add(new JLabel("Estimado /140:", SwingConstants.CENTER));
            }
            panelComp.add(pesoYuca2);
            if (flag == 111) {
                panelComp.add(new JLabel("Man�:"));
                panelComp.add(pesoMani);
                panelComp.add(new JLabel("Total:", SwingConstants.CENTER));
                panelComp.add(pesoMani2);
                panelComp.add(new JLabel("Tostado:"));
                panelComp.add(pesoSisco);
                panelComp.add(new JLabel("Total:", SwingConstants.CENTER));
                panelComp.add(pesoSisco2);
            } else {
                panelComp.add(new JLabel(""));
                panelComp.add(new JLabel(""));
                panelComp.add(new JLabel(""));
                panelComp.add(new JLabel(""));
                panelComp.add(new JLabel(""));
                panelComp.add(new JLabel(""));
            }
            JPanel panelCuadVend = new JPanel(new GridLayout(1, 2));
            panelCuadVend.add(panelComp);
            if (flag == 222) {
                panelCuadVend.add(panelEntrega);
            }

            panelProd.add(new JScrollPane(panelTotal, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.NORTH);
            panelProd.add(panelCuadVend, BorderLayout.CENTER);

            if (!consulta) {
                if (flag < 9) {
                    if (flag == 1) {
                        panelCentro.add(panelPesos, BorderLayout.NORTH);
                    } else {
                        panelCentro.add(panelCodigo, BorderLayout.NORTH);
                    }
                    panelCentro.add(panelTablaCentro, BorderLayout.CENTER);
                    panelCentro.add(new JScrollPane(panelTotal, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.SOUTH);
                } else if (flag == 100) {
                    panelCentro.add(panelCodigo, BorderLayout.NORTH);
                    panelCentro.add(panelTablaCentro, BorderLayout.CENTER);
                    panelCentro.add(new JScrollPane(panelTotal, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.SOUTH);
                } else if (flag == 111 || flag == 222) {
                    panelCentro.add(panelTablaCentro, BorderLayout.CENTER);
                    panelCentro.add(panelProd, BorderLayout.SOUTH);
                } else if (flag == 9) {
                    panelCentro.add(panelCodigo, BorderLayout.NORTH);
                    panelCentro.add(panel, BorderLayout.CENTER);
                    panelCentro.add(new JScrollPane(panelTotal, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.SOUTH);
                } else {
                    panelCentro.add(panelDatos, BorderLayout.NORTH);
                }
            } else {
                if (flag == 0) {
                    panelCentro.add(new JScrollPane(tablaConsulta), BorderLayout.CENTER);
                    panelCentro.add(new JScrollPane(panelTotal, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.SOUTH);
                } else if (flag == 101) {
                    panelCentro.add(new JScrollPane(panelTablaCentro), BorderLayout.CENTER);
                    panelCentro.add(new JScrollPane(panelTotal, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.SOUTH);
                } else if (flag == 1) {
                    panelCentro.add(panel, BorderLayout.CENTER);
                    panelCentro.add(panelProd, BorderLayout.SOUTH);
                } else if (flag > 1 && flag != 5 && flag != 6) {
                    panelCentro.add(panel, BorderLayout.CENTER);
                    panelCentro.add(new JScrollPane(panelTotal, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.SOUTH);
                } else if (flag == 5) {
                    panelCentro.add(panel, BorderLayout.CENTER);
                    JPanel panelTotales = new JPanel(new GridLayout(1, 9));
                    textDev = new JTextField("");
                    textDesp = new JTextField("");
                    textDesc = new JTextField("");
                    textRetCont = new JTextField("");
                    textRetCob = new JTextField("");
                    textCob = new JTextField("");
                    textCont = new JTextField("");
                    textCred = new JTextField("", SwingConstants.LEFT);
                    textDesp.setHorizontalAlignment(SwingConstants.RIGHT);
                    textDev.setHorizontalAlignment(SwingConstants.RIGHT);
                    textDesc.setHorizontalAlignment(SwingConstants.RIGHT);
                    textRetCont.setHorizontalAlignment(SwingConstants.RIGHT);
                    textRetCob.setHorizontalAlignment(SwingConstants.RIGHT);
                    textCob.setHorizontalAlignment(SwingConstants.RIGHT);
                    textCont.setHorizontalAlignment(SwingConstants.RIGHT);
                    textCred.setHorizontalAlignment(SwingConstants.RIGHT);
                    panelTotales.add(new JLabel(""));
                    panelTotales.add(textDesp);
                    panelTotales.add(textDev);
                    panelTotales.add(textDesc);
                    panelTotales.add(textRetCont);
                    panelTotales.add(textRetCob);
                    panelTotales.add(textCob);
                    panelTotales.add(textCont);
                    panelTotales.add(textCred);
                    panelCentro.add(new JScrollPane(panelTotales, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.SOUTH);
                } else if (flag == 6) {
                    panelCentro.add(panelDatos, BorderLayout.NORTH);
                    panelCentro.add(panel, BorderLayout.CENTER);
                }
            }

            add(panelCentro, BorderLayout.CENTER);

            JPanel panelSur = new JPanel();

            guardar = new JButton("Guardar");
            guardar.setEnabled(false);
            panelSur.add(guardar);
            cancelar = new JButton("Cancelar");
            panelSur.add(cancelar);
            if (flag == 101) {
                existenciaInicial = new JButton("Guardar Existencia Inicial");
                existenciaInicial.setEnabled(false);
                panelSur.add(existenciaInicial);
            } else if (flag == 5) {
                botonCuadro = new JButton("Guardar Saldos de Ventas");
                botonCuadro.setEnabled(false);
                panelSur.add(botonCuadro);
            }

            add(panelSur, BorderLayout.SOUTH);

            nombreFecha = anio.getText() + "/" + mes.getText() + "/" + dia.getText();
            nombreFecha2 = anio2.getText() + "/" + mes2.getText() + "/" + dia2.getText();
        }

    }
}
