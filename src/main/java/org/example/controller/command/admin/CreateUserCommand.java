package org.example.controller.command.admin;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.controller.command.CommandUtility;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public class CreateUserCommand implements Command {
    private static final Logger logger = Logger.getLogger(CreateUserCommand.class);

    private static final String ERROR_MESSAGE = "Error message: ";

    private final UserService userService;

    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Command starts");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        if (login == null || login.equals("")
                || password == null || password.equals("")
                || firstname == null || firstname.equals("")
                || lastname == null || lastname.equals("")
        ) {
            request.setAttribute("errorMessage", rb.getString("message.empty.fields"));
            logger.error(ERROR_MESSAGE + rb.getString("message.empty.fields"));
            return "/WEB-INF/views/admin/add_user_page.jsp";
        }
        try {
            userService.createUser(login, CommandUtility.hash(password), firstname, lastname);
            request.setAttribute("successMessage", rb.getString("message.user.created"));
            logger.trace("User has been added");
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
            request.setAttribute("errorMessage", rb.getString("message.login.exists"));
        }

        logger.debug("Commands finished");
        return "/WEB-INF/views/admin/add_user_page.jsp";
    }
}
