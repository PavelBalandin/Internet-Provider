package org.example.model.dao;

import org.example.model.entity.Tariff;
import org.example.model.entity.TariffPage;

import java.util.List;

public interface TariffDAO extends GenericDAO<Tariff> {
    List<Tariff> findAllByServiceId(int id, String sort, String order);

    TariffPage findPaginated(int page, int size);

    List<Tariff> findTariffsByLogin(String login);
}
