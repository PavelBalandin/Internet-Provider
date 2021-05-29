package org.example.controller.command.user;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.Tariff;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteTariffFromOrderCommand implements Command {

    private static final Logger logger = Logger.getLogger(DeleteTariffFromOrderCommand.class);

    private final TariffService tariffService;

    public DeleteTariffFromOrderCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Commands starts");

        String errorMessage = null;

        String id = request.getParameter("id");

        if (id == null || id.equals("")) {
            errorMessage = "Required parameters are missing";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
            return "/WEB-INF/views/user/user_page.jsp";
        }

        List<Tariff> orderTariffList = (List<Tariff>) request.getSession().getAttribute("orderTariffList");

        if (orderTariffList == null || orderTariffList.isEmpty()) {
            errorMessage = "Order list is empty";
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

        if (orderTariffList.remove(tariff)) {
            request.setAttribute("successMessage", "Tariff has been deleted successfully");
            logger.trace("Tariff has been deleted successfully");
        } else {
            errorMessage = "There is no such tariff";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
        }


        logger.debug("Commands finished");
        return "/WEB-INF/views/user/user_page.jsp";
    }
}
