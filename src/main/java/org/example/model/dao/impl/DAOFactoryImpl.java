package org.example.model.dao.impl;

import org.example.model.dao.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactoryImpl extends DaoFactory {

    private DBCPDataSource dbcpDataSource = DBCPDataSource.getInstance();

    @Override
    public UserDAO createUserDao() {
        return new UserDAOImpl(getConnection());
    }

    @Override
    public ServiceDAO createServiceDao() {
        return new ServiceDAOImpl(getConnection());
    }

    @Override
    public TariffDAO createTariffDao() {
        return new TariffDAOImpl(getConnection());
    }

    @Override
    public PaymentDAO createPaymentDao() {
        return new PaymentDAOImpl(getConnection());
    }

    private Connection getConnection() {
        try {
            return dbcpDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
