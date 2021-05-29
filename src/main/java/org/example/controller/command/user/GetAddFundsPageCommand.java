package org.example.controller.command.user;

import org.example.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetAddFundsPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/views/user/add_funds_page.jsp";
    }
}
