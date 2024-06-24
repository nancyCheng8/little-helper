package com.demo.littlehelper.common.exceptionhandler.exceptions;

import com.demo.littlehelper.common.exceptionhandler.ErrorCode;

public class InvalidParameterException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public InvalidParameterException(ErrorCode errorCode) {
        super(ErrorCode.appendErrorMessage(errorCode));
    }
}
