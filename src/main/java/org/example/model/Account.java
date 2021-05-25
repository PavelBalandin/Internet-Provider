package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account extends BaseEntity {

    private static final long serialVersionUID = -3033716424295738795L;

    private BigDecimal payment;

    private int userId;

    public Account(Long id, BigDecimal payment, int userId) {
        super(id);
        this.payment = payment;
        this.userId = userId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return userId == account.userId &&
                Objects.equals(payment, account.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment, userId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "payment=" + payment +
                ", userId=" + userId +
                '}';
    }
}
