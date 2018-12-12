package com.chinthakad.delifoodstore.seedwork.model.dto;


import com.chinthakad.delifoodstore.seedwork.model.enums.ResponseStatus;
import com.chinthakad.delifoodstore.seedwork.model.AbstractModel;

import java.util.Arrays;
import java.util.List;

/**
 * Standard response structure that is used across all the <b>Obp</b> Micro-services.
 *
 * @author Chinthaka Dharmasiri on 4/16/2017.
 * @see AbstractModel
 * @see ErrorDto
 */
public class ResponseDto<T> extends AbstractModel {

    private ResponseStatus status;
    private T data;
    private List<ErrorDto> errors;

    public ResponseDto() {
        // no-arg constructor
    }

    public ResponseDto(ResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseDto(ResponseStatus status, List<ErrorDto> errors) {
        this.status = status;
        this.errors = errors;
    }

    public static <T> ResponseDto<T> forSuccess(T data) {
        return new ResponseDto(ResponseStatus.SUCCESS, data);
    }

    public static <T> ResponseDto<T> forError(ErrorDto... errors) {
        return new ResponseDto(ResponseStatus.ERROR, Arrays.asList(errors));
    }

    public static <T> ResponseDto<T> forError(List<ErrorDto> errors) {
        return forError(errors.toArray(new ErrorDto[errors.size()]));
    }

    public static <T> ResponseDto<T> forFail(ErrorDto errorDto) {
        return new ResponseDto(ResponseStatus.FAIL, errorDto);
    }

    public static <T> ResponseDto<T> forFail(List<ErrorDto> errorDtos) {
        return new ResponseDto(ResponseStatus.FAIL, errorDtos);
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }

    public boolean hasErrors() {
        return errors != null;
    }

}
