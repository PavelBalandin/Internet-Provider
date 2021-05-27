package org.example.model.dao;

import org.example.model.entity.User;

public interface UserDao extends GenericDao<User> {

    User findByLogin(String login);
}
