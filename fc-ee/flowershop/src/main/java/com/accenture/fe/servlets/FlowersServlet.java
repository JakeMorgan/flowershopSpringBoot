package com.accenture.fe.servlets;

import com.accenture.be.business.FlowerBusinessService;
import com.accenture.be.business.OrderBusinessService;
import com.accenture.be.entity.Flower;
import com.accenture.be.entity.Order;
import com.accenture.be.entity.OrderItem;
import com.accenture.be.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/flowers")
public class FlowersServlet extends HttpServlet {
    @Autowired
    FlowerBusinessService flowerBusinessService;
    @Autowired
    OrderBusinessService orderBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Flower> flowers = flowerBusinessService.getFlowers();
        HttpSession session = request.getSession(false); //Убрать true
        session.setAttribute("flowers", flowers);

        request.getRequestDispatcher("/flowers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse responce)
    throws ServletException, IOException{
        BigDecimal total = new BigDecimal(0);
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        if(request.getParameter("click") != null){
            orderItems.add(new OrderItem(flowerBusinessService.getFlower(Long.valueOf(1)), 1));
        }

        if(request.getParameter("buy") != null){
            HttpSession session = request.getSession(false);
            for (OrderItem o:orderItems){
                total.add(o.getCost());
            }
            Order order = orderBusinessService.createOrder((User)session.getAttribute("user"), total);
            for(OrderItem o:orderItems){
                orderBusinessService.createOrderItem(order, o);
            }
        }
    }
}
