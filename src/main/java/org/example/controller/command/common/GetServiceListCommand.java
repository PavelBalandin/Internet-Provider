package org.example.controller.command.common;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.Service;
import org.example.model.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetServiceListCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetServiceListCommand.class);

    private final ServiceService serviceService;

    public GetServiceListCommand(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Commands starts");
        List<Service> serviceList = serviceService.getAllServices();

        request.setAttribute("serviceList", serviceList);
        logger.trace("Service List: " + serviceList);

        logger.debug("Commands finished");
        return "/index.jsp";
    }
}
