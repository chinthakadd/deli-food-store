package com.chinthakad.delifoodstore.core.domain.order.model;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Invoice document will confirm the payment for a particular order.
 */
public class Invoice {

    private long customerId;
    private long paymentMethodId;
    private BigDecimal total;
    private Instant purchasedDate;

    public Invoice(long customerId, long paymentMethodId, BigDecimal total) {
        this.customerId = customerId;
        this.paymentMethodId = paymentMethodId;
        this.total = total;
        this.purchasedDate = Instant.now();
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Instant getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Instant purchasedDate) {
        this.purchasedDate = purchasedDate;
    }
}
