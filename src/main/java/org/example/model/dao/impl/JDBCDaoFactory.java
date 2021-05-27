package org.example.model.dao.impl;

import org.example.model.dao.DaoFactory;
import org.example.model.dao.ServiceDao;
import org.example.model.dao.TariffDao;
import org.example.model.dao.UserDao;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DBCPDataSource dbcpDataSource = DBCPDataSource.getInstance();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public ServiceDao createServiceDao() {
        return new JDBCServiceDao(getConnection());
    }

    @Override
    public TariffDao createTariffDao() {
        return new JDBCTariffDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dbcpDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
