package org.example.controller.command.user;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.controller.command.CommandUtility;
import org.example.model.entity.Tariff;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteTariffFromOrderCommand implements Command {

    private static final Logger logger = Logger.getLogger(DeleteTariffFromOrderCommand.class);

    private final TariffService tariffService;

    public DeleteTariffFromOrderCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Commands starts");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        String id = request.getParameter("id");

        if (id == null || id.equals("")) {
            request.setAttribute("errorMessage", rb.getString("message.error"));
            logger.error(rb.getString("message.error"));
            return "/WEB-INF/views/user/user_page.jsp";
        }

        List<Tariff> orderTariffList = (List<Tariff>) request.getSession().getAttribute("orderTariffList");

        if (orderTariffList == null || orderTariffList.isEmpty()) {
            request.setAttribute("errorMessage", rb.getString("message.order.empty"));
            logger.error(rb.getString("message.order.empty"));
            return "/WEB-INF/views/user/user_page.jsp";
        }

        Tariff tariff = tariffService.getTariffById(Integer.parseInt(id));

        if (tariff == null) {
            request.setAttribute("errorMessage", rb.getString("message.error"));
            logger.error(rb.getString("message.error"));
            return "/WEB-INF/views/user/user_page.jsp";
        }
        logger.trace("Tariff:" + tariff);

        if (orderTariffList.remove(tariff)) {
            request.setAttribute("successMessage", rb.getString("message.tariff.deleted"));
            logger.trace(rb.getString("message.tariff.deleted"));
        } else {
            request.setAttribute("errorMessage", rb.getString("message.error"));
            logger.error(rb.getString("message.error"));
        }


        logger.debug("Commands finished");
        return "/WEB-INF/views/user/user_page.jsp";
    }
}
