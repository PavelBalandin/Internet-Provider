package org.example.model.dao.impl;

import org.apache.log4j.Logger;
import org.example.controller.command.common.LoginCommand;
import org.example.model.dao.TariffDAO;
import org.example.model.dao.mapper.*;
import org.example.model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TariffDAOImpl implements TariffDAO {

    private final String SELECT_TARIFFS = "SELECT * FROM TARIFFS;";
    private final String SELECT_TARIFFS_BY_ID =
            "SELECT t.*, s.id as serviceid, s.name as servicename " +
                    "FROM tariffs t JOIN services s ON t.service_id = s.id " +
                    "WHERE t.id = ?;";

    private final String SELECT_TARIFFS_BY_LOGIN = "SELECT t.*, ut.date_begin, ut.date_end, " +
            "s.id AS serviceid, s.name AS servicename  FROM users u \n" +
            "JOIN user_tariff ut ON u.id = ut.user_id\n" +
            "JOIN tariffs t ON t.id = ut.tariff_id\n" +
            "JOIN services s ON s.id = t.service_id\n" +
            "where u.login = ?;";

    private final String SELECT_TARIFFS_BY_SERVICE_ID = "SELECT * FROM TARIFFS WHERE service_id = %d ORDER BY %s %s;";
    private final String INSERT_TARIFFS = "INSERT INTO tariffs VALUES (DEFAULT, ?, ?, ?, ?, ?);";
    private final String UPDATE_TARIFFS = "UPDATE TARIFFS SET name  = ?, description = ?, duration = ?, price = ?, service_id = ?  WHERE id = ?;";
    private final String DELETE_TARIFFS = "DELETE FROM TARIFFS WHERE id = ?;";

    private final String SELECT_SERVICES = "SELECT s.id AS serviceid, s.name as servicename, t.*  FROM services s JOIN tariffs t ON s.id = t.service_id;";

    private final String SELECT_TARIFFS_PAGINATED =
            "SELECT ts.*, se.id AS join_service_id, se.name as join_service_name\n" +
                    "FROM (SELECT t.*, s.id AS serviceid, s.name AS servicename, count(*) over()\n" +
                    "FROM tariffs t JOIN services s ON t.service_id = s.id\n" +
                    "GROUP BY t.id, s.id\n" +
                    "ORDER BY t.id ASC\n" +
                    "OFFSET ?\n" +
                    "LIMIT ?) \n" +
                    "AS ts RIGHT JOIN services se ON TRUE;";

    private final Logger logger = Logger.getLogger(LoginCommand.class);

    private Connection connection;

    public TariffDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Tariff tariff) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_TARIFFS)) {
            ps.setString(1, tariff.getName());
            ps.setString(2, tariff.getDescription());
            ps.setString(3, tariff.getDuration());
            ps.setBigDecimal(4, tariff.getPrice());
            ps.setInt(5, tariff.getService().getId());
            ps.execute();
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);

    }

    @Override
    public Tariff findById(int id) {
        Tariff tariff = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_TARIFFS_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            TariffMapper tariffMapper = new TariffMapper();
            ServiceMapper serviceMapper = new ServiceMapper();

            if (rs.next()) {
                tariff = tariffMapper
                        .extractFromResultSet(rs);
                Service service = serviceMapper
                        .extractFromResultSet(rs);
                tariff.setService(service);
            }
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }

        DBCPDataSource.commitAndClose(connection);
        return tariff;
    }


    @Override
    public List<Tariff> findAllByServiceId(int id, String sort, String order) {
        if (sort == null) {
            sort = "name";
        }
        if (order == null) {
            order = "asc";
        }

        List<Tariff> tariffs = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String query = String.format(SELECT_TARIFFS_BY_SERVICE_ID, id, sort, order);
            ResultSet rs = statement.executeQuery(query);
            TariffMapper tariffMapper = new TariffMapper();
            while (rs.next()) {
                Tariff tariff = tariffMapper.extractFromResultSet(rs);
                tariffs.add(tariff);
            }
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return tariffs;
    }

    @Override
    public TariffPage findPaginated(int page, int size) {
        TariffPage tariffPage = new TariffPage();
        Map<Integer, Service> services = new HashMap<>();
        Map<Integer, Tariff> tariffs = new HashMap<>();

        try (
                PreparedStatement ps = connection.prepareStatement(SELECT_TARIFFS_PAGINATED);
        ) {
            ps.setInt(1, page * size);
            ps.setInt(2, size);
            ResultSet rs = ps.executeQuery();
            ServiceMapper serviceMapper = new ServiceMapper();
            TariffMapper tariffMapper = new TariffMapper();
            while (rs.next()) {
                Service service = new Service.Builder()
                        .withId(rs.getInt("join_service_id"))
                        .withName(rs.getString("join_service_name"))
                        .build();
                serviceMapper
                        .makeUnique(services, service);
                Service tariffService = serviceMapper
                        .extractFromResultSet(rs);
                tariffService = serviceMapper
                        .makeUnique(services, tariffService);
                Tariff tariff = tariffMapper
                        .extractFromResultSet(rs);
                tariff = tariffMapper
                        .makeUnique(tariffs, tariff);
                tariff.setService(tariffService);
                tariffPage.setTotal(rs.getInt("count"));
            }
            tariffPage.setTariffList(new ArrayList<>(tariffs.values()));
            tariffPage.setServiceList(new ArrayList<>(services.values()));
            tariffPage.setPage(page);
            tariffPage.setPageSize(size);
            DBCPDataSource.commitAndClose(connection);
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        return tariffPage;
    }

    @Override
    public List<Tariff> findTariffsByLogin(String login) {
        Map<Integer, Service> services = new HashMap<>();
        List<Tariff> tariffList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(SELECT_TARIFFS_BY_LOGIN)) {
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            ServiceMapper serviceMapper = new ServiceMapper();
            TariffMapper tariffMapper = new TariffMapper();

            while (rs.next()) {
                Service service = serviceMapper
                        .extractFromResultSet(rs);
                service = serviceMapper
                        .makeUnique(services, service);
                Tariff tariff = tariffMapper
                        .extractFromResultSet(rs);
                tariff.setService(service);

                tariffList.add(tariff);
            }
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return tariffList;
    }

    @Override
    public List<Tariff> findAll() {
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
                tariff.setService(service);
            }
            DBCPDataSource.commitAndClose(connection);
            return new ArrayList<>(tariffs.values());
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Tariff tariff) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_TARIFFS)) {
            ps.setString(1, tariff.getName());
            ps.setString(2, tariff.getDescription());
            ps.setString(3, tariff.getDuration());
            ps.setBigDecimal(4, tariff.getPrice());
            ps.setInt(5, tariff.getService().getId());
            ps.setInt(6, tariff.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_TARIFFS)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
    }

    @Override
    public void close() {

    }

}
