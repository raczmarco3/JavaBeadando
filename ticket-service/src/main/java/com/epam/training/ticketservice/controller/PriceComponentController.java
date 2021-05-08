package com.epam.training.ticketservice.controller;

import com.epam.training.ticketservice.service.PriceComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PriceComponentController {
    private PriceComponentService priceComponentService;

    @Autowired
    public PriceComponentController(PriceComponentService priceComponentService) {
        this.priceComponentService = priceComponentService;
    }

    public void createPriceComponent(String componentName, int componentPrice) {
        priceComponentService.createPriceComponent(componentName, componentPrice);
    }
}
