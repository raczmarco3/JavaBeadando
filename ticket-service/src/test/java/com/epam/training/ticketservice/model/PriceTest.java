package com.epam.training.ticketservice.model;

import com.epam.training.ticketservice.model.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriceTest {
    private Price testPrice;

    @BeforeEach
    public void setUp() {
        testPrice = new Price(1500);
    }

    @Test
    public void getPrice() {
        Assertions.assertEquals(1500, testPrice.getPrice());
    }

    @Test
    public void setPrice() {
        testPrice.setPrice(2000);
        Assertions.assertEquals(2000, testPrice.getPrice());
    }
}
