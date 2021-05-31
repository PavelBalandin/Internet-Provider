package org.example.model.dao;

import org.example.model.dao.impl.DAOFactoryImpl;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDAO createUserDao();

    public abstract ServiceDAO createServiceDao();

    public abstract TariffDAO createTariffDao();

    public abstract PaymentDAO createPaymentDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new DAOFactoryImpl();
                }
            }
        }
        return daoFactory;
    }
}
