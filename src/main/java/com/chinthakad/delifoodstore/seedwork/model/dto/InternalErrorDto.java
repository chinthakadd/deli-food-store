package com.chinthakad.delifoodstore.seedwork.model.dto;

import com.chinthakad.delifoodstore.seedwork.model.AbstractModel;

import java.util.List;

public class InternalErrorDto extends AbstractModel {

    private String errorString;
    private List<ErrorDto> errors;

    public InternalErrorDto() {
    }

    public InternalErrorDto(String errorString) {
        this.errorString = errorString;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
