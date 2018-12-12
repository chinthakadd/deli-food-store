package com.chinthakad.delifoodstore.presentation.model;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.Instant;

public class InvoiceDto {
    private long invoiceNumber;

    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal totalAmount;

    private Instant purchasedDate;

    public InvoiceDto() {
    }

    public InvoiceDto(long invoiceNumber, BigDecimal totalAmount, Instant purchasedDate) {
        this.invoiceNumber = invoiceNumber;
        this.totalAmount = totalAmount;
        this.purchasedDate = purchasedDate;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Instant getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Instant purchasedDate) {
        this.purchasedDate = purchasedDate;
    }
}
