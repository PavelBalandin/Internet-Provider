package org.example.controller.command;

import org.apache.log4j.Logger;
import org.example.model.entity.Status;
import org.example.model.entity.User;
import org.example.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserCommand implements Command {

    private static final Logger logger = Logger.getLogger(UpdateUserCommand.class);

    private static final String ERROR_MESSAGE = "Error message: ";

    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String errorMessage = null;

        String id = request.getParameter("id");

        String statusId = request.getParameter("status");

        if (id == null || id.equals("") || statusId == null || statusId.equals("")) {
            errorMessage = "Please fill all fields";
            request.setAttribute("errorMessage", errorMessage);
            return "/WEB-INF/views/admin/update_user_page.jsp";
        }

        Status stat = new Status();
        stat.setId(Integer.valueOf(statusId));
        User user = new User.Builder()
                .withId(Integer.parseInt(id))
                .withStatus(stat)
                .build();

        if (userService.updateUser(user)) {
            request.setAttribute("successMessage", "User has been updated successfully");
        } else {
            errorMessage = "User hasn't been changed";
            request.setAttribute("errorMessage", errorMessage);
        }

        return "/WEB-INF/views/admin/update_user_page.jsp";
    }
}
