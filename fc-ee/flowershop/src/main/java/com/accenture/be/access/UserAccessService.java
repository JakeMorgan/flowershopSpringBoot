package com.accenture.be.access;

import com.accenture.be.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserAccessService {
    Optional<User> get(String userName);
    @Transactional
    Optional<User> getByUserNameAndPassword(String userName, String password);
    @Transactional
    User create(User user);
    @Transactional
    User update(User user);
}
