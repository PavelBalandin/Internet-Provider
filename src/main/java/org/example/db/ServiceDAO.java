package org.example.db;

import org.example.model.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    private static final String SELECT_SERVICES = "SELECT * FROM SERVICES;";

    private static final String SELECT_SERVICE_BY_ID = "SELECT * FROM SERVICES WHERE id = ?;";

    private static final String SELECT_SERVICE_BY_NAME = "SELECT * FROM SERVICES WHERE name = ?;";

    private static final String INSERT_SERVICE = "INSERT INTO SERVICES VALUES(DEFAULT, ?);";

    private static final String UPDATE_SERVICE = "UPDATE SERVICES SET name  = ? WHERE id = ?;";

    private static final String DELETE_SERVICE = "DELETE FROM SERVICES WHERE id = ?;";

    private static final String URL = "jdbc:postgresql://localhost:5432/InternetProvider?user=postgres&password=0147258";

    public List<Service> getServices() {
        List<Service> serviceList = new ArrayList<>();
        try (Connection connection = DBManager.getInstance().getConnection(URL)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SELECT_SERVICES)) {
                    while (resultSet.next()) {
                        Service service = new Service(
                                (long) resultSet.getInt("id"),
                                resultSet.getString("name")
                        );
                        serviceList.add(service);
                    }
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return serviceList;
    }
}
