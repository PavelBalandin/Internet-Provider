package org.example.db;

import org.example.model.entity.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    private static final String SELECT_SERVICES = "SELECT * FROM SERVICES;";

    private static final String SELECT_SERVICE_BY_ID = "SELECT * FROM SERVICES WHERE id = ?;";

    private static final String SELECT_SERVICE_BY_NAME = "SELECT * FROM SERVICES WHERE name = ?;";

    private static final String INSERT_SERVICE = "INSERT INTO SERVICES VALUES(DEFAULT, ?);";

    private static final String UPDATE_SERVICE = "UPDATE SERVICES SET name  = ? WHERE id = ?;";

    private static final String DELETE_SERVICE = "DELETE FROM SERVICES WHERE id = ?;";

//    public List<Service> getServices() {
//        List<Service> serviceList = new ArrayList<>();
//        try (Connection connection = DBCPDataSource.getInstance().getConnection()) {
//            try (Statement statement = connection.createStatement()) {
//                try (ResultSet resultSet = statement.executeQuery(SELECT_SERVICES)) {
//                    while (resultSet.next()) {
//                        Service service = new Service(
//                                (long) resultSet.getInt("id"),
//                                resultSet.getString("name")
//                        );
//                        serviceList.add(service);
//                    }
//                }
//            }
//        } catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//        }
//        return serviceList;
//    }


}
