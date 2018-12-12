package com.chinthakad.delifoodstore.core.domain.customer.model;

import com.chinthakad.delifoodstore.seedwork.model.AbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class Account extends AbstractModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(Account.class);

    private long id;
    private String accountNumber;
    private Instant expiryDate;

    public Account() {
    }

    public Account(String accountNumber, Instant expiryDate) {
        this.accountNumber = accountNumber;
        this.expiryDate = expiryDate;
    }

    /**
     * Naive implementation of checking the card type.
     * TODO: use https://binlist.net and do real validation.
     *
     * @return PaymentMethodType
     */
    public PaymentMethodType identifyCardType() {
        if (accountNumber.startsWith("1")) {
            return PaymentMethodType.CREDIT;
        } else {
            return PaymentMethodType.DEBIT;
        }
    }

    /**
     * validity rules are as follows.
     * - account number cannot be null
     * - account number must have 16 digits
     * - expiry date must be greater than now
     *
     * @return
     */
    @Override
    public boolean isValid() {
        boolean accountNumberValid = (accountNumber != null && accountNumber.matches("[0-9]{16}"))
                && expiryDate.isAfter(Instant.now());
        LOGGER.info("Account Number validity check: {}", accountNumberValid);
        return accountNumberValid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}
