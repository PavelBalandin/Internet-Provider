package org.example.model.service;

import org.example.model.entity.Service;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class ServiceServiceTest {

    private static ServiceService serviceService;

    @BeforeClass
    public static void beforeClass() {
        TestUtility.createTestDataQuery(Queries.CREATE_TABLES);
        serviceService = new ServiceService();
    }

    @Test
    public void getAllServices() {
        List<Service> serviceList = serviceService.getAllServices();
        assertFalse(serviceList.isEmpty());
    }

    @AfterClass
    public static void afterClass() {
        TestUtility.createTestDataQuery(Queries.DELETE_TABLES);
    }
}