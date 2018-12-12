package com.chinthakad.delifoodstore.seedwork.model.dto;

import com.chinthakad.delifoodstore.seedwork.model.AbstractModel;

public class ErrorDto extends AbstractModel {

    private String code;
    private String message;
    private InternalErrorDto internal;

    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorDto(String code, String message, InternalErrorDto internal) {
        this.code = code;
        this.message = message;
        this.internal = internal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InternalErrorDto getInternal() {
        return internal;
    }

    public void setInternal(InternalErrorDto internal) {
        this.internal = internal;
    }
}
