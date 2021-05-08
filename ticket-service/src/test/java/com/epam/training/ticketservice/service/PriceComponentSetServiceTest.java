package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.PriceComponent;
import com.epam.training.ticketservice.model.PriceComponentSet;
import com.epam.training.ticketservice.repository.PriceComponentRepository;
import com.epam.training.ticketservice.repository.PriceComponentSetRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PriceComponentSetServiceTest {

    @Mock
    private PriceComponentSetRepository mockPriceComponentSetRepository;
    @Mock
    private PriceComponentRepository mockPriceComponentRepository;

    private PriceComponentSetService priceComponentSetServiceUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        priceComponentSetServiceUnderTest = new PriceComponentSetService(mockPriceComponentSetRepository, mockPriceComponentRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testSetPriceComponentSet() {
        // Setup
        when(mockPriceComponentRepository.findByComponentName("componentName")).thenReturn(new PriceComponent("componentName", 0));

        // Configure PriceComponentSetRepository.save(...).
        final PriceComponentSet priceComponentSet = new PriceComponentSet("componentName", "typeName", "attachedId", 0);
        when(mockPriceComponentSetRepository.save(any(PriceComponentSet.class))).thenReturn(priceComponentSet);

        // Run the test
        priceComponentSetServiceUnderTest.setPriceComponentSet("componentName", "typeName", "attachedId");

        // Verify the results
    }
}
