package com.accenture.be.business;

import com.accenture.be.entity.Flower;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
@Component
public interface FlowerBusinessService {
    Flower getFlower(Long id);
    List<Flower> getFlowers();
    @Transactional
    Flower updateFlowersCount(Long id, int count);
    Flower create(String name, BigDecimal price, int quantity);
}
