package com.kss.userdevicemanagement.common;


import com.kss.userdevicemanagement.ex.ApiException;
import com.kss.userdevicemanagement.ex.RestUnauthorizedException;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String time;

    private int code;

    private String message;

    private T data;

    public ResponseData() {
        this.code = 0;
        this.time =
                LocalDateTime.now()
                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.message = "Successful!";
    }

    public ResponseData<T> success(T data) {
        this.data = data;
        return this;
    }

    public ResponseData<T> success(T data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
        return this;
    }

    public ResponseData<T> error(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ResponseData<T> error(int code, String message, T data) {
        this.data = data;
        this.code = code;
        this.message = message;
        return this;
    }

    public ResponseData<T> error(ApiException apiException) {
        this.code = apiException.getErrorCode();
        this.message = apiException.getMessage();
        return this;
    }

    public ResponseData<T> error(RestUnauthorizedException apiException) {
        this.code = apiException.getErrorCode();
        this.message = apiException.getMessage();
        return this;
    }


    public void setData(T data) {
        this.data = data;
    }

}
