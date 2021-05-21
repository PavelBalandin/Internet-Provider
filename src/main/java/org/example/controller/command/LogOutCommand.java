package org.example.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute("login");
        CommandUtility.destroyUserFromContext(request, login);
        CommandUtility.destroyUserFromSession(request);
        logger.trace("User with " + login + " is logged out");
        return "/index.jsp";
    }
}
