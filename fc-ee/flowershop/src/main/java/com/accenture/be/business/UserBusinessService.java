package com.accenture.be.business;

import com.accenture.be.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public interface UserBusinessService {
    Optional<User> login(String userName, String password);

    Optional<User> register(String userName, String password, String address, String phone, BigDecimal balance, String role);
    User updateBalance(String username, BigDecimal total);
    User updateData(String oldName, String username, String password, String address, String phone);
}
