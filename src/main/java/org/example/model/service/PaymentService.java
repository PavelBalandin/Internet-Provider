package org.example.model.service;

import org.example.model.dao.DaoFactory;
import org.example.model.dao.PaymentDao;
import org.example.model.entity.Payment;
import org.example.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class PaymentService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Payment> getPaymentListByUser(String login) {
        try (PaymentDao dao = daoFactory.createPaymentDao()) {
            return dao.findPaymentListByUserLogin(login);
        }
    }

    public void createPayment(String userLogin, BigDecimal payment) {

        User user = new User.Builder()
                .withLogin(userLogin)
                .build();

        Payment paymentEntity = new Payment();
        paymentEntity.setPayment(payment);
        paymentEntity.setUser(user);

        try (PaymentDao dao = daoFactory.createPaymentDao()) {
            dao.create(paymentEntity);
        }
    }

}
