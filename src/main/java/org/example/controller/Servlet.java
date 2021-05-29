package org.example.controller;

import org.apache.log4j.Logger;
import org.example.controller.command.*;
import org.example.controller.command.admin.*;
import org.example.controller.command.common.GetServiceListCommand;
import org.example.controller.command.common.GetTariffListByServiceCommand;
import org.example.controller.command.common.LogOutCommand;
import org.example.controller.command.common.LoginCommand;
import org.example.controller.command.user.*;
import org.example.model.service.PaymentService;
import org.example.model.service.ServiceService;
import org.example.model.service.TariffService;
import org.example.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();

    private static final String URL_PATTERN = ".*/InternetProvider/";

    private static final Logger logger = Logger.getLogger(Servlet.class);


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        logger.trace("URI: " + uri);

        String commandName = uri.replaceAll(URL_PATTERN, "");
        logger.trace("Command name: " + commandName);

        Command command = commands.getOrDefault(commandName, r -> "/index.jsp");
        logger.trace("Command: " + command.getClass().getSimpleName());

        String page = command.execute(req);
        if (page.contains("redirect:")) {
            page = page.replace("redirect:", "");
            logger.trace("Redirect: " + page);
            resp.sendRedirect(page);
        } else {
            logger.trace("Forward: " + page);
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        commands.put("login", new LoginCommand(new UserService()));
        commands.put("logout", new LogOutCommand());

        commands.put("/", new GetServiceListCommand(new ServiceService()));
        commands.put("getServiceList", new GetServiceListCommand(new ServiceService()));
        commands.put("getTariffListByService", new GetTariffListByServiceCommand(new TariffService()));

        commands.put("getUser", new GetUserCommand(new UserService()));
        commands.put("createUser", new CreateUserCommand(new UserService()));
        commands.put("updateUser", new UpdateUserCommand(new UserService()));

        commands.put("getTariffList", new GetTariffListCommand(new TariffService(), new ServiceService()));
        commands.put("createTariff", new CreateTariffCommand(new TariffService(), new ServiceService()));
        commands.put("updateTariff", new UpdateTariffCommand(new TariffService(), new ServiceService()));
        commands.put("deleteTariff", new DeleteTariffCommand(new TariffService(), new ServiceService()));

        commands.put("getUserOrderPage", new GetUserOrderPageCommand());
        commands.put("getAddFundsPage", new GetAddFundsPageCommand());
        commands.put("getUserPaymentListPage", new GetUserPaymentListPageCommand(new PaymentService()));
        commands.put("addTariffToOrder", new AddTariffToOrderCommand(new TariffService()));
        commands.put("deleteTariffFromOrder", new DeleteTariffFromOrderCommand(new TariffService()));
        commands.put("makeOrder", new MakeOrderCommand(new UserService(), new TariffService()));
        commands.put("createPayment", new CreatePaymentCommand(new PaymentService()));
        commands.put("getUserTariffListPage", new GetUserTariffListPageCommand(new TariffService()));
    }
}
