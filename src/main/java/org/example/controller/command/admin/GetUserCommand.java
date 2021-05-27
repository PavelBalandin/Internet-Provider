package org.example.controller.command.admin;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.User;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class GetUserCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetUserCommand.class);

    private final UserService userService;

    public GetUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Command starts");

        String login = request.getParameter("login");

        String errorMessage = null;

        if (login == null || login.equals("")) {
            errorMessage = "Enter login";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
            return "/WEB-INF/views/admin/update_user_page.jsp";
        }

        User user = userService.getUserByLogin(login);
        if (user == null) {
            errorMessage = "Incorrect login";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
            return "/WEB-INF/views/admin/update_user_page.jsp";
        }

        request.setAttribute("user", user);
        logger.trace("Get user: " + user);

        logger.debug("Commands finished");
        return "/WEB-INF/views/admin/update_user_page.jsp";

    }
}
