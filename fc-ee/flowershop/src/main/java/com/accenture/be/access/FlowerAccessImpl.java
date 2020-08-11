package com.accenture.be.access;

import com.accenture.be.entity.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
@Component
public class FlowerAccessImpl implements FlowerAccessService {
    private static final Logger LOG = LoggerFactory.getLogger(FlowerAccessImpl.class);
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Flower> getFlowers() {
        try{
            TypedQuery<Flower> query = entityManager.createQuery("select f from Flower f", Flower.class);
            return query.getResultList();
        }catch(NoResultException ex){
            return Collections.emptyList();
        }
    }

    @Override
    public Flower getById(Long id) {
        try {
            return entityManager.find(Flower.class, id);
        } catch (NoResultException ex) {
            throw new RuntimeException("Flower getById = null");
        }
    }

    @Override
    @Transactional
    public Flower update(Flower flower) {
        return entityManager.merge(flower);
    }

    public Flower create(Flower flower){
        entityManager.persist(flower);
        return flower;
    }

    @Override
    public Long countFlowers() {
        Query query = entityManager.createQuery("select Count(f) from Flower f");
        return (Long) query.getSingleResult();
    }
}
