package com.demo.littlehelper.common.exceptionhandler.exceptions;

import com.demo.littlehelper.common.exceptionhandler.ErrorCode;

public class TokenExpireException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public TokenExpireException(ErrorCode errorCode, String token) {
        super(ErrorCode.appendErrorMessageWithToken(errorCode, token));
    }
}
