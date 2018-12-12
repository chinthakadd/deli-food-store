package com.chinthakad.delifoodstore.presentation.controller;

import com.chinthakad.delifoodstore.presentation.model.CustomerDto;
import com.chinthakad.delifoodstore.core.application.CustomerService;
import com.chinthakad.delifoodstore.core.domain.customer.model.Customer;
import com.chinthakad.delifoodstore.seedwork.exception.ShoppingCartException;
import com.chinthakad.delifoodstore.seedwork.model.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the customer resource.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * @return a list of customers registered in this shopping experience.
     */
    @GetMapping
    public ResponseDto<List<CustomerDto>> getCustomers() {
        try {
            List<Customer> customers = customerService.getCustomers();
            List<CustomerDto> customerDtoList = customers.stream()
                    .map(CustomerDto::from)
                    .collect(Collectors.toList());
            return ResponseDto.forSuccess(customerDtoList);
        } catch (ShoppingCartException sce) {
            return ResponseDto.forError(sce.getErrors());
        }
    }

}
