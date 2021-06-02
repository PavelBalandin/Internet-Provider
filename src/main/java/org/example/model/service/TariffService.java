package org.example.model.service;

import org.apache.log4j.Logger;
import org.example.model.dao.DaoFactory;
import org.example.model.dao.TariffDAO;
import org.example.model.entity.Service;
import org.example.model.entity.Tariff;
import org.example.model.entity.TariffPage;

import java.math.BigDecimal;
import java.util.List;

public class TariffService {

    private static final Logger logger = Logger.getLogger(TariffService.class);

    DaoFactory daoFactory = DaoFactory.getInstance();

    public Tariff getTariffById(int id) {
        TariffDAO dao = daoFactory.createTariffDao();
        return dao.findById(id);
    }

    public List<Tariff> getAllTariffs() {
        TariffDAO dao = daoFactory.createTariffDao();
        return dao.findAll();
    }

    public List<Tariff> getTariffsByUserLogin(String login) {
        TariffDAO dao = daoFactory.createTariffDao();
        return dao.findTariffsByLogin(login);
    }

    public TariffPage getPaginated(int page, int size) {
        TariffDAO dao = daoFactory.createTariffDao();
        return dao.findPaginated(page, size);
    }

    public List<Tariff> getAllTariffsByServiceId(int id, String sort, String order) {
        TariffDAO dao = daoFactory.createTariffDao();
        return dao.findAllByServiceId(id, sort, order);
    }

    public Tariff createTariff(String name, String description, String duration, BigDecimal price, int serviceId) {
        Tariff tariff = new Tariff.Builder()
                .withName(name)
                .withDescription(description)
                .withDuration(duration)
                .withPrice(price)
                .withService(new Service.Builder()
                        .withId(serviceId)
                        .build())
                .build();

        TariffDAO dao = daoFactory.createTariffDao();
        Tariff tariffFromDb = dao.create(tariff);
        logger.info("Tariff has been created:" + tariffFromDb);
        return tariffFromDb;
    }

    public Tariff updateTariff(int id, String name, String description, String duration, BigDecimal price, int serviceId) {
        Tariff tariff = new Tariff.Builder()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withDuration(duration)
                .withPrice(price)
                .withService(new Service.Builder()
                        .withId(serviceId)
                        .build())
                .build();

        TariffDAO dao = daoFactory.createTariffDao();
        Tariff tariffFromDb = dao.update(tariff);
        logger.info("Tariff has been updated:" + tariffFromDb);
        return tariffFromDb;
    }

    public void deleteTariff(int id) {
        TariffDAO dao = daoFactory.createTariffDao();
        dao.delete(id);
    }
}
