package com.epam.training.ticketservice.model;


import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PriceComponentSet {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String componentName;

    @Column
    private String typeName;

    @Column
    private String attachedId;

    public PriceComponentSet() {
    }

    public PriceComponentSet(String componentName, String typeName, String attachedId) {
        this.componentName = componentName;
        this.typeName = typeName;
        this.attachedId = attachedId;
    }
}
