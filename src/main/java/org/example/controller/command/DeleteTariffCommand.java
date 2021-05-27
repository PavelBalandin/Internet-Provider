package org.example.controller.command;

import org.example.model.entity.Service;
import org.example.model.entity.Tariff;
import org.example.model.service.ServiceService;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteTariffCommand implements Command {

    private final TariffService tariffService;
    private final ServiceService serviceService;

    public DeleteTariffCommand(TariffService tariffService, ServiceService serviceService) {
        this.tariffService = tariffService;
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        System.out.println(id);
        tariffService.deleteTariff(Integer.parseInt(id));

        List<Tariff> tariffs = tariffService.getAllTariffs();
        List<Service> services = serviceService.getAllServices();
        request.setAttribute("tariffs", tariffs);
        request.setAttribute("services", services);
        return "/WEB-INF/views/admin/edit_tariff_page.jsp";
    }
}
