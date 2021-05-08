package com.epam.training.ticketservice.controller;

import com.epam.training.ticketservice.service.PriceComponentSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PriceComponentSetController {
    private PriceComponentSetService priceComponentSetService;

    @Autowired
    public PriceComponentSetController(PriceComponentSetService priceComponentSetService) {
        this.priceComponentSetService = priceComponentSetService;
    }

    public void setPriceComponentSet(String componentName, String typeName, String attachedId) {
        priceComponentSetService.setPriceComponentSet(componentName, typeName, attachedId);
    }
}
