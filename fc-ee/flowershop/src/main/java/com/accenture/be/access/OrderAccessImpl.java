package com.accenture.be.access;

import com.accenture.be.entity.Order;
import com.accenture.be.entity.OrderItem;
import com.accenture.be.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class OrderAccessImpl implements OrderAccessService {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Order> getOrders() {
        try{
            TypedQuery<Order> query = entityManager.createQuery("select o from Order o ORDER BY case o.status" +
                    " when 'CREATED' then 100" +
                    " when 'SENT' then 101" +
                    " when 'COMPLETED' then 102" +
                    " end, o.orderCreateDate desc", Order.class);
            return query.getResultList();
        }catch(NoResultException ex){
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional
    public Order create(Order order) {
            entityManager.persist(order);
            return order;
    }

    @Transactional
    public OrderItem createOrderItem(OrderItem orderItem){
        entityManager.persist(orderItem);
        return orderItem;
    }

    @Override
    @Transactional
    public Order update(Order order) {
        return entityManager.merge(order);
    }

    @Override
    public Optional<Order> getById(Long id) {
        try {
            return Optional.of(entityManager.find(Order.class, id));
        } catch (NoResultException ex) {
            throw new RuntimeException("Order getById = null");
        }
    }

    @Override
    public List<Order> getOrderByUser(User user) {
        try {
            TypedQuery<Order> query = entityManager.createQuery("select o from Order o where o.user=:user ORDER BY case o.status" +
                    " when 'CREATED' then 100" +
                    " when 'SENT' then 101" +
                    " when 'COMPLETED' then 102" +
                    " end, o.orderCreateDate desc", Order.class);
            query.setParameter("user", user);
            return query.getResultList();
        }catch(NoResultException ex){
            return Collections.emptyList();
        }
    }
}
