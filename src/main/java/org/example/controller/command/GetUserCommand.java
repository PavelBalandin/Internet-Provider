package org.example.controller.command;

import org.example.model.entity.User;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class GetUserCommand implements Command {

    private final UserService userService;

    public GetUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");

        String errorMessage = null;

        if (login == null || login.equals("")) {
            errorMessage = "Enter login";
            request.setAttribute("errorMessage", errorMessage);
            return "/WEB-INF/views/admin/update_user_page.jsp";
        }

        User user = userService.getUserByLogin(login);
        if (user == null) {
            errorMessage = "Incorrect login";
            request.setAttribute("errorMessage", errorMessage);
            return "/WEB-INF/views/admin/update_user_page.jsp";
        }

        System.out.println(user);
        request.setAttribute("user", user);
        return "/WEB-INF/views/admin/update_user_page.jsp";

    }
}
