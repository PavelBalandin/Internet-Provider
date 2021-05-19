package org.example.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String errorMessage = null;

        //Validate data
        if (login == null || login.equals("") || password == null || password.equals("")) {
            errorMessage = "Please enter your login and password";
            request.setAttribute("message", errorMessage);
            return "/login.jsp";
        }


        return null;
    }
}
