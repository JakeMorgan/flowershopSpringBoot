package com.accenture.be.business;

import com.accenture.be.access.FlowerAccessService;
import com.accenture.be.entity.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class FlowerBusinessServiceImpl implements FlowerBusinessService {
    @Autowired
    private FlowerAccessService flowerAccessService;

    public Flower getFlower(Long id){
        return flowerAccessService.getById(id);
    }

    public List<Flower> getFlowers(){
        return flowerAccessService.getFlowers();
    }

    public Flower updateFlowersCount(Long id, int quantity) {
        return flowerAccessService.update(flowerAccessService.getById(id).subtractionQuantity(quantity));
    }

    public Flower create(String name, BigDecimal price, int quantity){
        return flowerAccessService.create(new Flower(name, price, quantity));
    }

    public Long countFlowers() {
        return flowerAccessService.countFlowers();
    }
}
