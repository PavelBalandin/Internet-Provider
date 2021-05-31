package org.example.controller.command.common;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

public class ChangeLocaleCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Commands starts");
        String lang = request.getParameter("lang");
        request.getSession().setAttribute("sessionLocale", lang);
        Config.set(request.getSession(), "javax.servlet.jsp.jstl.fmt.locale", lang);
        logger.trace("Set locale " + lang + " for " + request.getSession().getAttribute("login"));
        logger.debug("Commands finished");
        return "redirect:/";
    }
}
