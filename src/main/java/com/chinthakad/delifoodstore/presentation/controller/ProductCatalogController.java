package com.chinthakad.delifoodstore.presentation.controller;

import com.chinthakad.delifoodstore.core.application.ProductCatalogService;
import com.chinthakad.delifoodstore.presentation.model.ProductDto;
import com.chinthakad.delifoodstore.seedwork.model.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Product Catalog resource which returns a list of all products that can be
 * purchased using the shopping cart system.
 * <p>
 * TODO: Use Pagination Here.
 * TODO: Write an exception advice
 */
@RestController
@RequestMapping("/product-catalog")
public class ProductCatalogController {

    private ProductCatalogService productCatalogService;

    @Autowired
    public ProductCatalogController(ProductCatalogService productCatalogService) {
        this.productCatalogService = productCatalogService;
    }

    @GetMapping
    public ResponseDto<List<ProductDto>> getProductCatalog() {
        return ResponseDto.forSuccess(productCatalogService.getAvailableProducts()
                .stream()
                .map(
                        product -> {
                            ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getDescription(),
                                    product.getPrice(), product.isReturnPolicy());
                            if (product.hasDiscount()) {
                                productDto.setDiscountPercentage(product.getProductDiscount().getPercentage());
                                productDto.setDiscountValidUntil(product.getProductDiscount().getValidUntil());
                            }
                            return productDto;
                        }
                ).collect(Collectors.toList()));
    }
}
