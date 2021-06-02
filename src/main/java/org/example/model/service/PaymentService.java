package org.example.model.service;

import org.apache.log4j.Logger;
import org.example.model.dao.DaoFactory;
import org.example.model.dao.PaymentDAO;
import org.example.model.entity.Payment;
import org.example.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class PaymentService {

    private final Logger logger = Logger.getLogger(PaymentService.class);

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Payment> getPaymentListByUser(String login) {
        PaymentDAO dao = daoFactory.createPaymentDao();
        return dao.findPaymentListByUserLogin(login);
    }

    public Payment createPayment(String userLogin, BigDecimal payment) {
        Payment paymentEntity = new Payment();
        paymentEntity.setPayment(payment);
        paymentEntity.setUser(new User.Builder()
                .withLogin(userLogin)
                .build()
        );

        PaymentDAO dao = daoFactory.createPaymentDao();
        Payment paymentFromDb = dao.create(paymentEntity);
        logger.info("Payment has been created: " + paymentFromDb);
        return paymentFromDb;
    }
}
