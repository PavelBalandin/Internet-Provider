package org.example.controller.command.user;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.Tariff;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MakeOrderCommand implements Command {

    private static final Logger logger = Logger.getLogger(MakeOrderCommand.class);

    private UserService userService;

    public MakeOrderCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Commands starts");

        String errorMessage = null;

        List<Tariff> orderTariffList = (List<Tariff>) request.getSession().getAttribute("orderTariffList");

        if (orderTariffList == null || orderTariffList.isEmpty()) {
            errorMessage = "Order list is empty";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
            return "/WEB-INF/views/user/user_page.jsp";
        }

        String userLogin = (String) request.getSession().getAttribute("login");

        try {
            BigDecimal remainder = userService.makeOrder(userLogin, orderTariffList);
            if (remainder.compareTo(BigDecimal.ZERO) >= 0) {
                request.setAttribute("successMessage", "Order has been successfully completed");
                orderTariffList = new ArrayList<>();
                request.getSession().setAttribute("orderTariffList", orderTariffList);
                logger.trace("Order has been successfully completed");
            } else {
                errorMessage = "You don't have enough money";
                request.setAttribute("errorMessage", errorMessage);
                logger.error(errorMessage);
            }

        } catch (RuntimeException ex) {
            errorMessage = "Order failed";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
            logger.error(ex);
            return "/WEB-INF/views/user/user_page.jsp";

        }


        logger.debug("Commands finished");
        return "/WEB-INF/views/user/user_page.jsp";
    }
}
