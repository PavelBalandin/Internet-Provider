package org.example.model.service;

import org.example.model.dao.impl.DBCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUtility {

    private static Connection connection;

    public static void createTestDataQuery(String query) {
        try {
            connection = DBCPDataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
            DBCPDataSource.commitAndClose(connection);
        } catch (SQLException e) {
            DBCPDataSource.rollbackAndClose(connection);
            System.out.println(e.getMessage());
        }
    }
}
