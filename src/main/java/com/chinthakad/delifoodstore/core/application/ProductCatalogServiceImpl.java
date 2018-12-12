package com.chinthakad.delifoodstore.core.application;

import com.chinthakad.delifoodstore.core.domain.product.model.ProductRepository;
import com.chinthakad.delifoodstore.core.domain.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductCatalogServiceImpl implements ProductCatalogService {

    private ProductRepository productRepository;

    @Autowired
    public ProductCatalogServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Return a list of products available at this point of time.
     *
     * @return a List of available products.
     */
    @Override
    public List<Product> getAvailableProducts() {
        return productRepository.findAll().stream().filter(Product::hasStocks).collect(Collectors.toList());
    }
}
