package com.kss.userdevicemanagement.ex;

import com.kss.userdevicemanagement.common.EnumCodeResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestUnauthorizedException extends Exception {
    int errorCode;
    String message;
    public RestUnauthorizedException() {
        this.message = EnumCodeResponse.UNAUTHORIZED.getMessage();
        this.errorCode = EnumCodeResponse.UNAUTHORIZED.getCode();
    }

}
