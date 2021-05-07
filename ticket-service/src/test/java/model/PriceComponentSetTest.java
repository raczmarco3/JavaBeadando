package model;

import com.epam.training.ticketservice.model.PriceComponentSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriceComponentSetTest {
    private PriceComponentSet priceComponentSet;

    @BeforeEach
    public void setUp() {
        priceComponentSet = new PriceComponentSet("elso", "mozi",
                "Pedersoli", 1500);
    }

    @Test
    public void getAttachedId() {
        Assertions.assertEquals("Pedersoli", priceComponentSet.getAttachedId());
    }

    @Test
    public void getPrice() {
        Assertions.assertEquals(1500, priceComponentSet.getPrice());
    }


}
