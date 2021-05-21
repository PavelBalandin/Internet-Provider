package org.example.controller;

import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(Controller.class);

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
        String uri = req.getRequestURI();
        logger.trace("URI: " + uri);

        String commandName = uri.replaceAll(".*/InternetProvider/", "");
        logger.trace("Command name: " + commandName);

        Command command = commands.getOrDefault(commandName, r -> "/index.jsp");
        logger.trace("Command: " + command.getClass().getSimpleName());

        String page = command.execute(req);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
