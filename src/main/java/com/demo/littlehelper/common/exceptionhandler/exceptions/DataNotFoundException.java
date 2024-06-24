package com.demo.littlehelper.common.exceptionhandler.exceptions;

import com.demo.littlehelper.common.exceptionhandler.ErrorCode;

public class DataNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public DataNotFoundException(ErrorCode errorCode) {
        super(ErrorCode.appendErrorMessage(errorCode));
    }
}
