package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.PriceComponentSet;
import com.epam.training.ticketservice.repository.PriceComponentSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PriceComponentSetService {
    private PriceComponentSetRepository priceComponentSetRepository;

    @Autowired
    public PriceComponentSetService(PriceComponentSetRepository priceComponentSetRepository) {
        this.priceComponentSetRepository = priceComponentSetRepository;
    }

    @Transactional
    public void setPriceComponentSet(String componentName, String typeName, String attachedId) {
        priceComponentSetRepository.save(new PriceComponentSet(componentName, typeName, attachedId));
    }
}
