package org.example.model.service;

import org.apache.log4j.Logger;
import org.example.model.dao.DaoFactory;
import org.example.model.dao.UserDAO;
import org.example.model.entity.Status;
import org.example.model.entity.Tariff;
import org.example.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class UserService {

    private final Logger logger = Logger.getLogger(UserService.class);

    DaoFactory daoFactory = DaoFactory.getInstance();

    public User getUserById(int id) {
        UserDAO dao = daoFactory.createUserDao();
        return dao.findById(id);

    }

    public User getUserByLogin(String login) {
        UserDAO dao = daoFactory.createUserDao();
        return dao.findByLogin(login);
    }

    public User createUser(String name, String password, String firstname, String lastname) {
        UserDAO dao = daoFactory.createUserDao();
        User userFromDb = dao.create(new User.Builder()
                .withLogin(name)
                .withPassword(password)
                .withFirstName(firstname)
                .withLastName(lastname)
                .build());
        logger.info("User has been created: " + userFromDb);
        return userFromDb;
    }

    public User updateUser(int id, int statusId) {
        Status status = new Status();
        status.setId(statusId);
        UserDAO dao = daoFactory.createUserDao();
        User userFromDb = dao.update(new User.Builder()
                .withId(id)
                .withStatus(status)
                .build());
        logger.info("User has been updated: " + userFromDb);
        return userFromDb;
    }

    public BigDecimal makeOrder(String userLogin, List<Tariff> tariffList) {
        UserDAO dao = daoFactory.createUserDao();

        BigDecimal remainder = dao.makeOrder(userLogin, tariffList);
        logger.info("Remainder: " + remainder);
        return remainder;
    }
}
