package com.accenture.fe.servlets;

import com.accenture.be.business.FlowerBusinessService;
import com.accenture.be.business.OrderBusinessService;
import com.accenture.be.business.UserBusinessService;
import com.accenture.be.entity.Flower;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/flowers")
public class FlowersServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(FlowersServlet.class);
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private UserBusinessService userBusinessService;

    //Список кол-ва цветов для orderItems (ID (0,1,0,3....))
    //In session

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Flower> flowers = flowerBusinessService.getFlowers();
        HttpSession session = request.getSession(false);
        session.setAttribute("flowers", flowers);
        request.getRequestDispatcher("/flowers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        List<Long> countFlowers = new ArrayList<>();

        if (session.getAttribute("countFlowers") == null) {
            session.setAttribute("countFlowers", countFlowers);
        } else {
            countFlowers = (List<Long>) session.getAttribute("countFlowers");
        }
        if (request.getParameter("back") != null) {
            response.sendRedirect("index");
        }
        if (request.getParameter("basket") != null) {
            response.sendRedirect(request.getContextPath() + "/flowers/basket");
        }
        if(request.getParameter("buy") != null){
            user = orderBusinessService.buy(user, countFlowers);
            session.setAttribute("user", user);
            session.setAttribute("countFlowers", new ArrayList<>());
            List<Flower> flowers = flowerBusinessService.getFlowers();
            session.setAttribute("flowers", flowers);
            request.getRequestDispatcher("/flowers.jsp").forward(request, response);
            return;
        }

        List<Flower> sessionList = (List<Flower>) session.getAttribute("flowers");
        for (Flower flower : sessionList) {
            if (request.getParameter(flower.getId().toString()) != null) {
                countFlowers.add(flower.getId());
                session.setAttribute("countFlowers", countFlowers);
                request.getRequestDispatcher("/flowers.jsp").forward(request, response);
            }
        }
    }
}
