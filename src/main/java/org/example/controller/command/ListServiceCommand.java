package org.example.controller.command;

import org.example.model.entity.Service;
import org.example.model.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListServiceCommand implements Command {

    private final ServiceService serviceService;

    public ListServiceCommand(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        List<Service> serviceList = serviceService.getAllServices();

        request.setAttribute("serviceList", serviceList);
        return "/index.jsp";
    }
}
