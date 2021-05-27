package org.example.controller.command;

import org.example.model.entity.Service;
import org.example.model.entity.Tariff;
import org.example.model.service.ServiceService;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public class UpdateTariffCommand implements Command {
    private final TariffService tariffService;
    private final ServiceService serviceService;

    public UpdateTariffCommand(TariffService tariffService, ServiceService serviceService) {
        this.tariffService = tariffService;
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request) {
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
            errorMessage = "Please fill all fields correctly";
            request.setAttribute("errorMessage", errorMessage);
            List<Tariff> tariffs = tariffService.getAllTariffs();
            List<Service> services = serviceService.getAllServices();
            request.setAttribute("tariffs", tariffs);
            request.setAttribute("services", services);
            return "/WEB-INF/views/admin/edit_tariff_page.jsp";
        }

        tariffService.updateTariff(Integer.parseInt(id), name, description, duration, new BigDecimal(price), Integer.parseInt(serviceId));

        List<Tariff> tariffs = tariffService.getAllTariffs();
        List<Service> services = serviceService.getAllServices();
        request.setAttribute("tariffs", tariffs);
        request.setAttribute("services", services);
        return "/WEB-INF/views/admin/edit_tariff_page.jsp";
    }
}
