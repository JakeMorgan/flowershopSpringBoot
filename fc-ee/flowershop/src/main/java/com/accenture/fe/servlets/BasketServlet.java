package com.accenture.fe.servlets;

import com.accenture.be.business.FlowerBusinessService;
import com.accenture.be.business.OrderBusinessService;
import com.accenture.be.entity.Flower;
import com.accenture.be.others.Basket;
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

@WebServlet(urlPatterns = "/flowers/basket")
public class BasketServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(BasketServlet.class);
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
        if (request.getParameter("back") != null) {
            response.sendRedirect(request.getContextPath() + "/flowers");
            return;
        }
        LOG.error("ХААААААААААААААААААААААААААААААААААЙ");
        HttpSession session = request.getSession(false);
        List<Long> countFlowers = (List<Long>) session.getAttribute("countFlowers");
        List<Flower> flowerList = flowerBusinessService.getFlowers();
        for (Flower flower : flowerList) {
            if (request.getParameter(flower.getId().toString()) != null) {
                for (Long count : countFlowers) {
                    if (count == flower.getId()) {
                        countFlowers.remove(count);
                        session.setAttribute("countFlowers", countFlowers);
                        List<Basket> basketList = orderBusinessService.basketFilter((countFlowers));
                        session.setAttribute("basketList", basketList);
                        request.getRequestDispatcher("/basket.jsp").forward(request, response);
                        return;
                    }
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        List<Basket> basketList = orderBusinessService.basketFilter((List<Long>) session.getAttribute("countFlowers"));
        session.setAttribute("basketList", basketList);
        request.getRequestDispatcher("/basket.jsp").forward(request, response);
    }


}
