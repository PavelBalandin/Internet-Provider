package org.example.model.dao.mapper;

import org.example.model.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class StatusMapper implements ObjectMapper<Status> {
    @Override
    public Status extractFromResultSet(ResultSet rs) throws SQLException {
        Status status = new Status();
        status.setId(rs.getInt("status_id"));
        status.setName(rs.getString("status_name"));
        return status;
    }

    @Override
    public Status makeUnique(Map<Integer, Status> cache, Status status) {
        cache.putIfAbsent(status.getId(), status);
        return cache.get(status.getId());
    }
}
