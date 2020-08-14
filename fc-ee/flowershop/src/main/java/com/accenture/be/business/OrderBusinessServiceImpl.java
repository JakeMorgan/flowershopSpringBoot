package com.accenture.be.business;

import com.accenture.be.access.OrderAccessService;
import com.accenture.be.entity.Order;
import com.accenture.be.entity.OrderItem;
import com.accenture.be.entity.User;
import com.accenture.fe.Enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Component
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderAccessService orderAccessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private UserBusinessService userBusinessService;

    @Override
    public Order createOrder(User user, BigDecimal total) {
        return orderAccessService.create(new Order(user, total));
    }

    public OrderItem createOrderItem(Order order, OrderItem orderItem){
        orderItem.setOrder(order);
        return orderAccessService.createOrderItem(orderItem);
    }

    public Optional<Order> getById(Long id) {
        return orderAccessService.getById(id);
    }
    @Override
    public List<Order> getOrdersList() {
        return orderAccessService.getOrders();
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderAccessService.getOrderByUser(user);
    }

    public Order nextStatusOrder(Long id) {
        Optional<Order> order = orderAccessService.getById(id);
        order.get().nextStatus();
        if (order.get().getStatus() == OrderStatus.COMPLETED) {
            order.get().setOrderCompleteDate(new Date());
        }
        return orderAccessService.update(order.get());
    }

    @Transactional
    public List<OrderItem> getOrderItems(Long orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        Optional<Order> order = getById(orderId);
        orderItems = order.get().getOrderItems();
        orderItems.size();
        return orderItems;
    }

    @Override
    @Transactional
    public User buy(User user, List<Long> countFlowers) {
        List<OrderItem> orderItems = new ArrayList<>();
        if (countFlowers.isEmpty()) {
            throw new RuntimeException("Корзина пуста");
        }
        //Фильтрация: ID - Count
        Map<Long, Integer> hashMap = new HashMap<>();
        Integer am;
        for (Long i : countFlowers) {
            am = hashMap.get(i);
            hashMap.put(i, am == null ? 1 : am + 1);
        }
        //Прогон по HashMap и добавление объектов в список
        for (Long key : hashMap.keySet()) {
            orderItems.add(new OrderItem(flowerBusinessService.getFlower(Long.valueOf(key)), hashMap.get(key)));
        }
        //Общая стоимость
        BigDecimal total = new BigDecimal(0);

        for (OrderItem o : orderItems) {
            total = total.add(o.getCost());
        }

        if (user.checkBalance(total)) {
            user = userBusinessService.updateBalance(user.getUserName(), total);

            Order order = createOrder(user, total);
            for (OrderItem o : orderItems) {
                createOrderItem(order, o);
            }
        } else {
            throw new RuntimeException("No money");
        }
        return user;
    }
}
