package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.model.PriceComponent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceComponentRepository extends CrudRepository<PriceComponent, String> {
    PriceComponent findByComponentName(String componentName);
}
