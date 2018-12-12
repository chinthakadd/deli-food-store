package com.chinthakad.delifoodstore.infrastructure.persistence;

import com.chinthakad.delifoodstore.core.domain.shoppingcart.model.ShoppingCart;
import com.chinthakad.delifoodstore.core.domain.shoppingcart.model.ShoppingCartRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Repository
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    private Map<Long, ShoppingCart> shoppingCartStore = new HashMap<>();

    @Override
    public Optional<ShoppingCart> findByCustomerId(long customerId) {
        return Optional.ofNullable(shoppingCartStore.get(customerId));
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCartStore.put(shoppingCart.getCustomerId(), shoppingCart);
    }
}
