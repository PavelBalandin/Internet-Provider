package org.example.db;

import org.example.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    private static final String SELECT_SERVICES = "SELECT * FROM SERVICES;";

    private static final String SELECT_SERVICE_BY_ID = "SELECT * FROM SERVICES WHERE id = ?;";

    private static final String SELECT_SERVICE_BY_NAME = "SELECT * FROM SERVICES WHERE name = ?;";

    private static final String INSERT_SERVICE = "INSERT INTO SERVICES VALUES(DEFAULT, ?);";

    private static final String UPDATE_SERVICE = "UPDATE SERVICES SET name  = ? WHERE id = ?;";

    private static final String DELETE_SERVICE = "DELETE FROM SERVICES WHERE id = ?;";

    public List<Service> gerServices() {
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(new Service((long) 1, "Internet"));
        serviceList.add(new Service((long) 2, "Phone"));
        serviceList.add(new Service((long) 3, "IP-TV"));
        serviceList.add(new Service((long) 4, "TV"));
        serviceList.add(new Service((long) 5, "X box"));
        return serviceList;
    }
}
