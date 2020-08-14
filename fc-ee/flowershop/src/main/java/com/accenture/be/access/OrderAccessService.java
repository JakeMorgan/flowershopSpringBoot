package com.accenture.be.access;

import com.accenture.be.entity.Order;
import com.accenture.be.entity.OrderItem;
import com.accenture.be.entity.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface OrderAccessService {
    @Transactional
    List<Order> getOrders();
    @Transactional
    Order create(Order order);
    @Transactional
    Order update(Order order);
    @Transactional
    Optional<Order> getById(Long id);

    List<Order> getOrderByUser(User user);

    OrderItem createOrderItem(OrderItem orderItem);
}
