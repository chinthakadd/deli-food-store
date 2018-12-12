package com.chinthakad.delifoodstore.core.integration;

import com.chinthakad.delifoodstore.core.domain.customer.model.PaymentMethod;
import com.chinthakad.delifoodstore.core.domain.customer.model.Customer;

import java.math.BigDecimal;

public interface PaymentClient {
    void authorize(Customer customer, PaymentMethod paymentMethod, BigDecimal total);
}
