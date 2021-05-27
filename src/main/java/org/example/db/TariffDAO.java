package org.example.db;

import org.example.model.entity.Tariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffDAO {
    private static final String SELECT_TARIFFS = "SELECT * FROM TARIFFS;";

    private static final String SELECT_TARIFFS_BY_ID = "SELECT * FROM TARIFFS WHERE id = ?;";

    private static final String SELECT_TARIFFS_BY_SERVICE_ID = "SELECT * FROM TARIFFS WHERE service_id = %d ORDER BY %s %s;";

    private static final String INSERT_TARIFFS = "INSERT INTO tariffs VALUES (DEFAULT, ?, ?, ?, ?, ?);";

    private static final String UPDATE_TARIFFS = "UPDATE TARIFFS SET name  = ?, description = ?, duration = ?, prise = ?, service_id = ?  WHERE id = ?;";

    private static final String DELETE_TARIFFS = "DELETE FROM TARIFFS WHERE id = ?;";

//    public List<Tariff> getTariffsByServiceId(int id, String sortBy, String sortHow) {
//        if (sortBy == null) {
//            sortBy = "name";
//        }
//        if (sortHow == null) {
//            sortHow = "asc";
//        }
//
//        List<Tariff> tariffList = new ArrayList<>();
//        try (Connection connection = DBCPDataSource.getInstance().getConnection()) {
//            try (Statement statement = connection.createStatement()) {
//                String query = String.format(SELECT_TARIFFS_BY_SERVICE_ID, id, sortBy, sortHow);
//                try (ResultSet resultSet = statement.executeQuery(query)) {
//                    while (resultSet.next()) {
//                        Tariff tariff = new Tariff(
//                                (long) resultSet.getInt("id"),
//                                resultSet.getString("name"),
//                                resultSet.getString("description"),
//                                resultSet.getString("duration"),
//                                resultSet.getInt("price"),
//                                resultSet.getInt("service_id")
//                        );
//                        tariffList.add(tariff);
//                    }
//                }
//            }
//        } catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//        }
//        return tariffList;
//    }
}
