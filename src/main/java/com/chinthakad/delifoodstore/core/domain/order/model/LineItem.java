package com.chinthakad.delifoodstore.core.domain.order.model;

/**
 * Represent a Product or a Shopping Cart Item in the context of an order.
 */
public class LineItem {

    private long productId;

    public LineItem() {
    }

    public LineItem(long productId) {
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
