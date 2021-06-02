package org.example.controller.command.admin;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.controller.command.CommandUtility;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public class UpdateUserCommand implements Command {

    private static final Logger logger = Logger.getLogger(UpdateUserCommand.class);

    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Command starts");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        String id = request.getParameter("id");

        String statusId = request.getParameter("status");

        if (id == null || id.equals("") || statusId == null || statusId.equals("")) {
            request.setAttribute("errorMessage", rb.getString("message.empty.fields"));
            logger.error("Validation: " + rb.getString("message.empty.fields"));
            return "/WEB-INF/views/admin/update_user_page.jsp";
        }

        try {
            userService.updateUser(Integer.parseInt(id), Integer.parseInt(statusId));
            request.setAttribute("successMessage", rb.getString("message.user.updated"));
            logger.trace("User has been updated successfully");
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
            request.setAttribute("errorMessage", rb.getString("message.error"));
        }

        logger.debug("Commands finished");
        return "/WEB-INF/views/admin/update_user_page.jsp";
    }
}
