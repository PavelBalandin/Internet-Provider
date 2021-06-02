package org.example.controller.command.admin;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.controller.command.CommandUtility;
import org.example.model.entity.TariffPage;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ResourceBundle;

public class UpdateTariffCommand implements Command {

    private static final Logger logger = Logger.getLogger(UpdateTariffCommand.class);

    private final TariffService tariffService;

    public UpdateTariffCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Command starts");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String duration = request.getParameter("duration");
        String serviceId = request.getParameter("serviceId");

        if (id == null || id.equals("")
                || name == null || name.equals("")
                || description == null || description.equals("")
                || price == null || price.equals("") || !price.matches("[0-9]+(.[0-9]{1,2})?")
                || duration == null || duration.equals("") || !duration.matches("[0-9]+")
                || serviceId == null || serviceId.equals("")
        ) {
            String page = request.getParameter("page");
            if (page == null) {
                page = "0";
            }

            String size = request.getParameter("size");
            if (size == null) {
                size = "5";
            }

            request.setAttribute("errorMessage", rb.getString("message.empty.fields"));
            logger.trace(rb.getString("message.empty.fields"));
            TariffPage tariffPage = tariffService.getPaginated(Integer.parseInt(page), Integer.parseInt(size));
            request.setAttribute("tariffPage", tariffPage);
            return "/WEB-INF/views/admin/edit_tariff_page.jsp";
        }

        try {
            tariffService.updateTariff(Integer.parseInt(id), name, description, duration, new BigDecimal(price), Integer.parseInt(serviceId));
            request.setAttribute("successMessage", rb.getString("message.tariff.updated"));
            logger.trace("Tariff has been updated");
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
            request.setAttribute("errorMessage", rb.getString("message.error"));
        }


        String page = request.getParameter("page");
        if (page == null) {
            page = "0";
        }

        String size = request.getParameter("size");
        if (size == null) {
            size = "5";
        }

        TariffPage tariffPage = tariffService.getPaginated(Integer.parseInt(page), Integer.parseInt(size));
        request.setAttribute("tariffPage", tariffPage);

        logger.debug("Commands finished");
        return "/WEB-INF/views/admin/edit_tariff_page.jsp";
    }
}
