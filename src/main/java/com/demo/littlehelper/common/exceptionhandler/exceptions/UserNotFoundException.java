package com.demo.littlehelper.common.exceptionhandler.exceptions;

import com.demo.littlehelper.common.exceptionhandler.ErrorCode;

public class UserNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(ErrorCode errorCode) {
        super(ErrorCode.appendErrorMessage(errorCode));
    }
}
