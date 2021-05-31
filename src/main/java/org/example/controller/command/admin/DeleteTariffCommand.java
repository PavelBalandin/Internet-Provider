package org.example.controller.command.admin;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.Service;
import org.example.model.entity.Tariff;
import org.example.model.entity.TariffPage;
import org.example.model.service.ServiceService;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteTariffCommand implements Command {

    private static final Logger logger = Logger.getLogger(DeleteTariffCommand.class);

    private final TariffService tariffService;
    private final ServiceService serviceService;

    public DeleteTariffCommand(TariffService tariffService, ServiceService serviceService) {
        this.tariffService = tariffService;
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Command starts");

        String errorMessage = null;

        String id = request.getParameter("id");

        try {
            tariffService.deleteTariff(Integer.parseInt(id));
            request.setAttribute("successMessage", "Tariff has been deleted successfully");
            logger.trace("Tariff has been deleted");
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
            errorMessage = "Tariff hasn't been deleted";
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
