package org.example.controller;

import org.example.controller.command.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init() throws ServletException {
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("service", new ListServiceCommand());
        commands.put("tariff", new ListTariffCommand());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Controller starts");

        String path = req.getRequestURI().replaceAll(".*/InternetProvider/", "");
        System.out.println("Path: " + path);

        Command command = commands.getOrDefault(path, (r) -> "/index.jsp");

        String page = command.execute(req);
        System.out.println("Page: " + page);

        req.getRequestDispatcher(page).forward(req, resp);
    }
}
