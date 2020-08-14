package com.accenture.fe.servlets;

import com.accenture.be.business.FlowerBusinessService;
import com.accenture.be.business.OrderBusinessService;
import com.accenture.be.entity.OrderItem;
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

@WebServlet(urlPatterns = "/admin/info")
public class OrderInfoServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(OrderInfoServlet.class);
    @Autowired
    OrderBusinessService orderBusinessService;
    @Autowired
    FlowerBusinessService flowerBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        Long orderId = (Long) session.getAttribute("admininfo");
        List<OrderItem> orderItems = orderBusinessService.getOrderItems(orderId);
        session.setAttribute("orderItemsList", orderItems);
        request.getRequestDispatcher("/admininfo.jsp").forward(request, response);
    }
}
