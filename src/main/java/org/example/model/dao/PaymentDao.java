package org.example.model.dao;

import org.example.model.entity.Payment;

import java.util.List;

public interface PaymentDao extends GenericDao<Payment> {

    List<Payment> findPaymentListByUserLogin(String login);
}
