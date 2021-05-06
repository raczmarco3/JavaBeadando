package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.PriceComponent;
import com.epam.training.ticketservice.model.PriceComponentSet;
import com.epam.training.ticketservice.repository.PriceComponentRepository;
import com.epam.training.ticketservice.repository.PriceComponentSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PriceComponentSetService {
    private PriceComponentSetRepository priceComponentSetRepository;
    private PriceComponentRepository priceComponentRepository;

    @Autowired
    public PriceComponentSetService(PriceComponentSetRepository priceComponentSetRepository,
                                    PriceComponentRepository priceComponentRepository) {
        this.priceComponentSetRepository = priceComponentSetRepository;
        this.priceComponentRepository = priceComponentRepository;
    }

    @Transactional
    public void setPriceComponentSet(String componentName, String typeName, String attachedId) {
        PriceComponent priceComponent = priceComponentRepository.findByComponentName(componentName);
        int price = priceComponent.getComponentPrice();
        priceComponentSetRepository.save(new PriceComponentSet(componentName, typeName, attachedId, price));
    }
}
