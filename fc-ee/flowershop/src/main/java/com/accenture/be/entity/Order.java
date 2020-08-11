package com.accenture.be.entity;

import com.accenture.fe.Enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date orderCreateDate;
    @Temporal(TemporalType.DATE)
    private Date orderCompleteDate;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;
    private BigDecimal total;
    private OrderStatus status;

    public Order() {

    }

    public Order(User user, BigDecimal total) {
        this.user = user;
        this.total = total;
        this.orderCreateDate = new Date();
        this.status = OrderStatus.CREATED;
        this.orderCompleteDate = null;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCompleteDate(Date orderCompleteDate) {
        this.orderCompleteDate = orderCompleteDate;
    }
}
