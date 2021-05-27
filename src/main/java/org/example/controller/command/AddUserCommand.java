package org.example.controller.command;

import org.apache.log4j.Logger;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AddUserCommand implements Command {
    private static final Logger logger = Logger.getLogger(AddUserCommand.class);

    private static final String ERROR_MESSAGE = "Error message: ";

    private final UserService userService;

    public AddUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
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
            logger.trace(ERROR_MESSAGE + errorMessage);
            return "/WEB-INF/views/admin/add_user_page.jsp";
        }

        if (userService.createUser(login, password, firstname, lastname)) {
            request.setAttribute("successMessage", "User has been added successfully");
        } else {
            errorMessage = "This login is already connected to an account";
            request.setAttribute("errorMessage", errorMessage);
        }

        return "/WEB-INF/views/admin/add_user_page.jsp";
    }
}
