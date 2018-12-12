package com.chinthakad.delifoodstore.core.domain.shoppingcart.model;

import java.util.Optional;

public interface ShoppingCartRepository {

    Optional<ShoppingCart> findByCustomerId(long customerId);

    void save(ShoppingCart shoppingCart);
}

