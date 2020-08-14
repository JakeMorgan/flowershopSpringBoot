package com.accenture.fe.servlets;

import com.accenture.be.business.OrderBusinessService;
import com.accenture.be.business.UserBusinessService;
import com.accenture.be.entity.Order;
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
import java.util.List;

@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(IndexServlet.class);
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private OrderBusinessService orderBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession session = request.getSession();
        User un = (User)session.getAttribute("user");
        if(request.getParameter("save") != null) {
            User user = userBusinessService.updateData(un.getUserName(), request.getParameter("username"), request.getParameter("password"), request.getParameter("address"),
                    request.getParameter("phone"));
            session.setAttribute("user", user);
        }
        if(request.getParameter("flowers") != null){
            response.sendRedirect("flowers");
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException{
        HttpSession session = request.getSession(false);
        List<Order> ordersList = orderBusinessService.getUserOrders((User) session.getAttribute("user"));
        session.setAttribute("userOrdersList", ordersList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
