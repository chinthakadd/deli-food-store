package com.chinthakad.delifoodstore.infrastructure.payment;

import com.chinthakad.delifoodstore.core.domain.customer.model.PaymentMethod;
import com.chinthakad.delifoodstore.core.integration.PaymentClient;
import com.chinthakad.delifoodstore.core.domain.customer.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Mock implemnentation.
 */
@Component
public class PaymentMockClient implements PaymentClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentMockClient.class);

    @Override
    public void authorize(Customer customer, PaymentMethod paymentMethod, BigDecimal total) {
        LOGGER.info("Authorizing Payment for {} with Payment Method: {} for Total: {}",
                customer.getId(), paymentMethod.getId(), total);
    }
}
