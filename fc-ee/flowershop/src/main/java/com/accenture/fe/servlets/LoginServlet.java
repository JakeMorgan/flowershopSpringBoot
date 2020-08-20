package com.accenture.fe.servlets;

import com.accenture.be.access.UserRepository;
import com.accenture.be.business.UserBusinessService;
import com.accenture.be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);
    @Autowired
    UserBusinessService userBusinessService;
    @Autowired
    UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        if (request.getParameter("loginsubmit") != null) {
            Optional<User> optionalUser = userBusinessService.login(request.getParameter("username"), request.getParameter("password"));
            if (optionalUser.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", optionalUser.get());
                response.sendRedirect("index");
            } else {
                LOG.error("Неверный Login or Password");
                response.sendRedirect("login");
            }
        } else if (request.getParameter("regsubmit") != null) {
            Optional<User> optionalUser = userBusinessService.register(request.getParameter("username"), request.getParameter("password"), "", "", new BigDecimal(0), "user");
            if (optionalUser.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", optionalUser.get());
                response.sendRedirect("index");
            } else {
                LOG.error("Пользователь уже существует");
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private User setUser(User u) {
        return u;
    }
}
