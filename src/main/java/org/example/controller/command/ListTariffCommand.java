package org.example.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ListTariffCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/tariff.jsp";
    }
}
