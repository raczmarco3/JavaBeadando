package com.epam.training.ticketservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PriceComponent {
    @Id
    @Column
    private String componentName;

    @Column
    private int componentPrice;

    public PriceComponent() {
    }

    public PriceComponent(String componentName, int componentPrice) {
        this.componentName = componentName;
        this.componentPrice = componentPrice;
    }

}
