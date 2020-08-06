package com.accenture.be.business;
import com.accenture.be.access.UserAccessService;
import com.accenture.be.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

@Component
public class UserBusinessServiceImpl implements UserBusinessService {
    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);
    @Autowired
    private UserAccessService userAccessService;

    @Override
    public Optional<User> login(String userName, String password){
        //Optional<User> user = userAccessService.getByUserNameAndPassword(userName, password);
        //return user.isPresent() ? Optional.empty() : user;
        return userAccessService.getByUserNameAndPassword(userName, password);
    }

    @Override
    public Optional<User> register(String userName, String password, String address, String phone, BigDecimal balance){
        return userAccessService.get(userName).isPresent() ? Optional.empty() : Optional.ofNullable(userAccessService.create(new User(userName, password, address, phone, balance)));
    }

    @Override
    public User getInfo(String userName){
        return userAccessService.get(userName).isPresent() ? userAccessService.get(userName).get() : null;
    }

    @Override
    public User updateBalance(String username, BigDecimal total) {
        User user = userAccessService.get(username).get();
        BigDecimal oBalance = user.getBalance();
        user.setBalance(user.getBalance().compareTo(total) < 0 ? new BigDecimal(0) : user.getBalance().subtract(total));
        userAccessService.update(user);
        return user;
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
