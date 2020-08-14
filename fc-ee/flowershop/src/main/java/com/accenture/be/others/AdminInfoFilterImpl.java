package com.accenture.be.others;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/info")
public class AdminInfoFilterImpl implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String adminPageURI = request.getContextPath() + "/admin";

        boolean idIn = session != null && session.getAttribute("admininfo") != null;
        boolean adminRequest = request.getRequestURI().equals(adminPageURI);
        if (idIn || adminRequest) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(adminPageURI);
        }
    }
}