package org.example.model.service;

import org.example.model.dao.DaoFactory;
import org.example.model.dao.UserDao;
import org.example.model.entity.Status;
import org.example.model.entity.User;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public User getUserById(int id) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findById(id);
        }
    }

    public User getUserByLogin(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByLogin(login);
        }
    }

    public boolean createUser(String name, String password, String firstname, String lastname) {
        User user = new User();
        user.setLogin(name);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);

        try (UserDao dao = daoFactory.createUserDao()) {
            dao.create(user);
            return true;
        } catch (RuntimeException exception) {
            return false;
        }
    }

    public boolean updateUser(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.update(user);
            return true;
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
