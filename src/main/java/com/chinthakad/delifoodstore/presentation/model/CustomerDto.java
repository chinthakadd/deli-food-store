package com.chinthakad.delifoodstore.presentation.model;

import com.chinthakad.delifoodstore.core.domain.customer.model.Customer;

public class CustomerDto {

    public CustomerDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private long id;
    private String name;

    public static CustomerDto from(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
