package com.chinthakad.delifoodstore.presentation.model;

import com.chinthakad.delifoodstore.core.domain.customer.model.Account;
import com.chinthakad.delifoodstore.core.domain.customer.model.PaymentMethod;
import com.chinthakad.delifoodstore.presentation.controller.CustomerPaymentMethodController;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneOffset;

/**
 * Hold the attributes required to represent a new payment method that
 * a customer can register.
 *
 * @see CustomerPaymentMethodController
 */
public class NewPaymentMethodDto {

    // TODO: Perform hibernate validations.
    @NotNull
    private String accountNumber;

    @NotNull
    private Integer expiryYear;

    @NotNull
    private Integer expiryMonth;

    public PaymentMethod convertToDomain() {
        return new PaymentMethod(new Account(accountNumber,
                LocalDate.of(expiryYear, expiryMonth, 1).atStartOfDay().toInstant(ZoneOffset.UTC)));
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }
}
