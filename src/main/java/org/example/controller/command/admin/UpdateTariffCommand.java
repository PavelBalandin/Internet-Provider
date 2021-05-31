package org.example.controller.command.admin;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.Service;
import org.example.model.entity.Tariff;
import org.example.model.entity.TariffPage;
import org.example.model.service.ServiceService;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public class UpdateTariffCommand implements Command {

    private static final Logger logger = Logger.getLogger(UpdateTariffCommand.class);

    private final TariffService tariffService;
    private final ServiceService serviceService;

    public UpdateTariffCommand(TariffService tariffService, ServiceService serviceService) {
        this.tariffService = tariffService;
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Command starts");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String duration = request.getParameter("duration");
        String serviceId = request.getParameter("serviceId");

        String errorMessage = null;

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

            errorMessage = "Please fill all fields correctly";
            logger.error(errorMessage);

            request.setAttribute("errorMessage", errorMessage);
            TariffPage tariffPage = tariffService.getPaginated(Integer.parseInt(page), Integer.parseInt(size));
            request.setAttribute("tariffPage", tariffPage);
            return "/WEB-INF/views/admin/edit_tariff_page.jsp";
        }

        try {
            tariffService.updateTariff(Integer.parseInt(id), name, description, duration, new BigDecimal(price), Integer.parseInt(serviceId));
            request.setAttribute("successMessage", "Tariff has been updated successfully");
            logger.trace("Tariff has been updated");
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
            errorMessage = "Tariff hasn't been changed";
            request.setAttribute("errorMessage", errorMessage);
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
