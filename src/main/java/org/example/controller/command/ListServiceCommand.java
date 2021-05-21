package org.example.controller.command;

import org.example.db.ServiceDAO;
import org.example.model.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListServiceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        List<Service> serviceList = new ServiceDAO().getServices();

        request.setAttribute("serviceList", serviceList);
        return "/index.jsp";
    }
}
