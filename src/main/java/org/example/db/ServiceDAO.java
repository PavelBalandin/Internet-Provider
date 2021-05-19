package org.example.db;

public class ServiceDAO {
    private static final String SELECT_SERVICES = "SELECT * FROM SERVICES;";

    private static final String SELECT_SERVICE_BY_ID = "SELECT * FROM SERVICES WHERE id = ?;";

    private static final String SELECT_SERVICE_BY_NAME = "SELECT * FROM SERVICES WHERE name = ?;";

    private static final String INSERT_SERVICE = "INSERT INTO SERVICES VALUES(DEFAULT, ?);";

    private static final String UPDATE_SERVICE = "UPDATE SERVICES SET name  = ? WHERE id = ?;";

    private static final String DELETE_SERVICE = "DELETE FROM SERVICES WHERE id = ?;";
}
