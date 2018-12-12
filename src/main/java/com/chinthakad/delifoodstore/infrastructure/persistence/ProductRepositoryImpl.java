package com.chinthakad.delifoodstore.infrastructure.persistence;

import com.chinthakad.delifoodstore.core.domain.product.model.Product;
import com.chinthakad.delifoodstore.core.domain.product.model.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {


    private List<Product> productStore = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        return productStore;
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(productStore.get((int) (id - 1)));
    }

    @Override
    public void save(Product product) {
        if (product.getId() <= productStore.size()) {
            productStore.set((int) (product.getId() - 1), product);
        } else {
            productStore.add(product);
        }
    }

}
