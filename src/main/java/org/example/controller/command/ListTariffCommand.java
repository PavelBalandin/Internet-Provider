package org.example.controller.command;

import org.example.model.entity.Tariff;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListTariffCommand implements Command {

    private final TariffService tariffService;

    public ListTariffCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        String params = request.getParameter("sort");
        String sort = null;
        String order = null;
        if (params != null) {
            sort = params.replaceAll("\\s.*", "");
            order = params.replaceAll(".*\\s", "");
            request.getSession().setAttribute("sort", params);
        }

        List<Tariff> tariffList = tariffService.getAllTariffsByServiceId(Integer.parseInt(id), sort, order);
        request.setAttribute("tariffList", tariffList);
        request.setAttribute("service_id", id);
        return "/tariff.jsp";
    }
}
