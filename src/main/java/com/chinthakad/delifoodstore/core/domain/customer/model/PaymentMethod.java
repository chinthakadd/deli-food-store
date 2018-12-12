package com.chinthakad.delifoodstore.core.domain.customer.model;

import com.chinthakad.delifoodstore.seedwork.model.AbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Add
 */
public class PaymentMethod extends AbstractModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentMethod.class);

    private long id;
    private PaymentMethodType type;
    private Account account;

    public PaymentMethod() {
    }

    public PaymentMethod(Account account) {
        this.account = account;
        this.type = account.identifyCardType();

    }

    public PaymentMethod(PaymentMethodType type, Account account) {
        this.type = type;
        this.account = account;
    }

    @Override
    public boolean isValid() {
        boolean paymentMethodValidity = (type != null) && (account != null && account.isValid());
        LOGGER.info("Payment method validity check: {}", paymentMethodValidity);
        return paymentMethodValidity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PaymentMethodType getType() {
        return type;
    }

    public void setType(PaymentMethodType type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
