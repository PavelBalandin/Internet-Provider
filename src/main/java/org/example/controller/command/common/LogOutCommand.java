package org.example.controller.command.common;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.controller.command.CommandUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {
    private static final Logger logger = Logger.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Command starts");
        String login = (String) request.getSession().getAttribute("login");

        CommandUtility.destroyUserFromContext(request, login);
        CommandUtility.destroyUserFromSession(request);

        logger.trace("User with " + login + " is logged out");

        logger.debug("Commands finished");
        return "redirect:/";
    }
}
