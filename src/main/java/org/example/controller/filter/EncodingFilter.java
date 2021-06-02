package org.example.controller.filter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger logger = Logger.getLogger(EncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Filter initialization starts");

        logger.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Filter starts");
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setCharacterEncoding("UTF-8");

        logger.trace("Set content type: text/html");
        logger.trace("Set character encoding: UTF-8");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getSession().getAttribute("sessionLocale") != null) {
            req.getSession().setAttribute("lang", req.getSession().getAttribute("sessionLocale"));
            logger.trace("Set session locale: " + req.getSession().getAttribute("sessionLocale"));
        }

        filterChain.doFilter(servletRequest, servletResponse);

        logger.debug("Filter initialization finished");
    }

    @Override
    public void destroy() {
        logger.debug("Filter destruction starts");

        logger.debug("Filter destruction finished");
    }
}
