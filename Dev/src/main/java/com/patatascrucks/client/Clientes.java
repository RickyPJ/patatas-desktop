package com.patatascrucks.client;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.patatascrucks.util.ResultSetTableModel;

public class Clientes extends JFrame {

    static final String CONTROLADOR_JDBC = "com.mysql.jdbc.Driver";
    static final String URL_BASEDATOS = "jdbc:mysql://192.168.1.254/patatas";
    static final String USUARIO = "root";
    static final String PASSWORD = "ytsejam";
    static final String CONSULTA_IMPLICITA = "SELECT Clientes.codCliente, ruta, nombreCliente, apellidoCliente, direccion, comercial, telefono, idCliente, formaPago "
            + "FROM Clientes, Rutas WHERE Clientes.codCliente = Rutas.codCliente ORDER BY codCliente";
    private Connection conexion;
    private Statement sentencia;
    private ResultSetTableModel modelo;
    private final JTable tabla;

    public Clientes() {
        super("Clientes Patatas");

        try {
            modelo = new ResultSetTableModel(CONTROLADOR_JDBC, URL_BASEDATOS, USUARIO, PASSWORD,
                    CONSULTA_IMPLICITA);
            conexion = modelo.getConnection();
            sentencia = conexion.createStatement();

            modelo.establecerConsulta(CONSULTA_IMPLICITA);
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC");
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            System.err.println("Incapaz de conectarse");
            e.printStackTrace();
            System.exit(1);
        }

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);

        addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent evento) {
                        modelo.desconectarDeBaseDatos();
                        System.exit(0);
                    }
                }
        );
    }

    public static void main(String[] args) {
        new Clientes();
    }
}
