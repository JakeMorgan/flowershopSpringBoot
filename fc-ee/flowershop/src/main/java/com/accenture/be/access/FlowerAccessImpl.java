package com.accenture.be.access;

import com.accenture.be.entity.Flower;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
@Component
public class FlowerAccessImpl implements FlowerAccessService {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Flower> getFlowers() {
        List<Flower> flowerList = null;
        try{
            TypedQuery<Flower> query = entityManager.createQuery("select f from Flower f", Flower.class);
            flowerList = query.getResultList();
            return flowerList;
        }catch(NoResultException ex){
            return Collections.emptyList();
        }
    }

    @Override
    public Flower getById(Long id) {
        try {
            return entityManager.find(Flower.class, id);
        } catch (NoResultException ex) {
            throw new NullPointerException("Flower getById = null");
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
}
