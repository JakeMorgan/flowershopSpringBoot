package com.accenture.be.entity;

import com.accenture.fe.Enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Entity
@Table(name="ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDateTime orderCreateDate;
    @Temporal(TemporalType.DATE)
    private LocalDateTime orderCompleteDate;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;
    private BigDecimal total;
    private OrderStatus status;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Order() {

    }

    public Order(User user, BigDecimal total) {
        this.user = user;
        this.total = total;
        this.orderCreateDate = LocalDateTime.now();
        this.status = OrderStatus.CREATED;
        this.orderCompleteDate = null;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCompleteDate(LocalDateTime orderCompleteDate) {
        this.orderCompleteDate = orderCompleteDate;
    }
}
