package com.accenture.be.entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.accenture.be.entity.User;
import com.accenture.fe.Enums.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name="ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate orderCreateDate;
    private LocalDate orderCompleteDate;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;
    private BigDecimal total;
    public OrderStatus status;

    public Order(User user, BigDecimal total){
        this.user = user;
        this.total = total;
        this.orderCreateDate = LocalDate.now();
        this.status = OrderStatus.CREATED;
        this.orderCompleteDate = null;
    }

    public void setStatus(OrderStatus status){
        this.status = status;
    }

    public void setCompleteDate(LocalDate orderCompleteDate){
        this.orderCompleteDate = orderCompleteDate;
    }
}
