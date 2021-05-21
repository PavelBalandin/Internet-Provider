package org.example.controller.command;

import org.apache.log4j.Logger;
import org.example.db.UserDAO;
import org.example.model.Role;
import org.example.model.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    private static final String ERROR_MESSAGE = "Error message: ";

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        logger.trace("User with login " + login + " is logging");

        String errorMessage = null;

        if (login == null || login.equals("") || password == null || password.equals("")) {
            errorMessage = "Please enter your login and password";
            request.setAttribute("errorMessage", errorMessage);
            logger.trace(ERROR_MESSAGE + errorMessage);
            return "/login.jsp";
        }

        User user = new UserDAO().findUserByLogin(login);
        if (user == null || !user.getPassword().equals(password)) {
            errorMessage = "Incorrect username or password";
            request.setAttribute("errorMessage", errorMessage);
            logger.trace(ERROR_MESSAGE + errorMessage);
            return "/login.jsp";
        }

        if (CommandUtility.checkUserIsLogged(request, login)) {
            errorMessage = "You must finish session on another device";
            request.setAttribute("errorMessage", errorMessage);
            logger.trace(ERROR_MESSAGE + errorMessage);
            return "/login.jsp";
        }

        CommandUtility.setUserRole(request, login, Role.getRole(user).toString());
        logger.trace("User with " + login + " is logged in");

        return "/index.jsp";
    }
}
