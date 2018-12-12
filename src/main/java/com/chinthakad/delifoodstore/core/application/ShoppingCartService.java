package com.chinthakad.delifoodstore.core.application;

import com.chinthakad.delifoodstore.core.domain.order.model.Invoice;
import com.chinthakad.delifoodstore.core.domain.shoppingcart.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart viewShoppingCart(long customerId);

    void addItem(long customerId, long productId);

    void removeItem(long customerId, long productId);

    void emptyCart(long customerId);

    Invoice checkout(long customerId, long paymentMethodId);
}
