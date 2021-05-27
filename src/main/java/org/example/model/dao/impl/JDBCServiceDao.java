package org.example.model.dao.impl;

import org.apache.log4j.Logger;
import org.example.controller.command.LoginCommand;
import org.example.model.dao.ServiceDao;
import org.example.model.dao.mapper.ServiceMapper;
import org.example.model.dao.mapper.TariffMapper;
import org.example.model.entity.Service;
import org.example.model.entity.Tariff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCServiceDao implements ServiceDao {

    private final String SELECT_SERVICES = "SELECT s.id AS serviceid, s.name as servicename, t.*  FROM services s JOIN tariffs t ON s.id = t.service_id;";
    private static final String SELECT_SERVICE_BY_ID = "SELECT * FROM SERVICES WHERE id = ?;";
    private static final String SELECT_SERVICE_BY_NAME = "SELECT * FROM SERVICES WHERE name = ?;";
    private static final String INSERT_SERVICE = "INSERT INTO SERVICES VALUES(DEFAULT, ?);";
    private static final String UPDATE_SERVICE = "UPDATE SERVICES SET name  = ? WHERE id = ?;";
    private static final String DELETE_SERVICE = "DELETE FROM SERVICES WHERE id = ?;";

    private final Logger logger = Logger.getLogger(LoginCommand.class);

    private Connection connection;

    public JDBCServiceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Service entity) {

    }

    @Override
    public Service findById(int id) {
        return null;
    }

    @Override
    public List<Service> findAll() {
        Map<Integer, Service> services = new HashMap<>();
        Map<Integer, Tariff> tariffs = new HashMap<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SELECT_SERVICES);

            ServiceMapper serviceMapper = new ServiceMapper();
            TariffMapper tariffMapper = new TariffMapper();

            while (rs.next()) {
                Service service = serviceMapper
                        .extractFromResultSet(rs);
                Tariff tariff = tariffMapper
                        .extractFromResultSet(rs);
                service = serviceMapper
                        .makeUnique(services, service);
                tariff = tariffMapper
                        .makeUnique(tariffs, tariff);
                service.getTariffs().add(tariff);
            }
            DBCPDataSource.commitAndClose(connection);
            return new ArrayList<>(services.values());
        } catch (SQLException e) {
            DBCPDataSource.rollbackAndClose(connection);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Service entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
