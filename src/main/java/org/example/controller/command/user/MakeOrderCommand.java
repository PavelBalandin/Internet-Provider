package org.example.controller.command.user;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.controller.command.CommandUtility;
import org.example.model.entity.Tariff;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MakeOrderCommand implements Command {

    private static final Logger logger = Logger.getLogger(MakeOrderCommand.class);

    private UserService userService;

    public MakeOrderCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Commands starts");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        List<Tariff> orderTariffList = (List<Tariff>) request.getSession().getAttribute("orderTariffList");

        if (orderTariffList == null || orderTariffList.isEmpty()) {
            request.setAttribute("errorMessage", rb.getString("message.order.empty"));
            logger.error(rb.getString("message.order.empty"));
            return "/WEB-INF/views/user/user_page.jsp";
        }

        String userLogin = (String) request.getSession().getAttribute("login");

        try {
            BigDecimal remainder = userService.makeOrder(userLogin, orderTariffList);
            if (remainder.compareTo(BigDecimal.ZERO) >= 0) {
                request.setAttribute("successMessage", rb.getString("message.order.completed"));
                orderTariffList = new ArrayList<>();
                request.getSession().setAttribute("orderTariffList", orderTariffList);
                logger.trace(rb.getString("message.order.completed"));
            } else {
                request.setAttribute("errorMessage", rb.getString("message.order.enough.money"));
                logger.error(rb.getString("message.order.enough.money"));
            }

        } catch (RuntimeException ex) {
            request.setAttribute("errorMessage", rb.getString("message.error"));
            logger.error(rb.getString("message.error"));
            logger.error(ex);
            return "/WEB-INF/views/user/user_page.jsp";
        }

        logger.debug("Commands finished");
        return "/WEB-INF/views/user/user_page.jsp";
    }
}
