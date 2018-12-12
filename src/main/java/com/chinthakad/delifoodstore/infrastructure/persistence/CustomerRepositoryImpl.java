package com.chinthakad.delifoodstore.infrastructure.persistence;

import com.chinthakad.delifoodstore.core.domain.customer.model.CustomerRepository;
import com.chinthakad.delifoodstore.core.domain.customer.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    static List<Customer> customerStore = new ArrayList<>();

    @Override
    public List<Customer> findAll() {
        return customerStore;
    }

    @Override
    public Optional<Customer> findById(long id) {
        return Optional.ofNullable(customerStore.get((int) (id - 1)));
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId() <= customerStore.size()) {
            customerStore.set(((int) customer.getId() - 1), customer);
        } else {
            customerStore.add(customer);
        }
    }

}
