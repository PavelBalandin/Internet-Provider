package org.example.model.service;

import org.example.model.entity.Tariff;
import org.example.model.entity.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    private static UserService userService;

    @BeforeClass
    public static void beforeClass() {
        TestUtility.createTestDataQuery(Queries.CREATE_TABLES);
        userService = new UserService();
    }

    @Test
    public void getUserByLogin() {
        User user = userService.getUserByLogin("Pasha");
        assertEquals("Pasha", user.getLogin());
    }

    @Test
    public void createUser() {
        User userFromDb = userService.createUser("TestLogin", "TestFirstName", "TestLastName", "TestPassword");
        assertEquals("TestLogin", userFromDb.getLogin());
    }

    @Test
    public void updateUser() {
        User userFromDb = userService.updateUser(3, 2);
        System.out.println(userFromDb);
        int id = userFromDb.getStatus().getId();
        assertEquals(2, id);
    }

    @Test
    public void makeOrder() {
        List<Tariff> tariffList = new ArrayList<>();
        tariffList.add(new Tariff.Builder().withId(1).build());
        tariffList.add(new Tariff.Builder().withId(2).build());
        tariffList.add(new Tariff.Builder().withId(3).build());
        BigDecimal bigDecimal = userService.makeOrder("Pasha", tariffList);
        assertEquals(new BigDecimal("2475.00"), bigDecimal);
    }

    @AfterClass
    public static void afterClass() {
        TestUtility.createTestDataQuery(Queries.DELETE_TABLES);
    }
}