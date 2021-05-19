package org.example.db;

public class TariffDAO {
    private static final String SELECT_TARIFFS = "SELECT * FROM TARIFFS;";

    private static final String SELECT_SERVICE_BY_ID = "SELECT * FROM TARIFFS WHERE id = ?;";

    private static final String INSERT_SERVICE = "INSERT INTO tariffs VALUES (DEFAULT, ?, ?, ?, ?, ?);";

    private static final String UPDATE_SERVICE = "UPDATE TARIFFS SET name  = ?, description = ?, duration = ?, prise = ?, service_id = ?  WHERE id = ?;";

    private static final String DELETE_SERVICE = "DELETE FROM TARIFFS WHERE id = ?;";
}
