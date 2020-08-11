package com.accenture.fe.servlets;

import com.accenture.be.business.FlowerBusinessService;
import com.accenture.be.business.OrderBusinessService;
import com.accenture.be.business.UserBusinessService;
import com.accenture.be.entity.Flower;
import com.accenture.be.entity.Order;
import com.accenture.be.entity.OrderItem;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = "/flowers")
public class FlowersServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(FlowersServlet.class);
    @Autowired
    FlowerBusinessService flowerBusinessService;
    @Autowired
    OrderBusinessService orderBusinessService;
    @Autowired
    UserBusinessService userBusinessService;

    //Список кол-ва цветов для orderItems (ID (0,1,0,3....))
    private ArrayList<Integer> countFlowers = new ArrayList<>();

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

        ArrayList<OrderItem> orderItems = new ArrayList<>();

        if(request.getParameter("buy") != null){
            if (countFlowers.isEmpty()) {
                throw new RuntimeException("Корзина пуста");
            }
            //Фильтрация: ID - Count
            HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
            Integer am;
            for (Integer i : countFlowers) {
                am = hashMap.get(i);
                hashMap.put(i, am == null ? 1 : am + 1);
            }
            //Прогон по HashMap и добавление объектов в список
            for (Integer key : hashMap.keySet()) {
                orderItems.add(new OrderItem(flowerBusinessService.getFlower(Long.valueOf(key)), hashMap.get(key)));
            }
            //Общая стоимость
            BigDecimal total = new BigDecimal(0);

            for (OrderItem o:orderItems){
                total = total.add(o.getCost());
            }

            if (user.checkBalance(total)) {
                session.setAttribute("user", userBusinessService.updateBalance(user.getUserName(), total));

                Order order = orderBusinessService.createOrder(user, total);
                for (OrderItem o : orderItems) {
                    orderBusinessService.createOrderItem(order, o);
                }
            } else {
                throw new RuntimeException("No money");
            }
            countFlowers.clear();
            request.getRequestDispatcher("/flowers.jsp").forward(request, response);
            return;
        }

        for (int i = 0; i <= flowerBusinessService.countFlowers(); i++) {
            if (request.getParameter("" + i) != null) {
                countFlowers.add(i);
                request.getRequestDispatcher("/flowers.jsp").forward(request, response);
            }
        }
    }
}
