package org.example.filter;

import org.example.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AccessFilter implements Filter {
    private static final String URL_PATTERN = ".*/InternetProvider/";

    private static List<String> common = new ArrayList<String>();
    private static List<String> guest = new ArrayList<String>();
    private static Map<String, List<String>> accessMap = new HashMap<String, List<String>>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        common.add("/");
        common.add("service");
        common.add("tariff");
        common.add("/user_registration.jsp");
        guest.add("/login.jsp");
        guest.add("login");
        accessMap.put("ADMIN",
                Arrays.asList("logout", "admin", "add-user", "update-user", "edit-tariff", "create-tariff", "update-tariff", "delete-tariff"));
        accessMap.put("USER",
                Arrays.asList("logout", "user", "add-to-order", "make-order", "up-balance"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (accessAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }

    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;

        String uri = req.getRequestURI();

        String commandName = uri.replaceAll(URL_PATTERN, "");

        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (common.contains(commandName)) {
            return true;
        }

        HttpSession session = req.getSession(false);
        if (session == null)
            return false;

        String userRole = (String) session.getAttribute("role");
        System.out.println("user role => " + userRole);
        if (userRole == null) {
            return guest.contains(commandName);
        }

        System.out.println("access => ");

        return accessMap.get(userRole).contains(commandName);

    }

    @Override
    public void destroy() {

    }
}
