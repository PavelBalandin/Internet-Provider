package org.example.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import java.sql.Connection;
import java.sql.SQLException;

public final class DBCPDataSource {
    private static final BasicDataSource ds = new BasicDataSource();
    private static DBCPDataSource instance;

    static {
        ds.setUrl("jdbc:postgresql://localhost:5432/InternetProvider");
        ds.setUsername("postgres");
        ds.setPassword("0147258");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static synchronized DBCPDataSource getInstance() {
        if (instance == null) {
            instance = new DBCPDataSource();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = ds.getConnection();
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return connection;
    }

    private DBCPDataSource() {
    }

    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
