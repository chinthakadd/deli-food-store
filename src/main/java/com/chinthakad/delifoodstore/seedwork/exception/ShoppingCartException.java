package com.chinthakad.delifoodstore.seedwork.exception;

import com.chinthakad.delifoodstore.seedwork.model.dto.ErrorDto;

import java.util.Arrays;
import java.util.List;

public class ShoppingCartException extends RuntimeException {

    private List<ErrorDto> errors;

    public ShoppingCartException(ErrorDto... errors) {
        super(Arrays.toString(errors));
        this.errors = Arrays.asList(errors);

    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }

}
