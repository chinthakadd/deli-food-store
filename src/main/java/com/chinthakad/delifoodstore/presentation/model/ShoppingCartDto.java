package com.chinthakad.delifoodstore.presentation.model;

import com.chinthakad.delifoodstore.presentation.controller.CustomerShoppingCartController;
import com.chinthakad.delifoodstore.core.domain.product.model.Product;
import com.chinthakad.delifoodstore.core.domain.shoppingcart.model.ShoppingCart;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represent the Shopping Cart of a customer. Exposed by
 * {@link CustomerShoppingCartController#getShoppingCart(long)} }
 */
public class ShoppingCartDto {

    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal totalPrice;

    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal discount;
    private List<ShoppingCartItem> items;

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public static ShoppingCartDto from(ShoppingCart shoppingCart) {

        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setTotalPrice(shoppingCart.getTotal());
        shoppingCartDto.setDiscount(shoppingCart.getTotalDiscount());

        List<ShoppingCartItem> shoppingCartItems = shoppingCart.getItems().stream()
                .map(
                        shoppingCartItem -> {
                            return new ShoppingCartItem(shoppingCartItem.getId(), shoppingCartItem.getProduct().getName(),
                                    shoppingCartItem.getPrice());
                        }
                ).collect(Collectors.toList());
        shoppingCartDto.setItems(shoppingCartItems);

        return shoppingCartDto;
    }

    /**
     * Represent an item in the shopping cart.
     * Its a view model for {@link Product}
     * domain.
     */
    public static class ShoppingCartItem {

        public ShoppingCartItem() {
        }

        public ShoppingCartItem(long id, String name, BigDecimal price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        private long id;
        private String name;
        private BigDecimal price;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
