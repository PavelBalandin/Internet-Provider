package org.example.controller.command.admin;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.controller.command.CommandUtility;
import org.example.model.entity.User;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public class GetUserCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetUserCommand.class);

    private final UserService userService;

    public GetUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Command starts");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        String login = request.getParameter("login");


        if (login == null || login.equals("")) {
            request.setAttribute("errorMessage", rb.getString("message.empty.fields"));
            logger.error(rb.getString("message.empty.fields"));
            return "/WEB-INF/views/admin/update_user_page.jsp";
        }

        User user = userService.getUserByLogin(login);
        if (user == null) {
            request.setAttribute("errorMessage", rb.getString("message.incorrect.login"));
            logger.error(rb.getString("message.incorrect.login"));
            return "/WEB-INF/views/admin/update_user_page.jsp";
        }

        request.setAttribute("user", user);
        logger.trace("Get user: " + user);

        logger.debug("Commands finished");
        return "/WEB-INF/views/admin/update_user_page.jsp";

    }
}
