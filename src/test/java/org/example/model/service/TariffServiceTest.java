package org.example.model.service;

import org.example.model.dao.impl.DBCPDataSource;
import org.example.model.entity.Tariff;
import org.example.model.entity.TariffPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class TariffServiceTest {

    private static TariffService tariffService;

    private Connection connection;

    @BeforeClass
    public static void beforeClass() {
        TestUtility.createTestDataQuery(Queries.CREATE_TABLES);
        tariffService = new TariffService();
    }

    @Test
    public void getAllTariffs() {
        List<Tariff> tariffList = tariffService.getAllTariffs();
        assertFalse(tariffList.isEmpty());
    }

    @Test
    public void getTariffsByUserLogin() {
        List<Tariff> tariffList = tariffService.getTariffsByUserLogin("Pasha");
        assertFalse(tariffList.isEmpty());
    }

    @Test
    public void getPaginated() {
        TariffPage tariffPage = tariffService.getPaginated(0, 5);
        System.out.println(tariffPage.getTariffList());
        assertFalse(tariffPage.getTariffList().isEmpty());
        assertEquals(5, tariffPage.getTariffList().size());
        assertFalse(tariffPage.getServiceList().isEmpty());
        assertEquals(0, tariffPage.getPage());
        assertEquals(5, tariffPage.getPageSize());
    }

    @Test
    public void getAllTariffsByServiceId() {
        List<Tariff> tariffList = tariffService.getAllTariffsByServiceId(1, "name", "asc");
        assertFalse(tariffList.isEmpty());
    }

    @Test
    public void createTariff() {
        Tariff tariff = tariffService.createTariff("Test name", "Test description", 30, new BigDecimal(100), 1);
        assertNotNull(tariff);
    }

    @Test
    public void updateTariff() {
        Tariff tariff = tariffService.updateTariff(3, "Updated name", "Updated description", 60, new BigDecimal(999), 2);
        assertEquals(new Integer(3), tariff.getId());
        assertEquals("Updated name", tariff.getName());
        assertEquals("Updated description", tariff.getDescription());
        assertEquals(60, tariff.getDuration());
        assertEquals(new BigDecimal("999.00"), tariff.getPrice());
        assertEquals(new Integer(2), tariff.getService().getId());
    }

    @Test
    public void deleteTariff() {
        tariffService.deleteTariff(3);
        try {
            connection = DBCPDataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tariffs t WHERE t.id = 3");
            assertFalse(rs.next());
            DBCPDataSource.commitAndClose(connection);
        } catch (SQLException e) {
            DBCPDataSource.rollbackAndClose(connection);
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    public static void afterClass() {
        TestUtility.createTestDataQuery(Queries.DELETE_TABLES);
    }
}