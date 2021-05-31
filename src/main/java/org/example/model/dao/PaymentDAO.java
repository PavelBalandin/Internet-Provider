package org.example.model.dao;

import org.example.model.entity.Payment;

import java.util.List;

public interface PaymentDAO extends GenericDAO<Payment> {
    List<Payment> findPaymentListByUserLogin(String login);
}
