package com.accenture.be.others;

import com.accenture.be.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin")
public class AdminFilterImpl implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        if (loggedIn) {
            String userRole = ((User) session.getAttribute("user")).getRole();
            if (userRole.equals("user")) {
                response.sendRedirect(request.getContextPath() + "/index");
            } else if (userRole.equals("admin")) {
                filterChain.doFilter(request, response);
            }
        } else response.sendRedirect(request.getContextPath() + "/login");
    }
}
