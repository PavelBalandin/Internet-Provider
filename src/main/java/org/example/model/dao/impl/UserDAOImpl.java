package org.example.model.dao.impl;

import org.apache.log4j.Logger;
import org.example.controller.command.common.LoginCommand;
import org.example.model.dao.UserDAO;
import org.example.model.dao.mapper.RoleMapper;
import org.example.model.dao.mapper.StatusMapper;
import org.example.model.dao.mapper.UserMapper;
import org.example.model.entity.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDAOImpl implements UserDAO {

    private final String SELECT_USERS = "SELECT * FROM USERS;";
    private final String SELECT_USER_BY_ID = "SELECT * FROM USERS WHERE id = ?;";
    private final String SELECT_USER_BY_LOGIN = "SELECT u.*, \n" +
            "r.id as role_id, r.name as role_name,\n" +
            "s.id as status_id, s.name as status_name\n" +
            "FROM users u JOIN roles r ON u.role_id = r.id \n" +
            "JOIN statuses s ON u.status_id = s.id \n" +
            "where u.login = ?;";
    private final String INSERT_USER = "INSERT INTO USERS VALUES(DEFAULT, ?, ?, ?, ?, DEFAULT, DEFAULT);";
    private final String UPDATE_USER = "UPDATE USERS SET status_id = ? WHERE id = ?;";
    private final String DELETE_USER = "DELETE FROM USERS WHERE id = ?;";
    private final String DELETE_USER_BY_LOGIN = "DELETE FROM USERS WHERE login = ?;";

    private final String MAKE_ORDER = "SELECT make_order(? , ?);";


    private final Logger logger = Logger.getLogger(LoginCommand.class);

    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.execute();
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        List<Payment> payments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            RoleMapper roleMapper = new RoleMapper();
            StatusMapper statusMapper = new StatusMapper();
            if (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
                Role role = roleMapper.extractFromResultSet(rs);
                Status status = statusMapper.extractFromResultSet(rs);
                user.setRole(role);
                user.setStatus(status);
            }

        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
            ps.setInt(1, user.getStatus().getId());
            ps.setInt(2, user.getId());
            ps.execute();
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public BigDecimal makeOrder(String userLogin, List<Tariff> tariffList) {
        try (PreparedStatement ps = connection.prepareStatement(MAKE_ORDER)) {
            Array sqlArray = connection.createArrayOf("INTEGER", tariffList.stream().map(BaseEntity::getId).collect(Collectors.toList()).toArray());
            ps.setString(1, userLogin);
            ps.setArray(2, sqlArray);
            ResultSet rs = ps.executeQuery();
            BigDecimal reminder = new BigDecimal("-1");
            if (rs.next()) {
                reminder = rs.getBigDecimal("make_order");
                DBCPDataSource.commitAndClose(connection);
            }
            return reminder;
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
    }
}
