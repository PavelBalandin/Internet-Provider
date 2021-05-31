package org.example.model.service;

import org.example.model.dao.DaoFactory;
import org.example.model.dao.UserDAO;
import org.example.model.entity.Status;
import org.example.model.entity.Tariff;
import org.example.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public User getUserById(int id) {
        UserDAO dao = daoFactory.createUserDao();
        return dao.findById(id);

    }

    public User getUserByLogin(String login) {
        UserDAO dao = daoFactory.createUserDao();
        return dao.findByLogin(login);
    }

    public void createUser(String name, String password, String firstname, String lastname) {
        User user = new User();
        user.setLogin(name);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);

        UserDAO dao = daoFactory.createUserDao();
        dao.create(user);
    }

    public void updateUser(int id, int statusId) {
        Status status = new Status();
        status.setId(statusId);

        User user = new User.Builder()
                .withId(id)
                .withStatus(status)
                .build();

        UserDAO dao = daoFactory.createUserDao();
        dao.update(user);
    }

    public BigDecimal makeOrder(String userLogin, List<Tariff> tariffList) {
        UserDAO dao = daoFactory.createUserDao();
        return dao.makeOrder(userLogin, tariffList);
    }
}
