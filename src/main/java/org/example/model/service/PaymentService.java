package org.example.model.service;

import org.example.model.dao.DaoFactory;
import org.example.model.dao.PaymentDAO;
import org.example.model.entity.Payment;
import org.example.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class PaymentService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Payment> getPaymentListByUser(String login) {
        PaymentDAO dao = daoFactory.createPaymentDao();
        return dao.findPaymentListByUserLogin(login);
    }

    public void createPayment(String userLogin, BigDecimal payment) {

        User user = new User.Builder()
                .withLogin(userLogin)
                .build();

        Payment paymentEntity = new Payment();
        paymentEntity.setPayment(payment);
        paymentEntity.setUser(user);

        PaymentDAO dao = daoFactory.createPaymentDao();
        dao.create(paymentEntity);

    }

}
