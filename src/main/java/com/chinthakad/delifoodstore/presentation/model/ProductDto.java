package com.chinthakad.delifoodstore.presentation.model;

import com.chinthakad.delifoodstore.presentation.controller.ProductCatalogController;
import com.chinthakad.delifoodstore.seedwork.model.AbstractModel;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Hold Product Details that are exposed by
 * {@link ProductCatalogController}
 *
 * @see ProductCatalogController
 */
public class ProductDto extends AbstractModel {

    public ProductDto() {
    }

    public ProductDto(long id, String name, String description, BigDecimal price, boolean returnPolicy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.returnPolicy = returnPolicy;
    }

    public ProductDto(long id, String name, String description, BigDecimal price, boolean returnPolicy,
                      BigDecimal discountPercentage, Instant discountValidUntil) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.returnPolicy = returnPolicy;
        this.discountPercentage = discountPercentage;
        this.discountValidUntil = discountValidUntil;
    }

    private long id;
    private String name;
    private String description;

    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal price;

    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal discountPercentage;

    private boolean returnPolicy;

    private Instant discountValidUntil;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReturnPolicy() {
        return returnPolicy;
    }

    public void setReturnPolicy(boolean returnPolicy) {
        this.returnPolicy = returnPolicy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Instant getDiscountValidUntil() {
        return discountValidUntil;
    }

    public void setDiscountValidUntil(Instant discountValidUntil) {
        this.discountValidUntil = discountValidUntil;
    }
}
