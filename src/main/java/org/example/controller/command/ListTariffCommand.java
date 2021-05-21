package org.example.controller.command;

import org.example.db.TariffDAO;
import org.example.model.Tariff;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListTariffCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        String sort = request.getParameter("sort");
        String sortBy = null;
        String sortHow = null;
        if (sort != null) {
            sortBy = sort.replaceAll("\\s.*", "");
            sortHow = sort.replaceAll(".*\\s", "");
            request.getSession().setAttribute("sort", sort);
        }
        List<Tariff> tariffList = new TariffDAO().getTariffsByServiceId(Integer.parseInt(id), sortBy, sortHow);
        request.setAttribute("tariffList", tariffList);
        request.setAttribute("service_id", id);
        return "/tariff.jsp";
    }
}
