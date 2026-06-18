package persistencia;

import util.ConstantesDB;

import java.sql.*;
import java.util.Vector;

public class AgenteDB implements ConstantesDB {
    // instancia del agente
    protected static AgenteDB mInstancia = null;
    // Conexion con la base de datos
    protected static Connection mBD;
    // Identificador ODBC de la base de datos
    private static final String url = CONNECTION_STRING + "/" + DBNAME;

    // Constructor
    private AgenteDB() throws Exception {
        conectar();

    }

    // Implementacion del patron singleton
    // Este patron de dise�o permite implementar clases de las cuales
    // solo existir una instancia
    // http://es.wikipedia.org/wiki/Singleton
    public static AgenteDB getAgente() throws Exception {
        if (mInstancia == null) {
            mInstancia = new AgenteDB();
        }
        return mInstancia;
    }

    // Metodo para realizar la conexion a la base de datos
    private void conectar() throws Exception {
        Class.forName(DRIVER);
        mBD = DriverManager.getConnection(url, ConstantesDB.DBUSER, ConstantesDB.DBPASS);
    }

    // Metodo para desconectar de la base de datos
    public void desconectar() throws Exception {
        mBD.close();
    }

    // Metodo para realizar una insercion en la base de datos
    public int insert(String SQL) throws Exception {
        conectar();
        PreparedStatement stmt = mBD.prepareStatement(SQL);
        int res = stmt.executeUpdate();
        stmt.close();
        desconectar();
        return res;
    }

    // Metodo para realizar una eliminacion en la base de datos
    public int delete(String SQL) throws Exception {
        conectar();
        PreparedStatement stmt = mBD.prepareStatement(SQL);
        int res = stmt.executeUpdate();
        stmt.close();
        desconectar();
        return res;
    }

    // Metodo para realizar una eliminacion en la base de datos
    public int update(String SQL) throws Exception {
        conectar();
        PreparedStatement stmt = mBD.prepareStatement(SQL);
        int res = stmt.executeUpdate();
        stmt.close();
        desconectar();
        return res;
    }

    public Vector<Object> select(String SQL) throws Exception {
        Vector<Object> vectoradevolver = new Vector<Object>();
        conectar();
        Statement stmt = mBD.createStatement();
        ResultSet res = stmt.executeQuery(SQL);

        // 1. Get Metadata to know how many columns the query returned
        ResultSetMetaData rsm = res.getMetaData();
        int numColumnas = rsm.getColumnCount();

        while (res.next()) {
            Vector<Object> v = new Vector<Object>();

            // 2. Loop dynamically from 1 to the number of columns
            for (int i = 1; i <= numColumnas; i++) {
                v.add(res.getObject(i));
            }

            vectoradevolver.add(v);
        }

        stmt.close();
        desconectar();
        return vectoradevolver;
    }
}