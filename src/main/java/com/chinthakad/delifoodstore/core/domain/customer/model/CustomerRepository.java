package com.chinthakad.delifoodstore.core.domain.customer.model;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findById(long id);

    void save(Customer customer);
}
