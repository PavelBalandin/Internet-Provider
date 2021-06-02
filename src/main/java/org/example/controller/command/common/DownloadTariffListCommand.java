package org.example.controller.command.common;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.Service;
import org.example.model.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DownloadTariffListCommand implements Command {

    private static final Logger logger = Logger.getLogger(DownloadTariffListCommand.class);

    private final ServiceService serviceService;

    public DownloadTariffListCommand(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Commands starts");
        List<Service> serviceList = serviceService.getAllServices();

        request.setAttribute("serviceList", serviceList);
        logger.trace("Service List: " + serviceList);

        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=Tariffs.txt");

        try {
            PrintWriter out = response.getWriter();
            serviceList.forEach(service -> {
                out.write(service.getName() + System.lineSeparator());
                service.getTariffs().forEach(tariff -> out.write(tariff.getName() + "\t" +
                        tariff.getDescription() + "\t" +
                        tariff.getPrice() + " грн." + "\t" +
                        tariff.getDuration() + " днів" + System.lineSeparator()));
            });
            out.close();
        } catch (IOException ex) {
            logger.error(ex);
            logger.trace("File hasn't been downloaded");
        }
        logger.trace("File has been downloaded");

        logger.debug("Commands finished");
        return "/index.jsp";
    }
}
