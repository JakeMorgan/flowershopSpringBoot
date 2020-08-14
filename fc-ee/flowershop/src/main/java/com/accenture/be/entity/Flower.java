package com.accenture.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="FLOWER")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;

    public Flower() {
    }

    public Flower(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (this.quantity < 0) {
            throw new RuntimeException("Quantity < 0");
        } else {
            this.quantity = quantity;
        }

    }

    public Flower subtractionQuantity(int quantity) {
        if (this.quantity - quantity < 0) {
            throw new RuntimeException("Subtraction quantity < 0");
        } else {
            this.quantity -= quantity;
            return this;
        }
    }
}
