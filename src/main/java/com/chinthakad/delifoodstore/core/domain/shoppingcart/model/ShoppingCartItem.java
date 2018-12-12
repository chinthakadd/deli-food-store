package com.chinthakad.delifoodstore.core.domain.shoppingcart.model;

import com.chinthakad.delifoodstore.core.domain.product.model.Product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class ShoppingCartItem {

    private long id;

    private Instant addedDate;

    public ShoppingCartItem(long id, Product product) {
        this.id = id;
        this.product = product;
        this.addedDate = Instant.now();
    }

    /**
     * related to Product.
     */
    private Product product;

    public BigDecimal getDiscount() {
        if(product.hasDiscount()){
            return product.getProductDiscount().calculateDiscount(product.getPrice());
        } else {
            return new BigDecimal(0);
        }
    }

    public BigDecimal getPrice() {
        product.calculatePrice();
        return product.getPrice();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Instant addedDate) {
        this.addedDate = addedDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * two shopping cart items are equal, only if their ids match.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItem that = (ShoppingCartItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
