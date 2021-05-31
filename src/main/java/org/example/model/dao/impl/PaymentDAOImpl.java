package org.example.model.dao.impl;

import org.example.model.dao.PaymentDAO;
import org.example.model.dao.mapper.PaymentMapper;
import org.example.model.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    private final String SELECT_PAYMENTS_BY_USER_LOGIN = "SELECT * FROM payments p WHERE \n" +
            "(SELECT u.id  FROM users u where u.login = ?) = p.user_id;";

    private final String INSERT_PAYMENT =
            "INSERT INTO payments VALUES(DEFAULT, ?, (SELECT u.id  FROM users u where u.login = ?), DEFAULT);";

    private Connection connection;

    public PaymentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Payment payment) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_PAYMENT)) {
            ps.setBigDecimal(1, payment.getPayment());
            ps.setString(2, payment.getUser().getLogin());
            ps.execute();
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
    }

    @Override
    public Payment findById(int id) {
        return null;
    }

    @Override
    public List<Payment> findAll() {
        return null;
    }

    @Override
    public void update(Payment entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Payment> findPaymentListByUserLogin(String login) {
        List<Payment> paymentList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_PAYMENTS_BY_USER_LOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            PaymentMapper paymentMapper = new PaymentMapper();
            while (rs.next()) {
                Payment payment = paymentMapper.extractFromResultSet(rs);
                paymentList.add(payment);
            }
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return paymentList;
    }

    @Override
    public void close() {

    }
}
