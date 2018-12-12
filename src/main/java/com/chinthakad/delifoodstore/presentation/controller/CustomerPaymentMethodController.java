package com.chinthakad.delifoodstore.presentation.controller;

import com.chinthakad.delifoodstore.presentation.model.NewPaymentMethodDto;
import com.chinthakad.delifoodstore.presentation.model.PaymentMethodDto;
import com.chinthakad.delifoodstore.core.application.CustomerService;
import com.chinthakad.delifoodstore.core.domain.customer.model.PaymentMethod;
import com.chinthakad.delifoodstore.seedwork.exception.ShoppingCartException;
import com.chinthakad.delifoodstore.seedwork.model.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Expose payment methods that are registered by the customer to perform
 * transactions within the shopping cart system.
 */
@RestController
@RequestMapping("/customers/{customerId}")
public class CustomerPaymentMethodController {

    private CustomerService customerService;

    @Autowired
    public CustomerPaymentMethodController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Return the available payment options.
     */
    @GetMapping("/payment-methods")
    public ResponseDto<List<PaymentMethodDto>> getPaymentMethods(@PathVariable long customerId) {
        try {
            List<PaymentMethodDto> paymentMethods = customerService.getPaymentMethods(customerId)
                    .stream()
                    .map(paymentMethod -> new PaymentMethodDto(
                            paymentMethod.getId(),
                            paymentMethod.getAccount().getAccountNumber().substring(12, 16)))
                    .collect(Collectors.toList());

            return ResponseDto.forSuccess(paymentMethods);
        } catch (ShoppingCartException sce) {
            return ResponseDto.forError(sce.getErrors());
        }
    }

    /**
     * Allow adding new payment methods
     *
     * @param newPaymentMethodDto
     */
    @PutMapping("/payment-methods")
    public ResponseEntity addPaymentMethod(
            @PathVariable long customerId,
            @Valid @RequestBody NewPaymentMethodDto newPaymentMethodDto) {
        PaymentMethod paymentMethod = newPaymentMethodDto.convertToDomain();
        try {
            customerService.addNewPaymentMethod(customerId, paymentMethod);
            // return 204..
            return ResponseEntity.noContent().build();
        } catch (ShoppingCartException sce) {
            return ResponseEntity.badRequest().body(ResponseDto.forError(sce.getErrors()));
        }
    }
}
