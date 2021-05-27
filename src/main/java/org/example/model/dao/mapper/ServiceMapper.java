package org.example.model.dao.mapper;

import org.example.model.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ServiceMapper implements ObjectMapper<Service> {
    @Override
    public Service extractFromResultSet(ResultSet rs) throws SQLException {
        Service service = new Service();
        service.setId(rs.getInt("serviceid"));
        service.setName(rs.getString("servicename"));
        return service;
    }

    @Override
    public Service makeUnique(Map<Integer, Service> cache, Service service) {
        cache.putIfAbsent(service.getId(), service);
        return cache.get(service.getId());
    }
}
