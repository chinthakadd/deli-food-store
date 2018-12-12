package com.chinthakad.delifoodstore.presentation.controller;

import com.chinthakad.delifoodstore.presentation.model.InvoiceDto;
import com.chinthakad.delifoodstore.presentation.model.PaymentMethodDto;
import com.chinthakad.delifoodstore.core.domain.order.model.Invoice;
import com.chinthakad.delifoodstore.core.application.ShoppingCartService;
import com.chinthakad.delifoodstore.core.domain.shoppingcart.model.ShoppingCart;
import com.chinthakad.delifoodstore.presentation.model.CartItemDto;
import com.chinthakad.delifoodstore.seedwork.exception.ShoppingCartException;
import com.chinthakad.delifoodstore.seedwork.model.dto.ResponseDto;
import com.chinthakad.delifoodstore.presentation.model.ShoppingCartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("customers/{customerId}/shopping-cart")
public class CustomerShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public CustomerShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseDto<ShoppingCartDto> getShoppingCart(@PathVariable long customerId) {

        try {
            ShoppingCart shoppingCart = shoppingCartService.viewShoppingCart(customerId);
            return ResponseDto.forSuccess(ShoppingCartDto.from(shoppingCart));
        } catch (ShoppingCartException sce) {
            return ResponseDto.forError(sce.getErrors());
        }
    }

    @PostMapping("/payments")
    public ResponseDto<InvoiceDto> payment(@PathVariable long customerId, @RequestBody PaymentMethodDto paymentMethodDto) {
        try {
            Invoice invoice = shoppingCartService.checkout(customerId, paymentMethodDto.getId());
            // return 204..
            return ResponseDto.forSuccess(
                    new InvoiceDto(invoice.getPaymentMethodId(), invoice.getTotal(), invoice.getPurchasedDate()));
        } catch (ShoppingCartException sce) {
            return ResponseDto.forError(sce.getErrors());
        }
    }


    @PutMapping
    public ResponseEntity addItem(@PathVariable long customerId, @Valid @RequestBody CartItemDto cartItem) {
        try {
            shoppingCartService.addItem(customerId, cartItem.getProductId());
            // return 204..
            return ResponseEntity.noContent().build();
        } catch (ShoppingCartException sce) {
            return ResponseEntity.badRequest().body(ResponseDto.forError(sce.getErrors()));
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<? extends Object> removeItem(@PathVariable long customerId, @Valid @RequestBody CartItemDto cartItem) {
        try {
            shoppingCartService.removeItem(customerId, cartItem.getProductId());
            // return 204..
            return ResponseEntity.noContent().build();
        } catch (ShoppingCartException sce) {
            return ResponseEntity.badRequest().body(ResponseDto.forError(sce.getErrors()));
        }
    }

    @DeleteMapping
    public ResponseEntity emptyCart(@PathVariable long customerId) {
        try {
            shoppingCartService.emptyCart(customerId);
            // return 204..
            return ResponseEntity.noContent().build();
        } catch (ShoppingCartException sce) {
            return ResponseEntity.badRequest().body(ResponseDto.forError(sce.getErrors()));
        }
    }

}
