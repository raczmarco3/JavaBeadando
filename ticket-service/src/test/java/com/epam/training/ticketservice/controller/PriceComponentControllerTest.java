package com.epam.training.ticketservice.controller;

import com.epam.training.ticketservice.model.PriceComponent;
import com.epam.training.ticketservice.model.User;
import com.epam.training.ticketservice.repository.PriceComponentRepository;
import com.epam.training.ticketservice.service.PriceComponentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class PriceComponentControllerTest {
    @Mock
    private PriceComponentService priceComponentService;
    @Mock
    private PriceComponentRepository priceComponentRepository;
    private PriceComponentController priceComponentController;
    private PriceComponent priceComponent = new PriceComponent("elso", -500);
    List<PriceComponent> priceComponentList = new ArrayList<>();



    @BeforeEach
    void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        priceComponentService = new PriceComponentService(priceComponentRepository);
        priceComponentController = new PriceComponentController(priceComponentService);
    }

    @Test
    public void createPriceComponent() {
        priceComponentList.add(priceComponent);
        when(priceComponentRepository.findByComponentName("elso")).thenReturn(priceComponent);
        when(priceComponentRepository.findAll()).thenReturn(priceComponentList);
        priceComponentController.createPriceComponent("elso",-500);
        Assertions.assertTrue(priceComponentRepository.findByComponentName("elso")
                .getComponentPrice() == -500);
    }
}
