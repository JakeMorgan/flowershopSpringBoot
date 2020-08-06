package com.accenture.be.business;
import com.accenture.be.entity.Order;
import com.accenture.be.entity.OrderItem;
import com.accenture.be.entity.User;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
@Component
public interface OrderBusinessService {
    @Transactional
    Order createOrder(User user, BigDecimal total);
    @Transactional
    OrderItem createOrderItem(Order order, OrderItem orderItem);
    @Transactional
    List<Order> getOrdersList();
    void completeOrder(Long id);
    List<Order> getUserOrders(User user);
}
