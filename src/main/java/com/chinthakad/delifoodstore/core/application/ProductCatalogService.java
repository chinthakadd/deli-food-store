package com.chinthakad.delifoodstore.core.application;


import com.chinthakad.delifoodstore.core.domain.product.model.Product;

import java.util.List;

public interface ProductCatalogService {
    List<Product> getAvailableProducts();
}
