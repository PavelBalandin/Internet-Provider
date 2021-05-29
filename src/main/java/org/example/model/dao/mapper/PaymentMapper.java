package org.example.model.dao.mapper;

import org.example.model.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

public class PaymentMapper implements ObjectMapper<Payment> {
    @Override
    public Payment extractFromResultSet(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getInt("id"));
        payment.setPayment(rs.getBigDecimal("payment"));
        payment.setDateTime(rs.getObject("payment_date", LocalDateTime.class));
        return payment;
    }

    @Override
    public Payment makeUnique(Map<Integer, Payment> cache, Payment payment) {
        cache.putIfAbsent(payment.getId(), payment);
        return cache.get(payment.getId());
    }
}
