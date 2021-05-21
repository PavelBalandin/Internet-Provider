package org.example.model;

public enum Role {
    USER, ADMIN;

    public static Role getRole(User user) {
        int roleId = user.getRoleId() - 1;
        return Role.values()[roleId];
    }
}
