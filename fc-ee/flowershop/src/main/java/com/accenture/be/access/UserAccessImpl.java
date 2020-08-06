package com.accenture.be.access;

import com.accenture.be.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Optional;

@Component
@Service
public class UserAccessImpl implements UserAccessService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> get(String userName) {
        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
            query.setParameter("username", userName);
            return Optional.ofNullable(query.getSingleResult());
        }catch(NoResultException ex){
                return Optional.empty();
            }
    }

    @Override
    public Optional<User> getByUserNameAndPassword(String userName, String password) {
        try {
            TypedQuery<User> query = entityManager.createQuery("Select u From User u Where u.username=:username and " +
                    "u.password=:password", User.class);
            query.setParameter("username", userName);
            query.setParameter("password", password);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public User create(User user) {
            entityManager.persist(user);
            return user;
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }
}
