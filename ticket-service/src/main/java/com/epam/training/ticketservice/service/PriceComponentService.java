package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.PriceComponent;
import com.epam.training.ticketservice.repository.PriceComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PriceComponentService {
    private PriceComponentRepository priceComponentRepository;

    @Autowired
    public PriceComponentService(PriceComponentRepository priceComponentRepository) {
        this.priceComponentRepository = priceComponentRepository;
    }

    @Transactional
    public void createPriceComponent(String componentName, int componentPrice) {
        priceComponentRepository.save(new PriceComponent(componentName, componentPrice));
    }
}
