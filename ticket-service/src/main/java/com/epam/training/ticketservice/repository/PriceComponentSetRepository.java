package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.model.PriceComponentSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceComponentSetRepository extends CrudRepository<PriceComponentSet, Integer> {
}
