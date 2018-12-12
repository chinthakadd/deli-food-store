package com.chinthakad.delifoodstore.presentation.model;

/**
 * Present the payment method details.
 */
public class PaymentMethodDto {

    private long id;
    private String last4;

    public PaymentMethodDto(long id, String last4) {
        this.id = id;
        this.last4 = last4;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }
}
