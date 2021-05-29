package org.example.model.dao;

import org.example.model.entity.Tariff;

import java.util.List;

public interface TariffDao extends GenericDao<Tariff> {
    List<Tariff> findAllByServiceId(int id, String sort, String order);

    List<Tariff> findPaginated(int page, int size);

    List<Tariff> findTariffsByLogin(String login);
}
