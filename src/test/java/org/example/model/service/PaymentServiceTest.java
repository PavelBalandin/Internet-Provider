package org.example.model.service;

import org.example.model.entity.Payment;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PaymentServiceTest {
    private static PaymentService paymentService;

    @BeforeClass
    public static void beforeClass() {
        TestUtility.createTestDataQuery(Queries.CREATE_TABLES);
        paymentService = new PaymentService();
    }

    @Test
    public void getPaymentListByUser() {
        List<Payment> paymentList = paymentService.getPaymentListByUser("Pasha");
        assertFalse(paymentList.isEmpty());
    }

    @Test
    public void createPayment() {
        BigDecimal pay = new BigDecimal("250.00");
        String login = "Pasha";
        Payment payment = paymentService.createPayment(login, pay);
        assertEquals(payment.getPayment(), pay);
    }

    @AfterClass
    public static void afterClass() {
        TestUtility.createTestDataQuery(Queries.DELETE_TABLES);
    }
}