package com.accenture.fe.servlets;

import com.accenture.be.business.FlowerBusinessService;
import com.accenture.be.business.OrderBusinessService;
import com.accenture.be.entity.Order;
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
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AdminServlet.class);
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
        throws IOException, ServletException{
        if(request.getParameter("Add") != null){
            flowerBusinessService.create(request.getParameter("name"), new BigDecimal(request.getParameter("price")),
                    Integer.parseInt(request.getParameter("quantity")));
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        }
    }

    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        HttpSession session = request.getSession(false);
        List<Order> ordersList = orderBusinessService.getOrdersList();
        session.setAttribute("ordersList", ordersList);
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
