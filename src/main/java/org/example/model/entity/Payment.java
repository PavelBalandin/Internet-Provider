package org.example.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Payment extends BaseEntity {

    private static final long serialVersionUID = -1994823525117724177L;

    private BigDecimal payment;

    private User user;


    private LocalDateTime dateTime;

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment1 = (Payment) o;
        return Objects.equals(payment, payment1.payment) &&
                Objects.equals(user, payment1.user) &&
                Objects.equals(dateTime, payment1.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment, user, dateTime);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment=" + payment +
                ", user=" + user +
                ", dateTime=" + dateTime +
                '}';
    }
}
