//
// ResultSetTableModel.java
//

package util;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;
import javax.swing.*;

// Las filas y columnas del objeto ResultSet se cuentan desde 1
// y las filas y columnas del objeto JTable se cuentan desde 0.
// Al procesar filas o columnas de ResultSet para usarlas en un objeto JTable,
// es necesario sumar 1 al numero de fila o columna para manipular la columna apropiada del objeto ResultSet
// (es decir, la columna 0 de JTable es la columna 1 de ResultSet y la fila 0 de JTable es la fila 1 de ResultSet).
public final class ResultSetTableModel extends AbstractTableModel
{
   private final Connection conexion;
   private final Statement instruccion;
   private ResultSet conjuntoResultados;
   private ResultSetMetaData metaDatos;
   private int numeroDeFilas;

   // Lleva la cuenta del estado de la conexion a la base de datos
   private boolean conectadoABaseDatos = false;

   // El constructor inicializa conjuntoResultados y obtiene su objeto de metadatos; determina el numero de filas
   public ResultSetTableModel(String controlador,String url, String nombreusuario, String contrasenia, String consulta)
   throws SQLException, ClassNotFoundException
   {
      // Se conecta a la base de datos
      Class.forName(controlador);
      conexion = DriverManager.getConnection(url, nombreusuario, contrasenia);

      // Crea objeto Statement para consultar la base de datos
      instruccion = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

      // Actualiza el estado de la conexion a la base de datos
      conectadoABaseDatos = true;

      // Establece consulta y la ejecuta
      establecerConsulta(consulta);
   } // fin del constructor ResultSetTableModel

   // Establece nueva cadena de consulta en la base de datos
   public void establecerConsulta(String consulta) throws SQLException, IllegalStateException
   {
      // Verifica que este disponible la conexion a la base de datos
      if (!conectadoABaseDatos)
         throw new IllegalStateException("No hay conexion a la base de datos");

      // Especifica la consulta y la ejecuta
      conjuntoResultados = instruccion.executeQuery(consulta);

      // Obtiene metadatos para el objeto ResultSet
      metaDatos = conjuntoResultados.getMetaData();

      // Determina el numero de filas en el objeto ResultSet
      conjuntoResultados.last(); // avanza a la ultima fila
      numeroDeFilas = conjuntoResultados.getRow(); // obtiene el numero de fila

      // Notifica al objeto JTable que el modelo ha cambiado
      fireTableStructureChanged();
   } // fin del metodo establecerConsulta

   // Obtiene la clase que representa el tipo de la columna
   @Override
   public Class getColumnClass(int columna) throws IllegalStateException
   {
      // Verifica que este disponible la conexion a la base de datos
      if (!conectadoABaseDatos)
         throw new IllegalStateException("No hay conexion a la base de datos");

      // Determina la clase de Java de la columna
      try
      {
         String nombreClase = metaDatos.getColumnClassName(columna + 1);

         // Devuelve objeto Class que representa a nombreClase
         return Class.forName(nombreClase);
      } // fin de try
      catch (SQLException | ClassNotFoundException excepcion)
      {
         excepcion.printStackTrace();
      } // fin de catch

      return Object.class; // si ocurren problemas en el codigo anterior, asume el tipo Object
   } // fin del metodo getColumnClass

   // Obtiene el numero de columnas en el objeto ResultSet
   @Override
   public int getColumnCount() throws IllegalStateException
   {
      // Verifica que este disponible la conexion a la base de datos
      if (!conectadoABaseDatos)
         throw new IllegalStateException("No hay conexion a la base de datos");

      // Determina el numero de columnas
      try
      {
         return metaDatos.getColumnCount();
      } // fin de try
      catch (SQLException excepcionSql)
      {
         excepcionSql.printStackTrace();
      } // fin de catch

      return 0; // si ocurren problemas en el codigo anterior, devuelve 0 para el numero de columnas
   } // fin del metodo getColumnCount

   // Obtiene el nombre de una columna especifica en el objeto ResultSet
   @Override
   public String getColumnName(int columna) throws IllegalStateException
   {
      // Verifica que este disponible la conexion a la base de datos
      if (!conectadoABaseDatos)
         throw new IllegalStateException("No hay conexion a la base de datos");

      // Determina el nombre de la columna
      try
      {
         return metaDatos.getColumnName(columna + 1);
      } // fin de try
      catch (SQLException excepcionSql)
      {
         excepcionSql.printStackTrace();
      } // end catch

      return ""; // si hay problemas, devuelve la cadena vacia para el nombre de la columna
   } // fin del metodo getColumnName

   // Devuelve el numero de filas en el objeto ResultSet
   @Override
   public int getRowCount() throws IllegalStateException
   {
      // Verifica que este disponible la conexion a la base de datos
      if (!conectadoABaseDatos)
         throw new IllegalStateException("No hay conexion a la base de datos");

      return numeroDeFilas;
   } // fin del metodo getRowCount

   // Obtiene el valor en la fila y columna especificas
   @Override
   public Object getValueAt(int fila, int columna) throws IllegalStateException
   {
      // Verifica que este disponible la conexion a la base de datos
      if (!conectadoABaseDatos)
         throw new IllegalStateException("No hay conexion a la base de datos");

      // Obtiene un valor en una fila y columna especificadas del objeto ResultSet
      try
      {
         conjuntoResultados.absolute(fila + 1);
         return conjuntoResultados.getObject(columna + 1);
      } // fin de try
      catch (SQLException excepcionSql)
      {
         JOptionPane.showMessageDialog(null, "Datos Invalidos!" , "Error", JOptionPane.ERROR_MESSAGE);
      } // fin de catch

      return ""; // si hay problemas, devuelve el objeto cadena vacia
   } // fin del metodo getValueAt

   // Cierra objetos Statement y Connection
   public void desconectarDeBaseDatos()
   {
      // Cierra objetos Statement y Connection
      try
      {
         instruccion.close();
         conexion.close();
      } // fin de try
      catch (SQLException excepcionSql)
      {
         excepcionSql.printStackTrace();
      } // fin de catch
      finally // actualiza el estado de la conexion a la base de datos
      {
         conectadoABaseDatos = false;
      } // fin de finally
   } // fin del metodo desconectarDeBaseDatos

   // Devolver la conexion
   public Connection getConnection()
   {
      return conexion;
   } // fin de getConnection
}  // fin de la clase ResultSetTableModel
