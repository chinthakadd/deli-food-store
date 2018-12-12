package com.chinthakad.delifoodstore.core.domain.product.model;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findById(long id);

    void save(Product product);
}
