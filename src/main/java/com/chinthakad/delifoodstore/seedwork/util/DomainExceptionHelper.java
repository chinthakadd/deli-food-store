package com.chinthakad.delifoodstore.seedwork.util;

import com.chinthakad.delifoodstore.core.domain.customer.model.PaymentMethod;
import com.chinthakad.delifoodstore.seedwork.exception.ShoppingCartException;
import com.chinthakad.delifoodstore.seedwork.model.dto.ErrorDto;

import java.math.BigDecimal;

/**
 * Help simplify the exception construction process.
 */
public final class DomainExceptionHelper {

    private DomainExceptionHelper() {
    }

    public enum DomainErrorCode {
        INVALID_PAYMENT_METHOD,
        CUSTOMER_NOT_FOUND,
        SHOPPING_CART_UNAVAILABLE,
        PRODUCT_NOT_FOUND,
        INSUFFICIENT_CHECKOUT_TOTAL
    }

    public static ShoppingCartException invalidPaymentMethod(long paymentId) {
        String errorMessage = String.format("Payment Method: %s is invalid", paymentId);
        ErrorDto errorDto = new ErrorDto(DomainErrorCode.INVALID_PAYMENT_METHOD.name(), errorMessage);
        return new ShoppingCartException(errorDto);
    }


    public static ShoppingCartException invalidPaymentMethod(PaymentMethod paymentMethod) {
        String errorMessage = String.format("Payment Method: %s is invalid", paymentMethod);
        ErrorDto errorDto = new ErrorDto(DomainErrorCode.INVALID_PAYMENT_METHOD.name(), errorMessage);
        return new ShoppingCartException(errorDto);
    }


    public static ShoppingCartException customerNotFound(long customerId) {
        String errorMessage = String.format("Customer does not exist with ID: %s", customerId);
        ErrorDto errorDto = new ErrorDto(DomainErrorCode.CUSTOMER_NOT_FOUND.name(), errorMessage);
        return new ShoppingCartException(errorDto);
    }

    public static ShoppingCartException shoppingCartNotAvailable(long customerId) {
        String errorMessage = String.format("Customer with ID : %s does not have shopping cart", customerId);
        ErrorDto errorDto = new ErrorDto(DomainErrorCode.SHOPPING_CART_UNAVAILABLE.name(), errorMessage);
        return new ShoppingCartException(errorDto);
    }

    public static ShoppingCartException productNotFound(long productId) {
        String errorMessage = String.format("Product with ID: %s  is not found", productId);
        ErrorDto errorDto = new ErrorDto(DomainErrorCode.PRODUCT_NOT_FOUND.name(), errorMessage);
        return new ShoppingCartException(errorDto);
    }

    public static ShoppingCartException insufficientCheckoutTotal(BigDecimal total) {
        String errorMessage = String.format("Total : %s is insufficient", total.toPlainString());
        ErrorDto errorDto = new ErrorDto(DomainErrorCode.INSUFFICIENT_CHECKOUT_TOTAL.name(), errorMessage);
        return new ShoppingCartException(errorDto);
    }
}
