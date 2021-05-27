package org.example.controller.command.admin;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.Status;
import org.example.model.entity.User;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserCommand implements Command {

    private static final Logger logger = Logger.getLogger(UpdateUserCommand.class);

    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Command starts");

        String errorMessage = null;

        String id = request.getParameter("id");

        String statusId = request.getParameter("status");

        if (id == null || id.equals("") || statusId == null || statusId.equals("")) {
            errorMessage = "Please fill all fields";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("Validation: " + errorMessage);
            return "/WEB-INF/views/admin/update_user_page.jsp";
        }

        try {
            userService.updateUser(Integer.parseInt(id), Integer.valueOf(statusId));
            request.setAttribute("successMessage", "User has been updated successfully");
            logger.trace("User has been updated successfully");
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
            errorMessage = "User hasn't been changed";
            request.setAttribute("errorMessage", errorMessage);
        }

        logger.debug("Commands finished");
        return "/WEB-INF/views/admin/update_user_page.jsp";
    }
}
