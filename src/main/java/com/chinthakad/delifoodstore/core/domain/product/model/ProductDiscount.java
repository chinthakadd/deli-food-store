package com.chinthakad.delifoodstore.core.domain.product.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represent a Product Discount Value.
 */
public class ProductDiscount {

    private Instant validUntil;
    private BigDecimal percentage;

    public ProductDiscount() {
    }

    public ProductDiscount(Instant validUntil, BigDecimal percentage) {
        this.validUntil = validUntil;
        this.percentage = percentage;
    }

    /**
     * Calculates the total discount based on the percentage.
     * If not percentage is set, then discount will be 0.
     *
     * @param totalPrice product pricing
     * @return applicable discount
     */
    public BigDecimal calculateDiscount(BigDecimal totalPrice) {
        BigDecimal percentageDiscount = percentage;
        if (percentageDiscount == null) {
            percentageDiscount = new BigDecimal(0);
        }
        return totalPrice.multiply(percentageDiscount);
    }

    /**
     * Check if the discount can be applied. Discount is considered applicable
     * if the {@link #validUntil} time has not expired.
     *
     * @return true if discount is valid at this point of time
     */
    public boolean isApplicable() {
        return (Objects.nonNull(validUntil) && validUntil.isAfter(Instant.now()));
    }


    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
