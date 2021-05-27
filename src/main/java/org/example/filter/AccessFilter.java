package org.example.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AccessFilter implements Filter {
    private static final String URL_PATTERN = ".*/InternetProvider/";

    private static final List<String> common = new ArrayList<>();
    private static final List<String> guest = new ArrayList<>();
    private static final Map<String, List<String>> accessMap = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        common.addAll(
                Arrays.asList("/", "/user_registration.jsp", "/css/main.css", "getServiceList", "getTariffListByService"));
        guest.addAll(
                Arrays.asList("/login.jsp", "login"));
        accessMap.put("ADMIN",
                Arrays.asList("/admin_page.jsp", "logout", "getUser", "createUser", "updateUser",
                        "getTariffList", "createTariff", "updateTariff", "deleteTariff"));
        accessMap.put("USER",
                Arrays.asList("/user_page.jsp", "logout", "createOrder", "upBalance"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (accessAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            String errorMessage = "You don't have permission to access the requested resource";
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
        if (userRole == null) {
            return guest.contains(commandName);
        }

        return accessMap.get(userRole).contains(commandName);

    }

    @Override
    public void destroy() {

    }
}
