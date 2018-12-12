package com.chinthakad.delifoodstore.core.application;

import com.chinthakad.delifoodstore.core.domain.customer.model.CustomerRepository;
import com.chinthakad.delifoodstore.core.domain.customer.model.PaymentMethod;
import com.chinthakad.delifoodstore.seedwork.util.DomainExceptionHelper;
import com.chinthakad.delifoodstore.core.domain.customer.model.Customer;
import com.chinthakad.delifoodstore.seedwork.exception.ShoppingCartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getCustomers() throws ShoppingCartException {
        return customerRepository.findAll();
    }

    @Override
    public List<PaymentMethod> getPaymentMethods(long customerId) throws ShoppingCartException {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            return customer.getPaymentMethods();
        }
        throw DomainExceptionHelper.customerNotFound(customerId);
    }

    @Override
    public void addNewPaymentMethod(long customerId, PaymentMethod paymentMethod) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.addPaymentMethod(paymentMethod);
            return;
        }
        throw DomainExceptionHelper.customerNotFound(customerId);
    }
}
