package com.accenture.be.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String address;
    private String phone;
    private BigDecimal balance;
    private int discount;
    public User(){

    }

    public User(String userName, String password, String address, String phone, BigDecimal balance){
        this.username = userName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.balance = balance;
        this.discount = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getDiscount(){ return discount; }

    public void setDiscount(int discount){ this.discount = discount; }

    public User subtractionBalance(BigDecimal total) {
        if (balance.compareTo(total) < 0) {
            throw new RuntimeException("subtraction balance < 0");
        } else {
            balance = balance.subtract(total);
            return this;
        }
    }

    public Boolean checkBalance(BigDecimal total) {
        if (balance.compareTo(total) < 0) {
            return false;
        } else {
            return true;
        }
    }
}
