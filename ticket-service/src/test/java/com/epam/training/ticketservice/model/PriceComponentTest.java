package com.epam.training.ticketservice.model;

import com.epam.training.ticketservice.model.PriceComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriceComponentTest {
    private PriceComponent priceComponent;

    @BeforeEach
    public void setUp(){
        priceComponent = new PriceComponent("elso", -500);
    }

    @Test
    public void getComponentPrice() {
        Assertions.assertEquals(-500, priceComponent.getComponentPrice());
    }
}
