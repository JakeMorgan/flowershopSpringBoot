package com.accenture.be.business;

import com.accenture.be.access.UserAccessService;
import com.accenture.be.access.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> login(String userName, String password){
        //return userAccessService.getByUserNameAndPassword(userName, password);
        return userRepository.findByUserNameAndPassword(userName, password);
    }

    @Override
    public Optional<User> register(String userName, String password, String address, String phone, BigDecimal balance, String role) {
        //return userAccessService.get(userName).isPresent() ? Optional.empty() : Optional.of(userAccessService.create(new User(userName, password, address, phone, balance, role)));
        return userRepository.findByUserName(userName).isPresent() ? Optional.empty() : Optional.of(userRepository.save(new User(userName, password, address, phone, balance, role)));
    }

    @Override
    public User updateBalance(Long id, BigDecimal total) {
        //return userAccessService.update(userAccessService.get(username).get().subtractionBalance(total));
        return userRepository.save(userRepository.findById(id).get().subtractionBalance(total));

    }

    @Override
    public User updateData(Long id, String username, String password, String address, String phone){
        User user = userRepository.findById(id).get();
        user.setUserName(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhone(phone);
        userRepository.save(user);
        return user;
    }
}
