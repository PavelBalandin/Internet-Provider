package org.example.model.dao.mapper;

import org.example.model.entity.Tariff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TariffMapper implements ObjectMapper<Tariff> {
    @Override
    public Tariff extractFromResultSet(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setId(rs.getInt("id"));
        tariff.setName(rs.getString("name"));
        tariff.setDescription(rs.getString("description"));
        tariff.setDuration(rs.getInt("duration"));
        tariff.setPrice(rs.getBigDecimal("price"));
        return tariff;
    }

    @Override
    public Tariff makeUnique(Map<Integer, Tariff> cache, Tariff tariff) {
        cache.putIfAbsent(tariff.getId(), tariff);
        return cache.get(tariff.getId());
    }
}
