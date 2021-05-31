package org.example.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;

public class CommandUtility {
    public static void setUserRole(HttpServletRequest request, String login, String role) {
        HttpSession session = request.getSession();
        session.setAttribute("login", login);
        session.setAttribute("role", role);
    }

    public static boolean checkUserIsLogged(HttpServletRequest request, String login) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");

        if (loggedUsers.stream().anyMatch(login::equals)) {
            return true;
        }
        loggedUsers.add(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    public static void destroyUserFromContext(HttpServletRequest request, String login) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        loggedUsers.remove(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }

    public static void destroyUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
    }

    public static ResourceBundle setResourceBundle(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute("sessionLocale");
        if (locale == null) {
            locale = "ua";
        }
        return ResourceBundle.getBundle("resources", new Locale(locale));
    }
}
