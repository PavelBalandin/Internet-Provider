package org.example.controller.command.admin;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class CreateUserCommand implements Command {
    private static final Logger logger = Logger.getLogger(CreateUserCommand.class);

    private static final String ERROR_MESSAGE = "Error message: ";

    private final UserService userService;

    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Command starts");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        String errorMessage = null;

        if (login == null || login.equals("")
                || password == null || password.equals("")
                || firstname == null || firstname.equals("")
                || lastname == null || lastname.equals("")
        ) {
            errorMessage = "Please fill all fields";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(ERROR_MESSAGE + errorMessage);
            return "/WEB-INF/views/admin/add_user_page.jsp";
        }
        try {
            userService.createUser(login, password, firstname, lastname);
            request.setAttribute("successMessage", "User has been added successfully");
            logger.trace("User has been added");
        } catch (RuntimeException ex) {
            errorMessage = "This login is already connected to an account";
            logger.error(ex.getMessage());
            request.setAttribute("errorMessage", errorMessage);
        }

        logger.debug("Commands finished");
        return "/WEB-INF/views/admin/add_user_page.jsp";
    }
}
