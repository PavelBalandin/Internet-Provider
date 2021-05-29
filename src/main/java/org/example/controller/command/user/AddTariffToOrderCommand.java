package org.example.controller.command.user;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.Tariff;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddTariffToOrderCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddTariffToOrderCommand.class);

    private final TariffService tariffService;

    public AddTariffToOrderCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Commands starts");

        String errorMessage = null;

        List<Tariff> orderTariffList = (List<Tariff>) request.getSession().getAttribute("orderTariffList");


        if (orderTariffList == null) {
            orderTariffList = new ArrayList<>();
        }

        String id = request.getParameter("id");

        if (id == null || id.equals("")) {
            errorMessage = "Required parameters are missing";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
            return "/WEB-INF/views/user/user_page.jsp";
        }

        Tariff tariff = tariffService.getTariffById(Integer.parseInt(id));

        if (tariff == null) {
            errorMessage = "There is no such tariff";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
            return "/WEB-INF/views/user/user_page.jsp";
        }
        logger.trace("Tariff:" + tariff);

        if (orderTariffList.contains(tariff)) {
            errorMessage = "This tariff is already on the list";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
            return "/WEB-INF/views/user/user_page.jsp";
        }

        orderTariffList.add(tariff);
        request.getSession().setAttribute("orderTariffList", orderTariffList);
        request.setAttribute("successMessage", "Tariff has been added successfully");

        logger.trace("Order tariff list:" + orderTariffList);

        logger.debug("Commands finished");
        return "/WEB-INF/views/user/user_page.jsp";
    }
}
