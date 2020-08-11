package com.accenture.be.access;

import com.accenture.be.entity.Flower;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FlowerAccessService {
    List<Flower> getFlowers();
    Flower getById(Long id);
    @Transactional
    Flower update(Flower flower);
    Flower create(Flower flower);

    Long countFlowers();
}
