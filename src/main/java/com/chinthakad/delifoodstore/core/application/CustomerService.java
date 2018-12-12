package com.chinthakad.delifoodstore.core.application;

import com.chinthakad.delifoodstore.core.domain.customer.model.PaymentMethod;
import com.chinthakad.delifoodstore.seedwork.exception.ShoppingCartException;
import com.chinthakad.delifoodstore.core.domain.customer.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers() throws ShoppingCartException;

    List<PaymentMethod> getPaymentMethods(long customerId) throws ShoppingCartException;

    void addNewPaymentMethod(long customerId, PaymentMethod paymentMethod);

}
