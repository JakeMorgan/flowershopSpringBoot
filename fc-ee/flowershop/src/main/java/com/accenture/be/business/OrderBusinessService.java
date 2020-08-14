package com.accenture.be.business;
import com.accenture.be.entity.Order;
import com.accenture.be.entity.OrderItem;
import com.accenture.be.entity.User;
import com.accenture.be.others.Basket;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public interface OrderBusinessService {
    @Transactional
    Order createOrder(User user, BigDecimal total);
    @Transactional
    OrderItem createOrderItem(Order order, OrderItem orderItem);
    @Transactional
    List<Order> getOrdersList();

    Order nextStatusOrder(Long id);
    List<Order> getUserOrders(User user);

    User buy(User user, List<Long> countFlowers);

    Optional<Order> getById(Long id);

    List<OrderItem> getOrderItems(Long orderId);

    List<Basket> basketFilter(List<Long> countFlowers);

    void checkCountFlowers(List<Long> countFlowers);
}
