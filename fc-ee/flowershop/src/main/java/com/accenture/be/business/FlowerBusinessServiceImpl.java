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

    public Flower updateFlowersCount(Long id, int quantity) throws Exception{
            Flower flower = flowerAccessService.getById(id);
            if(flower == null) throw new Exception("UpdateFlowersCount - flower null");
            flower.setQuantity(flower.getQuantity() - quantity < 0 ? 0 : flower.getQuantity() - quantity);
            flowerAccessService.update(flower);
            return flower;
    }

    public Flower create(String name, BigDecimal price, int quantity){
        Flower flower = new Flower(name, price, quantity);
        flowerAccessService.create(flower);
        return flower;
    }
}
