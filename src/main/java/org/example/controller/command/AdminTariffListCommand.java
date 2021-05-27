package org.example.controller.command;

import org.example.model.entity.Service;
import org.example.model.entity.Tariff;
import org.example.model.service.ServiceService;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminTariffListCommand implements Command {
    private final TariffService tariffService;
    private final ServiceService serviceService;

    public AdminTariffListCommand(TariffService tariffService, ServiceService serviceService) {
        this.tariffService = tariffService;
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("page");
        if (page == null) {
            page = "0";
        }

        String size = request.getParameter("size");
        if (size == null) {
            size = "5";
        }

        List<Tariff> tariffs = tariffService.getPaginated(Integer.parseInt(page), Integer.parseInt(size));
        List<Service> services = serviceService.getAllServices();
        request.setAttribute("tariffs", tariffs);
        request.setAttribute("services", services);
        return "/WEB-INF/views/admin/edit_tariff_page.jsp";
    }
}
