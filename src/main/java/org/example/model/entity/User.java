package org.example.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends BaseEntity {

    private static final long serialVersionUID = 2304156280159969962L;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private List<Payment> payments = new ArrayList<>();

    private Role role;

    private Status status;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(payments, user.payments) &&
                Objects.equals(role, user.role) &&
                Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, firstName, lastName, payments, role, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", payments=" + payments +
                ", role=" + role +
                ", status=" + status +
                '}';
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder withId(int id) {
            user.setId(id);
            return this;
        }

        public Builder withLogin(String login) {
            user.setLogin(login);
            return this;
        }

        public Builder withPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder withFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public Builder withLastName(String firstName) {
            user.setLastName(firstName);
            return this;
        }

        public Builder withRole(Role role) {
            user.setRole(role);
            return this;
        }

        public Builder withStatus(Status status) {
            user.setStatus(status);
            return this;
        }

        public User build() {
            return user;
        }

    }
}
