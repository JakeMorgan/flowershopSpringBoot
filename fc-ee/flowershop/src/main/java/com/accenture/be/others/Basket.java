package com.accenture.be.others;

import com.accenture.be.entity.Flower;

import java.math.BigDecimal;

public class Basket {
    private Long id;
    private Flower flower;
    private Integer amount;
    private BigDecimal cost;

    public Basket(Long id, Flower flower, Integer amount, BigDecimal cost) {
        this.id = id;
        this.flower = flower;
        this.amount = amount;
        this.cost = cost;
    }

    public Basket() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
