package com.accenture.be.access;

import com.accenture.be.entity.Order;
import com.accenture.be.entity.OrderItem;
import com.accenture.be.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
@Component
public class OrderAccessImpl implements OrderAccessService {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Order> getOrders() {
        try{
            TypedQuery<Order> query = entityManager.createQuery("select o from Order o", Order.class);
            List<Order> orderList = query.getResultList();
            return orderList;
        }catch(NoResultException ex){
            return Collections.emptyList();
        }
    }

    @Override
    public Order create(Order order) {
            entityManager.persist(order);
            return order;
    }

    public OrderItem createOrderItem(OrderItem orderItem){
        entityManager.persist(orderItem);
        return orderItem;
    }

    @Override
    public Order update(Order order) {
        return entityManager.merge(order);
    }

    @Override
    public Order getById(Long id) {
        try {
            return entityManager.find(Order.class, id);
        } catch (NoResultException ex) {
            throw new RuntimeException("Order getById = null");
        }
    }

    @Override
    public List<Order> getOrderByUser(User user) {
        try {
            TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order WHERE o.user=:user", Order.class);
            query.setParameter("user", user);
            List<Order> orderList = query.getResultList();
            return orderList;
        }catch(NoResultException ex){
            return Collections.emptyList();
        }
    }
}
