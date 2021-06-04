package org.example.model.dao.impl;

import org.example.model.dao.ServiceDAO;
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

public class ServiceDAOImpl implements ServiceDAO {

    private final String SELECT_SERVICES = "SELECT s.id AS serviceid, s.name as servicename, t.*  FROM services s JOIN tariffs t ON s.id = t.service_id;";

    private Connection connection;

    public ServiceDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Service create(Service entity) {
        return null;
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
        } catch (SQLException e) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(e);
        }
        return new ArrayList<>(services.values());
    }

    @Override
    public Service update(Service entity) {
        return null;
    }

    @Override
    public void delete(int id) {
    }
}
