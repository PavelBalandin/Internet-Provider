package org.example.controller.command.common;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.controller.command.CommandUtility;
import org.example.model.entity.User;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    private static final String ERROR_TAG = "Error tag: ";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Command starts");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        logger.trace("User with login " + login + " is logging");

        if (login == null || login.equals("") || password == null || password.equals("")) {
            request.setAttribute(ERROR_MESSAGE, rb.getString("fill.fields"));
            logger.trace(ERROR_TAG + rb.getString("fill.fields"));
            return LOGIN_PAGE;
        }

        User user = userService.getUserByLogin(login);
        if (user == null || !user.getPassword().equals(CommandUtility.hash(password))) {
            request.setAttribute(ERROR_MESSAGE, rb.getString("incorrect.login.or.password"));
            logger.trace(ERROR_TAG + rb.getString("incorrect.login.or.password"));
            return LOGIN_PAGE;
        }

        if (CommandUtility.checkUserIsLogged(request, login)) {
            request.setAttribute(ERROR_MESSAGE, rb.getString("unfinished.session"));
            logger.trace(ERROR_TAG + rb.getString("unfinished.session"));
            return LOGIN_PAGE;
        }

        CommandUtility.setUserRole(request, login, user.getRole().getName());
        logger.trace("User with " + login + " is logged in");

        logger.debug("Commands finished");
        return "redirect:/";
    }
}
