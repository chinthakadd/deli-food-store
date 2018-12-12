package com.chinthakad.delifoodstore.core.domain.product.model;

import com.chinthakad.delifoodstore.seedwork.model.AbstractModel;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represent a Product Entity in the Product Domain.
 */
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE, dependencyCheck = false)
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Product extends AbstractModel {

    public Product() {
    }

    public Product(long id, String name, String description, BigDecimal price, boolean returnPolicy, int units,
                   ProductDiscount productDiscount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.returnPolicy = returnPolicy;
        this.units = units;
        this.productDiscount = productDiscount;
    }

    @Autowired
    private ProductRepository productRepository;

    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean returnPolicy;
    private int units;
    private ProductDiscount productDiscount;

    public void releaseUnits(int purchasedUnits) {
        if (purchasedUnits > units) {

        } else {
            units -= purchasedUnits;
        }
        productRepository.save(this);
    }

    /**
     * checks if product has stocks available.
     *
     * @return true if {@link #units} are greater than 0.
     */
    public boolean hasStocks() {
        return (units > 0);
    }

    /**
     * Checks if an Product Discount is available
     *
     * @return true of ProductDiscount is available and it is applicable.
     * @see ProductDiscount#isApplicable()
     */
    public boolean hasDiscount() {
        return Objects.nonNull(productDiscount) && productDiscount.isApplicable();
    }

    /**
     * Calculate price by applying the discount to the {@link #price}. Takes the {@link ProductDiscount}
     * into consideration while applying the discount.
     */
    public void calculatePrice() {
        if (hasDiscount()) {
            // price cannot be null.
            price = price.subtract(productDiscount.calculateDiscount(price));
        }
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isReturnPolicy() {
        return returnPolicy;
    }

    public void setReturnPolicy(boolean returnPolicy) {
        this.returnPolicy = returnPolicy;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public ProductDiscount getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(ProductDiscount productDiscount) {
        this.productDiscount = productDiscount;
    }
}
