package com.chinthakad.delifoodstore.seedwork.util;

import com.chinthakad.delifoodstore.core.domain.customer.model.CustomerRepository;
import com.chinthakad.delifoodstore.core.domain.product.model.Product;
import com.chinthakad.delifoodstore.core.domain.product.model.ProductDiscount;
import com.chinthakad.delifoodstore.core.domain.product.model.ProductRepository;
import com.chinthakad.delifoodstore.core.domain.shoppingcart.model.ShoppingCartRepository;
import com.chinthakad.delifoodstore.core.domain.customer.model.Customer;
import com.chinthakad.delifoodstore.core.domain.shoppingcart.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public final class DataLoader {

    private DataLoader() {
    }

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void loadData() {
        // todo: move to a data loader.
        customerRepository.save(new Customer(1, "Chinthaka"));
        customerRepository.save(new Customer(2, "Pahan"));
        customerRepository.save(new Customer(3, "Ganeshji"));

        productRepository.save(new Product(1, "Banana", "Tasty Banana", new BigDecimal(2.30), false,
                10, null));

        productRepository.save(new Product(2, "Carrot", "Tasty Carrot", new BigDecimal(1.50),
                false, 0, null));

        productRepository.save(new Product(3, "Biriyani", "Hydrabad Biriyani", new BigDecimal(25.00), true, 20,
                new ProductDiscount(Instant.now().plus(365, ChronoUnit.DAYS), new BigDecimal(0.05))
        ));


        shoppingCartRepository.save(ShoppingCart.newFor(1));
        shoppingCartRepository.save(ShoppingCart.newFor(2));
        shoppingCartRepository.save(ShoppingCart.newFor(3));
    }
}
