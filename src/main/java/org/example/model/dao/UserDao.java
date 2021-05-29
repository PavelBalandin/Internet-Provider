package org.example.model.dao;

import org.example.model.entity.Tariff;
import org.example.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao extends GenericDao<User> {

    User findByLogin(String login);

    BigDecimal makeOrder(String userLogin, List<Tariff> tariffList);
}
