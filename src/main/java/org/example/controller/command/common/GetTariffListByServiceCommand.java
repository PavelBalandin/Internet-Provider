package org.example.controller.command.common;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.Tariff;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetTariffListByServiceCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetTariffListByServiceCommand.class);

    private final TariffService tariffService;

    public GetTariffListByServiceCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Commands starts");

        String id = request.getParameter("id");
        String params = request.getParameter("sort");
        String sort = null;
        String order = null;
        if (params != null) {
            sort = params.replaceAll("\\s.*", "");
            order = params.replaceAll(".*\\s", "");
            request.getSession().setAttribute("sort", params);
        }
        if (sort == null) {
            sort = "name";
        }
        if (order == null) {
            order = "asc";
        }

        List<Tariff> tariffList = tariffService.getAllTariffsByServiceId(Integer.parseInt(id), sort, order);
        logger.trace("Tariff List: " + tariffList);

        request.setAttribute("tariffList", tariffList);
        request.setAttribute("serviceId", id);

        logger.debug("Commands finished");
        return "/tariff.jsp";
    }
}
