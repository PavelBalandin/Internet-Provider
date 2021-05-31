package org.example.model.service;

import org.example.model.dao.DaoFactory;
import org.example.model.dao.ServiceDAO;
import org.example.model.entity.Service;

import java.util.List;

public class ServiceService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Service> getAllServices() {
        ServiceDAO dao = daoFactory.createServiceDao();
        return dao.findAll();

    }

}
