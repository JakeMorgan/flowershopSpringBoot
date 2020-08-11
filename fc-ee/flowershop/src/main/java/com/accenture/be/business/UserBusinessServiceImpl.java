package com.accenture.be.business;

import com.accenture.be.access.UserAccessService;
import com.accenture.be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class UserBusinessServiceImpl implements UserBusinessService {
    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);
    @Autowired
    private UserAccessService userAccessService;

    @Override
    public Optional<User> login(String userName, String password){
        return userAccessService.getByUserNameAndPassword(userName, password);
    }

    @Override
    public Optional<User> register(String userName, String password, String address, String phone, BigDecimal balance){
        return userAccessService.get(userName).isPresent() ? Optional.empty() : Optional.of(userAccessService.create(new User(userName, password, address, phone, balance)));
    }

    @Override
    public User updateBalance(String username, BigDecimal total) {
        return userAccessService.update(userAccessService.get(username).get().subtractionBalance(total));
    }

    @Override
    public User updateData(String oldName, String username, String password, String address, String phone){
        User user = userAccessService.get(oldName).get();
        user.setUserName(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhone(phone);
        userAccessService.update(user);
        return user;
    }
}
