package com.chinthakad.delifoodstore.presentation.model;

import com.chinthakad.delifoodstore.seedwork.model.AbstractModel;

import javax.validation.constraints.NotNull;

public class CartItemDto extends AbstractModel {

    @NotNull
    private Long productId;

    public CartItemDto() {
    }

    public CartItemDto(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
