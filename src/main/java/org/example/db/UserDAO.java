package org.example.db;

public class UserDAO {
    private static final String SELECT_USERS = "SELECT * FROM USERS;";

    private static final String SELECT_USER_BY_ID = "SELECT * FROM USERS WHERE id = ?;";

    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM USERS WHERE login = ?;";

    private static final String INSERT_USER = "INSERT INTO USERS VALUES(DEFAULT, ?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_USER = "UPDATE USERS SET login  = ?, password = ?, firstname = ?, lastname = ?, role_id = ?, status_id = ? WHERE id = ?;";

    private static final String DELETE_USER = "DELETE FROM USERS WHERE id = ?;";

    private static final String DELETE_USER_BY_LOGIN = "DELETE FROM USERS WHERE login = ?;";
}
